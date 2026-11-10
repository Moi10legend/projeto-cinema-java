package test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import model.PrecoInteira;

class PrecoInteiraTest {

    private final PrecoInteira preco = new PrecoInteira();

    @Test
    void deveCobrarValorCheio() {
        assertEquals(30.0, preco.calcularPreco(30.0), 0.001);
    }

    @Test
    void deveManterValorZero() {
        assertEquals(0.0, preco.calcularPreco(0.0), 0.001);
    }

    @Test
    void deveManterValorDecimal() {
        assertEquals(27.5, preco.calcularPreco(27.5), 0.001);
    }
}