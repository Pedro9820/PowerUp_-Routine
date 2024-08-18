package src.br.ufrpe.powerUp.dados;

import src.br.ufrpe.powerUp.dados.exceptions.CIException;
import src.br.ufrpe.powerUp.dados.exceptions.CJEException;
import src.br.ufrpe.powerUp.dados.exceptions.CNException;
import src.br.ufrpe.powerUp.negocio.beans.Usuario;

import java.util.ArrayList;
import java.util.Objects;

public class RepositorioUsuarios {

    private ArrayList<Usuario> usuarios;

    public RepositorioUsuarios() {
        usuarios = new ArrayList<>();
    }

    public void adicionarConta(Usuario usuario) throws CNException, CJEException {
        if (usuario == null) {
            CNException cne = new CNException();
            throw cne;
        }
        else if (usuarios.contains(usuario)) {// conta ja existe
       CJEException cje = new CJEException();
       throw cje;
       }else {
            usuarios.add(usuario);
        }

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
