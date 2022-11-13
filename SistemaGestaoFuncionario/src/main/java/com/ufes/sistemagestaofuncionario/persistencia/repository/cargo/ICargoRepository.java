package com.ufes.sistemagestaofuncionario.persistencia.repository.cargo;

import com.ufes.sistemagestaofuncionario.model.Cargo;
import com.ufes.sistemagestaofuncionario.model.Funcionario;
import java.sql.SQLException;
import java.util.List;

public interface ICargoRepository {

    List<Cargo> buscarTodos() throws ClassNotFoundException, SQLException;

    Cargo buscarPorNome(String nome) throws SQLException, ClassNotFoundException;

    boolean criar(Funcionario funcionario) throws SQLException, ClassNotFoundException;
}
