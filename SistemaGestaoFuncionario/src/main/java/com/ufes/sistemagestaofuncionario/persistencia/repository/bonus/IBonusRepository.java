package com.ufes.sistemagestaofuncionario.persistencia.repository.bonus;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import java.sql.SQLException;
import java.util.List;

public interface IBonusRepository {

    boolean adicionar(List<Funcionario> funcionarios) throws ClassNotFoundException, SQLException;
}
