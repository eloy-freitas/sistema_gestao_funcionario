package com.ufes.sistemagestaofuncionario.presenter;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario.service.FuncionarioService;
import com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario.service.IFuncionarioService;
import com.ufes.sistemagestaofuncionario.view.funcionario.BuscarFuncionarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ListIterator;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class BuscarFuncionarioPresenter {

    private BuscarFuncionarioView view;
    private DefaultTableModel tmFuncionarios;
    private IFuncionarioService funcionarioService;

    public BuscarFuncionarioPresenter() {
        view = new BuscarFuncionarioView();
        initServices();
        initTable();
        populaTabela();
        initListeners();
        view.setVisible(true);
    }

    private void initServices(){
        try {
            funcionarioService = new FuncionarioService();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(view,
                    "Erro ao inicializar janela de busca.\n\n"
                    + ex.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initTable() {
        JTable tabela = view.getTblFuncionario();
        tmFuncionarios = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Nome", "Idade", "Função", "Salário Base (R$)"}
        );
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tmFuncionarios.setNumRows(0);

        tabela.setModel(tmFuncionarios);

    }
    
    private void populaTabela() {
        try {
            // Percorrendo a lista para adicionar à tabela.
            ListIterator<Funcionario> iterator
                    = funcionarioService.buscarTodos()
                            .listIterator();
            while (iterator.hasNext()) {
                Funcionario funcionario = iterator.next();
                tmFuncionarios.addRow(new Object[]{
                    funcionario.getId(),
                    funcionario.getNome(),
                    funcionario.getIdade(),
                    funcionario.getCargo().getNome(),
                    funcionario.getSalarioBase()
                });
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(view,
                    "Erro ao listar os funcionários.\n\n"
                    + ex.getMessage(),
                    "ERRO",
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

        // Botão Novo
        view.getBtnNovo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirManterFuncionario();
            }
        });

        // Botão visualizar
        view.getBtnVisualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVisualizarDetalhesFuncionario();
            }
        });
    }

    private void fechar() {
        view.dispose();
    }

    private void abrirVisualizarDetalhesFuncionario() {
        JTable tabela = view.getTblFuncionario();
        int linha = tabela.getSelectedRow();
        Object id = tabela.getModel().getValueAt(linha, 0);
        System.out.println(id.toString());
        Funcionario funcionario;
        try {
            funcionario = funcionarioService.buscarPorId(
                    Long.valueOf(id.toString()));
            new ExibirDetalhesFuncionarioPresenter(funcionario);
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(view,
                    "Erro ao buscar funcionário.\n\n"
                            + ex.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        } 
    }

    private void abrirManterFuncionario() {
        new ManterFuncionarioPresenter();
    }

}
