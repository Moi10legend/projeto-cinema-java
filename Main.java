import java.util.List;

class Main{
    public static void main(String [] args){
        // System.out.println("=== 1. TESTE DE PERSISTÊNCIA E ADMINISTRADOR ===");
        
        // FilmeDAO filmeDAO = new FilmeDAOTXT();
        // UsuarioDAO usuarioDAO = new UsuarioDAOTXT();
        // Administrador admin = new Administrador(35, "Carlos Admin", "admin@cinema.com", 5000.0, 101, filmeDAO, usuarioDAO);

        // Filme filme1 = new Filme(
        //     "Pelé: O Nascimento de uma Lenda",
        //     "A vida do maior do futebol",
        //     103,
        //     20.0,
        //     0.0,
        //     0
        // );

        // Filme filme2 = new Filme(
        //     "Inception",
        //     "Um ladrão que rouba segredos através dos sonhos",
        //     148,
        //     25.0,
        //     0.0,
        //     0
        // );

        // admin.incluirFilme(filme1);
        // admin.incluirFilme(filme2);

        // Usuario u1 = new Usuario(
        //     1, 
        //     "111.111.111-11",
        //     "123",
        //     20,
        //     Genero.M,
        //     "guilhermecocada@gmail.com",
        //     "GUILHERME SILVA",
        //     "1111 1111 1111 1111",
        //     "111"    
        // );
        // admin.adicionarUsuario(u1);

        System.out.println("\n=== 2. TESTE DE SALAS E SESSÕES ===");
        Sala sala1 = new Sala(1, Salas.SALACOMUM);
        // 1. Inicializa dependências
        FilmeDAO filmeDAO = new FilmeDAOTXT();

        // 2. Seleciona o filme carregado do TXT
        Filme filmeDaSessao = filmeDAO.buscarPorNome("Inception");
        Sessao sessao1 = null;

        // 4. Utiliza o objeto real na regra de negócio
        if (filmeDaSessao != null) {
            sessao1 = new Sessao(filmeDaSessao, "21:00", 1);
            sala1.cadastrarSessao(sessao1, 0);
            // Continua para a compra...
        } else {
            System.out.println("Filme não encontrado no banco de dados.");
        }

        sala1.cadastrarSessao(sessao1, 0);

        System.out.println("\n=== 3. TESTE DE COMPRA E LANCHONETE ===");
        UsuarioDAO usuarioDAO = new UsuarioDAOTXT();

        Usuario u1 = usuarioDAO.buscarPorEmail("guilhermecocada@gmail.com");

        Compra compra = new Compra(u1);

        compra.adicionarUmItemDaLanchonete(Produtos.CACHORROQUENTE);
        compra.adicionarUmItemDaLanchonete(Produtos.COCACOLA);
        compra.adicionarUmItemDaLanchonete(Produtos.PIPOCADOCE);

        try {
            compra.compraDeBilhetes(new Sala[]{sala1}, "Inception", 2, null);
        } catch (VendasException e) {
            System.out.println("Erro na compra: " + e.getMessage());
        }

        System.out.println("\n=== 4. ESTADO DAS CADEIRAS APÓS A COMPRA ===");
        sessao1.exibirCadeiras();
    } 
}