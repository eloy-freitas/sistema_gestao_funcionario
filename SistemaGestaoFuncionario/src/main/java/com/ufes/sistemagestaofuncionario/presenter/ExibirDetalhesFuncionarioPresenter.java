package com.ufes.sistemagestaofuncionario.presenter;

import com.ufes.sistemagestaofuncionario.view.funcionario.ExibirDetalhesFuncionarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ExibirDetalhesFuncionarioPresenter {
    private ExibirDetalhesFuncionarioView view;

    public ExibirDetalhesFuncionarioPresenter() {
        view = new ExibirDetalhesFuncionarioView();
        desativaCampos();
        initListeners();
        view.setVisible(true);
    }
    
    private void desativaCampos(){
        view.getTfCargo().setEditable(false);
        view.getTfNome().setEditable(false);
        view.getFtfSalario().setEditable(false);
        view.getFtfDataAdmissao().setEditable(false);
        view.getTfFaltas().setEditable(false);
        view.getFtfDataNascimento().setEditable(false);
        view.getCheckBoxFuncionarioMes().setEnabled(false);
    }
    
    private void initListeners(){
        // Botão fechar
        view.getBtnFechar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
            }
        });
        
        // Botão excluir        
        view.getBtnExcluir().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });
        
        // Botão editar
        view.getBtnEditar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });
    }
    
    private void fechar(){
        view.dispose();
    }
    
    private void excluir(){
        int opt = JOptionPane.showConfirmDialog(view,
                    "Deseja realmente excluir este funcionário?", 
                    "Confirmar Exclusão",
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE);
        
        // Exclusão confirmada
        if(opt == JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(view,
                    "Funcionário excluído com sucesso!", 
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            view.dispose();
        }
    }
    
    private void editar(){
        new ManterFuncionarioPresenter();
    }
}
