package com.ufes.sistemagestaofuncionario.presenter.manterfuncionariocommand;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.presenter.BuscarFuncionarioPresenter;
import com.ufes.sistemagestaofuncionario.presenter.ManterFuncionarioPresenter;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class AtualizarCommand implements ManterFuncionarioCommand{
    
    private ManterFuncionarioPresenter presenter;

    public AtualizarCommand(ManterFuncionarioPresenter presenter) {
        this.presenter = presenter;
    }
    
    @Override
    public void executar() {
        try {
            Funcionario funcionario = presenter.obterCampos();
            presenter.getFuncionarioService().atualizar(funcionario);
            presenter.getCargoService().atualizar(funcionario);
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(presenter.getView(),
                    "Erro ao atualizar o funcionário.\n\n"
                    + ex.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            presenter.fechar();
            new BuscarFuncionarioPresenter();
        }
    }
    
    
}
