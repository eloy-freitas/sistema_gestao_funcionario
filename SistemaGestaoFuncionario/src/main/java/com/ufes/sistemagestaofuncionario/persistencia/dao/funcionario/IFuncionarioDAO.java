package com.ufes.sistemagestaofuncionario.persistencia.dao.funcionario;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import java.sql.SQLException;
import java.util.List;

public interface IFuncionarioDAO {
    public boolean save(Funcionario funcionario) throws SQLException, ClassNotFoundException;
    public boolean save(List<Funcionario> funcionarios) throws SQLException, ClassNotFoundException;
    public boolean update(Funcionario funcionario) throws SQLException;
    public boolean delete(long id) throws SQLException;
    public Funcionario getById(long id) throws SQLException, ClassNotFoundException;
    public List<Funcionario> getAll() throws ClassNotFoundException, SQLException;
}
