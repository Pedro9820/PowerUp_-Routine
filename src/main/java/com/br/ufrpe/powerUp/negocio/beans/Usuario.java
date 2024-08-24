package com.br.ufrpe.powerUp.negocio.beans;

import java.util.ArrayList;
import java.util.Objects;

public class Usuario {
    private String ID;
    private String nome;
    private String senha;
    private int forca;
    private int stamina;
    private int velocidade;
    private int resistencia;
    private float peso;
    private float altura;
    private ArrayList<Objetivo> objetivos;

    public Usuario(String ID, String nome, String senha) {
        this.ID = ID;
        this.nome = nome;
        this.senha = senha;
        this.forca = 0;
        this.stamina = 0;
        this.velocidade = 0;
        this.resistencia = 0;
        this.peso = 0;
        this.altura = 0;

    }

    public void atualizar_atributo(TipoAtributo tipo, int qtd) {
        switch(tipo) {
            case FORCA: forca += qtd; break;
            case STAMINA: stamina += qtd; break;
            case VELOCIDADE: velocidade += qtd; break;
            case RESISTENCIA: resistencia += qtd; break;
        }
    }

   public void adicionar_objetivo(Objetivo objetivo) {
        objetivos.add(objetivo);
   }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Verifica se é o mesmo objeto
        if (o == null || getClass() != o.getClass()) return false; // Verifica se o tipo é o mesmo

        Usuario usuario = (Usuario) o;
        return nome.equals(usuario.nome) && senha.equals(usuario.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, senha);
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setNome(String nome) {
        nome = nome;
    }

    public void setSenha(String senha) { this.senha = senha; }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }


    public String getID() {
        return ID;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {return senha;}

    public int getForca() {
        return forca;
    }

    public int getStamina() {
        return stamina;
    }

    public int getResistencia() {
        return resistencia;
    }

    public float getPeso() {
        return peso;
    }

    public float getAltura() {
        return altura;
    }

    public int getVelocidade() {
        return velocidade;
    }
}
