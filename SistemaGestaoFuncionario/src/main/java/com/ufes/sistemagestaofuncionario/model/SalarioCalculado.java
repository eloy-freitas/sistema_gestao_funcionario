/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sistemagestaofuncionario.model;

import java.time.LocalDate;

/**
 *
 * @author b1n
 */
public class SalarioCalculado {
    
    private long idFuncionario;
    private String nomeFuncionario;
    private LocalDate dataCalculo;
    private double salarioBase;
    private double totalBonus;
    private double salarioTotal;

    public SalarioCalculado(long idFuncionario, String nomeFuncionario, LocalDate dataCalculo, double salarioBase, double totalBonus, double salarioTotal) {
        this.idFuncionario = idFuncionario;
        this.nomeFuncionario = nomeFuncionario;
        this.dataCalculo = dataCalculo;
        this.salarioBase = salarioBase;
        this.totalBonus = totalBonus;
        this.salarioTotal = salarioTotal;
    }

    public long getIdFuncionario() {
        return idFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public LocalDate getDataCalculo() {
        return dataCalculo;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public double getTotalBonus() {
        return totalBonus;
    }

    public double getSalarioTotal() {
        return salarioTotal;
    }    
    
}
