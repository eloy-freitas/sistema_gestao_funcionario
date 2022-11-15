package com.ufes.sistemagestaofuncionario.persistencia.repository.cargo;

import com.ufes.sistemagestaofuncionario.model.Cargo;
import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.persistencia.dao.cargo.CargoDAO;
import com.ufes.sistemagestaofuncionario.persistencia.dao.cargo.ICargoDAO;
import java.sql.SQLException;
import java.util.List;

public class CargoRepository implements ICargoRepository{
    private ICargoDAO cargoDAO;

    public CargoRepository() {

    }

    private void abrirConexao() throws SQLException, ClassNotFoundException{
        this.cargoDAO = new CargoDAO();
    }

    @Override
    public List<Cargo> buscarTodos() throws SQLException, ClassNotFoundException {
        abrirConexao();
        return cargoDAO.getAll();
    }

    @Override
    public Cargo buscarPorNome(String nome) throws SQLException, ClassNotFoundException{
        abrirConexao();
        return cargoDAO.getByNome(nome);
    }

    @Override
    public boolean criar(Funcionario funcionario) throws SQLException, ClassNotFoundException{
        abrirConexao();
        return cargoDAO.save(funcionario);
    }

    @Override
    public boolean criar(List<Funcionario> funcionarios) throws SQLException, ClassNotFoundException {
        abrirConexao();
        return cargoDAO.save(funcionarios);
    }
    
    
}
