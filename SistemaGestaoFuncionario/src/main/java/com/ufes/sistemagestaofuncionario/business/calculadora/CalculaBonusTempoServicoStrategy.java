package com.ufes.sistemagestaofuncionario.business.calculadora;

import com.ufes.sistemagestaofuncionario.model.Bonus;
import com.ufes.sistemagestaofuncionario.model.Funcionario;
import java.time.LocalDate;
import java.time.Period;

public class CalculaBonusTempoServicoStrategy extends CalculadoraBonusStrategy{

    public CalculaBonusTempoServicoStrategy() {
        super("Bônus por tempo de serviço");
    }
    
    @Override
    public Bonus calcular(Funcionario funcionario) {
        LocalDate data = LocalDate.now();
        double valorTempoServico = funcionario.getTempoServico();
        Bonus bonus = null;
        double valor = 0;
        
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
            
        return bonus;
    }
    
}
