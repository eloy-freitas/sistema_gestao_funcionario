package com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario;

import java.sql.SQLException;
import java.util.List;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.persistencia.dao.cargo.CargoDAO;
import com.ufes.sistemagestaofuncionario.persistencia.dao.funcionario.FuncionarioDAO;
import java.sql.ResultSet;
import java.time.LocalDate;

public class FuncionarioRepository implements IFuncionarioRepository{

	private FuncionarioDAO funcionarioDAO;
        private CargoDAO cargoDAO;

	public FuncionarioRepository() throws SQLException, ClassNotFoundException{
		super();
		this.funcionarioDAO = new FuncionarioDAO();
                this.cargoDAO = new CargoDAO();
	}

	@Override
	public boolean criar(Funcionario funcionario) throws ClassNotFoundException, SQLException {
		return funcionarioDAO.save(funcionario) && cargoDAO.save(funcionario); 
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

    @Override
    public List<Funcionario> buscarBuscarFuncionarioView() throws SQLException, ClassNotFoundException {
            return funcionarioDAO.getAllBuscarFuncionarioView();
    }

    @Override
    public ResultSet buscarSalarioCalculadoPorData(LocalDate data) throws SQLException, ClassNotFoundException {
        return funcionarioDAO.getSalarioCalculadoByDate(data);
    }

    @Override
    public ResultSet buscarTodosSalarioCalculado() throws SQLException, ClassNotFoundException {
        return funcionarioDAO.getAllSalarioCalculado();
    }
}
