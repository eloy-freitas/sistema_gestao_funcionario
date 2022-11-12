package com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario;

import java.sql.SQLException;
import java.util.List;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.persistencia.dao.funcionario.FuncionarioDAO;
import java.sql.ResultSet;

public class FuncionarioRepository implements IFuncionarioRepository{

	private FuncionarioDAO funcionarioDAO;

	public FuncionarioRepository() throws SQLException, ClassNotFoundException{
		super();
		this.funcionarioDAO = new FuncionarioDAO();
	}

	@Override
	public boolean criar(Funcionario funcionario) throws ClassNotFoundException, SQLException {
		return funcionarioDAO.save(funcionario);
	}

	@Override
	public boolean excluir(Long id) throws SQLException {
		return funcionarioDAO.delete(id);
	}
	
	@Override
	public boolean atualizar(Funcionario funcionario) throws SQLException {
		return funcionarioDAO.update(funcionario);
	}

	@Override
	public Funcionario buscarPorId(Long id) throws ClassNotFoundException, SQLException {
		return funcionarioDAO.getById(id);
	}

	@Override
	public List<Funcionario> buscarTodos() throws ClassNotFoundException, SQLException {
		return funcionarioDAO.getAll();
	}

    @Override
    public ResultSet buscarFuncionarioBonus(Long id) throws ClassNotFoundException, SQLException {
            return funcionarioDAO.getFuncionarioBonus(id);
    }

    @Override
    public Funcionario buscarFuncionarioPorName(String nome) throws SQLException, ClassNotFoundException {
            return funcionarioDAO.getByName(nome);
    }
       
    
        
	
}
