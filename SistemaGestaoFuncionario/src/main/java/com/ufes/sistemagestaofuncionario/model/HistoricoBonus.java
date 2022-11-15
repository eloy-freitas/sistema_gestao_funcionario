package com.ufes.sistemagestaofuncionario.model;

import java.time.LocalDate;


public class HistoricoBonus {
    private String nomeFuncionario;
    private LocalDate dataCalculo;
    private String nomeCargo;
    private String tipoBonus;
    private Double valorBonus;

    public HistoricoBonus(String nomeFuncionario, LocalDate dataCalculo, String nomeCargo, String tipoBonus, Double valorBonus) {
        this.nomeFuncionario = nomeFuncionario;
        this.dataCalculo = dataCalculo;
        this.nomeCargo = nomeCargo;
        this.tipoBonus = tipoBonus;
        this.valorBonus = valorBonus;
    }

    public LocalDate getDataCalculo() {
        return dataCalculo;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public String getTipoBonus() {
        return tipoBonus;
    }

    public Double getValorBonus() {
        return valorBonus;
    }
    
    
}
