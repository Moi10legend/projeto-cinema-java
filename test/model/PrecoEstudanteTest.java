package test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import model.PrecoEstudante;

class PrecoEstudanteTest {

    private final PrecoEstudante preco = new PrecoEstudante();

    @Test
    void deveCobrarMeiaEntrada() {
        assertEquals(15.0, preco.calcularPreco(30.0), 0.001);
    }

    @Test
    void deveMetadeComValorDecimal() {
        assertEquals(13.75, preco.calcularPreco(27.5), 0.001);
    }

    @Test
    void deveManterValorZero() {
        assertEquals(0.0, preco.calcularPreco(0.0), 0.001);
    }
}