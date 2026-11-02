package com.pratica;

import java.util.Locale;

public class ContaCliente {

    private int id;
    private Cliente cliente;
    private double saldo = 0.0;

    public ContaCliente(int id, Cliente cliente, double saldo) {
        this.id = id;
        this.cliente = cliente;
        this.saldo = saldo;
    }

    public ContaCliente(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
    }

    public ContaCliente(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double addDeposito(double valor) {
        if (valor <= 0) {
            System.out.println("Deposito invalido: o valor deve ser maior que zero.");
            return saldo;
        }
        saldo += valor;
        System.out.println("Novo saldo: R$" + formatar(saldo));
        return saldo;
    }

    public double subSaldo(double valor) {
        if (valor <= 0) {
            System.out.println("Saque invalido: o valor deve ser maior que zero.");
            return saldo;
        }
        if (valor > saldo) {
            System.out.println("Saldo insuficiente: saldo atual R$" + formatar(saldo));
            return saldo;
        }
        saldo -= valor;
        return saldo;
    }

    private String formatar(double valor) {
        return String.format(Locale.US, "%.2f", valor);
    }

    @Override
    public String toString() {
        String nome = (cliente == null) ? "sem cliente" : cliente.getNome();
        return nome + "(" + id + ") saldo=R$" + formatar(saldo);
    }
}
