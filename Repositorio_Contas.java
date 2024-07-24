import java.util.ArrayList;
import java.util.Objects;

public class Repositorio_Contas {

    private static ArrayList<Conta> contas = contas = new ArrayList<>();

    public void adicionar_conta(Conta conta) {
        contas.add(conta);
    }
    public void remover_conta(String id) {
        int indice = procurarID(id);
        contas.remove(indice);
    }

    private int procurarID(String id) {
        int indice = 0;
        for (int i = 0; i < contas.size(); i++) {
            if (Objects.equals(contas.get(i).getID(), id)) {
                indice = i;
            }
        }
        return indice;
    }
}
