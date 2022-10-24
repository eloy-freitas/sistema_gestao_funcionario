package com.ufes.sistemagestaofuncionario.persistencia.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoPostgreSQL {
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Connection conexao = null;
        try {
            Class.forName("org.postgresql.Driver");
            try {
                conexao = DriverManager.getConnection(
                        "jdbc:postgresql://172.20.0.10:5432/funcionario_db",
                        "admin",
                        "admin_123");
                System.out.println("Conectado.");
            } catch (SQLException ex) {
                throw new SQLException("Falha na conexão com banco.\n" 
                        + ex.getMessage());
            }
        } catch (ClassNotFoundException ex) {
            throw new ClassNotFoundException("Classe não encontrada.\n" 
                    + ex.getMessage());
        }
        return conexao;
    }
    
    public static void closeConnection(Connection conexao) throws SQLException{
        if(conexao != null){
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new SQLException("Erro ao encerrar conexão.\n" + ex);
            }
        }
    }
    
    public static void closeConnection(Connection conexao, Statement statement) throws SQLException{
        if(conexao != null){
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new SQLException("Erro ao encerrar conexão.\n" + ex);
            }
        }
        if(statement != null){
            statement.close();
        }
    }
    
    public static void closeConnection(Connection conexao, Statement statement,
            ResultSet resultSet) throws SQLException{
        if(conexao != null){
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new SQLException("Erro ao encerrar conexão.\n" + ex);
            }
        }
        if(statement != null){
            statement.close();
        }
        if(resultSet != null){
            resultSet.close();
        }
    }
    
}
