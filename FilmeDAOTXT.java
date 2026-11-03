import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilmeDAOTXT implements FilmeDAO {
    private static final String ARQUIVO_FILMES = "filmes.txt";

    @Override
    public void salvar(Filme filme) {
        List<Filme> filmes = buscarTodos();
        filmes.add(filme);
        sobrescreverArquivo(filmes);
    }

    @Override
    public void alterar(String nomeBusca, Filme filmeAtualizado) {
        List<Filme> filmes = buscarTodos();
        for (int i = 0; i < filmes.size(); i++) {
            if (filmes.get(i).getNome().equalsIgnoreCase(nomeBusca)) {
                filmes.set(i, filmeAtualizado);
                break;
            }
        }
        sobrescreverArquivo(filmes);
    }

    @Override
    public void excluir(String nomeFilme) {
        List<Filme> filmes = buscarTodos();
        filmes.removeIf(f -> f.getNome().equalsIgnoreCase(nomeFilme));
        sobrescreverArquivo(filmes);
    }

    @Override
    public List<Filme> buscarTodos() {
        List<Filme> filmes = new ArrayList<>();
        File arquivo = new File(ARQUIVO_FILMES);
        if (!arquivo.exists()) return filmes;

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";", -1);
                if (partes.length >= 6) {
                    Filme filme = new Filme(partes[0], partes[1], Integer.parseInt(partes[2]), 
                                  Double.parseDouble(partes[3]), Double.parseDouble(partes[4]), 
                                  Integer.parseInt(partes[5]));
                    filmes.add(filme);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar filmes: " + e.getMessage());
        }
        return filmes;
    }

    private void sobrescreverArquivo(List<Filme> filmes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_FILMES))) {
            for (Filme f : filmes) {
                writer.println(f.getNome() + ";" + f.getSinopse() + ";" + f.getDuracao() + ";" + 
                               f.getValor() + ";" + f.getNota() + ";" + f.getQuantidade_criticos());
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar filmes: " + e.getMessage());
        }
    }
}