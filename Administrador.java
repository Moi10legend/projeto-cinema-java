public class Administrador extends Base implements GerenciaDeFilmes{
    private double salario;
    private int ID;

    public Administrador(int idade, String nome, String email, double salario, int ID){
        super(idade, nome, email);
        this.salario = salario;
        this.ID = ID;
    }

    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }

    public void adicionarUsuario(){

    }

    public void alterarUsuario(){

    }

    public void excluirUsuario(){

    }

    public void incluirFilme(){

    }
    public void excluirFilme(){

    }
    public void alterarFilme(){
        
    }
}
