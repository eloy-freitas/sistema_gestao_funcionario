package com.ufes.sistemagestaofuncionario.persistencia.dao.salario;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import java.sql.SQLException;
import java.util.List;

public interface ISalarioDAO {

    boolean criar(List<Funcionario> funcionarios) throws SQLException, ClassNotFoundException;

}
