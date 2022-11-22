package com.ufes.sistemagestaofuncionario.persistencia.dao.bonus;

import com.ufes.sistemagestaofuncionario.model.Bonus;
import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.persistencia.conexao.ConexaoPostgreSQL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ListIterator;


public class BonusCalculadoDAO implements IBonusCalculadoDAO{
    
    Connection conexao;

    public BonusCalculadoDAO() throws ClassNotFoundException, SQLException{
        this.conexao = ConexaoPostgreSQL.getConnection();
    }
    
    @Override
    public boolean save(List<Funcionario> funcionarios) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = null;
        String query = "insert into funcionario_bonus ("
                + " id_funcionario" //1
                + ", id_bonus" //2
                + ", vl_bonus" //3 
                + ", dt_modificacao)\n" //4
                        + "values (?"
                + ", (select id_bonus from bonus b where b.nm_bonus = lower(?)), ?, ?);";
        try {
            ListIterator<Funcionario> itFuncionario 
                    = funcionarios.listIterator();
            
            while(itFuncionario.hasNext()){
                Funcionario funcionario = itFuncionario.next();
                ps = conexao.prepareStatement(query);
                ps.setLong(1, funcionario.getId());
                for(Bonus bonus : funcionario.getBonusRecebidos()){
                    ps.setString(2, bonus.getTipo());
                    ps.setDouble(3, bonus.getValor());
                    ps.setDate(4, Date.valueOf(LocalDate.now()));
                    ps.executeUpdate();
                }
            }
            return true;
         } catch (SQLException ex) {
            throw new SQLException("Erro ao registrar o bônus.\n" + ex.getMessage());
        } finally {
            ConexaoPostgreSQL.closeConnection(conexao, ps);
        }

    }
    
}
