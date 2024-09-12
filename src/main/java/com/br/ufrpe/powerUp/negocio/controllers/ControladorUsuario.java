package com.br.ufrpe.powerUp.negocio.controllers;

import com.br.ufrpe.powerUp.dados.RepositorioUsuarios;
import com.br.ufrpe.powerUp.dados.exceptions.AJRException;
import com.br.ufrpe.powerUp.dados.exceptions.ANexception;
import com.br.ufrpe.powerUp.dados.exceptions.CJEException;
import com.br.ufrpe.powerUp.dados.exceptions.CNException;
import com.br.ufrpe.powerUp.negocio.beans.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ControladorUsuario {
    private Usuario usuario;


    public ControladorUsuario(String nome, String senha, float altura, boolean login) throws CJEException, CNException {
        if (login){
            logarConta(nome, senha);
        } else {
            cadastrarConta(nome, senha, altura);
        }

    }

    private void logarConta(String nome, String senha) throws CNException {
        Usuario usuarioLogin = new Usuario("0", nome, senha, 0);

        RepositorioUsuarios repositorio = RepositorioUsuarios.getInstance();
        usuario = repositorio.procurarConta(usuarioLogin);
    }

    private void cadastrarConta(String nome, String senha, float altura) throws CJEException, CNException {
        Usuario usuarioCadastro = new Usuario("0", nome, senha, altura);

        RepositorioUsuarios repositorio = RepositorioUsuarios.getInstance();
        repositorio.adicionarConta(usuarioCadastro);
    }

    public void adicionarAtividadeExecutada(Atividade atividadeExec, LocalDateTime atinicio, LocalDateTime atfim) throws ANexception, AJRException {
        AtividadeExecutada atividade = new AtividadeExecutada(atividadeExec, atinicio, atfim);
        usuario.adicionarAtividadeExecutada(atividade);
    }



    public String getUsuarioName(){
        return usuario.getNome();
    }

    public String getUsuarioSenha() {
        return usuario.getSenha();
    }

    public int getUsuarioForca(){
        return usuario.getForca();
    }

    public int getUsuarioStamina(){
        return usuario.getStamina();
    }

    public int getUsuarioIntelecto() {return usuario.getIntelecto(); }

    public int getUsuarioCriatividade() { return usuario.getCriatividade(); }

    public float getUsuarioAltura(){
        return usuario.getAltura();
    }

    public ArrayList<Objetivo> getObjetivos() {
        return usuario.getObjetivos();
    }

    public void removerObjetivo(Objetivo objetivo) {
        this.usuario.removerObjetivo(objetivo);
    }


    public ArrayList<AtividadeExecutada> getAtividadesExecutadas() {
        return usuario.getAtividadesExecutadas();
    }

    public void atualizarAtributoUsuario(TipoAtributo tipo, int qtd){
        this.usuario.atualizarAtributo(tipo, qtd);
    }

    public void adicionarObjetivoUsuario(Objetivo objetivo){
        this.usuario.adicionar_objetivo(objetivo);
    }

    public void setForcaUsuario(int valor){
        this.usuario.setForca(valor);
    }

    public void setStaminaUsuario(int valor){
        this.usuario.setStamina(valor);
    }

    public void setAlturaUsuario(float valor){
        this.usuario.setAltura(valor);
    }

    public void setNomeUsuario(String nome){
        this.usuario.setNome(nome);
    }

    public void setIdUsuario(String id){
        this.usuario.setID(id);
    }

    public void setSenha(String senha) { this.usuario.setSenha(senha); }

    public double getVolume() {
        return usuario.getVolume();
    }

    public void setVolume(double volume) {
        usuario.setVolume(volume);
    }

    public boolean isbNotificacao() {
        return usuario.isbNotificacao();
    }

    public void setbNotificacao(boolean bNotificacao) {
        usuario.setbNotificacao(bNotificacao);
    }

    public int getNotificacoDias() {
        return usuario.getNotificacoDias();
    }

    public void setNotificacoDias(int notificacoDias) {
        if (notificacoDias >= 0) {
            usuario.setNotificacoDias(notificacoDias);
        }
    }

    public float getPesoAtual() {
        return this.usuario.getPesoAtual();
    }

    public void adicionarPeso(Peso peso) {
        this.usuario.adicionarPeso(peso);
    }

    public ArrayList<Peso> getHistoricoPesos() {
        return this.usuario.getHistoricoPesos();
    }

    public void salvarRepositorio() {
        RepositorioUsuarios repositorioUsuarios = RepositorioUsuarios.getInstance();
        try {
            repositorioUsuarios.salvarEmArquivo("src/repositorioUsuarios.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
