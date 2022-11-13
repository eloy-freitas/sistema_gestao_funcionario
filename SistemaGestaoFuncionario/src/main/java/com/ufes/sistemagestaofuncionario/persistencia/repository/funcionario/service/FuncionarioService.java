package com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario.service;

import java.sql.SQLException;
import java.util.List;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario.FuncionarioRepository;
import com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario.IFuncionarioRepository;
import java.sql.ResultSet;
import java.time.LocalDate;

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

    @Override
    public List<Funcionario> buscarBuscarFuncionarioView() throws SQLException, ClassNotFoundException {
            return funcionarioRepository.buscarBuscarFuncionarioView();
    }

    @Override
    public ResultSet buscarSalarioCalculadoPorData(LocalDate data) throws SQLException, ClassNotFoundException {
            return funcionarioRepository.buscarSalarioCalculadoPorData(data);
    }

    @Override
    public ResultSet buscarTodosSalarioCalculado() throws SQLException, ClassNotFoundException {
        return funcionarioRepository.buscarTodosSalarioCalculado();
    }

    @Override
    public boolean incluirBonus(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        return funcionarioRepository.incluirBonus(funcionario);
    }

    @Override
    public boolean incluirFaltas(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        return funcionarioRepository.incluirFaltas(funcionario);
    }

}
