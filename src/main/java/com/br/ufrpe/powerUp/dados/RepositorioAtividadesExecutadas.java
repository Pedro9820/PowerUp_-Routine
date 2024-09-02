package com.br.ufrpe.powerUp.dados;
import com.br.ufrpe.powerUp.dados.exceptions.AJRException;
import com.br.ufrpe.powerUp.dados.exceptions.ANexception;
import com.br.ufrpe.powerUp.negocio.AtividadeExecutada;

import java.time.LocalDate;
import java.util.ArrayList;


public class RepositorioAtividadesExecutadas {
    private ArrayList<AtividadeExecutada> atividadesRealizadas;
    private static RepositorioAtividadesExecutadas instancia;


    public RepositorioAtividadesExecutadas() {
        atividadesRealizadas = new ArrayList<AtividadeExecutada>();
    }

    public static RepositorioAtividadesExecutadas getInstance() {
        if (instancia == null) {
            instancia = new RepositorioAtividadesExecutadas();
        }
        return instancia;
    }

    public void adicionarAtividade(AtividadeExecutada atividade) throws ANexception, AJRException {
        if (atividade == null) {
            ANexception ane = new ANexception();
            throw ane;
        }
        else if (atividadesRealizadas.contains(atividade)) {
            AJRException cje = new AJRException();
            throw cje;
        }else {
            atividadesRealizadas.add(atividade);
        }

    }

    public void removerAtividade(AtividadeExecutada atividade) throws ANexception {
        if (atividade == null || !atividadesRealizadas.contains(atividade)) {
            ANexception ane = new ANexception();
            throw ane;
        }
        else {
           atividadesRealizadas.remove(atividade);
        }
    }

//    public void terminarAtividade(AtividadeExecutada atividade,boolean FoiExecutada) throws ANexception {
//        if (atividade == null || !atividadesRealizadas.contains(atividade)) {
//            ANexception ane = new ANexception();
//            throw ane;
//        }
//        else {
//            atividade.setAtividadeFim(LocalDate.now());
//            atividade.setFoiExecutada(FoiExecutada);
//        }
//    }

    public ArrayList<AtividadeExecutada> getAtividadesRealizadas() {
        return atividadesRealizadas;
    }
}

