package com.ufes.sistemagestaofuncionario.persistencia.dao.cargo;

import com.ufes.sistemagestaofuncionario.model.Cargo;
import com.ufes.sistemagestaofuncionario.model.Funcionario;
import java.sql.SQLException;
import java.util.List;

public interface ICargoDAO {

    public boolean save(Funcionario funcionario) throws SQLException;

    public boolean save(List<Funcionario> funcionarios) throws SQLException;

    public boolean delete(long id) throws SQLException;

    public Cargo getById(long id) throws SQLException;

    public Cargo getByNome(String nome) throws SQLException;

    public List<Cargo> getAll() throws SQLException;
    
    public boolean update(Funcionario funcionario) throws SQLException;

}
