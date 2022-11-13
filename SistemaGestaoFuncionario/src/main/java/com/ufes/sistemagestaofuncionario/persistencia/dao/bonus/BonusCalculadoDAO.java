package com.ufes.sistemagestaofuncionario.persistencia.dao.bonus;

import com.ufes.sistemagestaofuncionario.model.Bonus;
import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.persistencia.conexao.ConexaoPostgreSQL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class BonusCalculadoDAO implements IBonusCalculadoDAO{
    
    Connection conexao;

    public BonusCalculadoDAO() throws ClassNotFoundException, SQLException{
        this.conexao = ConexaoPostgreSQL.getConnection();
    }
    
    @Override
    public boolean save(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = null;
        String query = "insert into funcionario_bonus (id_funcionario, id_bonus, vl_bonus, dt_modificacao)\n"
                        + "values (?, (select id_bonus from bonus b where b.nm_bonus = ?), ?, ?);";
        try {
            if(!funcionario.getBonusRecebidos().isEmpty()){
                for(Bonus b : funcionario.getBonusRecebidos()){
                    ps = conexao.prepareStatement(query);
                    ps.setLong(1, funcionario.getId());
                    ps.setString(2, b.getTipo());
                    ps.setDouble(3, b.getValor());
                    ps.setDate(4, Date.valueOf(b.getData()));
                    ps.executeUpdate();
                }
                return true;
            }else
                return false;
            
            
         } catch (SQLException ex) {
            throw new SQLException("Erro ao registrar o funcionário.\n" + ex.getMessage());
        } finally {
            ConexaoPostgreSQL.closeConnection(conexao, ps);
        }

    }
    
}
