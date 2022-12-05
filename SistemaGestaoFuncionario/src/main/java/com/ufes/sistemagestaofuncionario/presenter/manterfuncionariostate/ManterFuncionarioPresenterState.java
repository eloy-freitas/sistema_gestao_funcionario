package com.ufes.sistemagestaofuncionario.presenter.manterfuncionariostate;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.presenter.ManterFuncionarioPresenter;
import com.ufes.sistemagestaofuncionario.presenter.manterfuncionariocommand.ManterFuncionarioCommand;

public abstract class ManterFuncionarioPresenterState {
    
    protected ManterFuncionarioPresenter presenter;
    protected ManterFuncionarioCommand command;
    
    public ManterFuncionarioPresenterState(ManterFuncionarioPresenter presenter) {
        this.presenter = presenter;
    }
    
    public void salvar() throws Exception{
        throw new Exception("Estado inválido para realizar essa ação");
    }
    
    public void editar() throws Exception{
        throw new Exception("Estado inválido para realizar essa ação");
    }
    
    public void excluir() throws Exception{
        throw new Exception("Estado inválido para realizar essa ação");
    }
    
    public void cancelar() throws Exception{
        throw new Exception("Estado inválido para realizar essa ação");
    }


    public abstract void initView();
    
    public Funcionario obterCampos() throws Exception{
        throw new Exception("Estado inválido para realizar essa ação");
    }
}