package com.ufes.sistemagestaofuncionario.model;

import java.time.LocalDate;


public class HistoricoBonus {
    private LocalDate dataCalculo;
    private String nomeCargo;
    private String tipoBonus;
    private Double valorBonus;

    public HistoricoBonus(LocalDate dataCalculo, String nomeCargo, String tipoBonus, Double valorBonus) {
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
