package com.ufes.sistemagestaofuncionario.business.calculadora;

import com.ufes.sistemagestaofuncionario.model.Bonus;
import com.ufes.sistemagestaofuncionario.model.Funcionario;
import java.time.LocalDate;

public class CalculaBonusDistanciaTrabalho extends CalculadoraBonusStrategy{

    public CalculaBonusDistanciaTrabalho() {
        super("Bônus distância do trabalho");
    }

    @Override
    public Bonus calcular(Funcionario funcionario) {
        Bonus bonus = null;
        double valor = 0;
        double distancia = funcionario.getDistanciaTrabalho();
        LocalDate data = LocalDate.now();
        
        if(distancia >= 0 && distancia < 100){
            bonus = new Bonus(this.getNome(), valor, data);
        }else if(distancia >= 100 && distancia < 300){
            valor = 0.02;
            bonus = new Bonus(this.getNome(), valor, data);
        }else if(distancia >= 300 && distancia < 500){
            valor = 0.03;
            bonus = new Bonus(this.getNome(), valor, data);
        }else if(distancia >= 500 && distancia < 1000){
            valor = 0.04;
            bonus = new Bonus(this.getNome(), valor, data);
        }else{
            valor = 0.05;
            bonus = new Bonus(this.getNome(), valor, data);
        }
        
        return bonus;
        
    }
    
    
}
