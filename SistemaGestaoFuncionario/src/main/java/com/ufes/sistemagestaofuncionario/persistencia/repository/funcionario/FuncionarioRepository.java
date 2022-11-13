package com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario;

import java.sql.SQLException;
import java.util.List;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.persistencia.dao.funcionario.FuncionarioDAO;
import com.ufes.sistemagestaofuncionario.persistencia.dao.funcionario.IFuncionarioDAO;
import java.sql.ResultSet;

public class FuncionarioRepository implements IFuncionarioRepository{

	private IFuncionarioDAO funcionarioDAO;

	public FuncionarioRepository() {
            super();
	}
        
        private void abrirConexao() throws SQLException, ClassNotFoundException{
            this.funcionarioDAO = new FuncionarioDAO();
        }

	@Override
	public boolean criar(Funcionario funcionario) throws ClassNotFoundException, SQLException {
                abrirConexao();
		return funcionarioDAO.save(funcionario);
	}

	@Override
	public boolean excluir(Long id) throws SQLException, ClassNotFoundException{
            abrirConexao();
            return funcionarioDAO.delete(id);
	}
	
	@Override
	public boolean atualizar(Funcionario funcionario) throws SQLException, ClassNotFoundException {
            abrirConexao();
            return funcionarioDAO.update(funcionario);
	}

	@Override
	public Funcionario buscarPorId(Long id) throws ClassNotFoundException, SQLException {
            abrirConexao();
            return funcionarioDAO.getById(id);
	}

	@Override
	public List<Funcionario> buscarTodos() throws ClassNotFoundException, SQLException {
            abrirConexao();
            return funcionarioDAO.getAll();
	}

        @Override
        public ResultSet buscarFuncionarioBonus(Long id) throws ClassNotFoundException, SQLException {
            abrirConexao();
            return funcionarioDAO.getFuncionarioBonus(id);
        }
        
        
	
}
