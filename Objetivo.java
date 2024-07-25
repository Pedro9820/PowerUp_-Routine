import java.time.LocalDate;

public class Objetivo {
    private LocalDate dataMaxima;
    private  String id, nome, descricao;
    private int quota;

    public boolean verificarCondicao(Conta conta){
        boolean verificacao=false;


        return verificacao;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQuota() {
        return quota;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }
}
