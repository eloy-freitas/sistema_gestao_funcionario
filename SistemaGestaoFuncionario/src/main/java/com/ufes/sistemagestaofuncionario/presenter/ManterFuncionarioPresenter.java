package com.ufes.sistemagestaofuncionario.presenter;

import com.ufes.sistemagestaofuncionario.view.funcionario.ManterFuncionarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManterFuncionarioPresenter {
    
    private ManterFuncionarioView view;
    
    public ManterFuncionarioPresenter(){
        view = new ManterFuncionarioView();
        initListeners();
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
    
    private void fechar(){
        view.dispose();
    }
    
}
