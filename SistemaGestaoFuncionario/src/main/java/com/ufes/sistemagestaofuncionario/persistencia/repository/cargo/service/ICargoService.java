package com.ufes.sistemagestaofuncionario.persistencia.repository.cargo.service;

import com.ufes.sistemagestaofuncionario.model.Cargo;
import java.sql.SQLException;
import java.util.List;

public interface ICargoService {
    List<Cargo> buscarTodos() throws ClassNotFoundException, SQLException;
    Cargo buscarPorNome(String nome) throws ClassNotFoundException, SQLException;
    boolean criar(Cargo cargo) throws ClassNotFoundException, SQLException; 
}
