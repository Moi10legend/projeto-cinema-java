import java.util.ArrayList;
// import java.util.List;
import java.util.Scanner;

public class Compra {
    private Usuario cliente;
    private String[] itensLanchonete;
    private double[] valoresItensLanchonete;
    private Bilhete[] bilhetes;
    
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

    public void setItensLanchonete(String[] itensLanchonete){
        this.itensLanchonete = itensLanchonete;
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
            for(int j = 0; j<salas[i].getSessoes().length; i++){
                Sessao sessaoAtual = salas[i].getSessoes()[j];
                if(sessaoAtual.getFilme().getNome() == nomeFilme){
                    sessoesQuePossuemOFilme.add(salas[i].getSessoes()[j]);
                    possuiSessao = true;
                }
            }
        }
        System.out.println("Sessões disponíveis para o filme " + nomeFilme + ":");
        for(int i = 0; i < sessoesQuePossuemOFilme.size(); i++){
            System.out.println(i + ": Sala: " + sessoesQuePossuemOFilme.get(i).getNumeroSala() + " Horário: " + sessoesQuePossuemOFilme.get(i).getHorario());
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o número da sessão que deseja comprar os bilhetes:");
        int sessaoEscolhida = scanner.nextInt();

        

    }
}
