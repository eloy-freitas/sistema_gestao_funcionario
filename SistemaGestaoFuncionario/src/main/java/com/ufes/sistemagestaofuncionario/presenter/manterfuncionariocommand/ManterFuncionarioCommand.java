package com.ufes.sistemagestaofuncionario.presenter.manterfuncionariocommand;

import java.sql.SQLException;


public interface ManterFuncionarioCommand {
    void executar()throws ClassNotFoundException, SQLException ;
}
