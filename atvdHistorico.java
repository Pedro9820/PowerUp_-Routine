import java.time.LocalDate;

public class atvdHistorico {

    private LocalDate data;
    private boolean foiConcluida;

    public atvdHistorico(LocalDate data, boolean foiConcluida) {
        this.data = data;
        this.foiConcluida = foiConcluida;
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
