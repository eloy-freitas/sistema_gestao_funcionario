package com.ufes.sistemagestaofuncionario.presenter.manterfuncionariocommand;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.presenter.ManterFuncionarioPresenter;
import java.sql.SQLException;


public class AtualizarCommand implements ManterFuncionarioCommand{
    
    private ManterFuncionarioPresenter presenter;

    public AtualizarCommand(ManterFuncionarioPresenter presenter) {
        this.presenter = presenter;
    }
    
    @Override
    public void executar() throws ClassNotFoundException, SQLException {
        Funcionario funcionario = presenter.obterCampos();
        presenter.getFuncionarioService().atualizar(funcionario);
        presenter.getCargoService().atualizar(funcionario);
    }
    
}
