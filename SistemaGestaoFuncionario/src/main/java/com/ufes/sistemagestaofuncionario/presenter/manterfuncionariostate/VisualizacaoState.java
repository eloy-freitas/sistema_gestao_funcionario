package com.ufes.sistemagestaofuncionario.presenter.manterfuncionariostate;

import com.ufes.sistemagestaofuncionario.presenter.BuscarFuncionarioPresenter;
import com.ufes.sistemagestaofuncionario.presenter.ManterFuncionarioPresenter;
import com.ufes.sistemagestaofuncionario.presenter.manterfuncionariocommand.ExcluirCommand;
import java.sql.SQLException;

import javax.swing.JOptionPane;


public class VisualizacaoState extends ManterFuncionarioPresenterState{

    public VisualizacaoState(ManterFuncionarioPresenter presenter) {
        super(presenter);
        initView();
    }
    
    @Override
    public void initView(){
        presenter.getView().getCheckBoxFuncionarioMes().setEnabled(false);
        presenter.getView().getDpDataAdmissao().setEnabled(false);
        presenter.getView().getDpDataNascimento().setEnabled(false);
        presenter.getView().getFtfSalario().setEnabled(false);
        presenter.getView().getTfDataAdmissao().setEnabled(false);
        presenter.getView().getTfDistanciaTrabalho().setEnabled(false);
        presenter.getView().getTfFaltas().setEnabled(false);
        presenter.getView().getTfNome().setEnabled(false);
        presenter.getView().getCbCargo().setEnabled(false);
        presenter.getView().getBtnSalvar().setEnabled(false);
        presenter.getView().getBtnEditar().setEnabled(true);
        presenter.getView().getBtnExcluir().setEnabled(true);
        presenter.getView().getBtnCancelar().setEnabled(true);
        presenter.getView().setVisible(true);
    }
    
    @Override
    public void editar(){
        presenter.setEstado(new EdicaoState(presenter));
    }
    
    @Override
    public void excluir() throws ClassNotFoundException, SQLException{
        int opt = JOptionPane.showConfirmDialog(
            presenter.getView(),
                "Deseja realmente excluir o funcionário abaixo?\n"
                + presenter.getFuncionario().getNome(),
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        // Exclusão confirmada
        if (opt == JOptionPane.YES_OPTION) { // Checando o retorno da exclusão
            try {          
                new ExcluirCommand(presenter).executar();
                JOptionPane.showMessageDialog(
                    presenter.getView(),
                    "Funcionário excluído com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
                );
                presenter.fechar();
                new BuscarFuncionarioPresenter();
            } catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(presenter.getView(),
                    "Erro ao excluir funcionário.\n\n"
                    + ex.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    @Override
    public void cancelar(){
        presenter.fechar();
        new BuscarFuncionarioPresenter();
    }
    
}
