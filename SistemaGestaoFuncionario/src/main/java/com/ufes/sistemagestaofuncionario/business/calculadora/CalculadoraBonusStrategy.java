package com.ufes.sistemagestaofuncionario.business.calculadora;

import com.ufes.sistemagestaofuncionario.model.Bonus;
import com.ufes.sistemagestaofuncionario.model.Funcionario;

public abstract class CalculadoraBonusStrategy {
    private final String nome;

    public CalculadoraBonusStrategy(String nome) {
        this.nome = nome;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public abstract Bonus calcular(Funcionario funcionario);
    
}
