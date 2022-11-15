package com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario.service;

import java.sql.SQLException;
import java.util.List;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.model.HistoricoBonus;
import com.ufes.sistemagestaofuncionario.model.SalarioCalculado;
import com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario.FuncionarioRepository;
import com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario.IFuncionarioRepository;
import java.sql.ResultSet;
import java.time.LocalDate;

public class FuncionarioService implements IFuncionarioService {

    private IFuncionarioRepository funcionarioRepository;

    public FuncionarioService() throws ClassNotFoundException, SQLException {
        this.funcionarioRepository = new FuncionarioRepository();
    }

    @Override
    public boolean salvar(Funcionario funcionario) throws ClassNotFoundException, SQLException {
        return funcionarioRepository.criar(funcionario);
    }

    @Override
    public boolean salvar(List<Funcionario> funcionarios) throws ClassNotFoundException, SQLException {
        return funcionarioRepository.criar(funcionarios);
    }

    @Override
    public boolean atualizar(Funcionario funcionario) throws ClassNotFoundException, SQLException {
        return funcionarioRepository.atualizar(funcionario);
    }

    @Override
    public long contarFuncionarios() throws ClassNotFoundException, SQLException {
        return funcionarioRepository.contarFuncionarios();
    }

    @Override
    public boolean excluir(Long id) throws ClassNotFoundException, SQLException {
        return funcionarioRepository.excluir(id);
    }

    @Override
    public Funcionario buscarPorId(Long id) throws ClassNotFoundException, SQLException {
        return funcionarioRepository.buscarPorId(id);
    }

    @Override
    public List<Funcionario> buscarTodos() throws ClassNotFoundException, SQLException {
        return funcionarioRepository.buscarTodos();
    }

    @Override
    public List<HistoricoBonus>  buscarFuncionarioBonus(Long id) throws ClassNotFoundException, SQLException {
        return funcionarioRepository.buscarFuncionarioBonus(id);
    }

    @Override
    public List<Funcionario> buscarFuncionarioPorName(String nome) throws SQLException, ClassNotFoundException {
        return funcionarioRepository.buscarFuncionarioPorName(nome);
    }

    @Override
    public List<Funcionario> buscarBuscarFuncionarioView() throws SQLException, ClassNotFoundException {
        return funcionarioRepository.buscarBuscarFuncionarioView();
    }

    @Override
    public ResultSet buscarSalarioCalculadoPorData(LocalDate data) throws SQLException, ClassNotFoundException {
        return funcionarioRepository.buscarSalarioCalculadoPorData(data);
    }

    @Override
    public List<SalarioCalculado> buscarTodosSalarioCalculado() throws SQLException, ClassNotFoundException {
        return funcionarioRepository.buscarTodosSalarioCalculado();
    }

    @Override
    public boolean incluirBonus(List<Funcionario> funcionarios) throws SQLException, ClassNotFoundException {
        return funcionarioRepository.incluirBonus(funcionarios);
    }

    @Override
    public boolean incluirFaltas(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        return funcionarioRepository.incluirFaltas(funcionario);
    }

    @Override
    public boolean incluirSalario(List<Funcionario> funcionarios) throws SQLException, ClassNotFoundException {
        return funcionarioRepository.incluirSalario(funcionarios);
    }

}
