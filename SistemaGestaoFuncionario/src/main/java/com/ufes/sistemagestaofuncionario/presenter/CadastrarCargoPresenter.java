package com.ufes.sistemagestaofuncionario.presenter;

import com.ufes.sistemagestaofuncionario.view.cargo.CadastrarCargoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastrarCargoPresenter {
    public CadastrarCargoView view;
    
    public CadastrarCargoPresenter(){
        view = new CadastrarCargoView();
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
    }
    
    private void fechar(){
        view.dispose();
    }
}
