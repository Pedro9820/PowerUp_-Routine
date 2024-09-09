package com.br.ufrpe.powerUp.negocio.beans;

import java.time.LocalDate;

public class Objetivo {
    private int id;
    private LocalDate dataMaxima;
    private String nome;
    private TipoAtributo atributo;
    private String descricao;
    private int quota;
    private int progresso;


    public Objetivo(LocalDate dataMaxima, String nome, String descricao, int quota, TipoAtributo atributo) {
        this.dataMaxima = dataMaxima;
        this.nome = nome;
        this.descricao = descricao;
        this.quota = quota;
        this.atributo = atributo;
        this.progresso = 0;
    }

    public boolean verificarConclusaoObjetivo(){
       return dataMaxima.isBefore(LocalDate.now()) && quota <= progresso;

    }

    public LocalDate getDataMaxima() {
        return dataMaxima;
    }

    public void setDataMaxima(LocalDate dataMaxima) {
        this.dataMaxima = dataMaxima;
    }

    public TipoAtributo getAtributo() {
        return atributo;
    }

    public void setAtributo(TipoAtributo atributo) {
        this.atributo = atributo;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQuota() {
        return quota;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public int getProgresso() {
        return progresso;
    }

    public void setProgresso(int progresso) {
        this.progresso = this.progresso + progresso;
    }
}
