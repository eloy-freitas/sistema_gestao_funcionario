package com.ufes.sistemagestaofuncionario.persistencia.dao.funcionario;

import com.ufes.sistemagestaofuncionario.model.Bonus;
import com.ufes.sistemagestaofuncionario.model.Cargo;
import com.ufes.sistemagestaofuncionario.model.FaltaAoTrabalho;
import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.model.HistoricoBonus;
import com.ufes.sistemagestaofuncionario.model.SalarioCalculado;
import com.ufes.sistemagestaofuncionario.persistencia.conexao.ConexaoPostgreSQL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO implements IFuncionarioDAO {

    Connection conexao;

    public FuncionarioDAO() throws ClassNotFoundException, SQLException {
        this.conexao = ConexaoPostgreSQL.getConnection();
    }

    @Override
    public long save(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        PreparedStatement psFuncionario = null;
        ResultSet rsFuncionario = null;
        try {
            String queryFuncionario = "INSERT INTO funcionario("
                    + "nm_funcionario"
                    + ", vl_salario_base"
                    + ", vl_distancia_trabalho "
                    + ", nu_idade"
                    + ", dt_admissao"
                    + ", dt_modificacao) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?, ?)"
                    + "RETURNING id_funcionario;";

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

            rsFuncionario = psFuncionario.executeQuery();

            if (!rsFuncionario.next()) {
                throw new SQLException("Funcion??rio n??o cadastrado.");
            }
            return rsFuncionario.getLong(1);

        } catch (SQLException ex) {
            throw new SQLException("Erro ao registrar o funcion??rio.\n" + ex.getMessage());
        } finally {
            ConexaoPostgreSQL.closeConnection(conexao, psFuncionario);
        }
    }

    @Override
    public boolean save(List<Funcionario> funcionarios) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = null;
        String query = ""
                .concat("\n INSERT INTO funcionario(")
                .concat("\n nm_funcionario")
                .concat("\n , vl_salario_base")
                .concat("\n , vl_distancia_trabalho")
                .concat("\n , vl_tempo_servico")
                .concat("\n , dt_admissao")
                .concat("\n , dt_modificacao)")
                .concat("\n VALUES (?, ?, ?, ?, ?, ?);");
        try {
            for (Funcionario f : funcionarios) {
                ps = conexao.prepareStatement(query);
                ps.setString(1, f.getNome());
                ps.setDouble(2, f.getSalarioBase());
                ps.setDouble(3, f.getDistanciaTrabalho());
                ps.setDouble(4, f.getTempoServico());
                Date sqlDate = Date.valueOf(f.getDataAdmissao());
                ps.setDate(5, sqlDate);
                sqlDate = Date.valueOf(LocalDate.now());
                ps.setDate(6, sqlDate);
                ps.executeUpdate();
            }
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao registrar o funcion??rio.\n" + ex.getMessage());
        } finally {
            ConexaoPostgreSQL.closeConnection(conexao, ps);
        }
    }

    @Override
    public boolean update(Funcionario funcionario) throws SQLException {
        PreparedStatement ps = null;
        try {
            String query = "UPDATE funcionario "
                    + "SET nm_funcionario = ?"
                    + ", vl_salario_base = ?"
                    + ", vl_distancia_trabalho = ?"
                    + ", nu_idade = ?"
                    + ", dt_admissao = ?"
                    + ", dt_modificacao = ? "
                    + "WHERE id_funcionario = ?;";
            ps = conexao.prepareStatement(query);
            ps.setString(1, funcionario.getNome());
            ps.setDouble(2, funcionario.getSalarioBase());
            ps.setDouble(3, funcionario.getDistanciaTrabalho());
            ps.setInt(4, funcionario.getIdade());
            ps.setDate(5, Date.valueOf(funcionario.getDataAdmissao()));
            // convertendo LocalDate para Date (SQL)
            Date sqlDate = Date.valueOf(LocalDate.now());
            ps.setDate(6, sqlDate);
            ps.setLong(7, funcionario.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao atualizar o funcion??rio.\n"
                    + ex.getMessage());
        } finally {
            ConexaoPostgreSQL.closeConnection(conexao, ps);
        }
    }

    @Override
    public boolean delete(long id) throws SQLException {
        PreparedStatement ps = null;
        List<String> queries = new ArrayList<>();
        try {
            queries.add("DELETE FROM funcionario_cargo WHERE id_funcionario = ?;");
            queries.add("DELETE FROM funcionario_bonus WHERE id_funcionario = ?;");
            queries.add("DELETE FROM funcionario_falta WHERE id_funcionario = ?;");
            queries.add("DELETE FROM salario WHERE id_funcionario = ?;");
            queries.add("DELETE FROM funcionario WHERE id_funcionario = ?;");
            for (String query : queries) {
                ps = conexao.prepareStatement(query);
                ps.setLong(1, id);
                ps.executeUpdate();
            }

            return true;
        } catch (SQLException ex) {
            throw new SQLException("Erro durante exclus??o do funcion??rio.\n"
                    + ex.getMessage());
        } finally {
            ConexaoPostgreSQL.closeConnection(conexao, ps);
        }
    }

    @Override
    public Funcionario getById(long id) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = ""
                    .concat("\n SELECT ")
                    .concat("\n     f.nm_funcionario")
                    .concat("\n     , f.vl_salario_base")
                    .concat("\n     , f.vl_distancia_trabalho")
                    .concat("\n     , s.vl_salario_total")
                    .concat("\n     , f.nu_idade")
                    .concat("\n     , f.dt_admissao")
                    .concat("\n     , c.id_cargo")
                    .concat("\n     , c.nm_cargo ")
                    .concat("\n FROM funcionario f ")
                    .concat("\n LEFT JOIN funcionario_cargo fc ")
                    .concat("\n ON f.id_funcionario = fc.id_funcionario ")
                    .concat("\n LEFT JOIN cargo c ")
                    .concat("\n ON fc.id_cargo = c.id_cargo ")
                    .concat("\n LEFT JOIN salario s ")
                    .concat("\n ON f.id_funcionario = s.id_funcionario ")
                    .concat("\n WHERE f.id_funcionario = ?;");
            ps = conexao.prepareStatement(query);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                throw new SQLException("Funcion??rio com id "
                        + id + "n??o encontrado");
            }
            String nome = rs.getString(1);
            double salarioBase = rs.getDouble(2);
            double distanciaTrabalho = rs.getDouble(3);
            double salarioTotal = rs.getDouble(4);
            int idade = rs.getInt(5);
            LocalDate dtAdmissao = new Date(rs.getDate(6)
                    .getTime()).toLocalDate();

            List<Bonus> bonusRecebidos = new ArrayList<>();
            List<FaltaAoTrabalho> faltas = new ArrayList<>();
            // Dados do cargo
            long idCargo = rs.getLong(7);
            String nomeCargo = rs.getString(8);
            Cargo cargo = new Cargo(idCargo, nomeCargo);

            return new Funcionario(id, nome, cargo, salarioBase,
                    distanciaTrabalho, dtAdmissao, idade, salarioTotal,
                    bonusRecebidos, faltas, 0);
        } catch (SQLException ex) {
            throw new SQLException("Erro ao buscar funcion??rio.\n"
                    + ex.getMessage());
        } finally {
            ConexaoPostgreSQL.closeConnection(conexao, ps, rs);
        }
    }

    @Override
    public long getFuncionarioCount() throws SQLException, ClassNotFoundException {
        PreparedStatement ps;
        ResultSet rs;
        long quantidadeFuncionarios = 0;
        String query = "SELECT COUNT(f.id_funcionario) "
                + "FROM funcionario f;";
        try {
            ps = conexao.prepareStatement(query);
            rs = ps.executeQuery();
            if(!rs.next())
                quantidadeFuncionarios = 0;
            quantidadeFuncionarios = rs.getLong(1);
        } catch (SQLException ex) {
            throw new SQLException("Ocorreu um erro ao contar os funcion??rios."
                    + ex.getMessage());
        }

        return quantidadeFuncionarios;
    }

    @Override
    public List<Funcionario> getAll() throws ClassNotFoundException, SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Funcionario> listaFuncionarios = new ArrayList();
        try {
            String query = "SELECT "
                    + "f.id_funcionario" //1
                    + ", f.nm_funcionario" //2
                    + ", f.vl_salario_base" //3
                    + ", f.vl_distancia_trabalho" //4
                    + ", f.nu_idade" //5
                    + ", f.dt_admissao" //6
                    + ", fc.id_cargo" //7
                    + ", c.nm_cargo " //8
                    + ", f.vl_tempo_servico " //9
                    + "FROM funcionario f "
                    + "LEFT JOIN funcionario_cargo fc "
                    + "ON f.id_funcionario = fc.id_funcionario "
                    + "LEFT JOIN cargo c "
                    + "ON fc.id_cargo = c.id_cargo;";
            ps = conexao.prepareStatement(query);
            rs = ps.executeQuery();
            if (!rs.next()) {
                throw new SQLException("N??o h?? funcion??rios cadastrados.");
            }
            do {
                Long id_funcionario = rs.getLong(1);
                String nome = rs.getString(2);
                double salarioBase = rs.getDouble(3);
                double distanciaTrabalho = rs.getDouble(4);
                int idade = rs.getInt(5);
                double salarioTotal = rs.getDouble(5);
                LocalDate dtAdmissao = new Date(rs.getDate(6)
                        .getTime()).toLocalDate();

                // informa????es do cargo
                long idCargo = rs.getLong(7);
                String nomeCargo = rs.getString(8);
                double tempoServico = rs.getDouble(9);
                Cargo cargo = new Cargo(idCargo, nomeCargo);

                listaFuncionarios.add(new Funcionario(id_funcionario, nome,
                        cargo, salarioBase, distanciaTrabalho,
                        dtAdmissao, idade, salarioTotal,
                        new ArrayList<>(), new ArrayList<>(), tempoServico));
            } while (rs.next());

        } catch (SQLException ex) {
            throw new SQLException("Erro ao buscar funcion??rios.\n"
                    + ex.getMessage());
        } finally {
            ConexaoPostgreSQL.closeConnection(conexao, ps, rs);
        }

        return listaFuncionarios;
    }

    @Override
    public List<HistoricoBonus> getFuncionarioBonus(long id) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = null;
        ResultSet result = null;
        List<HistoricoBonus> bonus = new ArrayList<>();
        try {
            String query = ""
                    .concat("select ")
                    .concat("\n 	fb.dt_modificacao")//1
                    .concat("\n 	, c.nm_cargo")//2
                    .concat("\n 	, b.nm_bonus")//3
                    .concat("\n 	, fb.vl_bonus")//4
                    .concat("\n 	, f.nm_funcionario")//5
                    .concat("\n from funcionario f")
                    .concat("\n inner join funcionario_bonus fb")
                    .concat("\n 	on f.id_funcionario = fb.id_funcionario")
                    .concat("\n inner join bonus b")
                    .concat("\n 	on fb.id_bonus = b.id_bonus")
                    .concat("\n left join funcionario_cargo fc")
                    .concat("\n 	on f.id_funcionario = fc.id_funcionario")
                    .concat("\n left join cargo c")
                    .concat("\n 	on fc.id_cargo = c.id_cargo")
                    .concat("\n where f.id_funcionario = ?;");
            ps = conexao.prepareStatement(query);
            ps.setLong(1, id);
            result = ps.executeQuery();
            Date data;
            String nomeCargo;
            String nomeBonus;
            double valorBonus;
            String nomeFuncionario;
            if (!result.next()) {
                throw new SQLException("Funcion??rio n??o possui b??nus calculados");
            }
            do {
                data = result.getDate(1);
                nomeCargo = result.getString(2);
                nomeBonus = result.getString(3);
                valorBonus = result.getDouble(4);
                nomeFuncionario = result.getString(5);
                bonus.add(
                        new HistoricoBonus(
                                nomeFuncionario,
                                data.toLocalDate(),
                                nomeCargo,
                                nomeBonus,
                                valorBonus)
                );
            } while (result.next());

            return bonus;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao buscar b??nus do funcin??rio.\n"
                    + ex.getMessage());
        } finally {
            ConexaoPostgreSQL.closeConnection(conexao, ps, result);
        }

    }

    @Override
    public List<Funcionario> getByName(String nome) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = null;
        ResultSet result = null;
        List<Funcionario> listaFuncionarios = new ArrayList<>();
        try {
            String query = ""
                    .concat("\n with cargo_atual as (")
                    .concat("\n		select ")
                    .concat("\n			f.id_funcionario ")
                    .concat("\n			, fc.id_cargo ")
                    .concat("\n			, max(fc.dt_modificacao) dt_modificacao ")
                    .concat("\n		from funcionario f ")
                    .concat("\n		left join funcionario_cargo fc ")
                    .concat("\n			on f.id_funcionario = fc.id_funcionario ")
                    .concat("\n		group by f.id_funcionario ")
                    .concat("\n			, fc.id_cargo ")
                    .concat("\n )")
                    .concat("\n	select ")
                    .concat("\n		f.id_funcionario") //1
                    .concat("\n		, f.nm_funcionario") //2
                    .concat("\n		, f.nu_idade") //3
                    .concat("\n		, c.nm_cargo ") //4
                    .concat("\n		, f.vl_salario_base") //5
                    .concat("\n	from funcionario f ")
                    .concat("\n	left join cargo_atual fc ")
                    .concat("\n		on f.id_funcionario = fc.id_funcionario ")
                    .concat("\n	left join cargo c ")
                    .concat("\n		on fc.id_cargo = c.id_cargo ")
                    .concat("\n	where f.nm_funcionario LIKE ?;");
            ps = conexao.prepareStatement(query);
            ps.setString(1, '%' + nome + '%');
            result = ps.executeQuery();
            if (!result.next()) {
                throw new SQLException("Funcion??rios com nome "
                        + nome + " n??o encontrados.");
            }
            do {
                int idFuncionario = result.getInt(1);
                String nomeFuncionario = result.getString(2);
                int idade = result.getInt(3);
                String nomeCargo = result.getString(4);
                double salarioBase = result.getDouble(5);

                Cargo cargo = new Cargo(nomeCargo);

                listaFuncionarios.add(new Funcionario(
                        idFuncionario,
                        nomeFuncionario,
                        cargo,
                        salarioBase,
                        idade
                ));
            } while (result.next());

            return listaFuncionarios;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao buscar funcion??rios.\n"
                    + ex.getMessage());
        } finally {
            ConexaoPostgreSQL.closeConnection(conexao, ps, result);
        }

    }

    @Override
    public List<Funcionario> getAllBuscarFuncionarioView() throws SQLException, ClassNotFoundException {
        PreparedStatement ps = null;
        ResultSet result = null;
        List<Funcionario> funcionarios = new ArrayList<>();
        try {
            String query = ""
                    .concat("\n with cargo_atual as (")
                    .concat("\n		select ")
                    .concat("\n			f.id_funcionario ")
                    .concat("\n			, fc.id_cargo ")
                    .concat("\n			, max(fc.dt_modificacao) dt_modificacao ")
                    .concat("\n		from funcionario f ")
                    .concat("\n		left join funcionario_cargo fc ")
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
                    .concat("\n	left join cargo_atual fc ")
                    .concat("\n		on f.id_funcionario = fc.id_funcionario ")
                    .concat("\n	left join cargo c ")
                    .concat("\n		on fc.id_cargo = c.id_cargo;");
            ps = conexao.prepareStatement(query);

            result = ps.executeQuery();
            if (!result.next()) {
                throw new SQLException("N??o h?? funcion??rios cadastrados.");
            }
            do {
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
            } while (result.next());

            return funcionarios;
        } catch (SQLException ex) {
            throw new SQLException("Erro ao buscar funcion??rios.\n"
                    + ex.getMessage());
        } finally {
            ConexaoPostgreSQL.closeConnection(conexao, ps, result);
        }

    }

    @Override
    public ResultSet getSalarioCalculadoByDate(LocalDate data) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
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

        } catch (SQLException ex) {
            throw new SQLException("Erro ao buscar funcion??rios.\n"
                    + ex.getMessage());
        } finally {
            ConexaoPostgreSQL.closeConnection(conexao, ps, result);
        }
    }

    @Override
    public List<SalarioCalculado> getAllSalarioCalculado() throws SQLException, ClassNotFoundException {
        PreparedStatement ps = null;
        ResultSet result = null;
        List<SalarioCalculado> salariosCalculados = new ArrayList<>();
        try {
            String query = ""
                    .concat("\n select ")
                    .concat("\n     f.id_funcionario ")//1
                    .concat("\n     , f.nm_funcionario ")//2
                    .concat("\n     , s.dt_modificacao")//3
                    .concat("\n     , coalesce (f.vl_salario_base, 0) vl_salario_base ")//4
                    .concat("\n     , coalesce (sum(fb.vl_bonus), 0) vl_bonus")//5
                    .concat("\n     , coalesce (s.vl_salario_total, 0) vl_salario_total ")//6
                    .concat("\n from funcionario f ")
                    .concat("\n inner join salario s ")
                    .concat("\n     on f.id_funcionario = s.id_funcionario ")
                    .concat("\n left join funcionario_bonus fb ")
                    .concat("\n     on f.id_funcionario = fb.id_funcionario ")
                    .concat("\n group by f.id_funcionario, f.nm_funcionario ")
                    .concat("\n     , s.dt_modificacao")
                    .concat("\n     , f.vl_salario_base ")
                    .concat("\n     , s.vl_salario_total;");

            ps = conexao.prepareStatement(query);
            result = ps.executeQuery();
            if (!result.next()) {
                throw new SQLException("O banco n??o possui sal??rios calculados.\n\n");
            }
            do {
                long id = result.getLong(1);
                String nome = result.getString(2);
                Date dataSQL = result.getDate(3);
                LocalDate dataCalculo = dataSQL.toLocalDate();
                double salarioBase = result.getDouble(4);
                double totalBonus = result.getDouble(5);
                double salarioTotal = result.getDouble(6);

                salariosCalculados.add(new SalarioCalculado(
                        id,
                        nome,
                        dataCalculo,
                        salarioBase,
                        totalBonus,
                        salarioTotal
                ));
            } while (result.next());

        } catch (SQLException ex) {
            throw new SQLException("Erro ao carregar sal??rios calculados. \n"
                    + ex.getMessage());
        } finally {
            ConexaoPostgreSQL.closeConnection(conexao, ps, result);
        }
        return salariosCalculados;
    }
}
