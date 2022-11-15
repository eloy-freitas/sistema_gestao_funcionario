package com.ufes.sistemagestaofuncionario.presenter;

import com.ufes.sistemagestaofuncionario.view.sobre.SobreView;
import io.github.cdimascio.dotenv.Dotenv;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SobrePresenter {
    
    private SobreView view;
    private Dotenv env;
    private long qtdFuncionarios = 0;
    
    public SobrePresenter(){
        this.view = new SobreView();
        initListeners();
        initDotEnv();
        initLabels();
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
    
    private void initDotEnv(){
        this.env = Dotenv.configure()
                .directory("./resources")
                .filename(".sobre")
                .load();
    }
    
    private void initLabels(){
        view.getLbVersao().setText("Versão: " + env.get("VERSAO_PROGRAMA"));
        view.getLbPersistencia().setText("Database: " + env.get("SGBD_NAME"));
        view.getLbQtdFuncionarios().setText(String.valueOf(qtdFuncionarios));
    }
    
    private void fechar(){
        view.dispose();
    }
}
