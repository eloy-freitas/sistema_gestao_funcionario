package com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario.service;

import java.sql.SQLException;
import java.util.List;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import java.sql.ResultSet;

public interface IFuncionarioService {
    boolean salvar(Funcionario funcionario) throws ClassNotFoundException, SQLException;
    boolean atualizar(Funcionario funcionario) throws ClassNotFoundException, SQLException;
    boolean excluir(Long id) throws ClassNotFoundException, SQLException;
    Funcionario buscarPorId(Long id) throws ClassNotFoundException, SQLException;
    List<Funcionario> buscarTodos() throws ClassNotFoundException, SQLException;
    ResultSet buscarFuncionarioBonus(Long id) throws ClassNotFoundException, SQLException;
}
