package com.pratica;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
}