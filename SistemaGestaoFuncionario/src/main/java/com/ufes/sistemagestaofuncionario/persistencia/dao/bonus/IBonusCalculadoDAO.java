package com.ufes.sistemagestaofuncionario.persistencia.dao.bonus;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import java.sql.SQLException;
import java.util.List;


public interface IBonusCalculadoDAO {
        public boolean save(List<Funcionario> funcionarios) throws SQLException, ClassNotFoundException;

}
