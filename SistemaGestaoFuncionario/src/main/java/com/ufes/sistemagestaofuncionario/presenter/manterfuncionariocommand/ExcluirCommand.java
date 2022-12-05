package com.ufes.sistemagestaofuncionario.presenter.manterfuncionariocommand;

import com.ufes.sistemagestaofuncionario.presenter.BuscarFuncionarioPresenter;
import com.ufes.sistemagestaofuncionario.presenter.ManterFuncionarioPresenter;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class ExcluirCommand implements ManterFuncionarioCommand{
    
    private ManterFuncionarioPresenter presenter;

    
    public ExcluirCommand(ManterFuncionarioPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void executar() throws ClassNotFoundException, SQLException{
        long id = presenter.getFuncionario().getId();
        presenter.getFuncionarioService().excluir(id);
        
    }
    
    
}
