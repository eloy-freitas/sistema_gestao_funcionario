package com.ufes.sistemagestaofuncionario.persistencia.repository.cargo.service;

import com.ufes.sistemagestaofuncionario.model.Cargo;
import com.ufes.sistemagestaofuncionario.model.Funcionario;
import java.sql.SQLException;
import java.util.List;

public interface ICargoService {

    List<Cargo> buscarTodos() throws ClassNotFoundException, SQLException;

    Cargo buscarPorNome(String nome) throws ClassNotFoundException, SQLException;

    boolean criar(Funcionario funcionario) throws ClassNotFoundException, SQLException;

    boolean criar(List<Funcionario> funcionarios) throws ClassNotFoundException, SQLException;

    boolean atualizar(Funcionario funcionario) throws ClassNotFoundException, SQLException;

}
