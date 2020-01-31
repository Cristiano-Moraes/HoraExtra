package com.moraes.mobile.horaextra.model;

public class Categoria {

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    private String nome;
    private double valor;

    public Categoria( String n, double v){
        nome = n;
        valor = v;
    }
    public Categoria (){ }
}
