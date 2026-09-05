public class Bilhete{
    private String userId;
    private String cpf;
    private Sala sala;
    private Sessao sessao;
    private Filme filme;
    private double valor;
    private String cadeiraComprada;

    public Bilhete(String userId, String cpf, Sala sala, Sessao sessao, Filme filme, double valor, String CadeiraComprada){
        this.userId = userId;
        this.cpf = cpf;
        this.sala = sala;
        this.sessao = sessao;
        this.filme = filme;
        this.valor = valor;
        this.cadeiraComprada = cadeiraComprada;
    }

}