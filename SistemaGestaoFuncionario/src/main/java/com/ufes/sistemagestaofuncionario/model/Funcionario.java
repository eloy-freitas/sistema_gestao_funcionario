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
    private double tempoServico;
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

    public Funcionario(long id, String nome, Cargo cargo, double salarioBase, double distanciaTrabalho,
                       LocalDate dataAdmissao, int idade, double salarioTotal,
                       List<Bonus> bonusRecebidos, List<FaltaAoTrabalho> faltaAoTrabalho, double tempoServico) {
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
        this.tempoServico = tempoServico;
    }
    
    public Funcionario(long id, String nome, Cargo cargo, double salarioBase, 
                       int idade) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.salarioBase = salarioBase;
        this.idade = idade;

    }
    
    public Funcionario(long id, String nome, Cargo cargo, double salarioBase, 
                       double distanciaTrabalho, double tempoServico, LocalDate dataAdmissao) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.salarioBase = salarioBase;
        this.distanciaTrabalho = idade;
        this.tempoServico = tempoServico;
        this.dataAdmissao = dataAdmissao;
    }

    public void setSalarioTotal(double salarioTotal) {
        this.salarioTotal = salarioTotal;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setBonusRecebidos(List<Bonus> bonusRecebidos) {
        this.bonusRecebidos = bonusRecebidos;
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

    public double getTempoServico() {
        return tempoServico;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public void setDistanciaTrabalho(double distanciaTrabalho) {
        this.distanciaTrabalho = distanciaTrabalho;
    }
    
    
}
