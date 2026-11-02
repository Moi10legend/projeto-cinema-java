package model;

public class PrecoEstudante implements EstrategiaPreco {
    public double calcularPreco(double valorBase) { return valorBase / 2.0; }
}
