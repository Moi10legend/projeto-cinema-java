import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {
    public static void salvarFilmes(String nomeArquivo, List<Filme> filmes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {
            for (Filme f : filmes) {
                // Formato: nome;sinopse;duracao;valor;nota;quantidade_criticos
                writer.println(f.getNome() + ";" + 
                               f.getSinopse() + ";" + 
                               f.getDuracao() + ";" + 
                               f.getValor() + ";" + 
                               f.getNota() + ";" + 
                               f.getQuantidade_criticos());
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar filmes em texto: " + e.getMessage());
        }
    }

    public static List<Filme> carregarFilmes(String nomeArquivo) {
        List<Filme> filmes = new ArrayList<>();
        File arquivo = new File(nomeArquivo);
        if (!arquivo.exists()) {
            return filmes;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";", -1);
                if (partes.length >= 6) {
                    String nome = partes[0];
                    String sinopse = partes[1];
                    int duracao = Integer.parseInt(partes[2]);
                    double valor = Double.parseDouble(partes[3]);
                    double nota = Double.parseDouble(partes[4]);
                    int qtdCriticos = Integer.parseInt(partes[5]);

                    Filme filme = new Filme(nome, sinopse, duracao, valor, nota, qtdCriticos);
                    filmes.add(filme);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar filmes de texto: " + e.getMessage());
        }
        return filmes;
    }

    // --- UTILIZADORES ---
    public static void salvarUsuarios(String nomeArquivo, List<Usuario> usuarios) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {
            for (Usuario u : usuarios) {
                // Formato: userId;cpf;senha;idade;genero;email;nomeDoCartao;numeroDoCartao;codigoSegurancaCartao
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
            System.err.println("Erro ao salvar utilizadores em texto: " + e.getMessage());
        }
    }

    public static List<Usuario> carregarUsuarios(String nomeArquivo) {
        List<Usuario> usuarios = new ArrayList<>();
        File arquivo = new File(nomeArquivo);
        if (!arquivo.exists()) {
            return usuarios;
        }

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
            System.err.println("Erro ao carregar utilizadores de texto: " + e.getMessage());
        }
        return usuarios;
    }
}
