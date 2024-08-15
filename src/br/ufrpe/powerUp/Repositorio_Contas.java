package src.br.ufrpe.powerUp;

import java.util.ArrayList;
import java.util.Objects;

public class Repositorio_Contas {

    private ArrayList<Conta> contas;

    public Repositorio_Contas() {
        contas= new ArrayList<>();
    }

    public void adicionar_conta(Conta conta) throws CNException, CJEException {
        if (conta == null) {
            CNException cne = new CNException();
            throw cne;
        }
        else if (contas.contains(conta)) {// conta ja existe
       CJEException cje = new CJEException();
       throw cje;
       }else if(!contas.contains(conta)){
            contas.add(conta);
        }

    }
    public void remover_conta(String id) throws CIException {
        int indice = procurarID(id);
        if (indice != -1) {
            contas.remove(indice);
        }
        else {//Conta nao está no array
            CIException cie = new CIException(id);
            throw cie;
        }
    }

    private int procurarID(String id) {
        int indice = -1;
        for (int i = 0; i < contas.size(); i++) {
            if (Objects.equals(contas.get(i).getID(), id)) {
                indice = i;
            }
        }
        return indice;
    }
}
