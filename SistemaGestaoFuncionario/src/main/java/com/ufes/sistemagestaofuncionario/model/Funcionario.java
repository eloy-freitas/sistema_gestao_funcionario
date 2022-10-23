package com.ufes.sistemagestaofuncionario.model;

import java.time.LocalDate;
import java.util.List;

/*
    BR1 - Informações básicas do funcionário
*/
public class Funcionario {
    private long id;
    private String nome;
    private Cargo cargo;
    private double salarioBase;
    private double distanciaTrabalho;
    private LocalDate dataAdmissao;
    private int idade;
    private double salarioTotal; // salarioBase + bonusRecebidos
    private List<Bonus> bonusRecebidos;
    private List<FaltaAoTrabalho> faltaAoTrabalho;
    
    public Funcionario(String nome, Cargo cargo, double salarioBase, 
            double distanciaTrabalho, LocalDate dataAdmissao, int idade) {
        this.nome = nome;
        this.cargo = cargo;
        this.salarioBase = salarioBase;
        this.distanciaTrabalho = distanciaTrabalho;
        this.dataAdmissao = dataAdmissao;
        this.idade = idade;
        this.salarioTotal = 0;
    }

    public Funcionario(long id, String nome, Cargo cargo, double salarioBase, double distanciaTrabalho, LocalDate dataAdmissao, int idade, double salarioTotal, List<Bonus> bonusRecebidos, List<FaltaAoTrabalho> faltaAoTrabalho) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.salarioBase = salarioBase;
        this.distanciaTrabalho = distanciaTrabalho;
        this.dataAdmissao = dataAdmissao;
        this.idade = idade;
        this.salarioTotal = salarioTotal;
        this.bonusRecebidos = bonusRecebidos;
        this.faltaAoTrabalho = faltaAoTrabalho;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public double getDistanciaTrabalho() {
        return distanciaTrabalho;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public int getIdade() {
        return idade;
    }

    public double getSalarioTotal() {
        return salarioTotal;
    }

    public List<Bonus> getBonusRecebidos() {
        return bonusRecebidos;
    }

    public List<FaltaAoTrabalho> getFaltaAoTrabalho() {
        return faltaAoTrabalho;
    }
    
}
