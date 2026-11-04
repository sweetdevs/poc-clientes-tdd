package com.pratica;

public class Cliente {

    private int id;
    private String nome;
    private int desconto;
    private String genero = "nd";

    public Cliente(int id, String nome, int desconto) {
        this.id = id;
        this.nome = nome;
        if (desconto < 0 || desconto > 100) {
            throw new IllegalArgumentException("Desconto deve estar entre 0 e 100");
        }
        this.desconto = desconto;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getGenero() {
        return genero;
    }

    public int getDesconto() {
        return desconto;
    }

    public void setDesconto(int desconto) {
        if (desconto < 0 || desconto > 100) {
            throw new IllegalArgumentException("Desconto deve estar entre 0 e 100");
        }
        this.desconto = desconto;
    }

    @Override
    public String toString() {
        return nome + "(" + id + ")(" + desconto + "%)";
    }
}