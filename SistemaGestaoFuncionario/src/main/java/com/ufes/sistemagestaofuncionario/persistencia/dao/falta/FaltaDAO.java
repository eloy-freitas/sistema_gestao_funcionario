package com.ufes.sistemagestaofuncionario.persistencia.dao.falta;

import com.ufes.sistemagestaofuncionario.model.FaltaAoTrabalho;
import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.persistencia.conexao.ConexaoPostgreSQL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;


public class FaltaDAO implements IFaltaDAO{
    
    private Connection conexao;
    
    public FaltaDAO() throws ClassNotFoundException, SQLException{
        this.conexao = ConexaoPostgreSQL.getConnection();
    }
    

    @Override
    public boolean save(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        PreparedStatement psFuncionario = null;
        
        try {
            String queryFuncionario = ""
                .concat("\n INSERT INTO funcionario_falta(")
                .concat("\n id_funcionario")
                .concat("\n , qt_faltas")
                .concat("\n , dt_vigencia")
                .concat("\n , dt_modificacao)")
                .concat("\n VALUES (?, ?, ?, ?);");
            if(funcionario.getFaltaAoTrabalho().isEmpty())
                throw new SQLException("Funcionário sem faltas cadastradas");
            else
                for(FaltaAoTrabalho fat : funcionario.getFaltaAoTrabalho()){
                    psFuncionario = conexao.prepareStatement(queryFuncionario);
                    psFuncionario.setLong(1, funcionario.getId());
                    psFuncionario.setInt(2, fat.getQuantidade());
                    psFuncionario.setDate(3, Date.valueOf(fat.getMes()));
                    psFuncionario.setDate(4, Date.valueOf(LocalDate.now()));
                    psFuncionario.executeUpdate();
                }
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao registrar o funcionário.\n" + ex.getMessage());
        } finally {
            ConexaoPostgreSQL.closeConnection(conexao, psFuncionario);
        }
    }
    
}   
