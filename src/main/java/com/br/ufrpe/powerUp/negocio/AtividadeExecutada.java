package com.br.ufrpe.powerUp.negocio;

import com.br.ufrpe.powerUp.negocio.beans.Atividade;
import com.br.ufrpe.powerUp.negocio.beans.Usuario;

import java.time.LocalDate;

public class AtividadeExecutada {

private Usuario usuario;
private Atividade atividadeExec;
private String id;
private LocalDate atinicio;
private LocalDate atfim;
private boolean foiExecutada;

public AtividadeExecutada(Usuario user, Atividade at) {
    this.usuario=user;
    this.atividadeExec=at;
}
public void terminarAtividade() {
    this.foiExecutada=true;

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
        return atinicio;
    }

    public void setAtinicio(LocalDate atinicio) {
        this.atinicio = atinicio;
    }

    public LocalDate getAtfim() {
        return atfim;
    }

    public void setAtfim(LocalDate atfim) {
        this.atfim = atfim;
    }

    public boolean isFoiExecutada() {
        return foiExecutada;
    }

    public void setFoiExecutada(boolean foiExecutada) {
        this.foiExecutada = foiExecutada;
    }
}
