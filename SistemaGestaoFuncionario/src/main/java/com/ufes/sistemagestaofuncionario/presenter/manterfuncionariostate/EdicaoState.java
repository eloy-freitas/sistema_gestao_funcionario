package com.ufes.sistemagestaofuncionario.presenter.manterfuncionariostate;

import com.ufes.sistemagestaofuncionario.model.Cargo;
import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.presenter.ManterFuncionarioPresenter;
import com.ufes.sistemagestaofuncionario.presenter.manterfuncionariocommand.AtualizarCommand;
import java.sql.SQLException;


public class EdicaoState extends ManterFuncionarioPresenterState{

    public EdicaoState(ManterFuncionarioPresenter presenter) {
        super(presenter);
        initView();
    }
    
    @Override
    public Funcionario obterCampos() {
        Funcionario funcionario = presenter.getFuncionario();
        
        funcionario.setNome(presenter.getView().getTfNome().getText());
        funcionario.setSalarioBase(Double.valueOf(
                presenter.getView().getFtfSalario().getText()));
        funcionario.setDistanciaTrabalho(Double.valueOf(
                presenter.getView().getTfDistanciaTrabalho().getText()));
        for (Cargo cargo : presenter.getCargos()) {
            if (presenter.getView().getCbCargo().getSelectedItem().toString()
                    .equals(cargo.getNome())) {
                funcionario.setCargo(cargo);
            }
        }
        
        return funcionario;
    }
    
    @Override
    public void initView(){
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
    public void salvar() throws ClassNotFoundException, SQLException{
        new AtualizarCommand(presenter).executar();
    }
}
