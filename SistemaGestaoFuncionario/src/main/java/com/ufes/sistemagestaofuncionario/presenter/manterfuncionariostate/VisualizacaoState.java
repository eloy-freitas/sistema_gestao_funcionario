package com.ufes.sistemagestaofuncionario.presenter.manterfuncionariostate;

import com.ufes.sistemagestaofuncionario.presenter.ManterFuncionarioPresenter;


public class VisualizacaoState extends ManterFuncionarioPresenterState{

    public VisualizacaoState(ManterFuncionarioPresenter presenter) {
        super(presenter);
        presenter.initServices();
        presenter.initListeners();
        presenter.populaCargos();
        presenter.initComboBox();
        presenter.initCampos();
        initView();
    }
    
    private void initView(){
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
    
    
}
