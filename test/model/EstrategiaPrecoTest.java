package test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import model.EstrategiaPreco;
import model.PrecoCritico;
import model.PrecoEstudante;
import model.PrecoInteira;

class EstrategiaPrecoTest {

    private static final double VALOR_BASE = 40.0;

    @Test
    void estrategiasImplementamAInterface() {
        assertTrue(new PrecoInteira() instanceof EstrategiaPreco);
        assertTrue(new PrecoEstudante() instanceof EstrategiaPreco);
        assertTrue(new PrecoCritico() instanceof EstrategiaPreco);
    }

    @Test
    void trocarEstrategiaMudaOResultado() {
        EstrategiaPreco estrategia = new PrecoInteira();
        assertEquals(40.0, estrategia.calcularPreco(VALOR_BASE), 0.001);

        estrategia = new PrecoEstudante();
        assertEquals(20.0, estrategia.calcularPreco(VALOR_BASE), 0.001);

        estrategia = new PrecoCritico();
        assertEquals(0.0, estrategia.calcularPreco(VALOR_BASE), 0.001);
    }

    @Test
    void estudantePagaMenosQueInteira() {
        double inteira = new PrecoInteira().calcularPreco(VALOR_BASE);
        double estudante = new PrecoEstudante().calcularPreco(VALOR_BASE);
        assertTrue(estudante < inteira);
    }
}