
package com.ufes.sistemagestaofuncionario.service.calculadora;

import com.ufes.sistemagestaofuncionario.business.calculadora.CalculaBonusDistanciaTrabalho;
import com.ufes.sistemagestaofuncionario.business.calculadora.CalculaBonusTempoServicoStrategy;
import com.ufes.sistemagestaofuncionario.business.calculadora.CalculadoraBonusAssiduidadeStrategy;
import com.ufes.sistemagestaofuncionario.business.calculadora.CalculadoraBonusStrategy;
import com.ufes.sistemagestaofuncionario.model.Bonus;
import com.ufes.sistemagestaofuncionario.model.Funcionario;
import java.util.ArrayList;
import java.util.List;

public class CalculadoraBonusService {
    private List<CalculadoraBonusStrategy> metodosCalculoBonus;

    public CalculadoraBonusService() {
        metodosCalculoBonus = new ArrayList<>();
        metodosCalculoBonus.add(new CalculaBonusDistanciaTrabalho());
        metodosCalculoBonus.add(new CalculaBonusTempoServicoStrategy());
        metodosCalculoBonus.add(new CalculadoraBonusAssiduidadeStrategy());
    }
    
    public Funcionario calcular(Funcionario funcionario){
        Bonus bonus = null;
        for(CalculadoraBonusStrategy calculo: this.metodosCalculoBonus){
            bonus = calculo.calcular(funcionario);
            funcionario.getBonusRecebidos().add(bonus);
        }
        
        return funcionario;
    }
    
    
}
