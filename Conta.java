public class Conta {
    private String ID, Nome;
    private byte força, stamina, intelecto, criatividade;
    private float peso, altura;


    public void atualizar_atributo(byte num, byte qtd){
        switch(num){
            case 1:força+=qtd;break;
            case 2:stamina+=qtd;break;
            case 3:intelecto+=qtd;break;
            case 4:criatividade+=qtd;break;
            case 5:peso+=qtd;break;
            case 6:altura+=qtd;break;
        }
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public void setForça(byte força) {
        this.força = força;
    }

    public void setStamina(byte stamina) {
        this.stamina = stamina;
    }

    public void setIntelecto(byte intelecto) {
        this.intelecto = intelecto;
    }

    public void setCriatividade(byte criatividade) {
        this.criatividade = criatividade;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public String getID() {
        return ID;
    }

    public String getNome() {
        return Nome;
    }

    public byte getForça() {
        return força;
    }

    public byte getStamina() {
        return stamina;
    }

    public byte getCriatividade() {
        return criatividade;
    }

    public float getPeso() {
        return peso;
    }

    public float getAltura() {
        return altura;
    }

    public byte getIntelecto() {
        return intelecto;
    }
}