package com.ufes.sistemagestaofuncionario.presenter;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario.service.FuncionarioService;
import com.ufes.sistemagestaofuncionario.view.funcionario.ExibirDetalhesFuncionarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ExibirDetalhesFuncionarioPresenter {

    private ExibirDetalhesFuncionarioView view;
    private Funcionario funcionario;
    private FuncionarioService funcionarioService;

    public ExibirDetalhesFuncionarioPresenter(Funcionario funcionario) {
        view = new ExibirDetalhesFuncionarioView();
        this.funcionario = funcionario;
        desativaCampos();
        initServices();
        initListeners();
        initCampos(this.funcionario);
        view.setVisible(true);
    }

    private void desativaCampos() {
        view.getTfCargo().setEditable(false);
        view.getTfNome().setEditable(false);
        view.getFtfSalario().setEditable(false);
        view.getTfFaltas().setEditable(false);
        view.getCheckBoxFuncionarioMes().setEnabled(false);
        view.getTfDataAdmissao().setEditable(false);
    }

    private void initListeners() {
        // Botão fechar
        view.getBtnFechar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
            }
        });

        // Botão excluir        
        view.getBtnExcluir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir(funcionario.getId());
            }
        });

        // Botão editar
        view.getBtnEditar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });
    }

    private void initServices() {
        try {
            this.funcionarioService = new FuncionarioService();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(view,
                    "Erro ao carregar serviços necessários."
                    + ex.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initCampos(Funcionario funcionario) {
        view.getTfCargo().setText(
                funcionario.getCargo().getNome());

        view.getFtfSalario().setText(Double.toString(
                funcionario.getSalarioBase()));

        view.getTfNome().setText(
                funcionario.getNome());

        view.getLbIdade().setText(Integer.toString(
                funcionario.getIdade()) + " anos");

        view.getTfDistanciaTrabalho().setText(Double.toString(
                funcionario.getDistanciaTrabalho()));

        DateTimeFormatter formatador
                = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        view.getTfDataAdmissao().setText(formatador.format(
                funcionario.getDataAdmissao()));
    }

    private void fechar() {
        view.dispose();
    }

    private void excluir(long id) {

        try {
            // Mensagem de confirmação
            int opt = JOptionPane.showConfirmDialog(view,
                    "Deseja realmente excluir o funcionário abaixo?\n"
                    + funcionario.getNome(),
                    "Confirmar Exclusão",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            // Exclusão confirmada
            if (opt == JOptionPane.YES_OPTION) {
                // Checando o retorno da exclusão
                if (funcionarioService.excluir(id)) {
                    JOptionPane.showMessageDialog(view,
                            "Funcionário excluído com sucesso!",
                            "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE);
                    view.dispose();
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(view,
                    "Erro ao excluir funcionário.\n\n"
                    + ex.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editar() {
        new ManterFuncionarioPresenter();
    }
}
