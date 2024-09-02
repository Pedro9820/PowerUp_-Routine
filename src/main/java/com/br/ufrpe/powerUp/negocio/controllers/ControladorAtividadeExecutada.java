package com.br.ufrpe.powerUp.negocio.controllers;

import com.br.ufrpe.powerUp.dados.RepositorioAtividadesExecutadas;
import com.br.ufrpe.powerUp.dados.exceptions.AJRException;
import com.br.ufrpe.powerUp.dados.exceptions.ANexception;
import com.br.ufrpe.powerUp.negocio.AtividadeExecutada;
import com.br.ufrpe.powerUp.negocio.beans.Atividade;
import com.br.ufrpe.powerUp.negocio.beans.Usuario;

import java.time.LocalDate;

public class ControladorAtividadeExecutada {
    private AtividadeExecutada atividade;

    public void iniciarAtividade(Usuario user, Atividade at){
        atividade = new AtividadeExecutada(user, at);
    }
    public void terminarAtividade(AtividadeExecutada atividade, boolean foiExecutada) throws ANexception, AJRException {
        RepositorioAtividadesExecutadas repositorio = RepositorioAtividadesExecutadas.getInstance();
        repositorio.terminarAtividade(atividade,foiExecutada);
        repositorio.adicionarAtividade(atividade);
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
    public void setAtInicio(LocalDate inicio){
        atividade.setAtinicio(inicio);

    }
    public void setAtFim(LocalDate fim){
        atividade.setAtividadeFim(fim);

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
    public LocalDate getInicio(){
        return atividade.getAtinicio();
    }
    public LocalDate getFim(){
        return atividade.getAtividadeFim();
    }
}
