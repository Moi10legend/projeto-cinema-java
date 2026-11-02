package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Administrador extends Base implements GerenciaDeFilmes{
    private static Administrador instanciaUnica;

    private List<Filme> listaFilmes;
    private List<Usuario> listaUsuarios;
    private List<Sala> listaSalas;       // Nova lista
    private List<Sessao> listaSessoes;

    private static final String ARQUIVO_FILMES = "data/filmes.txt";
    private static final String ARQUIVO_USUARIOS = "data/usuarios.txt";

    private double salario;
    private int ID;

    private Administrador(int idade, String nome, String email, double salario, int ID){
        super(idade, nome, email);
        this.salario = salario;
        this.ID = ID;

        this.listaFilmes = Persistencia.carregarFilmes(ARQUIVO_FILMES);
        this.listaUsuarios = Persistencia.carregarUsuarios(ARQUIVO_USUARIOS);
        this.listaSalas = new ArrayList<>();
        this.listaSessoes = new ArrayList<>();
        
        inicializarSalasESessoes();
    }

    public static Administrador getInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new Administrador(35, "Carlos Admin", "admin@cinema.com", 5000, 1);
        }
        return instanciaUnica;
    }

    private void inicializarSalasESessoes() {
        // Exemplo: Criando a Sala 1
        Sala sala1 = new Sala(1, Salas.SALACOMUM);
        listaSalas.add(sala1);

        // Verifica se existem filmes carregados do TXT para criar as sessões
        if (!listaFilmes.isEmpty()) {
            Filme primeiroFilme = listaFilmes.get(0); // Pega o filme 1 do TXT
            
            // Criando sessões associadas ao filme e à sala
            Sessao s1 = new Sessao(primeiroFilme, "14:00", 1);
            Sessao s2 = new Sessao(primeiroFilme, "16:30", 1);
            sala1.cadastrarSessao(s1, 0);
            sala1.cadastrarSessao(s2, 1);
            
            listaSessoes.add(s1);
            listaSessoes.add(s2);
        }
    }

    public List<Sala> getListaSalas() {
        return this.listaSalas;
    }

    //Função de busca que o seu Controller vai chamar
    public List<Sessao> buscarSessoesPorFilme(String nomeFilme) {
        List<Sessao> sessoesEncontradas = new ArrayList<>();
        for (Sessao s : listaSessoes) {
            if (s.getFilme().getNome().equalsIgnoreCase(nomeFilme)) {
                sessoesEncontradas.add(s);
            }
        }
        return sessoesEncontradas;
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

    public List<Usuario> getListaUsuario(){
        return this.listaUsuarios;
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

    public List<Filme> getListaFilmes(){
        return this.listaFilmes;
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

    public Usuario buscarUsuarioPorEmail(String email) {
        for (Usuario u : listaUsuarios) { //[cite: 2]
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u; // Retorna a instância completa do usuário
            }
        }
        return null; // Retorna null se o usuário não for encontrado
    }

    public Filme buscarFilmePorNome(String nomeFilme) {
        for (Filme f : listaFilmes) { //[cite: 2]
            if (f.getNome().equalsIgnoreCase(nomeFilme)) {
                return f; // Retorna a instância completa do filme
            }
        }
        return null; // Retorna null se o filme não for encontrado
    }
}
