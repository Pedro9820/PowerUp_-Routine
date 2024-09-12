package com.br.ufrpe.powerUp.negocio.beans;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Peso implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private float valor;
    private LocalDate data;

    public Peso(float valor, LocalDate data) {
        this.valor = valor;
        this.data = data;
    }

    public float getValor() {
        return valor;
    }
    public LocalDate getData() {
        return data;
    }

    
}

