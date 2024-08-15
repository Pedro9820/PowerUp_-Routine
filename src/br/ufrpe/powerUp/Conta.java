package src.br.ufrpe.powerUp;

import java.time.LocalDate;
import java.util.ArrayList;

public class Conta {
    private String ID, Nome;
    private int forca
            , stamina
            , intelecto
            , criatividade;
    private float peso
            ,altura;
    private ArrayList<Objetivo> objetivos;

    public Conta(String ID, String nome) {
        this.ID = ID;
        this.Nome = nome;
        this.forca = 0;
        this.stamina = 0;
        this.intelecto = 0;
        this.criatividade = 0;
        this.peso = 0;
        this.altura = 0;

    }

    public void atualizar_atributo(TipoAtributo tipo, int qtd) {
        switch(tipo) {
            case FORCA: forca += qtd; break;
            case STAMINA: stamina += qtd; break;
            case INTELECTO: intelecto += qtd; break;
            case CRIATIVIDADE: criatividade += qtd; break;
        }
    }

    public void criarObjetivo(LocalDate dataMaxima, String nome, String descricao, TipoAtributo tipo, int quota) {
        if (objetivos.isEmpty()) {
            objetivos = new ArrayList<>();
        }

        objetivos.add(new Objetivo(dataMaxima, nome, descricao, tipo, quota));

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

    public void setIntelecto(int intelecto) {
        this.intelecto = intelecto;
    }

    public void setCriatividade(int criatividade) {
        this.criatividade = criatividade;
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

    public int getCriatividade() {
        return criatividade;
    }

    public float getPeso() {
        return peso;
    }

    public float getAltura() {
        return altura;
    }

    public int getIntelecto() {
        return intelecto;
    }
}
