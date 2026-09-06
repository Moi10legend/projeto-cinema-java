// import java.util.ArrayList;
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
        for(int i = 0; i < salas.length; i++){
            for(int j = 0; j<salas[i].getSessoes().length; i++){
                if(salas[i].getSessoes()[j].getFilme().getNome() == nomeFilme){
                    System.out.println();
                }
            }
        }
        Scanner scanner = new Scanner(System.in);


    }
}
