package com.ufes.sistemagestaofuncionario.persistencia.repository.bonus;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.persistencia.dao.bonus.BonusCalculadoDAO;
import com.ufes.sistemagestaofuncionario.persistencia.dao.bonus.IBonusCalculadoDAO;
import java.sql.SQLException;
import java.util.List;

public class BonusRepository implements IBonusRepository{

    private IBonusCalculadoDAO bonusCalculadoDAO;
    
    public BonusRepository(){
        
    }
    
    private void abrirConexao() throws ClassNotFoundException, SQLException{
        this.bonusCalculadoDAO = new BonusCalculadoDAO();
    }
    
    @Override
    public boolean adicionar(List<Funcionario> funcionarios) throws ClassNotFoundException, SQLException{
        abrirConexao();
        return bonusCalculadoDAO.save(funcionarios);
    }
    
    
    
}
