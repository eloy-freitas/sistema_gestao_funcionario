package com.ufes.sistemagestaofuncionario.presenter;

import com.ufes.sistemagestaofuncionario.view.funcionario.ManterFuncionarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ManterFuncionarioPresenter {
    
    private ManterFuncionarioView view;
    
    public ManterFuncionarioPresenter(){
        view = new ManterFuncionarioView();
        initListeners();
        view.setVisible(true);
    }
    
    private void initListeners(){
        // Botão fechar
        view.getBtnFechar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
            }
        });
        
        // Botão salvar
        view.getBtnSalvar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                salvar();
            }
        });
    }
    
    private void fechar(){
        view.dispose();
    }
    
    private void salvar(){
        JOptionPane.showMessageDialog(view,
                "Funcionário <<nome>> salvo com sucesso!", 
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
        view.dispose();
    }
}
