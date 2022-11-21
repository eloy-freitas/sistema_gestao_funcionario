package com.ufes.sistemagestaofuncionario.presenter;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario.service.FuncionarioService;
import com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario.service.IFuncionarioService;
import com.ufes.sistemagestaofuncionario.view.funcionario.BuscarFuncionarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class BuscarFuncionarioPresenter {

    private BuscarFuncionarioView view;
    private DefaultTableModel tmFuncionarios;
    private IFuncionarioService funcionarioService;
    private List<Funcionario> listaFuncionarios = new ArrayList();

    /*
        Construtor para quando esta presenter é chamada pela view principal.
        Irá listar todos os funcionários que estiverem no banco de dados.
     */
    public BuscarFuncionarioPresenter() {
        view = new BuscarFuncionarioView();
        initServices();
        try {
            // Carregando todos os funcionários.
            this.listaFuncionarios = funcionarioService.buscarTodos();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(view,
                    "Erro ao listar os funcionários.\n\n"
                    + ex.getMessage(),
                    "ERRO",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        initTabela();
        populaTabela();
        initListeners();
        view.setVisible(true);
    }

    private void initServices() {
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

    private void initTabela() {
        JTable tabela = view.getTblFuncionario();
        tmFuncionarios = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Nome", "Idade", "Função", "Salário Base (R$)"}
        );
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tmFuncionarios.setNumRows(0);

        tabela.setModel(tmFuncionarios);

    }

    private void limpaTabela() {
        int rowCount = tmFuncionarios.getRowCount();
        if (rowCount > 0) {
            for (int i = rowCount - 1; i >= 0; i--) {
                tmFuncionarios.removeRow(i);
            }
        }
    }

    private void populaTabela() {
        limpaTabela();
        // Percorrendo a lista para adicionar à tabela.
        ListIterator<Funcionario> iterator
                = this.listaFuncionarios.listIterator();
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

        // Botão Buscar
        view.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscar();
            }
        });

        // Botão Ver Bonus
        view.getBtnVerBonus().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVisualizarBonus();
            }
        });
    }

    private void fechar() {
        view.dispose();
    }

    private void abrirVisualizarDetalhesFuncionario() {
        JTable tabela = view.getTblFuncionario();
        int linha = tabela.getSelectedRow();
        Funcionario funcionario;
        try {
            Object id = tabela.getModel().getValueAt(linha, 0);
            funcionario = funcionarioService.buscarPorId(
                    Long.valueOf(id.toString()));
            fechar();
            new ExibirDetalhesFuncionarioPresenter(funcionario);
            
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(view,
                    "Erro ao buscar funcionário.\n\n"
                    + ex.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        } catch(ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(view,
                    "Você deve selecionar um funcionário.\n\n",
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirVisualizarBonus() {
        JTable tabela = view.getTblFuncionario();
        int linha = tabela.getSelectedRow();
        Funcionario funcionario;
        try {
            Object id = tabela.getModel().getValueAt(linha, 0);
            funcionario = funcionarioService.buscarPorId(
                    Long.valueOf(id.toString()));
            
            fechar();
            new VisualizarBonusPresenter(funcionario);
            
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(view,
                    "Erro ao buscar funcionário.\n\n"
                    + ex.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        } catch(ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(view,
                    "Você deve selecionar um funcionário.\n\n",
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirManterFuncionario() {
        fechar();
        new ManterFuncionarioPresenter();
    }

    private void buscar() {
        String nome = view.getTfNome().getText();
        if (!nome.isBlank()) {
            try {
                //fechar();
                //new BuscarFuncionarioPresenter();
                setListaFuncionarios(funcionarioService.buscarFuncionarioPorName(nome));
                populaTabela();
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(view,
                        "Erro ao buscar funcionário.\n\n"
                        + ex.getMessage(),
                        "ERRO",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(view,
                    "Informe o nome do funcionário a ser buscado",
                    "Campos em Branco",
                    JOptionPane.INFORMATION_MESSAGE);
            view.getTfNome().requestFocus();
        }
    }

    private void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
        this.listaFuncionarios = listaFuncionarios;
    }

}
