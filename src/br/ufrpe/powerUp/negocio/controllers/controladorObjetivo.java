package src.br.ufrpe.powerUp.negocio.controllers;

import src.br.ufrpe.powerUp.negocio.beans.Objetivo;

public class controladorObjetivo {
    private Objetivo objetivo;

    public controladorObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
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


}
