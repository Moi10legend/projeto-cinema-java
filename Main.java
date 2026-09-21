class Main{
    public static void main(String [] args){
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

        Filme pele = new Filme(
            "Pelé: O Nascimento de uma Lenda",
            "A vida do maior do futebol",
            103,
            20.0
        );

        Sala sala1 = new Sala(1, 10.0);
        Sessao sessao1 = new Sessao(pele, "13:00", 1);

        sala1.cadastrarSessao(sessao1, 0);

        ItemLanchonete pipocaSalgada = new ItemLanchonete(Produtos.PIPOCASALGADA.getNome(), Produtos.PIPOCASALGADA.getValor());
        ItemLanchonete pipocaDoce = new ItemLanchonete(Produtos.PIPOCADOCE.getNome(), Produtos.PIPOCADOCE.getValor());
        ItemLanchonete cocaCola = new ItemLanchonete(Produtos.COCACOLA.getNome(), Produtos.COCACOLA.getValor());
        ItemLanchonete cachorroQuente = new ItemLanchonete(Produtos.CACHORROQUENTE.getNome(), Produtos.CACHORROQUENTE.getValor());
        ItemLanchonete sucoLaranja = new ItemLanchonete(Produtos.SUCOLARANJA.getNome(), Produtos.SUCOLARANJA.getValor());

        Compra compra = new Compra(u1);

        compra.adicionarUmItemDaLanchonete(sucoLaranja);
        compra.adicionarUmItemDaLanchonete(sucoLaranja);
        compra.adicionarUmItemDaLanchonete(pipocaSalgada);

        compra.compraDeBilhetes(new Sala[]{sala1}, "Pelé: O Nascimento de uma Lenda", 2);

        System.out.println();
        sessao1.exibirCadeiras();
    } 
}