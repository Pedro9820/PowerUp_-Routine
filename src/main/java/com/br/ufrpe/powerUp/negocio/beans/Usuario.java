package com.br.ufrpe.powerUp.negocio.beans;

import com.br.ufrpe.powerUp.dados.RepositorioAtividadesExecutadas;
import com.br.ufrpe.powerUp.dados.exceptions.AJRException;
import com.br.ufrpe.powerUp.dados.exceptions.ANexception;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Usuario implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String ID;
    private String nome;
    private String senha;
    private int forca;
    private int stamina;
    private int intelecto;
    private int criatividade;
    private float altura;
    private float pesoAtual;
    private double volume;
    private boolean bNotificacao;
    private int notificacoDias;
    private ArrayList<Peso> historicoPesos;
    private ArrayList<Objetivo> objetivos;
    private RepositorioAtividadesExecutadas repoAtividadesExecutadas;

    public Usuario(String ID, String nome, String senha, float altura) {
        this.ID = ID;
        this.nome = nome;
        this.senha = senha;
        this.forca = 0;
        this.stamina = 0;
        this.intelecto = 0;
        this.criatividade = 0;
        this.altura = altura;
        this.pesoAtual = 0;
        this.volume = 1;
        this.bNotificacao = true;
        this.notificacoDias = 1;
        this.historicoPesos = new ArrayList<>();
        this.objetivos = new ArrayList<>();
        this.repoAtividadesExecutadas = new RepositorioAtividadesExecutadas();

        // Adicionando pesos de teste
        this.historicoPesos.add(new Peso(70f, LocalDate.of(2024, 9, 9)));
        this.historicoPesos.add(new Peso(75f, LocalDate.of(2024, 9, 11)));

    }

    public void atualizarAtributo(TipoAtributo tipo, int qtd) {
        switch(tipo) {
            case FORCA: forca += qtd; break;
            case STAMINA: stamina += qtd; break;
            case INTELECTO: intelecto += qtd; break;
            case CRIATIVIDADE: criatividade += qtd; break;
        }

        for (Objetivo objetivo : objetivos) {
            if (objetivo.getAtributo() == tipo)
                objetivo.setProgresso(qtd);
        }
    }

   public void adicionar_objetivo(Objetivo objetivo) {
        objetivos.add(objetivo);
   }

   public void adicionarAtividadeExecutada(AtividadeExecutada atividadeExecutada) throws ANexception, AJRException {
        this.repoAtividadesExecutadas.adicionarAtividade(atividadeExecutada);
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

    public int getIntelecto() {
        return intelecto;
    }

    public int getCriatividade() {
        return criatividade;
    }

    public float getAltura() {
        return altura;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public boolean isbNotificacao() {
        return bNotificacao;
    }

    public void setbNotificacao(boolean bNotificacao) {
        this.bNotificacao = bNotificacao;
    }

    public int getNotificacoDias() {
        return notificacoDias;
    }

    public void setNotificacoDias(int notificacoDias) {
        this.notificacoDias = notificacoDias;
    }

    public ArrayList<Objetivo> getObjetivos() {
        return objetivos;
    }

    public void adicionarPeso(Peso peso) {
        if (historicoPesos.size() >= 60) {
            // Remover o peso mais antigo se o histórico estiver cheio
            historicoPesos.removeFirst();
        }
        historicoPesos.add(peso);
    }

    public float getPesoAtual() {
       pesoAtual = historicoPesos.getLast().getValor();
       return pesoAtual;
    }

    public ArrayList<Peso> getHistoricoPesos() {
        return historicoPesos;
    }

    public ArrayList<AtividadeExecutada> getAtividadesExecutadas() {
        return repoAtividadesExecutadas.getAtividadesRealizadas();
    }

    public void removerObjetivo(Objetivo objetivo) {
        this.objetivos.remove(objetivo);
    }

}
