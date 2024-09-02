package com.br.ufrpe.powerUp.negocio.controllers;

import com.br.ufrpe.powerUp.dados.RepositorioAtividadesExecutadas;
import com.br.ufrpe.powerUp.dados.exceptions.AJRException;
import com.br.ufrpe.powerUp.dados.exceptions.ANexception;
import com.br.ufrpe.powerUp.negocio.AtividadeExecutada;
import com.br.ufrpe.powerUp.negocio.beans.Atividade;
import com.br.ufrpe.powerUp.negocio.beans.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ControladorAtExecutada {
    private AtividadeExecutada atividade;

    public void iniciarAtividade(Usuario user, Atividade at){
        atividade = new AtividadeExecutada(user, at);
    }
    public void terminarAtividade(AtividadeExecutada at) throws ANexception, AJRException {
        //atividade.terminarAtividade();
        RepositorioAtividadesExecutadas repositorio = RepositorioAtividadesExecutadas.getInstance();
        repositorio.adicionarAtividade(at);
    }

    public void setUsuario(Usuario usuario){
        atividade.setUsuario(usuario);
    }
    public void setAtividadeExec(Atividade at){
        atividade.setAtividadeExec(at);

    }
    public void setID(String id){
        atividade.setId(id);
    }
    public void setAtInicio(LocalDateTime inicio){
        atividade.setAtinicio(inicio);

    }
    public void setAtFim(LocalDateTime fim){
        atividade.setAtfim(fim);

    }
    public Usuario getUsuario(){
        return atividade.getUsuario();
    }
    public Atividade getAtividadeExec(){
        return atividade.getAtividadeExec();
    }
    public String getID(){
        return atividade.getId();
    }
    public String getInicio(){
        return atividade.getAtinicio();
    }
    public String getFim(){
        return atividade.getAtfim();
    }
}
