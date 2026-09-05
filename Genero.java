public enum Genero{
    M("Masculino"),
    F("Feminino");

    private final String generoCompleto;

    Genero(String generoCompleto){
        this.generoCompleto = generoCompleto;
    }

    public String getGenero(){
        return this.generoCompleto;
    }
}