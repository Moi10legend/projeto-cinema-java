public class Sessao {
    private Filme filme;
    private String horario;
    private Cadeira[][] cadeiras = new Cadeira[10][15];

    public Sessao(Filme filme, String horario){
        this.filme = filme;
        this.horario = horario;
        inicializarCadeiras();
    }

    private void inicializarCadeiras(){
        for(int i = 0; i < 10; i++){
            char letraDaFila = (char) ('A'+i);

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
}
