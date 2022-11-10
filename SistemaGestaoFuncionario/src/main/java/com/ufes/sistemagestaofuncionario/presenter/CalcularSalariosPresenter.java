package com.ufes.sistemagestaofuncionario.presenter;

import com.ufes.sistemagestaofuncionario.view.funcionario.CalcularSalariosView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcularSalariosPresenter {
    private CalcularSalariosView view;

    public CalcularSalariosPresenter() {
        view = new CalcularSalariosView();
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
