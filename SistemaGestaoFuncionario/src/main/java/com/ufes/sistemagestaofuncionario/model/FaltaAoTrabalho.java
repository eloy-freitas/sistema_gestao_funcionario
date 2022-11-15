package com.ufes.sistemagestaofuncionario.model;

import java.time.LocalDate;

public class FaltaAoTrabalho {
    private int quantidade;
    private LocalDate mes;

    public FaltaAoTrabalho(int quantidade, LocalDate mes) {
        this.quantidade = quantidade;
        this.mes = mes;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public LocalDate getMes() {
        return mes;
    }
    
    
}
