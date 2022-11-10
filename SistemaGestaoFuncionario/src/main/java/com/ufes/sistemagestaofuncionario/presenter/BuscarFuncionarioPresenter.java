package com.ufes.sistemagestaofuncionario.presenter;

import com.ufes.sistemagestaofuncionario.view.funcionario.BuscarFuncionarioView;
import com.ufes.sistemagestaofuncionario.view.funcionario.ManterFuncionarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class BuscarFuncionarioPresenter {
    
    private BuscarFuncionarioView view;
    private DefaultTableModel tmFuncionarios;
    
    public BuscarFuncionarioPresenter(){
        view = new BuscarFuncionarioView();
        initTable();
        initListeners();
        view.setVisible(true);
    }
    
    private void initTable(){
        tmFuncionarios = new DefaultTableModel(
                new Object[][]{}, 
                new String[]{"ID", "Nome", "Idade", "Função", "Salário Base (R$)"}
        );
        view.getTblFuncionario().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tmFuncionarios.setNumRows(0);
               
        view.getTblFuncionario().setModel(tmFuncionarios);
    }
    
    private void initListeners(){
        // Botão fechar
        view.getBtnFechar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
            }
        });
        
        // Botão Novo
        view.getBtnNovo().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirManterFuncionario();
            }
        });
        
        // Botão visualizar
        view.getBtnVisualizar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVisualizarDetalhesFuncionario();
            }
        });
    }
    
    private void fechar(){
        view.dispose();
    }
    
    private void abrirManterFuncionario(){
        new ManterFuncionarioPresenter();
    }
    
    private void abrirVisualizarDetalhesFuncionario(){
        new ExibirDetalhesFuncionarioPresenter();
    }
    
}
