import java.util.ArrayList;
import java.util.Objects;

public class lista_atividade {

private ArrayList<Atividade> lista = new ArrayList<Atividade>();

public void adicionaratvd(Atividade atividade){
    lista.add(atividade);
}
public void excluirAtvd(String idatividade){
    lista.remove(procurarID(idatividade));
}
public void editarAtvd(String idatividade, Atividade atv){

}
private int procurarID(String id) {
    int indice = 0;
        for (int i = 0; i < lista.size(); i++) {
            if (Objects.equals(lista.get(i).getId(), id)) {
                indice = i;
            }
        }
        return indice;

}
    public ArrayList<Atividade> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Atividade> lista) {
        this.lista = lista;
    }
}
