package src.br.ufrpe.powerUp;

import java.time.LocalDate;
import java.time.LocalTime;

public class Atividade {

    private String id;
    private TipoAtributo tipo;
    private int intensidade;

    public Atividade(String id, String nome, TipoAtributo tipo, int intensidade, LocalTime horarioTermino, LocalTime horarioInicio) {
        this.id = id;
        this.tipo = tipo;
        this.intensidade = intensidade;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public int getIntensidade() {
        return intensidade;
    }

    public void setIntensidade(int intensidade) {
        this.intensidade = intensidade;
    }

    public TipoAtributo getTipo() {
        return tipo;
    }

    public void setTipo(TipoAtributo tipo) {
        this.tipo = tipo;
    }
}

