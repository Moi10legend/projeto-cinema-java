public class Sala{
    private Sessao[] sessoes = new Sessao[5];
    private String[] horarios = {"13:00", "15:30", "18:00", "20:30", "23:00"};
    private double valor = 30.0;
    private int numeroSala;
    private Salas tipoSala;

    public Sala(int numeroSala, Salas tipoSala){
        this.numeroSala = numeroSala;
        this.tipoSala = tipoSala;
    }

    public void cadastrarSessao(Sessao sessao, int indiceSessoes){
        this.sessoes[indiceSessoes] = sessao;
        System.out.println("Sessão cadastrada com sucesso!");
    }

    public void exibirGradeDeSessoes(){
        System.out.println("Programação da sala " + this.numeroSala + "================");
        
        for(int i = 0; i < 5; i++){
            if(sessoes[i] != null){
                System.out.println(horarios[i] + ": " + sessoes[i]);
            }
        }
    }

    public Sessao[] getSessoes(){
        return this.sessoes;
    }

    public int getNumeroSala(){
        return this.numeroSala;
    }

    public void setNumeroSala(int numeroSala){
        this.numeroSala = numeroSala;
    }

    public double getValor(){
        return this.valor;
    }
    
    public Salas getTipoSala(){
        return this.tipoSala;
    }

    public void setTipoSala(Salas tipoSala){
        this.tipoSala = tipoSala;
    }
}