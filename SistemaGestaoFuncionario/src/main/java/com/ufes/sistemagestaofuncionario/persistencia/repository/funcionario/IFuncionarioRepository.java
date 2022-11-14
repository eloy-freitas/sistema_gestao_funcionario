package com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario;

import java.sql.SQLException;
import java.util.List;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import java.sql.ResultSet;
import java.time.LocalDate;

public interface IFuncionarioRepository {

    boolean criar(Funcionario funcionario) throws ClassNotFoundException, SQLException;
    
    boolean criar(List<Funcionario> funcionarios) throws ClassNotFoundException, SQLException;
    
    boolean excluir(Long id) throws ClassNotFoundException, SQLException;

    boolean atualizar(Funcionario funcionario) throws ClassNotFoundException, SQLException;

    Funcionario buscarPorId(Long id) throws ClassNotFoundException, SQLException;

    List<Funcionario> buscarTodos() throws ClassNotFoundException, SQLException;

    ResultSet buscarFuncionarioBonus(Long id) throws ClassNotFoundException, SQLException;

    List<Funcionario> buscarFuncionarioPorName(String nome) throws SQLException, ClassNotFoundException;

    List<Funcionario> buscarBuscarFuncionarioView() throws SQLException, ClassNotFoundException;

    ResultSet buscarSalarioCalculadoPorData(LocalDate data) throws SQLException, ClassNotFoundException;

    ResultSet buscarTodosSalarioCalculado() throws SQLException, ClassNotFoundException;

    boolean incluirBonus(Funcionario funcionario) throws SQLException, ClassNotFoundException;

    boolean incluirFaltas(Funcionario funcionario) throws SQLException, ClassNotFoundException;

}
