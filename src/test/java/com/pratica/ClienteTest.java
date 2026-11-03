package com.pratica;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;


class ClienteTest {

    @Test
    void deveCriarClienteComDadosCorretos() {
        Cliente cliente = new Cliente(1, "Ana", 10);

        assertEquals(1, cliente.getId());
        assertEquals("Ana", cliente.getNome());
        assertEquals(10, cliente.getDesconto());
    }

    @Test
    void deveAlterarDesconto() {
        Cliente cliente = new Cliente(1, "Ana", 10);

        cliente.setDesconto(20);

        assertEquals(20, cliente.getDesconto());
    }

    @Test
    void deveRetornarClienteFormatado() {
        Cliente cliente = new Cliente(1, "Ana", 10);

        assertEquals("Ana(1)(10%)", cliente.toString());
    }

    @Test
    void deveTerGeneroNaoDefinidoPorPadrao() {
        Cliente cliente = new Cliente(1, "Ana", 10);

        assertEquals("nd", cliente.getGenero());
    }

    @Test
    void deveAceitarDescontoNosLimitesNoConstrutor() {
        assertEquals(0, new Cliente(1, "Ana", 0).getDesconto());
        assertEquals(100, new Cliente(2, "Bia", 100).getDesconto());
    }

    @Test
    void deveAceitarDescontoNosLimitesNoSetter() {
        Cliente cliente = new Cliente(1, "Ana", 10);

        cliente.setDesconto(0);
        assertEquals(0, cliente.getDesconto());

        cliente.setDesconto(100);
        assertEquals(100, cliente.getDesconto());
    }

    @Test
    void naoDeveCriarClienteComDescontoNegativo() {
        assertThrows(IllegalArgumentException.class, () -> new Cliente(1, "Ana", -1));
    }

    @Test
    void naoDeveCriarClienteComDescontoMaiorQueCem() {
        assertThrows(IllegalArgumentException.class, () -> new Cliente(1, "Ana", 101));
    }

    @Test
    void naoDeveAlterarParaDescontoInvalido() {
        Cliente cliente = new Cliente(1, "Ana", 10);

        assertThrows(IllegalArgumentException.class, () -> cliente.setDesconto(-5));
        assertThrows(IllegalArgumentException.class, () -> cliente.setDesconto(101));
        assertEquals(10, cliente.getDesconto());
    }
}
