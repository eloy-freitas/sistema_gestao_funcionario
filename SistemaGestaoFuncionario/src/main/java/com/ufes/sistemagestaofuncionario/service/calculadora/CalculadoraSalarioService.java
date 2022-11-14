package com.ufes.sistemagestaofuncionario.service.calculadora;

import com.ufes.sistemagestaofuncionario.model.Bonus;
import com.ufes.sistemagestaofuncionario.model.Funcionario;


public class CalculadoraSalarioService {

    public CalculadoraSalarioService() {
    }
    
    
    public Funcionario calcularSalario(Funcionario funcionario) throws Exception{
        if(funcionario == null)
            throw new Exception("Funcionário inválido");
        double salario = funcionario.getSalarioBase();
        double valor = 0;
        for(Bonus b: funcionario.getBonusRecebidos()){
            valor += (b.getValor() * salario);
        }
        
        funcionario.setSalarioTotal(valor + salario);
        
        return funcionario;
    }
}
