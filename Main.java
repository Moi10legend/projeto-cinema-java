import model.Administrador;
import model.Compra;
import model.Filme;
import model.Genero;
import model.Produtos;
import model.Sala;
import model.Salas;
import model.Sessao;
import model.Usuario;
import model.VendasException;

class Main{
    public static void main(String [] args){
        System.out.println("=== 1. TESTE DE PERSISTÊNCIA E ADMINISTRADOR ===");
        
        Administrador admin = new Administrador(35, "Carlos Admin", "admin@cinema.com", 5000.0, 101);

        Filme filme1 = admin.buscarFilmePorNome("Pelé: O Nascimento de uma Lenda");
        System.out.println(filme1);

        System.out.println("\n=== 2. TESTE DE SALAS E SESSÕES ===");
        Sala sala1 = new Sala(1, Salas.SALACOMUM);
        Sessao sessao1 = new Sessao(filme1, "18:00", 1);

        sala1.cadastrarSessao(sessao1, 0);

        Usuario u1 = admin.buscarUsuarioPorEmail("maria.cliente@email.com");
        System.out.println(u1);
        System.out.println("\n=== 3. TESTE DE COMPRA E LANCHONETE ===");
        Compra compra = new Compra(u1);

        compra.adicionarUmItemDaLanchonete(Produtos.CACHORROQUENTE);
        compra.adicionarUmItemDaLanchonete(Produtos.COCACOLA);
        compra.adicionarUmItemDaLanchonete(Produtos.PIPOCADOCE);

        try {
            compra.compraDeBilhetes(new Sala[]{sala1}, "Pelé: O Nascimento de uma Lenda", 2, null);
        } catch (VendasException e) {
            System.out.println("Erro na compra: " + e.getMessage());
        }

        System.out.println("\n=== 4. ESTADO DAS CADEIRAS APÓS A COMPRA ===");
        sessao1.exibirCadeiras();
    } 
}