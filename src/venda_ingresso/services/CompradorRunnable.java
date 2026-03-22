/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venda_ingresso.services;

import java.time.LocalDateTime;
import venda_ingresso.entities.Ingresso;
import venda_ingresso.enums.SetorEnum;

/**
 *
 * @author Junior
 */
public class CompradorRunnable implements Runnable {

    private String nomeComprador;
    private SetorEnum setor;
    private int quantidade;
    private GerenciadorIngresso gerenciador;

    public CompradorRunnable(String nomeComprador, SetorEnum setor, int quantidade, GerenciadorIngresso gerenciador) {
        this.nomeComprador = nomeComprador;
        this.setor = setor;
        this.quantidade = quantidade;
        this.gerenciador = gerenciador;
    }

    @Override
    public void run() {
        try {
            // M02 - simula latencia de rede
            Thread.sleep(50);

            Ingresso ingresso = new Ingresso();
            ingresso.setNome(nomeComprador);
            ingresso.setSetor(setor.getNome());
            ingresso.setQuantidade(quantidade);
            ingresso.setValor(setor.getValor());
            ingresso.setValorTotal(setor.getValor() * quantidade);
            ingresso.setDataHora(LocalDateTime.now());

            gerenciador.comprarIngresso(ingresso);

            System.out.println("Compra realizada por: " + nomeComprador +
                    " | Thread: " + Thread.currentThread().getName());

        } catch (InterruptedException e) {
            // M02 - trata InterruptedException corretamente
            Thread.currentThread().interrupt();
        }
    }
}