package com.br.ufrpe.powerUp.negocio;

import com.br.ufrpe.powerUp.negocio.beans.Atividade;

import java.util.ArrayList;
import java.util.Objects;

public class ListaAtividade {

private ArrayList<Atividade> lista;

public ListaAtividade() {
    this.lista = new ArrayList<>();
}

public void adicionarAtvd(Atividade atividade){
    lista.add(atividade);
}
public void excluirAtvd(String idatividade){
    lista.remove(procurarID(idatividade));
}

private int procurarID(String id) {
    int indice = -1;
        for (int i = 0; i < lista.size(); i++) {
            if (Objects.equals(lista.get(i).getId(), id)) {
                indice = i;
            }
        }
        return indice;
}

    // encontra atividade pelo ID
    public Atividade getAtividadePorId(String id) {
        int index = procurarID(id);
        if (index != -1) {
            return lista.get(index);
        }
        return null;
    }
    public ArrayList<Atividade> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Atividade> lista) {
        this.lista = lista;
    }
}
