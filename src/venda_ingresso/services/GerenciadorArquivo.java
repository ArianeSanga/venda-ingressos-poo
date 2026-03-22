package venda_ingresso.services;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import venda_ingresso.entities.Ingresso;

public class GerenciadorArquivo {

    public static void serializar(List<Ingresso> ingressos, String path) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(ingressos);
            oos.close();
        } catch (IOException e) {
            System.out.println("[ERRO] Falha ao serializar: " + e.getMessage());
        } finally {
            System.out.println("[LOG] Operação de serialização concluída.");
        }
    }

    public static List<Ingresso> desserializar(String path) {
        List<Ingresso> ingressos = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
            ingressos = (List<Ingresso>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            System.out.println("[ERRO] Arquivo não encontrado ou corrompido: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("[ERRO] Classe não encontrada: " + e.getMessage());
        } finally {
            System.out.println("[LOG] Operação de desserialização concluída.");
        }
        return ingressos;
    }

    public static void exportarTxt(List<Ingresso> ingressos, String path) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            bw.write("=== RELATÓRIO DE INGRESSOS ===");
            bw.newLine();
            for (Ingresso i : ingressos) {
                bw.write("Código: " + i.getCodigo());
                bw.newLine();
                bw.write("Nome: " + i.getNome());
                bw.newLine();
                bw.write("Setor: " + i.getSetor());
                bw.newLine();
                bw.write("Quantidade: " + i.getQuantidade());
                bw.newLine();
                bw.write("Valor: R$ " + i.getValor());
                bw.newLine();
                bw.write("Total: R$ " + i.getValorTotal());
                bw.newLine();
                bw.write("Data/Hora: " + i.getDataHora());
                bw.newLine();
                bw.write("-----------------------------");
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("[ERRO] Falha ao exportar txt: " + e.getMessage());
        } finally {
            System.out.println("[LOG] Operação de exportação txt concluída.");
        }
    }
}