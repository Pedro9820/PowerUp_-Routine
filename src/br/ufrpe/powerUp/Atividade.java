package src.br.ufrpe.powerUp;

import java.time.LocalDate;
import java.time.LocalTime;

public class Atividade {

private String id, nome;
private TipoAtributo tipo;
private int intensidade;
private boolean concluida, finalizadaHoje;
private LocalDate ultimaAtualizacao;
private LocalTime horarioInicio, horarioTermino;
private Historico historico;

public Atividade(String id, String nome, TipoAtributo tipo, int intensidade, LocalTime horarioTermino, LocalTime horarioInicio) {
    this.id = id;
    this.nome = nome;
    this.tipo = tipo;
    this.intensidade = intensidade;
    this.horarioInicio = horarioInicio;
    this.horarioTermino = horarioTermino;
    this.concluida = false;
    this.ultimaAtualizacao = null;
    this.historico = new Historico();
}

    // verifica se o horário de término foi excedido.
    public boolean checkConclusao() {
        boolean finalizada = finalizadaHoje;

        if (horarioTermino.isBefore(LocalTime.now())) {
            finalizada = true;
            addHistorico();
        }

        return finalizada;
    }

    // adiciona ao histórico uma vez ao dia, mesmo que seja chamado vária vezes.
    private void addHistorico() {
        LocalDate hoje = LocalDate.now();

        if (ultimaAtualizacao == null || !ultimaAtualizacao.equals(hoje)) {
            historico.adicionarAtividade(hoje, concluida);
            ultimaAtualizacao = hoje;
        }
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalTime getHorarioTermino() {
        return horarioTermino;
    }

    public void setHorarioTermino(LocalTime horarioTermino) {
        this.horarioTermino = horarioTermino;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public Historico getHistorico() {
        return historico;
    }

    public void setHistorico(Historico historico) {
        this.historico = historico;
    }
}
