package com.ufes.sistemagestaofuncionario.presenter;

import com.ufes.sistemagestaofuncionario.model.Cargo;
import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.persistencia.repository.cargo.service.CargoService;
import com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario.service.FuncionarioService;
import com.ufes.sistemagestaofuncionario.utils.conversores.ConversorCalendar;
import com.ufes.sistemagestaofuncionario.view.funcionario.ManterFuncionarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JOptionPane;

public class ManterFuncionarioPresenter {

    private ManterFuncionarioView view;
    private FuncionarioService funcionarioService;
    private CargoService cargoService;
    private List<Cargo> cargos;

    public ManterFuncionarioPresenter() {
        view = new ManterFuncionarioView();
        initServices();
        initListeners();
        populaCargos();
        initComboBox();
        view.setVisible(true);
    }

    private void initServices() {
        try {
            funcionarioService = new FuncionarioService();
            cargoService = new CargoService();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(view,
                    "Erro ao carregar dados iniciais necessários\n\n"
                    + ex.getMessage(),
                    "Erro de Inicialização",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initListeners() {
        // Botão fechar
        view.getBtnFechar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
            }
        });

        // Botão salvar
        view.getBtnSalvar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvar();
            }
        });

    }

    private void populaCargos() {
        try {
            cargos = cargoService.buscarTodos();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(view,
                    "Ocorreu um erro ao carregar os cargos.\n\n"
                    + ex.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComboBox() {
        ListIterator<Cargo> iterator = cargos.listIterator();

        while (iterator.hasNext()) {
            Cargo cargo = iterator.next();
            view.getCbCargo().addItem(cargo.getNome());
        }

    }

    private void fechar() {
        view.dispose();
    }

    private Funcionario obterCampos() throws ClassNotFoundException, SQLException {
        /* 
        *  Utilizando calendar por se mostrar o objeto mais estável retornado
        *  pelo DatePicker.
        *  Convertendo o calendar em date, para poder realizar a conversao para
        *  LocalDate.
         */
        LocalDate dataAdmissao = ConversorCalendar.calendarToLocalDate(
                (Calendar) view.getDpDataAdmissao().getModel().getValue()
        );
        LocalDate dataNascimento = ConversorCalendar.calendarToLocalDate(
                (Calendar) view.getDpDataNascimento().getModel().getValue()
        );
        /*
        *  Calculando a idade do funcionário, com base no valor obtido no campo
        *  DpDataNascimento.
         */
        Period period = Period.between(dataNascimento,
                LocalDate.now());

        int idade = period.getYears();
        System.out.println(idade);
        String nome = view.getTfNome().getText();
        String cargo = view.getCbCargo().getSelectedItem().toString();
        Double salarioBase = Double.valueOf(view.getFtfSalario().getText());
        // boolean funcionarioMes = view.getCheckBoxFuncionarioMes().isSelected();
        Double distanciaTrabalho = Double.valueOf(view.getTfDistanciaTrabalho().getText());

        return new Funcionario(
                nome,
                new Cargo(cargo),
                salarioBase,
                distanciaTrabalho,
                dataAdmissao,
                idade
        );
    }

    private void salvar() {
        try {
            Funcionario funcionario = obterCampos();
            if (funcionarioService.salvar(funcionario)) {
                JOptionPane.showMessageDialog(view,
                        "Funcionário(a)\n"
                        + funcionario.getNome() + "\n"
                        + "salvo(a) com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(view,
                    "Ocorreu um erro ao salvar o funcionário.\n\n"
                    + ex.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }

        view.dispose();
    }
}
