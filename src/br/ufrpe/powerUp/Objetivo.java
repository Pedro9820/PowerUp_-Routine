package src.br.ufrpe.powerUp;

import java.time.LocalDate;

public class Objetivo {
    private int id;
    private LocalDate dataMaxima;
    private  String nome;
    private  String descricao;
    private TipoAtributo tipo;
    private int quota;


    public Objetivo(LocalDate dataMaxima, String nome, String descricao, TipoAtributo tipo, int quota) {
        this.dataMaxima = dataMaxima;
        this.nome = nome;
        this.descricao = descricao;
        this.quota = quota;
        this.tipo = tipo;
    }

    public boolean verificarConclusaoObjetivo(int atributo){
        boolean verificacao = false;

        if (dataMaxima.isBefore(LocalDate.now()) && quota <= atributo) {
            verificacao = true;
        }

        return verificacao;
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
