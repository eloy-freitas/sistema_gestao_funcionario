package com.ufes.sistemagestaofuncionario.model;

import java.time.LocalDate;

public class Bonus {
    private String tipo;
    private double valor;
    private LocalDate data;

    public Bonus(String tipo, double valor, LocalDate data) {
        this.tipo = tipo;
        this.valor = valor;
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }
}
