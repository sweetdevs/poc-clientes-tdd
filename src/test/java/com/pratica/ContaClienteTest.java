package com.pratica;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContaClienteTest {

    private static final double DELTA = 0.001;

    private final PrintStream saidaOriginal = System.out;
    private ByteArrayOutputStream saidaCapturada;

    @BeforeEach
    void capturarSaida() {
        saidaCapturada = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saidaCapturada));
    }

    @AfterEach
    void restaurarSaida() {
        System.setOut(saidaOriginal);
    }

    private String saida() {
        return saidaCapturada.toString();
    }

    // ---------- construtores, getters e setters ----------

    @Test
    void deveCriarContaComSaldoInicial() {
        Cliente cliente = new Cliente(1, "Ana", 10);

        ContaCliente conta = new ContaCliente(7, cliente, 500.0);

        assertEquals(7, conta.getId());
        assertSame(cliente, conta.getCliente());
        assertEquals(500.0, conta.getSaldo(), DELTA);
    }

    @Test
    void deveCriarContaComSaldoZeroPorPadrao() {
        ContaCliente conta = new ContaCliente(7, new Cliente(1, "Ana", 10));

        assertEquals(0.0, conta.getSaldo(), DELTA);
    }

    @Test
    void deveCriarContaSomenteComId() {
        ContaCliente conta = new ContaCliente(3);

        assertEquals(3, conta.getId());
        assertNull(conta.getCliente());
        assertEquals(0.0, conta.getSaldo(), DELTA);
    }

    @Test
    void deveAlterarCliente() {
        ContaCliente conta = new ContaCliente(1);
        Cliente cliente = new Cliente(2, "Bruno", 5);

        conta.setCliente(cliente);

        assertSame(cliente, conta.getCliente());
    }

    @Test
    void deveAlterarSaldo() {
        ContaCliente conta = new ContaCliente(1, new Cliente(1, "Ana", 10), 100.0);

        conta.setSaldo(250.5);

        assertEquals(250.5, conta.getSaldo(), DELTA);
    }

    // ---------- addDeposito ----------

    @Test
    void deveDepositarEAtualizarSaldo() {
        ContaCliente conta = new ContaCliente(1, new Cliente(1, "Ana", 10), 100.0);

        double novoSaldo = conta.addDeposito(50.0);

        assertEquals(150.0, novoSaldo, DELTA);
        assertEquals(150.0, conta.getSaldo(), DELTA);
    }

    @Test
    void deveMostrarNovoSaldoAoDepositar() {
        ContaCliente conta = new ContaCliente(1, new Cliente(1, "Ana", 10), 100.0);

        conta.addDeposito(50.0);

        assertTrue(saida().contains("150.00"));
    }

    @Test
    void naoDeveDepositarValorNegativo() {
        ContaCliente conta = new ContaCliente(1, new Cliente(1, "Ana", 10), 100.0);

        double saldo = conta.addDeposito(-10.0);

        assertEquals(100.0, saldo, DELTA);
        assertEquals(100.0, conta.getSaldo(), DELTA);
        assertTrue(saida().contains("invalido"));
    }

    @Test
    void naoDeveDepositarValorZero() {
        ContaCliente conta = new ContaCliente(1, new Cliente(1, "Ana", 10), 100.0);

        assertEquals(100.0, conta.addDeposito(0.0), DELTA);
    }

    // ---------- subSaldo ----------

    @Test
    void deveSacarValorMenorQueOSaldo() {
        ContaCliente conta = new ContaCliente(1, new Cliente(1, "Ana", 10), 100.0);

        double novoSaldo = conta.subSaldo(30.0);

        assertEquals(70.0, novoSaldo, DELTA);
        assertEquals(70.0, conta.getSaldo(), DELTA);
    }

    @Test
    void devePermitirSacarTodoOSaldo() {
        ContaCliente conta = new ContaCliente(1, new Cliente(1, "Ana", 10), 100.0);

        assertEquals(0.0, conta.subSaldo(100.0), DELTA);
    }

    @Test
    void naoDeveSacarValorMaiorQueOSaldoEDeveAvisar() {
        ContaCliente conta = new ContaCliente(1, new Cliente(1, "Ana", 10), 100.0);

        double saldo = conta.subSaldo(100.01);

        assertEquals(100.0, saldo, DELTA);
        assertEquals(100.0, conta.getSaldo(), DELTA);
        assertTrue(saida().contains("insuficiente"));
    }

    @Test
    void naoDeveSacarDeContaSemSaldo() {
        ContaCliente conta = new ContaCliente(1);

        assertEquals(0.0, conta.subSaldo(10.0), DELTA);
        assertTrue(saida().contains("insuficiente"));
    }

    @Test
    void naoDeveSacarValorNegativo() {
        ContaCliente conta = new ContaCliente(1, new Cliente(1, "Ana", 10), 100.0);

        double saldo = conta.subSaldo(-20.0);

        assertEquals(100.0, saldo, DELTA);
        assertEquals(100.0, conta.getSaldo(), DELTA);
        assertTrue(saida().contains("invalido"));
    }

    // ---------- toString ----------

    @Test
    void deveRetornarContaFormatada() {
        ContaCliente conta = new ContaCliente(7, new Cliente(1, "Ana", 10), 150.0);

        assertEquals("Ana(7) saldo=R$150.00", conta.toString());
    }

    @Test
    void deveArredondarSaldoParaDuasCasasNoToString() {
        ContaCliente conta = new ContaCliente(7, new Cliente(1, "Ana", 10), 1234.567);

        assertEquals("Ana(7) saldo=R$1234.57", conta.toString());
    }

    @Test
    void deveRetornarContaFormatadaMesmoSemCliente() {
        ContaCliente conta = new ContaCliente(9);

        assertEquals("sem cliente(9) saldo=R$0.00", conta.toString());
    }
}
