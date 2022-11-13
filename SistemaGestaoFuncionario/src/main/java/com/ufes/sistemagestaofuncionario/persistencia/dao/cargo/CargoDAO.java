package com.ufes.sistemagestaofuncionario.persistencia.dao.cargo;

import com.ufes.sistemagestaofuncionario.model.Cargo;
import com.ufes.sistemagestaofuncionario.persistencia.conexao.ConexaoPostgreSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CargoDAO implements ICargoDAO{

    Connection conexao;
    
    public CargoDAO() throws ClassNotFoundException, SQLException{
        this.conexao = ConexaoPostgreSQL.getConnection();
    }
    
    @Override
    public boolean save(Cargo cargo) throws SQLException {
        PreparedStatement ps = null;
        try{
            String query = "INSERT INTO cargo (nm_cargo) "
                + "VALUES (?);";
            ps = conexao.prepareStatement(query);
            ps.setString(1, cargo.getNome());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex){
            throw new SQLException("Erro ao registrar o cargo.\n" + ex.getMessage());
        } finally {
            ConexaoPostgreSQL.closeConnection(conexao, ps);
        }
    }

    @Override
    public boolean delete(long id) throws SQLException {
        PreparedStatement ps = null;
        try{
            String query = "DELETE FROM cargo WHERE id_cargo = ?;";
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
                        + " até o momento");
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
