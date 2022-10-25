package com.ufes.sistemagestaofuncionario.persistencia.dao.funcionario;

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

public class FuncionarioDAO implements IFuncionarioDAO{
    
    Connection conexao;
    
    public FuncionarioDAO() throws ClassNotFoundException, SQLException{
        this.conexao = ConexaoPostgreSQL.getConnection();
    }
    

    @Override
    public boolean save(Funcionario funcionario) throws SQLException, ClassNotFoundException{
        PreparedStatement ps = null;
        try {
            String query = "INSERT INTO funcionario(nm_funcionario, "
                + "vl_salario_base, vl_distancia_trabalho, vl_salario_total, "
                + "nu_idade, dt_admissao, id_cargo, dt_modificacao) VALUES "
                + "(?, ?, ?, ?, ?, ?, ?, ?);";
            ps = conexao.prepareStatement(query);
            ps.setString(1, funcionario.getNome());
            ps.setDouble(2, funcionario.getSalarioBase());
            ps.setDouble(3, funcionario.getDistanciaTrabalho());
            ps.setDouble(4, funcionario.getSalarioTotal());
            ps.setInt(5, funcionario.getIdade());
            // Convertendo local date para o formato utilizado pelo PreparedStatement
            Date sqlDate = Date.valueOf(funcionario.getDataAdmissao());
            ps.setDate(6, sqlDate);
            ps.setLong(7, funcionario.getCargo().getId());
            sqlDate = Date.valueOf(LocalDate.now());
            ps.setDate(8, sqlDate);
            
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao registrar o funcionário.\n" + ex.getMessage());
        } finally {
            ConexaoPostgreSQL.closeConnection(conexao, ps);
        }
    }

