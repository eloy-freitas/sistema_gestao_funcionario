package com.ufes.sistemagestaofuncionario.presenter.manterfuncionariostate;

import com.ufes.sistemagestaofuncionario.presenter.ManterFuncionarioPresenter;
import com.ufes.sistemagestaofuncionario.presenter.manterfuncionariocommand.SalvarCommand;
import java.sql.SQLException;

public class InclusaoState extends ManterFuncionarioPresenterState{

    public InclusaoState(ManterFuncionarioPresenter presenter) {
        super(presenter);
        presenter.initServices();
        presenter.initListeners();
        presenter.populaCargos();
        presenter.initComboBox();
        presenter.getView().setVisible(true);
        initView();
    }
    
    private void initView(){
        presenter.getView().getLbIdade().setVisible(false);
        presenter.getView().getLbIdade2().setVisible(false);
        presenter.getView().getLbDataAdmissao2().setVisible(false);
        presenter.getView().getLbDataAdmissao().setVisible(false);
        presenter.getView().getTfDataAdmissao().setVisible(false);
        presenter.getView().getBtnEditar().setEnabled(false);
        presenter.getView().getBtnExcluir().setEnabled(false);
    }
    
    @Override
    public void salvar() throws ClassNotFoundException, SQLException{
        new SalvarCommand(presenter).executar();
        presenter.fechar();
    }
    
}
