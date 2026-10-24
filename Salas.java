public enum Salas {
    SALACOMUM("Comum", 1),
    SALA3D("3D", 1.2),
    SALAXD("XD", 1.3),
    SALAXD_3D("XD_3D", 1.4);

    private final String tipo;
    private final double multiplicador;

    Salas(String tipo, double multiplicador){
        this.tipo = tipo;
        this.multiplicador = multiplicador;
    }

    public String getTipo(){
        return this.tipo;
    }

    public double getMultiplicador(){
        return this.multiplicador;
    }
}
