import java.util.List;

public interface FilmeDAO {
    void salvar(Filme filme);
    void alterar(String nomeBusca, Filme filmeAtualizado);
    void excluir(String nomeFilme);
    List<Filme> buscarTodos();
}
