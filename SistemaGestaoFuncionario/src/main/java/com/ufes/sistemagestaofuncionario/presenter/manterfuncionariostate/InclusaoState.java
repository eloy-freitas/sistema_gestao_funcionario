package com.ufes.sistemagestaofuncionario.presenter.manterfuncionariostate;

import com.ufes.sistemagestaofuncionario.model.Cargo;
import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.presenter.ManterFuncionarioPresenter;
import com.ufes.sistemagestaofuncionario.presenter.manterfuncionariocommand.SalvarCommand;
import com.ufes.sistemagestaofuncionario.utils.conversores.ConversorCalendar;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

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

    @Override
    public Funcionario obterCampos() {
        /* 
        *  Utilizando calendar por se mostrar o objeto mais estável retornado
        *  pelo DatePicker.
        *  Convertendo o calendar em date, para poder realizar a conversao para
        *  LocalDate.
         */
        LocalDate dataAdmissao = ConversorCalendar.calendarToLocalDate(
                (Calendar) presenter.getView().getDpDataAdmissao().getModel().getValue()
        );
        LocalDate dataNascimento = ConversorCalendar.calendarToLocalDate(
                (Calendar) presenter.getView().getDpDataNascimento().getModel().getValue()
        );
        /*
        *  Calculando a idade do funcionário, com base no valor obtido no campo
        *  DpDataNascimento.
         */
        Period period = Period.between(dataNascimento,
                LocalDate.now());

        int idade = period.getYears();
        System.out.println(idade);
        String nome = presenter.getView().getTfNome().getText();
        String cargo = presenter.getView().getCbCargo().getSelectedItem().toString();
        Double salarioBase = Double.valueOf(presenter.getView().getFtfSalario().getText());
        // boolean funcionarioMes = view.getCheckBoxFuncionarioMes().isSelected();
        Double distanciaTrabalho = Double.valueOf(presenter.getView().getTfDistanciaTrabalho().getText());

        return new Funcionario(
                nome,
                new Cargo(cargo),
                salarioBase,
                distanciaTrabalho,
                dataAdmissao,
                idade
        );
    }
    
    @Override
    public void initView(){
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
