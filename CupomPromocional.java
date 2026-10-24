public enum CupomPromocional {
    SOCIOCINEMA(40),
    OCINEFOLO(60),
    GRANDEPODER(80);

    private final int porcentagem;

    CupomPromocional(int porcentagem){
        this.porcentagem = porcentagem;
    }

    public int getPorcentagem(){
        return this.porcentagem;
    }
}
