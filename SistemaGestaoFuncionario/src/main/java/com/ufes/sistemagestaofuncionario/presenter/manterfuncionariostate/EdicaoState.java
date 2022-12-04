package com.ufes.sistemagestaofuncionario.presenter.manterfuncionariostate;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.presenter.ManterFuncionarioPresenter;
import com.ufes.sistemagestaofuncionario.presenter.manterfuncionariocommand.AtualizarCommand;


public class EdicaoState extends ManterFuncionarioPresenterState{

    public EdicaoState(ManterFuncionarioPresenter presenter) {
        super(presenter);
        initView();
    }
    
    private void initView(){
        presenter.getView().getCheckBoxFuncionarioMes().setEnabled(true);
        presenter.getView().getDpDataAdmissao().setEnabled(true);
        presenter.getView().getDpDataNascimento().setEnabled(true);
        presenter.getView().getFtfSalario().setEnabled(true);
        presenter.getView().getTfDataAdmissao().setEnabled(false);
        presenter.getView().getTfDistanciaTrabalho().setEnabled(true);
        presenter.getView().getTfFaltas().setEnabled(true);
        presenter.getView().getTfNome().setEnabled(true);
        presenter.getView().getCbCargo().setEnabled(true);
        presenter.getView().getBtnEditar().setEnabled(false);
        presenter.getView().getBtnExcluir().setEnabled(false);
        presenter.getView().getBtnSalvar().setEnabled(true);
    }
    
    @Override
    public void atualizar(){
        new AtualizarCommand(presenter).executar();
        presenter.fechar();
    }
}
