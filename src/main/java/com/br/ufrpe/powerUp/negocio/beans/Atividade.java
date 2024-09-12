package com.br.ufrpe.powerUp.negocio.beans;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalTime;

public class Atividade implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String nome;
    private TipoAtributo tipo;
    private int intensidade;

    public Atividade(String id, String nome, TipoAtributo tipo, int intensidade) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.intensidade = intensidade;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public int getIntensidade() {
        return intensidade;
    }

    public void setIntensidade(int intensidade) {
        this.intensidade = intensidade;
    }

    public TipoAtributo getTipo() {
        return tipo;
    }

    public void setTipo(TipoAtributo tipo) {
        this.tipo = tipo;
    }
}

