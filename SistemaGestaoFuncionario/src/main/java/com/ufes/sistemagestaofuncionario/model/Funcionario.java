package com.ufes.sistemagestaofuncionario.model;

import java.time.LocalDate;
import java.util.List;

/*
    BR1 - Informações básicas do funcionário
*/
public class Funcionario {
    private long id;
    private String nome;
    private String cargo;
    private double salarioBase;
    private double distanciaTrabalho;
    private LocalDate dataAdmissao;
    private int idade;
    private double salarioTotal; // salarioBase + bonusRecebidos
    private List<Bonus> bonusRecebidos;
    private List<FaltaAoTrabalho> faltaAoTrabalho;
    
    public Funcionario(String nome, String cargo, double salarioBase, 
            double distanciaTrabalho, LocalDate dataAdmissao, int idade) {
        this.nome = nome;
        this.cargo = cargo;
        this.salarioBase = salarioBase;
        this.distanciaTrabalho = distanciaTrabalho;
        this.dataAdmissao = dataAdmissao;
        this.idade = idade;
        this.salarioTotal = 0;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCargo() {
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

    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public void setDistanciaTrabalho(double distanciaTrabalho) {
        this.distanciaTrabalho = distanciaTrabalho;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setSalarioTotal(double salarioTotal) {
        this.salarioTotal = salarioTotal;
    }

    public void setBonusRecebidos(List<Bonus> bonusRecebidos) {
        this.bonusRecebidos = bonusRecebidos;
    }

    public void setFaltaAoTrabalho(List<FaltaAoTrabalho> faltaAoTrabalho) {
        this.faltaAoTrabalho = faltaAoTrabalho;
    }
    
}
