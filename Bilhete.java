public class Bilhete{
    private String userId;
    private String cpf;
    private Sala sala;
    private Sessao sessao;
    private Filme filme;
    private double valor;
    private String cadeiraComprada;

    public Bilhete(String userId, String cpf, Sala sala, Sessao sessao, Filme filme, String cadeiraComprada){
        this.userId = userId;
        this.cpf = cpf;
        this.sala = sala;
        this.sessao = sessao;
        this.filme = filme;
        this.valor = this.filme.getValor() + this.sala.getValor();
        this.cadeiraComprada = cadeiraComprada;
    }

    public String getUserId(){
        return this.userId;
    }

    public String getCpf(){
        return this.cpf;
    }

    public Sala getSala(){
        return this.sala;
    }

    public Sessao getSessao(){
        return this.sessao;
    }

    public Filme getFilme(){
        return this.filme;
    }

    public double getValor(){
        return this.valor;
    }

    public String getCadeiraComprada(){
        return this.cadeiraComprada;
    }
}