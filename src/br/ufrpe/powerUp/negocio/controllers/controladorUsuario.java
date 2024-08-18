package src.br.ufrpe.powerUp.negocio.controllers;

import src.br.ufrpe.powerUp.negocio.beans.Objetivo;
import src.br.ufrpe.powerUp.negocio.beans.TipoAtributo;
import src.br.ufrpe.powerUp.negocio.beans.Usuario;

public class controladorUsuario {
    private Usuario usuario;


    public controladorUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getUsuarioName(){
        return usuario.getNome();
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
