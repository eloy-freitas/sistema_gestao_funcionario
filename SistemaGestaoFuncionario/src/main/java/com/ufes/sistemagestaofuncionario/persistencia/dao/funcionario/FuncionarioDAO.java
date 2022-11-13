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
        PreparedStatement psFuncionario = null;
        
        try {
            String queryFuncionario = "INSERT INTO funcionario(nm_funcionario, "
                + "vl_salario_base, vl_distancia_trabalho "
                + "nu_idade, dt_admissao, dt_modificacao) VALUES "
                + "(?, ?, ?, ?, ?, ?);";
            
            psFuncionario = conexao.prepareStatement(queryFuncionario);
            psFuncionario.setString(1, funcionario.getNome());
            psFuncionario.setDouble(2, funcionario.getSalarioBase());
            psFuncionario.setDouble(3, funcionario.getDistanciaTrabalho());
            psFuncionario.setInt(4, funcionario.getIdade());
            // Convertendo local date para o formato utilizado pelo PreparedStatement
            Date sqlDate = Date.valueOf(funcionario.getDataAdmissao());
            psFuncionario.setDate(5, sqlDate);
            sqlDate = Date.valueOf(LocalDate.now());
            psFuncionario.setDate(6, sqlDate);
            
            psFuncionario.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao registrar o funcionário.\n" + ex.getMessage());
        } finally {
            ConexaoPostgreSQL.closeConnection(conexao, psFuncionario);
        }
    }

    @Override
    public boolean save(List<Funcionario> funcionarios) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = null;
        String query = ""
            .concat("\n INSERT INTO funcionario(")
            .concat("\n id_funcionario")
            .concat("\n , nm_funcionario")
            .concat("\n , vl_salario_base")
            .concat("\n , vl_distancia_trabalho")
            .concat("\n , vl_tempo_servico")
            .concat("\n , dt_admissao")
            .concat("\n , dt_modificacao)")
            .concat("\n VALUES (?, ?, ?, ?, ?, ?, ?);");
        try {
            for(Funcionario f : funcionarios){
                ps = conexao.prepareStatement(query);
                ps.setLong(1, f.getId());
                ps.setString(2, f.getNome());
                ps.setDouble(3, f.getSalarioBase());
                ps.setDouble(4, f.getDistanciaTrabalho());
                ps.setDouble(5, f.getTempoServico());
                Date sqlDate = Date.valueOf(f.getDataAdmissao());
                ps.setDate(6, sqlDate);
                sqlDate = Date.valueOf(LocalDate.now());
                ps.setDate(7, sqlDate);
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

    @Override
    public ResultSet getFuncionarioBonus(long id) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = null;
        ResultSet result = null;
        try{
            String query = "" 
                .concat("select ")
                .concat("\n 	fb.dt_modificacao" )
                .concat("\n 	, c.nm_cargo" )
                .concat("\n 	, b.nm_bonus" )
                .concat("\n 	, fb.vl_bonus" )
                .concat("\n from funcionario f" )
                .concat("\n inner join funcionario_bonus fb" )
                .concat("\n 	on f.id_funcionario = fb.id_funcionario" )
                .concat("\n inner join bonus b" )
                .concat("\n 	on fb.id_bonus = b.id_bonus" )
                .concat("\n inner join funcionario_cargo fc" )
                .concat("\n 	on f.id_funcionario = fc.id_funcionario" )
                .concat("\n inner join cargo c" )
                .concat("\n 	on fc.id_cargo = c.id_cargo" )
                .concat("\n where f.id_funcionario = ?;");
            ps = conexao.prepareStatement(query);
            ps.setLong(1, id);
            result = ps.executeQuery();  
        } catch(SQLException ex) {
            throw new SQLException("Erro ao buscar funcionários.\n"
                    + ex.getMessage());
        } finally{
            ConexaoPostgreSQL.closeConnection(conexao, ps, result);
        }
        
        return result;
        
    }

    @Override
    public Funcionario getByName(String nome) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = null;
        ResultSet result = null;
        Funcionario funcionario = null;
        try{
            String query = "" 
                .concat("\n with cargo_atual as (")
                .concat("\n		select ")
                .concat("\n			f.id_funcionario ")
                .concat("\n			, fc.id_cargo ")
                .concat("\n			, max(fc.dt_modificacao) dt_modificacao ")
                .concat("\n		from funcionario f ")
                .concat("\n		inner join funcionario_cargo fc ")
                .concat("\n			on f.id_funcionario = fc.id_funcionario ")
                .concat("\n		group by f.id_funcionario ")
                .concat("\n			, fc.id_cargo ")
                .concat("\n )")
                .concat("\n	select ")
                .concat("\n		f.id_funcionario")
                .concat("\n		, f.nm_funcionario")
                .concat("\n		, f.nu_idade")
                .concat("\n		, c.nm_cargo ")
                .concat("\n		, f.vl_salario_base")
                .concat("\n	from funcionario f ")
                .concat("\n	inner join cargo_atual fc ")
                .concat("\n		on f.id_funcionario = fc.id_funcionario ")
                .concat("\n	inner join cargo c ")
                .concat("\n		on fc.id_cargo = c.id_cargo ")
                .concat("\n	where f.nm_funcionario = ?;");
            ps = conexao.prepareStatement(query);
            ps.setString(1, nome);
            result = ps.executeQuery();  
            if(!result.next())
                throw new SQLException("Funcionário com nome " 
                        + nome + "não encontrado");
            int idFuncionario = result.getInt(1);
            String nomeFuncionario = result.getString(2);
            int idade = result.getInt(3);
            String nomeCargo = result.getString(4);
            double salarioBase = result.getDouble(5);
         
            Cargo cargo = new Cargo(nomeCargo);
            
            funcionario = new Funcionario(
                    idFuncionario, 
                    nomeFuncionario, 
                    cargo, 
                    salarioBase, 
                    idade
            );
            
            return funcionario;
        } catch(SQLException ex) {
            throw new SQLException("Erro ao buscar funcionários.\n"
                    + ex.getMessage());
        } finally{
            ConexaoPostgreSQL.closeConnection(conexao, ps, result);
        }
        
    }

    @Override
    public List<Funcionario> getAllBuscarFuncionarioView() throws SQLException, ClassNotFoundException {
        PreparedStatement ps = null;
        ResultSet result = null;
        List<Funcionario> funcionarios = new ArrayList<>();
        try{
            String query = "" 
                .concat("\n with cargo_atual as (")
                .concat("\n		select ")
                .concat("\n			f.id_funcionario ")
                .concat("\n			, fc.id_cargo ")
                .concat("\n			, max(fc.dt_modificacao) dt_modificacao ")
                .concat("\n		from funcionario f ")
                .concat("\n		inner join funcionario_cargo fc ")
                .concat("\n			on f.id_funcionario = fc.id_funcionario ")
                .concat("\n		group by f.id_funcionario ")
                .concat("\n			, fc.id_cargo ")
                .concat("\n )")
                .concat("\n	select ")
                .concat("\n		f.id_funcionario")
                .concat("\n		, f.nm_funcionario")
                .concat("\n		, f.nu_idade")
                .concat("\n		, c.nm_cargo ")
                .concat("\n		, f.vl_salario_base")
                .concat("\n	from funcionario f ")
                .concat("\n	inner join cargo_atual fc ")
                .concat("\n		on f.id_funcionario = fc.id_funcionario ")
                .concat("\n	inner join cargo c ")
                .concat("\n		on fc.id_cargo = c.id_cargo;");
            ps = conexao.prepareStatement(query);

            result = ps.executeQuery();  
            if(!result.next())
                throw new SQLException("Não há funcionários cadastrados.");
            do{
                int idFuncionario = result.getInt(1);
                String nomeFuncionario = result.getString(2);
                int idade = result.getInt(3);
                String nomeCargo = result.getString(4);
                double salarioBase = result.getDouble(5);

                Cargo cargo = new Cargo(nomeCargo);
                
                funcionarios.add(
                    new Funcionario(
                        idFuncionario, 
                        nomeFuncionario, 
                        cargo, 
                        salarioBase, 
                        idade
                    )
                );
            }while(result.next());
            
            return funcionarios;
        } catch(SQLException ex) {
            throw new SQLException("Erro ao buscar funcionários.\n"
                    + ex.getMessage());
        } finally{
            ConexaoPostgreSQL.closeConnection(conexao, ps, result);
        }
    
    }

    @Override
    public ResultSet getSalarioCalculadoByDate(LocalDate data) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = null;
        ResultSet result = null;
        try{
            String query = "" 
                .concat("\n select ")
                .concat("\n     f.nm_funcionario ")
                .concat("\n     , s.dt_modificacao")
                .concat("\n     , f.vl_salario_base ")
                .concat("\n     , sum(fb.vl_bonus) vl_bonus")
                .concat("\n     , s.vl_salario_total ")
                .concat("\n from funcionario f ")
                .concat("\n left join salario s ")
                .concat("\n     on f.id_funcionario = s.id_funcionario ")
                .concat("\n left join funcionario_bonus fb ")
                .concat("\n     on f.id_funcionario = fb.id_funcionario ")
                .concat("\n where s.dt_modificacao = ?")
                .concat("\n     and fb.dt_modificacao = ?")
                .concat("\n group by f.nm_funcionario ")
                .concat("\n     , s.dt_modificacao")
                .concat("\n     , f.vl_salario_base ")
                .concat("\n     , s.vl_salario_total;");
            
            ps = conexao.prepareStatement(query);
            ps.setDate(1, Date.valueOf(data));
            ps.setDate(2, Date.valueOf(data));
            result = ps.executeQuery();  
            return result;
            
        } catch(SQLException ex) {
            throw new SQLException("Erro ao buscar funcionários.\n"
                    + ex.getMessage());
        } finally{
            ConexaoPostgreSQL.closeConnection(conexao, ps, result);
        }
    }

    @Override
    public ResultSet getAllSalarioCalculado() throws SQLException, ClassNotFoundException {
        PreparedStatement ps = null;
        ResultSet result = null;
        try{
            String query = "" 
                .concat("\n select ")
                .concat("\n     f.nm_funcionario ")
                .concat("\n     , s.dt_modificacao")
                .concat("\n     , f.vl_salario_base ")
                .concat("\n     , sum(fb.vl_bonus) vl_bonus")
                .concat("\n     , s.vl_salario_total ")
                .concat("\n from funcionario f ")
                .concat("\n left join salario s ")
                .concat("\n     on f.id_funcionario = s.id_funcionario ")
                .concat("\n left join funcionario_bonus fb ")
                .concat("\n     on f.id_funcionario = fb.id_funcionario ")
                .concat("\n group by f.nm_funcionario ")
                .concat("\n     , s.dt_modificacao")
                .concat("\n     , f.vl_salario_base ")
                .concat("\n     , s.vl_salario_total;");
            
            ps = conexao.prepareStatement(query);
            result = ps.executeQuery();  
            return result;
            
        } catch(SQLException ex) {
            throw new SQLException("Erro ao buscar funcionários.\n"
                    + ex.getMessage());
        } finally{
            ConexaoPostgreSQL.closeConnection(conexao, ps, result);
        }      
    }
}
