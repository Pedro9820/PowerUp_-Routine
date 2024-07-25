package src.br.ufrpe.powerUp;

import java.time.LocalDate;
import java.util.ArrayList;

public class Historico {

    private ArrayList<atvdHistorico> listHistorico;

    public Historico() {
        listHistorico = new ArrayList<>();
    }

    public void adicionarAtividade(LocalDate dataConclusao, boolean foiConcluida){
        listHistorico.add(new atvdHistorico(dataConclusao, foiConcluida));
        removerAtividade();
    }
    private void removerAtividade(){
        if (listHistorico.size() > 30) {
            listHistorico.remove(31);
        }
    }


}
