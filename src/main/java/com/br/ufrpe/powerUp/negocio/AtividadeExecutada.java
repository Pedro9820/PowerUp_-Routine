package com.br.ufrpe.powerUp.negocio;

import com.br.ufrpe.powerUp.negocio.beans.Atividade;
import com.br.ufrpe.powerUp.negocio.beans.Usuario;

import java.time.LocalDate;

public class AtividadeExecutada {

private Usuario usuario;
private Atividade atividadeExec;
private String id;
private LocalDate atividadeInicio;
private LocalDate atividadeFim;
private boolean foiExecutada;

public AtividadeExecutada(Usuario user, Atividade atividadeEscolhida) {
    this.usuario = user;
    this.atividadeInicio = LocalDate.now();
    this.atividadeExec = atividadeEscolhida;
}

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Atividade getAtividadeExec() {
        return atividadeExec;
    }

    public void setAtividadeExec(Atividade atividadeExec) {
        this.atividadeExec = atividadeExec;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getAtinicio() {
        return atividadeInicio;
    }

    public void setAtinicio(LocalDate atinicio) {
        this.atividadeInicio = atinicio;
    }

    public LocalDate getAtividadeFim() {
        return atividadeFim;
    }

    public void setAtividadeFim(LocalDate atividadeFim) {
        this.atividadeFim = atividadeFim;
    }

    public boolean isFoiExecutada() {
        return foiExecutada;
    }

    public void setFoiExecutada(boolean foiExecutada) {
        this.foiExecutada = foiExecutada;
    }
}
