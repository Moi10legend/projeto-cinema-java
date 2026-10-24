public class Filme{
    private String nome;
    private String sinopse;
    private int duracao;  //Em minutos
    private double valor;
    private double nota;
    private int quantidade_criticos;
    private Critica[] critica = new Critica[100];

    public Filme(String nome, String sinopse, int duracao, double valor, double nota,
        int quantidade_criticos){
        this.nome = nome;
        this.sinopse = sinopse;
        this.duracao = duracao;
        this.valor = valor;
        this.nota = nota;
        this.quantidade_criticos = quantidade_criticos;
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

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getQuantidade_criticos() {
        return quantidade_criticos;
    }

    public void setQuantidade_criticos(int quantidade_criticos) {
        this.quantidade_criticos = quantidade_criticos;
    }

    public Critica[] getCriticas(){
        return this.critica;
    }

    public void adicionarCritica(Critica critica){
        for(int i = 0; i < this.critica.length; i++){
            if(this.critica[i] == null){
                this.critica[i] = critica;
                break;
            }
        }
    }

}