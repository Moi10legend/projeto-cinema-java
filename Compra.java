import java.util.ArrayList;
// import java.util.List;
import java.util.Scanner;

public class Compra {
    private Usuario cliente;
    private ArrayList<ItemLanchonete> itensLanchonete = new ArrayList<ItemLanchonete>();
    private Bilhete[] bilhetes = new Bilhete[9];
    
    public Compra(Usuario cliente, String[] itensLanchonete, double[] valoresItensLanchonete){
        this.cliente = cliente;
        this.itensLanchonete = itensLanchonete;
        this.valoresItensLanchonete = valoresItensLanchonete;
    }

    public Usuario getCliente(){
        return this.cliente;
    }

    public void setCliente(Usuario cliente){
        this.cliente = cliente;
    }

    public String[] getItensLanchonete(){
        return this.itensLanchonete;
    }

    public void adicionarUmItemDaLanchonete(ItemLanchonete item){
        this.itensLanchonete.add(item);
    }

    public double[] getValoresItensLanchonete(){
        return this.valoresItensLanchonete;
    }

    public void setValoresItensLanchonete(double[] valores){
        this.valoresItensLanchonete = valores;
    }

    public void compraDeBilhetes(Sala[] salas, String nomeFilme, int qtdDeBilhetes){
        boolean possuiSessao = false;
        ArrayList<Sessao> sessoesQuePossuemOFilme = new ArrayList<>();
        for(int i = 0; i < salas.length; i++){
            for(int j = 0; j<salas[i].getSessoes().length; j++){
                Sessao sessaoAtual = salas[i].getSessoes()[j];
                if(sessaoAtual.getFilme().getNome().equals(nomeFilme) ){
                    sessoesQuePossuemOFilme.add(salas[i].getSessoes()[j]);
                    possuiSessao = true;
                }
            }
        }
        if (!possuiSessao){
            System.out.println("O filme requerido não possui sessões.");
            return;
        }
        System.out.println("Sessões disponíveis para o filme " + nomeFilme + ":");
        for(int i = 0; i < sessoesQuePossuemOFilme.size(); i++){
            System.out.println(i + ": Sala: " + sessoesQuePossuemOFilme.get(i).getNumeroSala() + " Horário: " + sessoesQuePossuemOFilme.get(i).getHorario());
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o número da sessão que deseja comprar os bilhetes:");
        int sessaoEscolhida = scanner.nextInt();

        if(qtdDeBilhetes > 1){
            Sessao sessao = sessoesQuePossuemOFilme.get(sessaoEscolhida);
            Cadeira[][] cadeiras = sessao.getCadeiras();
            int sugestoesEncontradas = 0;
            int limiteSugestoes = 3; // Mostraremos até 3 opções diferentes para o cliente

            System.out.println("Sugestões de " + qtdDeBilhetes + " cadeiras juntas:");
        }

    }
}
