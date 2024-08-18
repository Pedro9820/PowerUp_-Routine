package src.br.ufrpe.powerUp.dados;
import src.br.ufrpe.powerUp.dados.exceptions.AJRException;
import src.br.ufrpe.powerUp.dados.exceptions.ANexception;
import src.br.ufrpe.powerUp.negocio.AtividadeExecutada;
import java.util.ArrayList;


public class RepositorioAtividadesExecutadas {
    private ArrayList<AtividadeExecutada> atividadesRealizadas;

    public RepositorioAtividadesExecutadas() {

    }

    public void adicionarAtividade(AtividadeExecutada atividade) throws ANexception, AJRException {
        if (atividade == null) {
            ANexception ane = new ANexception();
            throw ane;
        }
        else if (atividadesRealizadas.contains(atividade)) {
            AJRException cje = new AJRException();
            throw cje;
        }else {
            atividadesRealizadas.add(atividade);
        }

    }

    public void removerAtividade(int i) throws ANexception {

    }

    }

