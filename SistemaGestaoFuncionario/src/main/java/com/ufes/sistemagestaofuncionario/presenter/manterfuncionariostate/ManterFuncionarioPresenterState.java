package com.ufes.sistemagestaofuncionario.presenter.manterfuncionariostate;

import com.ufes.sistemagestaofuncionario.presenter.ManterFuncionarioPresenter;
import com.ufes.sistemagestaofuncionario.presenter.manterfuncionariocommand.ManterFuncionarioCommand;

public abstract class ManterFuncionarioPresenterState {
    
    protected ManterFuncionarioPresenter presenter;
    protected ManterFuncionarioCommand command;
    
    public ManterFuncionarioPresenterState(ManterFuncionarioPresenter presenter) {
        this.presenter = presenter;
    }
    
    public void salvar() throws Exception{
        throw new Exception("Estado inválido");
    }
    
    public void editar() throws Exception{
        throw new Exception("Estado inválido");
    }
    
    public void excluir() throws Exception{
        throw new Exception("Estado inválido");
    }
    
    public void atualizar() throws Exception{
        throw new Exception("Estado inválido");
    }
}
