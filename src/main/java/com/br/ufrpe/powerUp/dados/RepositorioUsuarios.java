package com.br.ufrpe.powerUp.dados;

import com.br.ufrpe.powerUp.dados.exceptions.CIException;
import com.br.ufrpe.powerUp.dados.exceptions.CJEException;
import com.br.ufrpe.powerUp.dados.exceptions.CNException;
import com.br.ufrpe.powerUp.negocio.beans.Usuario;

import java.util.ArrayList;
import java.util.Objects;

public class RepositorioUsuarios {

    private static RepositorioUsuarios instancia;
    private ArrayList<Usuario> usuarios;

    private RepositorioUsuarios()  {
        usuarios = new ArrayList<>();
    }

    //garantindo que só exista um repositório de usuarios
    public static RepositorioUsuarios getInstance() {
        if (instancia == null) {
            instancia = new RepositorioUsuarios();
        }
        return instancia;
    }

    public void adicionarConta(Usuario usuario) throws CNException, CJEException {
        if (usuario == null) {
            CNException cne = new CNException();
            throw cne;

        } else if (usuarios.contains(usuario)) {// conta ja existe
            CJEException cje = new CJEException();
            throw cje;

       }else {
            usuarios.add(usuario);
        }

    }

    public Usuario procurarConta(Usuario usuarioAlvo) throws CNException {
        Usuario usuarioRetorno = null;

        for (Usuario usuario : usuarios) {
            if (usuarioAlvo.equals(usuario)) {
                usuarioRetorno = usuario;
            }
        }

        if (usuarioRetorno == null) {
            CNException cne = new CNException();
            throw cne;
        }

        return usuarioRetorno;
    }

    public void removerConta(String id) throws CIException {
        int indice = procurarID(id);
        if (indice != -1) {
            usuarios.remove(indice);
        }
        else {//Conta nao está no array
            CIException cie = new CIException(id);
            throw cie;
        }
    }

    private int procurarID(String id) {
        int indice = -1;
        for (int i = 0; i < usuarios.size(); i++) {
            if (Objects.equals(usuarios.get(i).getID(), id)) {
                indice = i;
            }
        }
        return indice;
    }
}