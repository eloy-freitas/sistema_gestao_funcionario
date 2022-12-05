package com.ufes.sistemagestaofuncionario.presenter.manterfuncionariostate;

import com.ufes.sistemagestaofuncionario.presenter.BuscarFuncionarioPresenter;
import com.ufes.sistemagestaofuncionario.presenter.ManterFuncionarioPresenter;
import com.ufes.sistemagestaofuncionario.presenter.manterfuncionariocommand.ExcluirCommand;
import java.sql.SQLException;


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
        new ExcluirCommand(presenter).executar();
        presenter.fechar();
        new BuscarFuncionarioPresenter();
    }
    
    @Override
    public void cancelar(){
        presenter.fechar();
        new BuscarFuncionarioPresenter();
    }
    
}
