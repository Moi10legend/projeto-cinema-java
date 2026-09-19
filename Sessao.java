public class Sessao {
    private Filme filme;
    private String horario;
    private Cadeira[][] cadeiras = new Cadeira[10][15];
    private int numeroSala;

    public Sessao(Filme filme, String horario, int numeroSala){
        this.filme = filme;
        this.horario = horario;
        this.numeroSala = numeroSala;
        inicializarCadeiras();
    }

    private void inicializarCadeiras(){
        for(int i = 0; i < 10; i++){
            char letraDaFila = (char) ('J'- i);

            for(int j = 0; j < 15; j++){
                String nomeCadeira = letraDaFila + String.valueOf(j+1);
                this.cadeiras[i][j] = new Cadeira(nomeCadeira);
            }
        }
    }

    public Filme getFilme(){
        return this.filme;
    }

    public void setFilme(Filme filme){
        this.filme = filme;
    }

    public String getHorario(){
        return this.horario;
    }

    public void setHorario(String horario){
        this.horario = horario;
    }

    public int getNumeroSala(){
        return this.numeroSala;
    }

    public void setNumeroSala(int numeroSala){
        this.numeroSala = numeroSala;
    }

    public Cadeira[][] getCadeiras(){
        return this.cadeiras;
    }

    public void exibirCadeiras(){
        for(int i = 0; i < 10; i++){
                for(int j = 0; j < 15; j++){
                    if(cadeiras[i][j].getEstaOcupada()){
                        System.out.print("❌ ");
                    }
                    else{
                        System.out.print(cadeiras[i][j].getFileiraENumero() + " ");
                    }
                }
                System.out.println();
            }
            System.out.println("-----------------------TELA-----------------------");
    }

    public boolean OcuparCadeira(String filaENumero){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 15; j++){
                if(this.cadeiras[i][j].getFileiraENumero().equals(filaENumero) && !this.cadeiras[i][j].getEstaOcupada()){
                    this.cadeiras[i][j].switchIsOcupada();
                    return true;
                }
            }
        }
        return false;
    }
}
