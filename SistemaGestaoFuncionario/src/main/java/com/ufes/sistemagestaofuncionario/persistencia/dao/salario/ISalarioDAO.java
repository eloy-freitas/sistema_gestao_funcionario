package com.ufes.sistemagestaofuncionario.persistencia.dao.salario;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import java.sql.SQLException;


public interface ISalarioDAO {
    boolean criar(Funcionario funcionario) throws SQLException, ClassNotFoundException;

}
