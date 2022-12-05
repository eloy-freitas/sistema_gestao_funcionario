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
      try {
            // Mensagem de confirmação
            int opt = JOptionPane.showConfirmDialog(presenter.getView(),
                    "Deseja realmente excluir o funcionário abaixo?\n"
                    + presenter.getFuncionario().getNome(),
                    "Confirmar Exclusão",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            // Exclusão confirmada
            if (opt == JOptionPane.YES_OPTION) {
                // Checando o retorno da exclusão
                long id = presenter.getFuncionario().getId();
                if (presenter.getFuncionarioService().excluir(id)) {
                    JOptionPane.showMessageDialog(presenter.getView(),
                            "Funcionário excluído com sucesso!",
                            "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(presenter.getView(),
                    "Erro ao excluir funcionário.\n\n"
                    + ex.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
}
