package model;
import java.util.ArrayList;
// import java.util.List;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Compra {
    private Usuario cliente;
    private ArrayList<Produtos> itensLanchonete = new ArrayList<Produtos>();
    private Bilhete[] bilhetes = new Bilhete[9];
    
    public Compra(Usuario cliente){
        this.cliente = cliente;
    }

    public Usuario getCliente(){
        return this.cliente;
    }

    public void setCliente(Usuario cliente){
        this.cliente = cliente;
    }

    public ArrayList<Produtos> getItensLanchonete(){
        return this.itensLanchonete;
    }

    public void adicionarUmItemDaLanchonete(Produtos item){
        this.itensLanchonete.add(item);
        System.out.println("Item adicionado sucesso!");
    }

    public void retirarUmITemDaLanchonete(int index){
        for(int i = index; i < itensLanchonete.size(); i++){
            if(itensLanchonete.get(i+1) == null){
                itensLanchonete.add(i, null);
                System.out.println("Item removido com sucesso");
                break;
            }

            Produtos aux = itensLanchonete.get(i);
            itensLanchonete.add(i, itensLanchonete.get(i+1));
            itensLanchonete.add(i+1, aux);
        }
    }

    public void compraDeBilhetes(Sala[] salas, String nomeFilme, int qtdDeBilhetes, 
        CupomPromocional cupom) throws VendasException{
        if(qtdDeBilhetes > 9){
            System.out.println("A quantidade de bilhetes é maior que o possível comprar de uma única vez.");
            return;
        }

        ArrayList<Sessao> sessoesQuePossuemOFilme = retornarSessoesQuePossuemOFilme(nomeFilme, salas);

        exibirSessoesQuePossuemOFilme(sessoesQuePossuemOFilme, nomeFilme);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o número da sessão que deseja comprar os bilhetes:");
        int sessaoEscolhida = scanner.nextInt()-1;
        scanner.nextLine();
        Sessao sessao = escolherSessao(sessaoEscolhida, sessoesQuePossuemOFilme, scanner);
        
        Cadeira[][] cadeiras = sessao.getCadeiras();

        sessao.exibirCadeiras();

        if(qtdDeBilhetes > 1){
            exibirSugestoesDeCadeiras(qtdDeBilhetes, cadeiras);
        }
        escolherCadeiras(sessao, scanner, qtdDeBilhetes, salas);
        
        exibirExtratoDaCompra(cupom, salas, sessao);
        
        scanner.close();
    }

    private ArrayList<Sessao> retornarSessoesQuePossuemOFilme(String nomeFilme, Sala[] salas)
        throws VendasException{
        boolean possuiSessao = false;
        // Filme filme;
        ArrayList<Sessao> sessoesQuePossuemOFilme = new ArrayList<>();

        for(int i = 0; i < salas.length; i++){
            for(int j = 0; j<salas[i].getSessoes().length; j++){
                Sessao sessaoAtual = salas[i].getSessoes()[j];
                if(sessaoAtual == null){
                    break;
                }
                if(sessaoAtual.getFilme().getNome().equals(nomeFilme) ){
                    // filme = sessaoAtual.getFilme();
                    sessoesQuePossuemOFilme.add(salas[i].getSessoes()[j]);
                    possuiSessao = true;
                }
            }
        }
        if (!possuiSessao){
            throw new VendasException("Erro: Este filme não está mais em cartaz.");
        }

        return sessoesQuePossuemOFilme;
    }

    private void exibirSessoesQuePossuemOFilme(ArrayList<Sessao> sessoesQuePossuemOFilme, String nomeFilme){
        System.out.println("Sessões disponíveis para o filme " + nomeFilme + ":");
        for(int i = 1; i == sessoesQuePossuemOFilme.size(); i++){
            System.out.println(i + ": Sala: " + sessoesQuePossuemOFilme.get(i-1).getNumeroSala() + " Horário: " + sessoesQuePossuemOFilme.get(i-1).getHorario());
        }
    }

    private Sessao escolherSessao(int sessaoEscolhida, ArrayList<Sessao> sessoesQuePossuemOFilme,
        Scanner scanner
    ) throws VendasException{
        Sessao sessao;
        while(true){
            if(sessaoEscolhida >= 0 && sessaoEscolhida < sessoesQuePossuemOFilme.size()){
                String horarioSessaoTexto = sessoesQuePossuemOFilme.get(sessaoEscolhida).getHorario();
                DateTimeFormatter formatador = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime horarioRealSessao = LocalTime.parse(horarioSessaoTexto, formatador);
                if(horarioRealSessao.isBefore(LocalTime.now())){
                    scanner.close();
                    throw new VendasException("Erro: o horário da sessão escolhida já passou");
                } 
                sessao = sessoesQuePossuemOFilme.get(sessaoEscolhida);
                break;
            }else{
                System.out.println("Número inválido");
                System.out.println("Digite novamente o número da sessão:");
                sessaoEscolhida = scanner.nextInt();
                scanner.nextLine();
            }
        }

        return sessao;
    }

    private void exibirSugestoesDeCadeiras(int qtdDeBilhetes, Cadeira[][] cadeiras){
        int qtdSugestoesEncontradas = 0;
        int limiteSugestoes = 3; // Mostraremos até 3 opções diferentes para o cliente
            // String[] listaSugestoesEncontradas = new String[3];
        String[] juncaoTemporaria = new String[qtdDeBilhetes];

        System.out.println("Sugestões de " + qtdDeBilhetes + " cadeiras juntas:");

        for(int i = 0; i < 10; i++){
            int contadorCadeirasjuntas = 0;
            for(int j = 0; j < 15; j++){
                if(qtdSugestoesEncontradas == limiteSugestoes){
                    break;
                }
                if(contadorCadeirasjuntas == qtdDeBilhetes){
                    String sugestaoCadeiras = String.join(", ", juncaoTemporaria);
                    // listaSugestoesEncontradas[qtdSugestoesEncontradas] = sugestaoCadeiras;
                    System.out.println(sugestaoCadeiras);
                    qtdSugestoesEncontradas += 1;
                    contadorCadeirasjuntas = 0;
                }
                if(!cadeiras[i][j].getEstaOcupada()){
                    juncaoTemporaria[contadorCadeirasjuntas] = cadeiras[i][j].getFileiraENumero();
                    contadorCadeirasjuntas += 1;
                }
            }
            if(qtdSugestoesEncontradas == limiteSugestoes){
                break;
            }
        }
    }

    private void escolherCadeiras(Sessao sessao, Scanner scanner, int qtdDeBilhetes, Sala[] salas) 
    throws VendasException{
        for(int i = 0; i < qtdDeBilhetes; i++){
            while(true){
                System.out.println("Digite a fileira e número da " + (i+1) + "ª cadeira (ex.:A14): ");
                String cadeiraEscolhida = scanner.nextLine();
                if(sessao.OcuparCadeira(cadeiraEscolhida)){
                    this.bilhetes[i] = new Bilhete(
                        String.valueOf(cliente.getUserId()), 
                        cliente.getCpf(), 
                        salas[sessao.getNumeroSala()-1], 
                        sessao,
                        sessao.getFilme(),
                        cadeiraEscolhida
                    );
                    System.out.println("Cadeira escolhida com sucesso!");
                    break;
                }else{
                    scanner.close();
                    throw new VendasException("Erro: Cadeira já ocuopada.");
                }    
            }
        }
    }

    private void exibirExtratoDaCompra(CupomPromocional cupom, Sala[] salas, Sessao sessao){
        System.out.println();
        System.out.println("Extrato da compra: ");
        double somaTotal = 0;
        for(int i = 0; i < itensLanchonete.size(); i++){
            System.out.println(itensLanchonete.get(i).getNome() + ": R$" + String.format("%.2f", itensLanchonete.get(i).getValor()));
            somaTotal += itensLanchonete.get(i).getValor(); 
        }
        for(int i = 0; i < 9; i++){
            if(this.bilhetes[i] == null){
                break;
            }

            if(this.cliente instanceof Estudante){
                System.out.println("Bilhete "+ (i+1) + ": R$" + String.format("%.2f",this.bilhetes[i].getValor()/2));
                somaTotal += this.bilhetes[i].getValor()/2;
            }else if(this.cliente instanceof Critico){
                System.out.println("Bilhete "+ (i+1) + ": R$0,00");
            }
            else{
                System.out.println("Bilhete "+ (i+1) + ": R$" + String.format("%.2f",this.bilhetes[i].getValor()));
                somaTotal += this.bilhetes[i].getValor();
            }
        }
        if (cupom != null) {
            somaTotal = somaTotal - somaTotal * (cupom.getPorcentagem() / 100.0);
        }
    
        System.out.println("Valor total: R$" + String.format("%.2f", somaTotal));

        for(int i = 0; i < salas[sessao.getNumeroSala()-1].getSessoes().length; i++){
            if(salas[sessao.getNumeroSala()-1].getSessoes()[i].getHorario().equals(sessao.getHorario())){
                salas[sessao.getNumeroSala()-1].cadastrarSessao(sessao, i);
                break;
            }
        }
        
        itensLanchonete.replaceAll(n -> null);
        for(int i = 0; i < 9; i++){
            if(this.bilhetes[i] == null){
                break;
            }

            this.bilhetes[i] = null;
        }
    }

    
}
