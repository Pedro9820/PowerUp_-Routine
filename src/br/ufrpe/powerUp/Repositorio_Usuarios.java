package src.br.ufrpe.powerUp;

import java.util.ArrayList;
import java.util.Objects;

public class Repositorio_Usuarios {

    private ArrayList<Usuario> usuarios;

    public Repositorio_Usuarios() {
        usuarios = new ArrayList<>();
    }

    public void adicionar_conta(Usuario usuario) throws CNException, CJEException {
        if (usuario == null) {
            CNException cne = new CNException();
            throw cne;
        }
        else if (usuarios.contains(usuario)) {// conta ja existe
       CJEException cje = new CJEException();
       throw cje;
       }else if(!usuarios.contains(usuario)){
            usuarios.add(usuario);
        }

    }
    public void remover_conta(String id) throws CIException {
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
