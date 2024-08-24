package com.br.ufrpe.powerUp.negocio.controllers;

import com.br.ufrpe.powerUp.dados.RepositorioUsuarios;
import com.br.ufrpe.powerUp.dados.exceptions.CJEException;
import com.br.ufrpe.powerUp.dados.exceptions.CNException;
import com.br.ufrpe.powerUp.negocio.beans.Objetivo;
import com.br.ufrpe.powerUp.negocio.beans.TipoAtributo;
import com.br.ufrpe.powerUp.negocio.beans.Usuario;

public class controladorUsuario {
    private Usuario usuario;


    public controladorUsuario(String nome, String senha, boolean login) throws CJEException, CNException {
        if (login){
            logarConta(nome, senha);
        } else {
            cadastrarConta(nome, senha);
        }

    }

    private void logarConta(String nome, String senha) throws CNException {
        Usuario usuarioLogin = new Usuario("0", nome, senha);

        RepositorioUsuarios repositorio = RepositorioUsuarios.getInstance();
        usuario = repositorio.procurarConta(usuarioLogin);
    }

    private void cadastrarConta(String nome, String senha) throws CJEException, CNException {
        Usuario usuarioCadastro = new Usuario("0", nome, senha);

        RepositorioUsuarios repositorio = RepositorioUsuarios.getInstance();
        repositorio.adicionarConta(usuarioCadastro);
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

    public int getUsuarioResistencia(){
        return usuario.getResistencia();
    }

    public int getUsuarioStamina(){
        return usuario.getStamina();
    }

    public int getUsuarioVelocidade(){
        return usuario.getVelocidade();
    }

    public float getUsuarioPeso(){
        return usuario.getPeso();
    }

    public float getUsuarioAltura(){
        return usuario.getAltura();
    }

    public void atualizarAtributoUsuario(TipoAtributo tipo, int qtd){
        this.usuario.atualizar_atributo(tipo, qtd);
    }

    public void adicionarObjetivoUsuario(Objetivo objetivo){
        this.usuario.adicionar_objetivo(objetivo);
    }

    public void setForcaUsuario(int valor){
        this.usuario.setForca(valor);
    }

    public void setResistenciaUsuario(int valor){
        this.usuario.setResistencia(valor);
    }

    public void setStaminaUsuario(int valor){
        this.usuario.setStamina(valor);
    }

    public void setVelocidadeUsuario(int valor){
        this.usuario.setVelocidade(valor);
    }

    public void setPesoUsuario(float valor){
        this.usuario.setPeso(valor);
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

}
