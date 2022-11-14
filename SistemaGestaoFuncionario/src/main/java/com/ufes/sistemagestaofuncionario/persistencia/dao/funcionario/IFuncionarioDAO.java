package com.ufes.sistemagestaofuncionario.persistencia.dao.funcionario;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface IFuncionarioDAO {

    public long save(Funcionario funcionario) throws SQLException, ClassNotFoundException;

    public boolean save(List<Funcionario> funcionarios) throws SQLException, ClassNotFoundException;

    public boolean update(Funcionario funcionario) throws SQLException;

    public boolean delete(long id) throws SQLException;

    public Funcionario getById(long id) throws SQLException, ClassNotFoundException;

    public List<Funcionario> getAll() throws SQLException, ClassNotFoundException;

    public ResultSet getFuncionarioBonus(long id) throws SQLException, ClassNotFoundException;

    public Funcionario getByName(String nome) throws SQLException, ClassNotFoundException;

    public List<Funcionario> getAllBuscarFuncionarioView() throws SQLException, ClassNotFoundException;

    public ResultSet getSalarioCalculadoByDate(LocalDate data) throws SQLException, ClassNotFoundException;

    public ResultSet getAllSalarioCalculado() throws SQLException, ClassNotFoundException;
}
