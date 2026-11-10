package test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import model.PrecoCritico;

class PrecoCriticoTest {

    private final PrecoCritico preco = new PrecoCritico();

    @Test
    void criticoNaoPagaIngresso() {
        assertEquals(0.0, preco.calcularPreco(30.0), 0.001);
    }

    @Test
    void criticoNaoPagaMesmoComValorAlto() {
        assertEquals(0.0, preco.calcularPreco(500.0), 0.001);
    }
}