class Main{
    public static void main(String [] args){
        System.out.println("=== 1. TESTE DE PERSISTÊNCIA E ADMINISTRADOR ===");
        
        Administrador admin = new Administrador(35, "Carlos Admin", "admin@cinema.com", 5000.0, 101);

        Filme filme1 = new Filme(
            "Pelé: O Nascimento de uma Lenda",
            "A vida do maior do futebol",
            103,
            20.0,
            0.0,
            0
        );

        Filme filme2 = new Filme(
            "Inception",
            "Um ladrão que rouba segredos através dos sonhos",
            148,
            25.0,
            0.0,
            0
        );

        admin.incluirFilme(filme1);
        admin.incluirFilme(filme2);

        Usuario u1 = new Usuario(
            1, 
            "111.111.111-11",
            "123",
            20,
            Genero.M,
            "guilhermecocada@gmail.com",
            "GUILHERME SILVA",
            "1111 1111 1111 1111",
            "111"    
        );
        admin.adicionarUsuario(u1);

        System.out.println("\n=== 2. TESTE DE SALAS E SESSÕES ===");
        Sala sala1 = new Sala(1, Salas.SALACOMUM);
        Sessao sessao1 = new Sessao(filme1, "13:00", 1);

        sala1.cadastrarSessao(sessao1, 0);

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