import java.io.Serializable;
import java.util.List;

public class Administrador extends Base implements GerenciaDeFilmes{
    private List<Filme> listaFilmes;
    private List<Usuario> listaUsuarios;

    private static final String ARQUIVO_FILMES = "filmes.txt";
    private static final String ARQUIVO_USUARIOS = "usuarios.txt";

    private double salario;
    private int ID;

    public Administrador(int idade, String nome, String email, double salario, int ID){
        super(idade, nome, email);
        this.salario = salario;
        this.ID = ID;

        this.listaFilmes = Persistencia.carregarFilmes(ARQUIVO_FILMES);
        this.listaUsuarios = Persistencia.carregarUsuarios(ARQUIVO_USUARIOS);
    }

    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
 
    public void adicionarUsuario(Usuario usuario){
        listaUsuarios.add(usuario);
        Persistencia.salvarUsuarios(ARQUIVO_USUARIOS, listaUsuarios);
        System.out.println("Usuário adicionado com sucesso!");
    }
 
    public void alterarUsuario(String emailBusca, Usuario usuarioNovo){
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getEmail().equalsIgnoreCase(emailBusca)) {
                listaUsuarios.set(i, usuarioNovo);
                Persistencia.salvarUsuarios(ARQUIVO_USUARIOS, listaUsuarios);
                System.out.println("Usuário alterado com sucesso!");
                return;
            }
        }
        System.out.println("Usuário não encontrado.");
    }

    public void excluirUsuario(String email){
        boolean removido = listaUsuarios.removeIf(u -> u.getEmail().equalsIgnoreCase(email));
        if (removido) {
            Persistencia.salvarUsuarios(ARQUIVO_USUARIOS, listaUsuarios);
            System.out.println("Usuário excluído com sucesso!");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }
 
    public void incluirFilme(Filme filme){
        listaFilmes.add(filme);
        Persistencia.salvarFilmes(ARQUIVO_FILMES, listaFilmes);
        System.out.println("Filme incluído com sucesso!");
    }

    public void excluirFilme(String nomeFilme){
        boolean removido = listaFilmes.removeIf(f -> f.getNome().equalsIgnoreCase(nomeFilme));
        if (removido) {
            Persistencia.salvarFilmes(ARQUIVO_FILMES, listaFilmes);
            System.out.println("Filme excluído com sucesso!");
        } else {
            System.out.println("Filme não encontrado.");
        }
    }
    public void alterarFilme(String nomeFilmeBusca, Filme filmeAtualizado){
        for (int i = 0; i < listaFilmes.size(); i++) {
            if (listaFilmes.get(i).getNome().equalsIgnoreCase(nomeFilmeBusca)) {
                listaFilmes.set(i, filmeAtualizado);
                Persistencia.salvarFilmes(ARQUIVO_FILMES, listaFilmes);
                System.out.println("Filme alterado com sucesso!");
                return;
            }
        }
        System.out.println("Filme não encontrado.");
    }
}
