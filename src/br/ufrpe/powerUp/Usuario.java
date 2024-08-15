package src.br.ufrpe.powerUp;

import java.time.LocalDate;
import java.util.ArrayList;

public class Usuario {
    private String ID, Nome;
    private int forca
            , stamina
            , velocidade
            , resistencia;
    private float peso
            ,altura;
    private ArrayList<Objetivo> objetivos;

    public Usuario(String ID, String nome) {
        this.ID = ID;
        this.Nome = nome;
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

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

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
        return Nome;
    }

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
