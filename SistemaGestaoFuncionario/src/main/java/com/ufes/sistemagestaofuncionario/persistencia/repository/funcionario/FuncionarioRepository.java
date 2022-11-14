package com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario;

import java.sql.SQLException;
import java.util.List;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.persistencia.dao.bonus.BonusCalculadoDAO;
import com.ufes.sistemagestaofuncionario.persistencia.dao.cargo.CargoDAO;
import com.ufes.sistemagestaofuncionario.persistencia.dao.funcionario.FuncionarioDAO;
import java.sql.ResultSet;
import java.time.LocalDate;
import com.ufes.sistemagestaofuncionario.persistencia.dao.bonus.IBonusCalculadoDAO;
import com.ufes.sistemagestaofuncionario.persistencia.dao.cargo.ICargoDAO;
import com.ufes.sistemagestaofuncionario.persistencia.dao.falta.FaltaDAO;
import com.ufes.sistemagestaofuncionario.persistencia.dao.falta.IFaltaDAO;
import com.ufes.sistemagestaofuncionario.persistencia.dao.funcionario.IFuncionarioDAO;

public class FuncionarioRepository implements IFuncionarioRepository {

    private IFuncionarioDAO funcionarioDAO;
    private ICargoDAO cargoDAO;
    private IBonusCalculadoDAO bonusCalculadoDAO;
    private IFaltaDAO faltaDAO;

    public FuncionarioRepository() throws SQLException, ClassNotFoundException {
        super();
    }

    private void abrirConexoes() throws ClassNotFoundException, SQLException {
        this.funcionarioDAO = new FuncionarioDAO();
        this.cargoDAO = new CargoDAO();
        this.bonusCalculadoDAO = new BonusCalculadoDAO();
        this.faltaDAO = new FaltaDAO();
    }

    @Override
    public boolean criar(Funcionario funcionario) throws ClassNotFoundException, SQLException {
        abrirConexoes();
        long id = funcionarioDAO.save(funcionario);
        funcionario.setId(id);
        return cargoDAO.save(funcionario);
    }

    @Override
    public boolean criar(List<Funcionario> funcionarios) throws ClassNotFoundException, SQLException {
        abrirConexoes();
        return funcionarioDAO.save(funcionarios);
    }
    
    

    @Override
    public boolean excluir(Long id) throws ClassNotFoundException, SQLException {
        abrirConexoes();
        return funcionarioDAO.delete(id);
    }

    @Override
    public boolean atualizar(Funcionario funcionario) throws ClassNotFoundException, SQLException {
        abrirConexoes();
        return funcionarioDAO.update(funcionario);
    }

    @Override
    public Funcionario buscarPorId(Long id) throws ClassNotFoundException, SQLException {
        abrirConexoes();
        return funcionarioDAO.getById(id);
    }

    @Override
    public List<Funcionario> buscarTodos() throws ClassNotFoundException, SQLException {
        abrirConexoes();
        return funcionarioDAO.getAll();
    }

    @Override
    public ResultSet buscarFuncionarioBonus(Long id) throws ClassNotFoundException, SQLException {
        abrirConexoes();
        return funcionarioDAO.getFuncionarioBonus(id);
    }

    @Override
    public Funcionario buscarFuncionarioPorName(String nome) throws SQLException, ClassNotFoundException {
        abrirConexoes();
        return funcionarioDAO.getByName(nome);
    }

    @Override
    public List<Funcionario> buscarBuscarFuncionarioView() throws SQLException, ClassNotFoundException {
        abrirConexoes();
        return funcionarioDAO.getAllBuscarFuncionarioView();
    }

    @Override
    public ResultSet buscarSalarioCalculadoPorData(LocalDate data) throws SQLException, ClassNotFoundException {
        abrirConexoes();
        return funcionarioDAO.getSalarioCalculadoByDate(data);
    }

    @Override
    public ResultSet buscarTodosSalarioCalculado() throws SQLException, ClassNotFoundException {
        abrirConexoes();
        return funcionarioDAO.getAllSalarioCalculado();
    }

    @Override
    public boolean incluirBonus(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        abrirConexoes();
        return bonusCalculadoDAO.save(funcionario);
    }

    @Override
    public boolean incluirFaltas(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        abrirConexoes();
        return faltaDAO.save(funcionario);
    }

}
