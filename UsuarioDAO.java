import java.util.List;

public interface UsuarioDAO {
    void salvar(Usuario usuario);
    void alterar(String emailBusca, Usuario usuarioNovo);
    void excluir(String email);
    List<Usuario> buscarTodos();
}