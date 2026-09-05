public class Usuario{
    private int userId;
    private String cpf;
    private String senha;
    private int idade;
    private Genero genero;
    private String email;
    private String nomeDoCartao;
    private String numeroDoCartao;
    private String codigoDeSegurancaCartao;

    public Usuario(int userId, String cpf, String senha, int idade, Genero genero, String email, String nomeDoCartao
        , String numeroDoCartao, String codigoDeSegurancaCartao){
            this.userId = userId;
            this.cpf = cpf;
            this.senha = senha;
            this.idade = idade;
            this.genero = genero;
            this.email = email;
            this.nomeDoCartao = nomeDoCartao;
            this.nomeDoCartao = numeroDoCartao;
            this.codigoDeSegurancaCartao = codigoDeSegurancaCartao;
        }

        public int getUserId(){
            return this.userId;
        }

        public void setUserId(int userId){
            this.userId = userId;
        }

        public String getCpf(){
            return this.cpf;
        }

        public void setCpf(String cpf){
            this.cpf = cpf;
        }

        public String getSenha(){
            return this.senha;
        }

        public void setSenha(String senha){
            this.senha = senha;
        }

        public int getIdade(){
            return this.idade;
        }

        public void setIdade(int idade){
            this.idade = idade;
        }

        public Genero getGenero(){
            return this.genero;
        }

        public void setGenero(Genero genero){
            this.genero = genero;
        }

        public String getEmail(){
            return this.email;
        }

        public void setEmail(String email){
            this.email = email;
        }

        public String getNomeDoCartao(){
            return this.nomeDoCartao;
        }

        public void setNomeDoCartao(String nomeDoCartao){
            this.nomeDoCartao = nomeDoCartao;
        }

        public String getnumeroDoCartao(){
            return this.numeroDoCartao;
        }

        public void setNumeroDoCartao(String numeroDoCartao){
            this.numeroDoCartao = numeroDoCartao;
        }

        public String getCodigoSegurancaCartao(){
            return this.codigoDeSegurancaCartao;
        }

        public void setCodigoSegurancaCartao(String codigoSegurancaCartao){
            this.codigoDeSegurancaCartao = codigoSegurancaCartao;
        }
}