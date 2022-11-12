package com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario.service;

import java.sql.SQLException;
import java.util.List;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario.FuncionarioRepository;
import com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario.IFuncionarioRepository;
import java.sql.ResultSet;

public class FuncionarioService implements IFuncionarioService{
	
	private IFuncionarioRepository funcionarioRepository;
	
	public FuncionarioService() throws ClassNotFoundException, SQLException {
		this.funcionarioRepository = new FuncionarioRepository();
	}

	@Override
	public boolean salvar(Funcionario funcionario) throws ClassNotFoundException, SQLException {
		funcionarioRepository.criar(funcionario);
		return false;
	}

	@Override
	public boolean atualizar(Funcionario funcionario) throws SQLException {
		return funcionarioRepository.atualizar(funcionario);
	}

	@Override
	public boolean excluir(Long id) throws SQLException {
		return funcionarioRepository.excluir(id);
	}

	@Override
	public Funcionario buscarPorId(Long id) throws ClassNotFoundException, SQLException {
		return funcionarioRepository.buscarPorId(id);
	}

	@Override
	public List<Funcionario> buscarTodos() throws ClassNotFoundException, SQLException {
		return funcionarioRepository.buscarTodos();
	}

    @Override
    public ResultSet buscarFuncionarioBonus(Long id) throws ClassNotFoundException, SQLException {
            return funcionarioRepository.buscarFuncionarioBonus(id);
    }

    @Override
    public Funcionario buscarFuncionarioPorName(String nome) throws SQLException, ClassNotFoundException {
            return funcionarioRepository.buscarFuncionarioPorName(nome);
    }
    
    

}
