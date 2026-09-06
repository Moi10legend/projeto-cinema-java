public class Cadeira {
    private String fileiraENumero;
    private boolean estaOcupada = false;

    public Cadeira(String fileiraENumero){
        this.fileiraENumero = fileiraENumero;
    }

    public String getFileiraENumero(){
        return this.fileiraENumero;
    }

    public void setFileiraENumero(String fileiraENumero){
        this.fileiraENumero = fileiraENumero;
    }

    public boolean getEstaOcupada(){
        return this.estaOcupada;
    }

    public void switchIsOcupada(){
        this.estaOcupada = !this.estaOcupada;
    }
}
