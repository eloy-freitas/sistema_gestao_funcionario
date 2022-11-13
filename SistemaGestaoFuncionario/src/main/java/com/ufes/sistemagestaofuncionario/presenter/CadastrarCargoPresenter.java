package com.ufes.sistemagestaofuncionario.presenter;

import com.ufes.sistemagestaofuncionario.model.Cargo;
import com.ufes.sistemagestaofuncionario.persistencia.repository.cargo.service.CargoService;
import com.ufes.sistemagestaofuncionario.persistencia.repository.cargo.service.ICargoService;
import com.ufes.sistemagestaofuncionario.view.cargo.CadastrarCargoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CadastrarCargoPresenter {
    
    private CadastrarCargoView view;
    private ICargoService cargoService;
    
    public CadastrarCargoPresenter(){
        view = new CadastrarCargoView();
        initListeners();
        try {
            this.cargoService = new CargoService();
        } 
        catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(view,
                    "Ocorreu um erro ao iniciar a tela de cadastro de cargos.\n\n"
                            + ex.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
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
        
        // Botão Salvar
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
        try {
            String nomeCargo = view.getTfCargo().getText();
            Cargo cargo = new Cargo(nomeCargo);
            if(cargoService.criar(cargo)){
                JOptionPane.showMessageDialog(view,
                        "Cargo " + cargo.getNome() + " salvo com sucesso.",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                view.dispose();
                // Para atualizar o combobox com o novo cargo inserido.
                new ManterFuncionarioPresenter();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(view,
                    "Ocorreu um erro ao cadastrar um novo cargo.\n\n"
                            + ex.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
