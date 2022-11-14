package com.ufes.sistemagestaofuncionario.persistencia.dao.salario;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.persistencia.conexao.ConexaoPostgreSQL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;


public class SalarioDAO implements ISalarioDAO{
    
    Connection conexao;
    
    public SalarioDAO() throws ClassNotFoundException, SQLException{
        this.conexao = ConexaoPostgreSQL.getConnection();
    }
    
    @Override
    public boolean criar(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = null;
        
        try {
            String queryFuncionario = ""
                        .concat("\n INSERT INTO salario(")
                        .concat("\n id_funcionario")
                        .concat("\n , vl_salario_total")
                        .concat("\n , dt_modificacao) ")
                        .concat("\n VALUES (?, ?, ?);");
            
            ps = conexao.prepareStatement(queryFuncionario);
            ps.setLong(1, funcionario.getId());
            ps.setDouble(2, funcionario.getSalarioTotal());
            Date sqlDate = Date.valueOf(LocalDate.now());
            ps.setDate(3, sqlDate);
            
            ps.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao registrar o salário.\n" + ex.getMessage());
        } finally {
            ConexaoPostgreSQL.closeConnection(conexao, ps);
        }
        
    }
    
}
