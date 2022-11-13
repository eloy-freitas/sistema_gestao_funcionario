package com.ufes.sistemagestaofuncionario.persistencia.repository.cargo;

import com.ufes.sistemagestaofuncionario.model.Cargo;
import java.sql.SQLException;
import java.util.List;

public interface ICargoRepository {
    List<Cargo> buscarTodos() throws ClassNotFoundException, SQLException;
    Cargo buscarPorNome(String nome) throws SQLException, ClassNotFoundException;
    boolean criar(Cargo cargo) throws SQLException, ClassNotFoundException; 
}
