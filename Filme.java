public class Filme{
    private String nome;
    private String sinopse;
    private int duracao;  //Em minutos
    private double valor;

    public Filme(String nome, String sinopse, int duracao, double valor){
        this.nome = nome;
        this.sinopse = sinopse;
        this.duracao = duracao;
        this.valor = valor;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getSinopse(){
        return this.sinopse;
    }

    public void setSinopse(String sinopse){
        this.sinopse = sinopse;
    }

    public int getDuracao(){
        return this.duracao;
    }

    public void setDuracao(int duracao){
        this.duracao = duracao;
    }

    public double getValor(){
        return this.valor;
    }

    public void setValor(int valor){
        this.valor = valor;
    }
}