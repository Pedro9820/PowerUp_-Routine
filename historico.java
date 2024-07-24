import java.time.LocalDate;    
public class historico {

private LocalDate data;
private boolean foiConcluida;

public void adicionarAtividade(Atividade Atv){

}
public void removerAtividade(Atividade Atv){

}

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public boolean isFoiConcluida() {
        return foiConcluida;
    }

    public void setFoiConcluida(boolean foiConcluida) {
        this.foiConcluida = foiConcluida;
    }
}
