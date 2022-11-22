package com.ufes.sistemagestaofuncionario.persistencia.repository.cargo.service;

import com.ufes.sistemagestaofuncionario.model.Cargo;
import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.persistencia.repository.cargo.CargoRepository;
import com.ufes.sistemagestaofuncionario.persistencia.repository.cargo.ICargoRepository;
import java.sql.SQLException;
import java.util.List;

public class CargoService implements ICargoService{
    private ICargoRepository cargoRepository;

    public CargoService() throws ClassNotFoundException, SQLException{
        this.cargoRepository = new CargoRepository();
    }

    @Override
    public List<Cargo> buscarTodos() throws ClassNotFoundException, SQLException {
        return cargoRepository.buscarTodos();
    }

    @Override
    public Cargo buscarPorNome(String nome) throws ClassNotFoundException, SQLException {
        return cargoRepository.buscarPorNome(nome);
    }

    @Override
    public boolean criar(Funcionario funcionario) throws ClassNotFoundException, SQLException {
        return cargoRepository.criar(funcionario);
    }

    @Override
    public boolean criar(List<Funcionario> funcionarios) throws ClassNotFoundException, SQLException {
        return cargoRepository.criar(funcionarios);
    }

    @Override
    public boolean atualizar(Funcionario funcionario) throws ClassNotFoundException, SQLException {
        return cargoRepository.atualizar(funcionario);
    }
}
