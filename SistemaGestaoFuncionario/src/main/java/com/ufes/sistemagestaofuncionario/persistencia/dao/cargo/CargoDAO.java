package com.ufes.sistemagestaofuncionario.persistencia.dao.cargo;

import com.ufes.sistemagestaofuncionario.model.Cargo;
import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.persistencia.conexao.ConexaoPostgreSQL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CargoDAO implements ICargoDAO{

    Connection conexao;
    
    public CargoDAO() throws ClassNotFoundException, SQLException{
        this.conexao = ConexaoPostgreSQL.getConnection();
    }
    
    @Override
    public boolean save(Funcionario funcionario) throws SQLException {
        PreparedStatement ps = null;
        try{
            String query = ""
                .concat("\n INSERT INTO funcionario_cargo(")
                .concat("\n id_funcionario")
                .concat("\n, id_cargo")
                .concat("\n, dt_modificacao)")
                .concat("\n VALUES (?,") 
                .concat("(select id_cargo from cargo where nm_cargo = lower(?)), ?);");
            ps = conexao.prepareStatement(query);
            ps.setLong(1, funcionario.getId());
            ps.setString(2, funcionario.getCargo().getNome());
            ps.setDate(3, Date.valueOf(LocalDate.now()));
            ps.executeUpdate();
            return true;
        } catch (SQLException ex){
            throw new SQLException("Erro ao registrar o cargo.\n" + ex.getMessage());
        } finally {
            ConexaoPostgreSQL.closeConnection(conexao, ps);
        }
    }

    @Override
    public boolean save(List<Funcionario> funcionarios) throws SQLException {
        PreparedStatement ps = null;
        try{
            if(!funcionarios.isEmpty()){
                for(Funcionario f : funcionarios){
                    String query = ""
                        .concat("\n INSERT INTO funcionario_cargo(")
                        .concat("\n id_funcionario")
                        .concat("\n, id_cargo")
                        .concat("\n, dt_modificacao)")
                        .concat("\n VALUES (?,") 
                        .concat("(select id_cargo from cargo where nm_cargo = lower(?)), ?);");
                    ps = conexao.prepareStatement(query);
                    ps.setLong(1, f.getId());
                    ps.setString(2, f.getCargo().getNome());
                    ps.setDate(3, Date.valueOf(LocalDate.now()));
                    ps.executeUpdate();
                }
                return true;
            }else
                return false;
            
        } catch (SQLException ex){
            throw new SQLException("Erro ao registrar o cargo.\n" + ex.getMessage());
        } finally {
            ConexaoPostgreSQL.closeConnection(conexao, ps);
        }
    }

    @Override
    public boolean update(Funcionario funcionario) throws SQLException{
        PreparedStatement ps = null;
        String query = "UPDATE funcionario_cargo"
                + " SET id_cargo = ?, dt_modificacao = ?"
                + " WHERE id_funcionario = ?";
        try {
            ps = conexao.prepareStatement(query);
            ps.setLong(1, funcionario.getCargo().getId());
            System.out.println(funcionario.getCargo().getId());
            ps.setDate(2, Date.valueOf(LocalDate.now()));
            ps.setLong(3, funcionario.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }
    
    

    @Override
    public boolean delete(long id) throws SQLException {
        PreparedStatement ps = null;
        try{
            String query = "DELETE FROM funcionario_cargo WHERE id_funcionario = ?;";
            ps = conexao.prepareStatement(query);
            ps.setLong(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex){
            throw new SQLException("Erro ao excluir o cargo desejado.\n"
                    + ex.getMessage());
        } finally{
            ConexaoPostgreSQL.closeConnection(conexao, ps);
        }
    }

    @Override
    public Cargo getById(long id) throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String query = "SELECT nm_cargo FROM cargo WHERE id_cargo = ?;";
            ps = conexao.prepareStatement(query);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if(!rs.next())
                throw new SQLException("Nenhum cargo encontrado com id: " + id);
            String nome = rs.getString(1);
            
            return new Cargo(id, nome);
        } catch (SQLException ex){
            throw new SQLException("Erro ao buscar cargo.\n" + ex.getMessage());
        } finally {
            ConexaoPostgreSQL.closeConnection(conexao, ps, rs);
        }
    }

    @Override
    public Cargo getByNome(String nome) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String query = "SELECT * FROM cargo WHERE nm_cargo = ?;";
            ps = conexao.prepareStatement(query);
            ps.setString(1, nome);
            rs = ps.executeQuery();
            if(!rs.next())
                throw new SQLException("Nenhum cargo encontrado com nome: " + nome);
            long id = rs.getLong(1);       

            return new Cargo(id, nome);
        } catch (SQLException ex){
            throw new SQLException("Erro ao buscar cargo.\n" + ex.getMessage());
        } finally {
            ConexaoPostgreSQL.closeConnection(conexao, ps, rs);
        }
    }

    @Override
    public List<Cargo> getAll() throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Cargo> listaCargos = new ArrayList();
        try{
            String query = "SELECT * FROM cargo ORDER BY id_cargo;";
            ps = conexao.prepareStatement(query);
            rs = ps.executeQuery();
            if(!rs.next())
                throw new SQLException("Não há cargos cadastrados"
                        + "até o momento");
            do {
                long id = rs.getLong(1);
                String nome = rs.getString(2);
                listaCargos.add(new Cargo(id, nome));
            } while(rs.next());
        } catch (SQLException ex){
            throw new SQLException("Erro ao listar os cargos.\n" + ex.getMessage());
        }
        return listaCargos;
    }
    
}
