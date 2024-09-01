package com.br.ufrpe.powerUp.negocio.controllers;

import com.br.ufrpe.powerUp.negocio.beans.Objetivo;

import java.time.LocalDate;

public class ControladorObjetivo {
    private Objetivo objetivo;

    public ControladorObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }
    public void criarObjetivo(LocalDate dataMaxima, String nome, String descricao, int quota){
        objetivo = new Objetivo(dataMaxima, nome, descricao, quota );
    }
    public int getObjetivoId() {
        return objetivo.getId();
    }

    public String getObjetivoNome() {
        return objetivo.getNome();
    }

    public String getObjetivoDescricao() {
        return objetivo.getDescricao();
    }

    public int getObjetivoQuota() {
        return objetivo.getQuota();
    }

    public void setObjetivoId(int id) {
        this.objetivo.setId(id);
    }

    public void setObjetivoNome(String nome) {
        this.objetivo.setNome(nome);
    }

    public void setObjetivoDescricao(String descricao) {
        this.objetivo.setDescricao(descricao);
    }

    public void setObjetivoQuota(int quota) {
        this.objetivo.setQuota(quota);
    }

    public Objetivo getObjetivo(){
        return objetivo;
    }
}