    @Override
    public boolean save(List<Funcionario> funcionarios) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = null;
        String query = "INSERT INTO funcionario(nm_funcionario, "
                + "vl_salario_base, vl_distancia_trabalho, vl_salario_total, "
                + "nu_idade, dt_admissao, id_cargo, dt_modificacao) VALUES "
                + "(?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            for(Funcionario f : funcionarios){
                ps = conexao.prepareStatement(query);
                ps.setString(1, f.getNome());
                ps.setDouble(2, f.getSalarioBase());
                ps.setDouble(3, f.getDistanciaTrabalho());
                ps.setDouble(4, f.getSalarioTotal());
                ps.setInt(5, f.getIdade());
                // Convertendo local date para o formato utilizado pelo PreparedStatement
                Date sqlDate = Date.valueOf(f.getDataAdmissao());
                ps.setDate(6, sqlDate);
                ps.setLong(7, f.getCargo().getId());
                sqlDate = Date.valueOf(LocalDate.now());
                ps.setDate(8, sqlDate);
                ps.executeUpdate();
            }
            return true;
         } catch (SQLException ex) {
            throw new SQLException("Erro ao registrar o funcionário.\n" + ex.getMessage());
        } finally {
            ConexaoPostgreSQL.closeConnection(conexao, ps);
        }
    }

    
    
    @Override
    public boolean update(Funcionario funcionario) throws SQLException {
        PreparedStatement ps = null;
        try{
            String query = "UPDATE funcionario "
                    + "SET nm_funcionario = ?"
                    + ", vl_salario_base = ?"
                    + ", vl_distancia_trabalho = ?"
                    + ", vl_salario_total = ?"
                    + ", nu_idade = ?"
                    + ", id_cargo = ?"
                    + ", dt_modificacao = ? "
                    + "WHERE id_funcionario = ?;";
            ps = conexao.prepareStatement(query);
            ps.setString(1, funcionario.getNome());
            ps.setDouble(2, funcionario.getSalarioBase());
            ps.setDouble(3, funcionario.getDistanciaTrabalho());
            ps.setDouble(4, funcionario.getSalarioTotal());
            ps.setInt(5, funcionario.getIdade());
            ps.setLong(6, funcionario.getCargo().getId());
            // convertendo LocalDate para Date (SQL)
            Date sqlDate = Date.valueOf(LocalDate.now());
            ps.setDate(7, sqlDate);
            ps.setLong(8, funcionario.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex){
            throw new SQLException("Erro ao atualizar o funcionário.\n"
                    + ex.getMessage());
        } finally {
            ConexaoPostgreSQL.closeConnection(conexao, ps);
        }
    }

    @Override
    public boolean delete(long id) throws SQLException{
        PreparedStatement ps = null;
        try{
            String query = "DELETE FROM funcionario WHERE id_funcionario = ?;";
            ps = conexao.prepareStatement(query);
            ps.setLong(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex){
            throw new SQLException("Erro durante exclusão do funcionário.\n"
                    + ex.getMessage());
        } finally {
            ConexaoPostgreSQL.closeConnection(conexao, ps);
        }
    }

    @Override
    public Funcionario getById(long id) throws SQLException, ClassNotFoundException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String query = "SELECT "
                    + "f.nm_funcionario"
                    + ", f.vl_salario_base"
                    + ", f.vl_distancia_trabalho"
                    + ", f.vl_salario_total"
                    + ", f.nu_idade"
                    + ", f.dt_admissao"
                    + ", f.id_cargo"
                    + ", c.nm_cargo "
                    + "FROM funcionario f "
                    + "INNER JOIN cargo c "
                    + "ON f.id_cargo = c.id_cargo "
                    + "WHERE f.id_funcionario = ?;";
            ps = conexao.prepareStatement(query);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if(!rs.next())
                throw new SQLException("Funcionário com id " 
                        + id + "não encontrado");
            String nome = rs.getString(1);
            double salarioBase = rs.getDouble(2);
            double distanciaTrabalho = rs.getDouble(3);
            double salarioTotal = rs.getDouble(4);
            int idade = rs.getInt(5);
            LocalDate dtAdmissao = new Date(rs.getDate(6)
                                                .getTime()).toLocalDate();
         
            // Dados do cargo
            long idCargo = rs.getLong(7);
            String nomeCargo = rs.getString(8);
            Cargo cargo = new Cargo(idCargo, nomeCargo);
            
            return new Funcionario(id, nome, cargo, salarioBase,
                distanciaTrabalho, dtAdmissao, idade, salarioTotal, 
                    null, null);
        } catch(SQLException ex) {
            throw new SQLException("Erro ao buscar funcionário.\n"
                    + ex.getMessage());
        } finally{
            ConexaoPostgreSQL.closeConnection(conexao, ps, rs);
        }
    }

    @Override
    public List<Funcionario> getAll() throws ClassNotFoundException, SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Funcionario> listaFuncionarios = new ArrayList();
        try{
            String query = "SELECT "
                    + "f.id_funcionario"
                    + ", f.nm_funcionario"
                    + ", f.vl_salario_base"
                    + ", f.vl_distancia_trabalho"
                    + ", f.vl_salario_total"
                    + ", f.nu_idade"
                    + ", f.dt_admissao"
                    + ", f.id_cargo"
                    + ", c.nm_cargo "
                    + "FROM funcionario f "
                    + "INNER JOIN cargo c "
                    + "ON f.id_cargo = c.id_cargo;";
            ps = conexao.prepareStatement(query);
            rs = ps.executeQuery();
            if(!rs.next())
                throw new SQLException("Não há funcionários cadastrados.");
            do{
                Long id_funcionario = rs.getLong(1);
                String nome = rs.getString(2);
                double salarioBase = rs.getDouble(3);
                double distanciaTrabalho = rs.getDouble(4);
                double salarioTotal = rs.getDouble(5);
                int idade = rs.getInt(6);
                LocalDate dtAdmissao = new Date(rs.getDate(7)
                                                    .getTime()).toLocalDate();

                // informações do cargo
                long idCargo = rs.getLong(8);
                String nomeCargo = rs.getString(9);
                Cargo cargo = new Cargo(idCargo, nomeCargo);
                
                listaFuncionarios.add(new Funcionario(id_funcionario, nome, 
                        cargo, salarioBase, distanciaTrabalho, 
                        dtAdmissao, idade, salarioTotal,
                        null, null));
            }while(rs.next());
            
        } catch(SQLException ex) {
            throw new SQLException("Erro ao buscar funcionários.\n"
                    + ex.getMessage());
        } finally{
            ConexaoPostgreSQL.closeConnection(conexao, ps, rs);
        }
        
        return listaFuncionarios;
    }
    
    
    
}
