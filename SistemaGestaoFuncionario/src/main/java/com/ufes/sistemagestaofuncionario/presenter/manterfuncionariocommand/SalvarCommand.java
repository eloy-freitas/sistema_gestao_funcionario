package com.ufes.sistemagestaofuncionario.presenter.manterfuncionariocommand;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.presenter.ManterFuncionarioPresenter;
import java.sql.SQLException;


public class SalvarCommand implements ManterFuncionarioCommand{

    private ManterFuncionarioPresenter presenter;
    
    public SalvarCommand(ManterFuncionarioPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void executar() throws ClassNotFoundException, SQLException{
        Funcionario funcionario = presenter.obterCampos();
        presenter.getFuncionarioService().salvar(funcionario);
    }
}
