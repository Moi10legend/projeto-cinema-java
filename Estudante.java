public class Estudante extends Usuario{
    public Estudante( int userId, String cpf, String senha, int idade, Genero genero, String email, String nomeDoCartao,
     String numeroDoCartao, String codigoDeSegurancaCartao){
        super(userId, cpf, senha, idade, genero, email, nomeDoCartao, numeroDoCartao, codigoDeSegurancaCartao);
    }
}
