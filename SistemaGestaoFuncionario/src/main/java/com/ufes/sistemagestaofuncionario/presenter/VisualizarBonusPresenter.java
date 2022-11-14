package com.ufes.sistemagestaofuncionario.presenter;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.model.HistoricoBonus;
import com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario.service.FuncionarioService;
import com.ufes.sistemagestaofuncionario.view.bonus.VisualizarBonusView;
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

public class VisualizarBonusPresenter {
    private FuncionarioService funcionarioService;
    private VisualizarBonusView view;
    private DefaultTableModel tmBonus;
    private List<HistoricoBonus> listaBonus;
    private Long idFuncionario;
    
    public VisualizarBonusPresenter(Long id) {
        this.idFuncionario = id;
        view = new VisualizarBonusView();
        listaBonus = new ArrayList<>();
        initListeners();
        initServices();
        initTable();
        view.setVisible(true);
    }
    
    private void initListeners(){
        view.getBtnFechar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
            }
        });
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
    
    private void initTable(){
        tmBonus = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Data Cálculo", "Cargo", "Tipo de Bônus", 
                    "Valor Bônus"}
        );
        tmBonus.setNumRows(0);
        
        view.getTblBonus().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        view.getTblBonus().setModel(tmBonus);
        populaTabela();
    }
    
    private void fechar(){
        view.dispose();
    }
    
    private void limpaTabela() {
        int rowCount = tmBonus.getRowCount();
        if (rowCount > 0) {
            for (int i = rowCount - 1; i >= 0; i--) {
                tmBonus.removeRow(i);
            }
        }
    }

    private void populaTabela() {
        limpaTabela();
        // Percorrendo a lista para adicionar à tabela.
        try{
            this.listaBonus = funcionarioService.buscarFuncionarioBonus(this.idFuncionario);
        }catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(view,
                    "Erro ao inicializar janela de busca.\n\n"
                    + ex.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
        
        ListIterator<HistoricoBonus> iterator
                = this.listaBonus.listIterator();
        while (iterator.hasNext()) {
            HistoricoBonus bonus = iterator.next();
            tmBonus.addRow(new Object[]{
                bonus.getDataCalculo(),
                bonus.getNomeCargo(),
                bonus.getTipoBonus(),
                bonus.getValorBonus()
            });

        }
    }
    
}
