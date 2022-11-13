package com.ufes.sistemagestaofuncionario.persistencia.dao.falta;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import java.sql.SQLException;


public interface IFaltaDAO {
        public boolean save(Funcionario funcionario) throws SQLException, ClassNotFoundException;

}
