package com.ufes.sistemagestaofuncionario.persistencia.dao.bonus;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import java.sql.SQLException;


public interface IBonusCalculadoDAO {
        public boolean save(Funcionario funcionario) throws SQLException, ClassNotFoundException;

}
