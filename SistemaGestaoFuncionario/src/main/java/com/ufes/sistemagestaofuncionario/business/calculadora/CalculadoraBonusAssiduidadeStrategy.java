package com.ufes.sistemagestaofuncionario.business.calculadora;

import com.ufes.sistemagestaofuncionario.model.Bonus;
import com.ufes.sistemagestaofuncionario.model.FaltaAoTrabalho;
import com.ufes.sistemagestaofuncionario.model.Funcionario;
import java.time.LocalDate;

public class CalculadoraBonusAssiduidadeStrategy extends CalculadoraBonusStrategy{

    public CalculadoraBonusAssiduidadeStrategy() {
        super("Bônus por assiduidade");
    }

    
    @Override
    public Bonus calcular(Funcionario funcionario) {
        int quantidade = 0;
        Bonus bonus = null;
        LocalDate data = LocalDate.now();
        if(!funcionario.getFaltaAoTrabalho().isEmpty()){
            for(FaltaAoTrabalho fat: funcionario.getFaltaAoTrabalho()){
                if(fat.getMes().equals(data))
                    quantidade = fat.getQuantidade();
                    break;
            }

            double valor = 0;

            if(quantidade == 0){
                valor = 0.05;
                bonus = new Bonus(this.getNome(), valor, data); 
            }else if(quantidade >= 1 && quantidade <= 3){
                valor = 0.03;
                bonus = new Bonus(this.getNome(), valor, data); 
            }else if(quantidade >= 4 && quantidade <= 5){
                valor = 0.01;
                bonus = new Bonus(this.getNome(), valor, data); 
            }else{
                bonus = new Bonus(this.getNome(), valor, data); 
}        }else
            bonus = new Bonus(getNome(), 0, data);
        
        return bonus;
    }
    
    
    
}
