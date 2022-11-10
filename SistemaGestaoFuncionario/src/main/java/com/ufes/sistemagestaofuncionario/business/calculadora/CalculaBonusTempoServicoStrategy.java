package com.ufes.sistemagestaofuncionario.business.calculadora;

import com.ufes.sistemagestaofuncionario.model.Bonus;
import com.ufes.sistemagestaofuncionario.model.Funcionario;
import java.time.LocalDate;
import java.time.Period;

public class CalculaBonusTempoServicoStrategy extends CalculadoraBonusStrategy{

    public CalculaBonusTempoServicoStrategy(String nome) {
        super(nome);
    }
    
    @Override
    public Funcionario calcular(Funcionario funcionario) {
        LocalDate data = LocalDate.now();
        int valorTempoServico = Period
                .between(
                    funcionario.getDataAdmissao(),
                    data
                )
                .getYears();
        Bonus bonus = null;
        double valor = 0;
        
        System.out.println(valorTempoServico);
        if(valorTempoServico <= 0){
            bonus = new Bonus(getNome(), 0, data);
        }else if(valorTempoServico >= 1 && valorTempoServico <= 5){
            valor = funcionario.getSalarioBase() * 0.02;
            bonus = new Bonus(getNome(), valor, data);            
        }else if(valorTempoServico >= 6 && valorTempoServico <= 10){
            valor = funcionario.getSalarioBase() * 0.03;
            bonus = new Bonus(getNome(), valor, data);            
        }else if(valorTempoServico >= 11 && valorTempoServico <= 15){
            valor = funcionario.getSalarioBase() * 0.08;
            bonus = new Bonus(getNome(), valor, data);            
        }else if(valorTempoServico >= 16 && valorTempoServico <= 20){
            valor = funcionario.getSalarioBase() * 0.1;
            bonus = new Bonus(getNome(), valor, data);            
        }else{
            valor = funcionario.getSalarioBase() * 0.15;
            bonus = new Bonus(getNome(), valor, data);            
        }          
            
        funcionario.getBonusRecebidos().add(bonus);
        
        return funcionario;
        
    }
    
}
