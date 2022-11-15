package com.ufes.sistemagestaofuncionario.presenter;

import com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario.service.FuncionarioService;
import com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario.service.IFuncionarioService;
import com.ufes.sistemagestaofuncionario.view.sobre.SobreView;
import io.github.cdimascio.dotenv.Dotenv;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class SobrePresenter {
    
    private SobreView view;
    private Dotenv env;
    private long qtdFuncionarios = 0;
    private IFuncionarioService funcionarioService;
    
    public SobrePresenter(){
        this.view = new SobreView();
        initListeners();
        initDotEnv();
        initServices();
        contaFuncionarios();
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
    
    private void initServices(){
        try {
            this.funcionarioService = new FuncionarioService();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(view,
                    "Erro ao iniciar os serviços necessários.\n\n"
                            + ex.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void contaFuncionarios(){
        try {
            this.qtdFuncionarios = funcionarioService.contarFuncionarios();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(view,
                    "Erro ao contar os funcionários.\n\n"
                            + ex.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        } 
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
