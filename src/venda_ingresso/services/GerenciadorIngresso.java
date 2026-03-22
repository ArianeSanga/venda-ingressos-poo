/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venda_ingresso.services;

import java.util.ArrayList;
import venda_ingresso.enums.SetorEnum;
import venda_ingresso.entities.Ingresso;
import venda_ingresso.exceptions.SetorEsgotadoException;

/**
 *
 * @author Junior
 */
public class GerenciadorIngresso {
    
    private ArrayList<Ingresso> ingressos;
    private int prox = 0;

    public GerenciadorIngresso() {
        
        ingressos = new ArrayList<>();
    }

    public boolean comprarIngresso(Ingresso ingresso) {
        if (ingresso != null) {
            int limite = 0;
            for (SetorEnum s : SetorEnum.values()) {
                if (s.getNome().equalsIgnoreCase(ingresso.getSetor())) {
                    limite = s.getLimiteIngressos();
                    break;
                }
            }
            long compradosNoSetor = ingressos.stream()
                    .filter(i -> i.getSetor().equalsIgnoreCase(ingresso.getSetor()))
                    .count();
            if (compradosNoSetor >= limite) {
                throw new SetorEsgotadoException("Setor " + ingresso.getSetor() + " esgotado!");
            }
            ingresso.setCodigo(++prox);
            ingressos.add(ingresso);
            return true;
        }
        return false;
    }
    
    //Retorna os ingressos adquiridos
    public ArrayList<Ingresso> getIngressos() {
        return ingressos;
    }
    
}

    
    
    
    

