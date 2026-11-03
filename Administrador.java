public class Administrador extends Base implements GerenciaDeFilmes {
    private double salario;
    private int ID;
    
    // Injeção de dependência das interfaces DAO
    private FilmeDAO filmeDAO;
    private UsuarioDAO usuarioDAO;

    public Administrador(int idade, String nome, String email, double salario, int ID) {
        super(idade, nome, email);
        this.salario = salario;
        this.ID = ID;
        
        // Instanciação das implementações concretas
        this.filmeDAO = new FilmeDAOTXT();
        this.usuarioDAO = new UsuarioDAOTXT();
    }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
    public int getID() { return ID; }
    public void setID(int iD) { ID = iD; }
 
    public void adicionarUsuario(Usuario usuario){
        usuarioDAO.salvar(usuario);
        System.out.println("Usuário adicionado com sucesso!");
    }
 
    public void alterarUsuario(String emailBusca, Usuario usuarioNovo){
        usuarioDAO.alterar(emailBusca, usuarioNovo);
        System.out.println("Comando de alteração enviado ao banco de dados.");
    }

    public void excluirUsuario(String email){
        usuarioDAO.excluir(email);
        System.out.println("Comando de exclusão enviado ao banco de dados.");
    }
 
    public void incluirFilme(Filme filme){
        filmeDAO.salvar(filme);
        System.out.println("Filme incluído com sucesso!");
    }

    public void excluirFilme(String nomeFilme){
        filmeDAO.excluir(nomeFilme);
        System.out.println("Comando de exclusão enviado ao banco de dados.");
    }

    public void alterarFilme(String nomeFilmeBusca, Filme filmeAtualizado){
        filmeDAO.alterar(nomeFilmeBusca, filmeAtualizado);
        System.out.println("Comando de alteração enviado ao banco de dados.");
    }
    
    // Método auxiliar caso precise listar na tela
    public void exibirTodosOsFilmes() {
        for(Filme f : filmeDAO.buscarTodos()) {
            System.out.println(f.getNome());
        }
    }
}