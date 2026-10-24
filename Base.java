public abstract class Base {
    private int idade;
    private String nome;
    private String email;

    public Base(int idade, String nome, String email){
        this.idade = idade;
        this.nome = nome;
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    abstract public void adicionarUsuario();
    abstract public void alterarUsuario();
}
