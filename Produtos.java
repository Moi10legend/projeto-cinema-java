public enum Produtos {
    PIPOCASALGADA("Pipoca Salgada" ,10.0),
    PIPOCADOCE("Pipoca Doce", 15.0),
    SUCOLARANJA("Suco de Laranja", 6.0),
    CACHORROQUENTE("Cachorro Quente", 10.0),
    COCACOLA("Coca-Cola 350ml", 6.0);

    private final String nome;
    private final double valor;

    Produtos(String nome, double valor){
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome(){
        return nome;
    }

    public double getValor(){
        return valor;
    }
}
