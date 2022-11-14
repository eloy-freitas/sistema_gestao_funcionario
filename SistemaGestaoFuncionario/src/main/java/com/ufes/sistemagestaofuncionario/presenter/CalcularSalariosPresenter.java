package com.ufes.sistemagestaofuncionario.presenter;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.model.SalarioCalculado;
import com.ufes.sistemagestaofuncionario.persistencia.repository.bonus.service.BonusService;
import com.ufes.sistemagestaofuncionario.persistencia.repository.bonus.service.IBonusService;
import com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario.service.FuncionarioService;
import com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario.service.IFuncionarioService;
import com.ufes.sistemagestaofuncionario.service.calculadora.CalculadoraBonusService;
import com.ufes.sistemagestaofuncionario.service.calculadora.CalculadoraSalarioService;
import com.ufes.sistemagestaofuncionario.view.funcionario.CalcularSalariosView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class CalcularSalariosPresenter {

    private CalcularSalariosView view;
    private CalculadoraBonusService calculoBonusService;
    private CalculadoraSalarioService calculadoraSalarioService;
    private IBonusService bonusService;
    private IFuncionarioService funcionarioService;
    private List<SalarioCalculado> listaSalarios;
    private DefaultTableModel tmCalculoSalarios;

    public CalcularSalariosPresenter() {
        view = new CalcularSalariosView();
        initListeners();
        initServices();
        initTabela();
        populaTabela();
        view.setVisible(true);
    }

    private void initListeners() {

        // Botão fechar
        view.getBtnFechar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
            }
        });

        // Botão Calcular
        view.getBtnCalcular().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcular();
            }

        });
    }

    private List<SalarioCalculado> atualizaListaSalarios() {
        try {
            this.listaSalarios = funcionarioService.buscarTodosSalarioCalculado();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(view,
                    "Erro ao carregar os salários.\n\n"
                    + ex.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
        return listaSalarios;
    }

    private void initServices() {
        this.calculoBonusService = new CalculadoraBonusService();
        this.calculadoraSalarioService = new CalculadoraSalarioService();
        try {
            this.funcionarioService = new FuncionarioService();
            this.bonusService = new BonusService();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(view,
                    "Erro ao iniciar os serviços necessários.\n\n"
                    + ex.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initTabela() {
        JTable tabela = view.getTblCalculoSalario();
        tmCalculoSalarios = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Funcionário", "Data",
                    "Salário Base (R$)", "Bônus (R$)", "Salário (R$)"}
        );
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tmCalculoSalarios.setNumRows(0);

        tabela.setModel(tmCalculoSalarios);
    }

    private void populaTabela() {
        listaSalarios = atualizaListaSalarios();
        // Percorrendo a lista para adicionar à tabela.
        if (listaSalarios != null) {
            ListIterator<SalarioCalculado> iterator
                    = this.listaSalarios.listIterator();
            // Formatando data
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            while (iterator.hasNext()) {
                SalarioCalculado salarioCalculado = iterator.next();
                tmCalculoSalarios.addRow(new Object[]{
                    salarioCalculado.getNomeFuncionario(),
                    formatador.format(salarioCalculado.getDataCalculo()),
                    salarioCalculado.getSalarioBase(),
                    salarioCalculado.getTotalBonus(),
                    salarioCalculado.getSalarioTotal()
                });

            }
        }
    }

    private void fechar() {
        view.dispose();
    }

    private void calcular() {
        List<Funcionario> listaFuncionarios = new ArrayList();
        List<Funcionario> listaFuncionariosCalculado = new ArrayList();
        try {
            listaFuncionarios
                    = funcionarioService.buscarTodos();
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(view,
                    ex.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
        try {
            ListIterator<Funcionario> iterator = listaFuncionarios.listIterator();
            while (iterator.hasNext()) {
                Funcionario funcionario = iterator.next();
                funcionario.setBonusRecebidos(new ArrayList());
                funcionario = calculoBonusService.calcular(funcionario);
                funcionario = calculadoraSalarioService.calcularSalario(funcionario);
                listaFuncionariosCalculado.add(funcionario);
            }
            bonusService.adicionar(listaFuncionarios);
            funcionarioService.incluirSalario(listaFuncionarios);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view,
                    ex.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            populaTabela();
        }

    }
}
