import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOTXT implements UsuarioDAO {
    private static final String ARQUIVO_USUARIOS = "usuarios.txt";

    @Override
    public void salvar(Usuario usuario) {
        List<Usuario> usuarios = buscarTodos();
        usuarios.add(usuario);
        sobrescreverArquivo(usuarios);
    }

    @Override
    public void alterar(String emailBusca, Usuario usuarioNovo) {
        List<Usuario> usuarios = buscarTodos();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getEmail().equalsIgnoreCase(emailBusca)) {
                usuarios.set(i, usuarioNovo);
                break;
            }
        }
        sobrescreverArquivo(usuarios);
    }

    @Override
    public void excluir(String email) {
        List<Usuario> usuarios = buscarTodos();
        usuarios.removeIf(u -> u.getEmail().equalsIgnoreCase(email));
        sobrescreverArquivo(usuarios);
    }

    @Override
    public List<Usuario> buscarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        File arquivo = new File(ARQUIVO_USUARIOS);
        if (!arquivo.exists()) return usuarios;

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";", -1);
                if (partes.length >= 9) {
                    int userId = Integer.parseInt(partes[0]);
                    String cpf = partes[1];
                    String senha = partes[2];
                    int idade = Integer.parseInt(partes[3]);
                    Genero genero = Genero.valueOf(partes[4]);
                    String email = partes[5];
                    String nomeDoCartao = partes[6];
                    String numeroDoCartao = partes[7];
                    String codigoSegurancaCartao = partes[8];

                    Usuario usuario = new Usuario(userId, cpf, senha, idade, genero, email, nomeDoCartao, numeroDoCartao, codigoSegurancaCartao);
                    usuarios.add(usuario);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar utilizadores: " + e.getMessage());
        }
        return usuarios;
    }

    private void sobrescreverArquivo(List<Usuario> usuarios) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_USUARIOS))) {
            for (Usuario u : usuarios) {
                writer.println(u.getUserId() + ";" +
                               u.getCpf() + ";" +
                               u.getSenha() + ";" +
                               u.getIdade() + ";" +
                               (u.getGenero() != null ? u.getGenero().name() : "M") + ";" +
                               u.getEmail() + ";" +
                               u.getNomeDoCartao() + ";" +
                               u.getnumeroDoCartao() + ";" +
                               u.getCodigoSegurancaCartao());
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar utilizadores: " + e.getMessage());
        }
    }
}