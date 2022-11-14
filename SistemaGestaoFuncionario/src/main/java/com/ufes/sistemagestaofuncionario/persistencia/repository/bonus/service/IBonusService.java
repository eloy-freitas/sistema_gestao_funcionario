package com.ufes.sistemagestaofuncionario.persistencia.repository.bonus.service;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import java.sql.SQLException;
import java.util.List;

public interface IBonusService {

    boolean adicionar(List<Funcionario> funcionarios) throws ClassNotFoundException, SQLException;
}
