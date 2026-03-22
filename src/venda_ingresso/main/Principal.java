/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venda_ingresso.main;

import venda_ingresso.entities.Ingresso;
import venda_ingresso.enums.SetorEnum;
import venda_ingresso.services.CompradorRunnable;
import venda_ingresso.services.GerenciadorArquivo;
import venda_ingresso.services.GerenciadorIngresso;
import venda_ingresso.ui.TelaInicial;
import java.util.List;

/**
 *
 * @author Junior
 */
public class Principal {

    public static void main(String[] args) {

        GerenciadorIngresso gerenciador = new GerenciadorIngresso();

        Thread daemon = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(500);
                    GerenciadorArquivo.serializar(gerenciador.getIngressos(), "ingressos.ser");
                    System.out.println("[DAEMON] Ingressos salvos automaticamente.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        daemon.setDaemon(true);
        daemon.start();

        Thread t1 = new Thread(new CompradorRunnable("Ana", SetorEnum.AMARELO, 2, gerenciador), "Thread-Ana");
        Thread t2 = new Thread(new CompradorRunnable("Bruno", SetorEnum.AZUL, 1, gerenciador), "Thread-Bruno");
        Thread t3 = new Thread(new CompradorRunnable("Carlos", SetorEnum.VERDE, 3, gerenciador), "Thread-Carlos");
        Thread t4 = new Thread(new CompradorRunnable("Diana", SetorEnum.BRANCO, 2, gerenciador), "Thread-Diana");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        daemon.interrupt();

        // M07 - threadOrigem fica null apos desserializar pois o campo eh transient.
// Campos transient nao sao incluidos na serializacao Java.
// Ao desserializar, campos transient sao inicializados com null (objetos) ou 0 (primitivos).
        List<Ingresso> ingressosCarregados = GerenciadorArquivo.desserializar("ingressos.ser");
        System.out.println("\n=== RELATORIO FINAL ===");
        for (Ingresso i : ingressosCarregados) {
            System.out.println("Codigo: " + i.getCodigo() +
                    " | Nome: " + i.getNome() +
                    " | threadOrigem: " + i.getThreadOrigem());
        }

        TelaInicial telaInicial = new TelaInicial();
    }
}