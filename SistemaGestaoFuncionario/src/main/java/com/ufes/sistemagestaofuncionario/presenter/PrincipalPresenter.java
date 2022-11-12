package com.ufes.sistemagestaofuncionario.presenter;

import com.ufes.sistemagestaofuncionario.persistencia.dao.funcionario.FuncionarioDAO;
import com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario.service.FuncionarioService;
import com.ufes.sistemagestaofuncionario.view.PrincipalView;
import java.sql.SQLException;

public class PrincipalPresenter {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //new PrincipalView();
        
        FuncionarioService s = new FuncionarioService();
        s.buscarFuncionarioPorName("abc");
    }
}
