package com.pratica;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FaturaClienteTest {

    @Test
    void deveCriarFaturaComDadosCorretos() {
        Cliente cliente = new Cliente(1, "Ana", 10);
        FaturaCliente fatura = new FaturaCliente(100, cliente, 200.0);

        assertEquals(100, fatura.getId());
        assertEquals(cliente, fatura.getCliente());
        assertEquals(200.0, fatura.getValor(), 0.001);
    }

    @Test
    void deveCriarFaturaSomenteComId() {
        FaturaCliente fatura = new FaturaCliente(100);

        assertEquals(100, fatura.getId());
        assertNull(fatura.getCliente());
        assertEquals(0.0, fatura.getValor(), 0.001);
    }

    @Test
    void deveAlterarCliente() {
        FaturaCliente fatura = new FaturaCliente(100);
        Cliente cliente = new Cliente(1, "Ana", 10);

        fatura.setCliente(cliente);

        assertEquals(cliente, fatura.getCliente());
    }

    @Test
    void deveAlterarValor() {
        FaturaCliente fatura = new FaturaCliente(100);

        fatura.setValor(300.0);

        assertEquals(300.0, fatura.getValor(), 0.001);
    }

    @Test
    void deveCalcularValorComDesconto() {
        Cliente cliente = new Cliente(1, "Ana", 10);
        FaturaCliente fatura = new FaturaCliente(100, cliente, 200.0);

        assertEquals(180.0, fatura.getValorComDesconto(), 0.001);
    }

    @Test
    void deveCalcularValorSemDesconto() {
        Cliente cliente = new Cliente(1, "Ana", 0);
        FaturaCliente fatura = new FaturaCliente(100, cliente, 200.0);

        assertEquals(200.0, fatura.getValorComDesconto(), 0.001);
    }

    @Test
    void deveRetornarFaturaFormatada() {
        Cliente cliente = new Cliente(1, "Ana", 10);
        FaturaCliente fatura = new FaturaCliente(100, cliente, 200.0);

        assertEquals(
            "Fatura [id=100, cliente=Ana(1)(10%), valor=200.0 ]",
            fatura.toString()
        );
    }
}