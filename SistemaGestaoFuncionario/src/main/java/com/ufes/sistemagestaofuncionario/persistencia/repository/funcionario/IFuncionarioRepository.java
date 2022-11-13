package com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario;

import java.sql.SQLException;
import java.util.List;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import java.sql.ResultSet;

public interface IFuncionarioRepository {
	boolean criar(Funcionario funcionario) throws ClassNotFoundException, SQLException;

	boolean excluir(Long id) throws SQLException;

	boolean atualizar(Funcionario funcionario) throws SQLException;

	Funcionario buscarPorId(Long id) throws ClassNotFoundException, SQLException;

	List<Funcionario> buscarTodos() throws ClassNotFoundException, SQLException;

    ResultSet buscarFuncionarioBonus(Long id) throws ClassNotFoundException, SQLException;
        
    Funcionario buscarFuncionarioPorName(String nome) throws SQLException, ClassNotFoundException; 
    
    List<Funcionario> buscarBuscarFuncionarioView() throws SQLException, ClassNotFoundException; 

}