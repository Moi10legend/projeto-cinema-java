public class Funcionario extends Base implements GerenciaDeFilmes{
    private double salario;

    public Funcionario(int idade, String nome, String email, double salario){
        super(idade, nome, email);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void adicionarUsuario(Usuario usuario) {
       
    }

    public void alterarUsuario(String emailBusca, Usuario usuarioNovo) {
        
    }

    public void incluirFilme(Filme filme) {

    }

    public void excluirFilme(String nomeFilme) {
        
    }

    public void alterarFilme(String nomeFilmeBusca, Filme filmeAtualizado) {
        
    }
    
}
