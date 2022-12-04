package com.ufes.sistemagestaofuncionario.presenter.manterfuncionariocommand;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.presenter.ManterFuncionarioPresenter;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class SalvarCommand implements ManterFuncionarioCommand{

    private ManterFuncionarioPresenter presenter;
    
    public SalvarCommand(ManterFuncionarioPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void executar() throws ClassNotFoundException, SQLException{
        Funcionario funcionario = presenter.obterCampos();
        if (presenter.getFuncionarioService().salvar(funcionario)) {
            JOptionPane.showMessageDialog(presenter.getView(),
                    "Funcionário(a)\n"
                    + funcionario.getNome() + "\n"
                    + "salvo(a) com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
}