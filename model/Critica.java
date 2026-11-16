package model;
public class Critica {
    private String nome;
    private String origem;
    private String mensagem;
    private double nota;

    public Critica(String nome, String origem, String mensagem){
        this.nome = nome;
        this.origem = origem;
        this.mensagem = mensagem;
    }

    public Critica(String nome, String origem, String mensagem, double nota) {
        this.nome = nome;
        this.origem = origem;
        this.nota = nota;
        this.mensagem = mensagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public double getNota(){
        return this.nota;
    }

    public void setNota(double nota){
        this.nota = nota;
    }
}
