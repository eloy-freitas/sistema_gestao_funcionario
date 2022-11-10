package com.ufes.sistemagestaofuncionario.presenter;

import com.ufes.sistemagestaofuncionario.view.funcionario.VisualizarBonusView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class VisualizarBonusPresenter {
    
    private VisualizarBonusView view;
    private DefaultTableModel tmBonus;

    public VisualizarBonusPresenter() {
        view = new VisualizarBonusView();
        initListeners();
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
    
    private void initTable(){
        tmBonus = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Data Cálculo", "Cargo", "Tipo de Bônus", 
                    "Valor Bônus"}
        );
        tmBonus.setNumRows(0);
        
        view.getTblBonus().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        view.getTblBonus().setModel(tmBonus);
    }
    
    private void fechar(){
        view.dispose();
    }
}
