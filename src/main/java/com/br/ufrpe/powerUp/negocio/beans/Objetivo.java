package com.br.ufrpe.powerUp.negocio.beans;

import java.time.LocalDate;

public class Objetivo {
    private int id;
    private LocalDate dataMaxima;
    private String nome;
    private String atividade;
    private String descricao;
    private int quota;


    public Objetivo(LocalDate dataMaxima, String nome, String descricao, int quota) {
        this.dataMaxima = dataMaxima;
        this.nome = nome;
        this.descricao = descricao;
        this.quota = quota;
        this.atividade = "test";
    }

    public boolean verificarConclusaoObjetivo(int atributo){
       return dataMaxima.isBefore(LocalDate.now()) && quota <= atributo;

    }

    public LocalDate getDataMaxima() {
        return dataMaxima;
    }

    public void setDataMaxima(LocalDate dataMaxima) {
        this.dataMaxima = dataMaxima;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
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

}
