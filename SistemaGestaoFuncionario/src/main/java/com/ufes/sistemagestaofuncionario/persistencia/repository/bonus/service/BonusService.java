package com.ufes.sistemagestaofuncionario.persistencia.repository.bonus.service;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.persistencia.repository.bonus.BonusRepository;
import com.ufes.sistemagestaofuncionario.persistencia.repository.bonus.IBonusRepository;
import java.sql.SQLException;
import java.util.List;

public class BonusService implements IBonusService{

    private IBonusRepository bonusRepository;
    
    public BonusService(){
        this.bonusRepository = new BonusRepository();
    }
    
    @Override
    public boolean adicionar(List<Funcionario> funcionarios) throws ClassNotFoundException, SQLException {
        return bonusRepository.adicionar(funcionarios);
    }
    
}
