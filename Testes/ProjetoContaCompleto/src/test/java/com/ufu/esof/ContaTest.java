
package com.ufu.esof;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContaTest {

    private Conta conta;

    @BeforeEach
    public void setUp() {
        conta = new Conta();
    }

    @Test
    public void testDepositoTransicaoParaOuro() {
        conta.depositar(1500);
        assertEquals("OURO", conta.getEstado());
    }

    @Test
    public void testDepositoComRendimentoOuro() {
        conta.depositar(1500);
        float saldoEsperado = 1500 + (1500 * 0.05f);
        assertEquals(saldoEsperado, conta.getSaldo(), 0.01);
    }

    @Test
    public void testSaqueComTaxaEstadoPrata() {
        conta.depositar(1000);
        boolean resultado = conta.sacar(100);
        assertTrue(resultado);
        assertEquals(898.0f, conta.getSaldo(), 0.01);
    }

    @Test
    public void testSaqueNegadoEstadoVermelho() {
        conta.depositar(500);
        conta.sacar(600);
        assertEquals("VERMELHO", conta.getEstado());
        assertFalse(conta.sacar(50));
    }

    @Test
    public void testRetornoParaEstadoPrata() {
        conta.depositar(1500);
        conta.sacar(600);
        assertEquals("PRATA", conta.getEstado());
    }
}
