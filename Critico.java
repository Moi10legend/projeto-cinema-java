public class Critico extends Usuario{
    private String origem;

    public Critico(int userId, String cpf, String senha, int idade, Genero genero, String email, 
        String nomeDoCartao, String numeroDoCartao, String codigoDeSegurancaCartao, String origem){
            super(userId, cpf, senha, idade, genero, email, nomeDoCartao, numeroDoCartao, codigoDeSegurancaCartao);
            this.origem = origem;
        }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public void atribuirNota(double nota, Filme filme){
        filme.setNota((filme.getNota()+nota)/2);
        filme.setQuantidade_criticos(filme.getQuantidade_criticos()+1);
    }

    public void atribuirCritica(String critica, Filme filme){
        Critica novaCritica = new Critica(this.getNomeDoCartao(), this.origem, critica);
        filme.adicionarCritica(novaCritica);
    }
}
