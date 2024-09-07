package com.br.ufrpe.powerUp.negocio.beans;

import com.br.ufrpe.powerUp.negocio.beans.Atividade;
import com.br.ufrpe.powerUp.negocio.beans.TipoAtributo;
import com.br.ufrpe.powerUp.negocio.beans.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AtividadeExecutada {

    private Usuario usuario;
    private Atividade atividadeExec;
    private String id;
    private LocalDateTime atinicio;
    private LocalDateTime atfim;
    private boolean foiExecutada;

public AtividadeExecutada(Usuario user, Atividade at) {
    this.usuario=user;
    this.atividadeExec=at;
}

    public AtividadeExecutada(Atividade atividadeExec, LocalDateTime atinicio, LocalDateTime atfim) {
        this.atividadeExec = atividadeExec;
        this.atinicio = atinicio;
        this.atfim = atfim;
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

    public String getNome() {
        return this.atividadeExec.getNome();
    }

    public TipoAtributo getTipo() {
        return this.atividadeExec.getTipo();
    }

    public int getIntensidade() {
        return this.atividadeExec.getIntensidade();
    }

    public String getAtinicio() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return atinicio.format(formatter);
    }

    public void setAtinicio(LocalDateTime atinicio) {
        this.atinicio = atinicio;
    }

    public String getAtfim() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return atfim.format(formatter);
    }

    public void setAtfim(LocalDateTime atfim) {
        this.atfim = atfim;
    }

    public boolean isFoiExecutada() {
        return foiExecutada;
    }

    public void setFoiExecutada(boolean foiExecutada) {
        this.foiExecutada = foiExecutada;
    }
}
