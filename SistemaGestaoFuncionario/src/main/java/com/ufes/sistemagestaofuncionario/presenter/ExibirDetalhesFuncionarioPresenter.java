package com.ufes.sistemagestaofuncionario.presenter;

import com.ufes.sistemagestaofuncionario.model.Cargo;
import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.persistencia.repository.cargo.service.CargoService;
import com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario.service.FuncionarioService;
import com.ufes.sistemagestaofuncionario.view.funcionario.ExibirDetalhesFuncionarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JOptionPane;

public class ExibirDetalhesFuncionarioPresenter {

    private ExibirDetalhesFuncionarioView view;
    private Funcionario funcionario;
    private FuncionarioService funcionarioService;
    private CargoService cargoService;
    private List<Cargo> listaCargos;

    public ExibirDetalhesFuncionarioPresenter(Funcionario funcionario) {
        view = new ExibirDetalhesFuncionarioView();
        this.funcionario = funcionario;
        desativaCampos();
        initServices();
        initListeners();
        populaCargos();
        initComboBox();
        initCampos(this.funcionario);
        view.setVisible(true);
    }

    private void desativaCampos() {
        view.getCbCargo().setEditable(false);
        view.getTfNome().setEditable(false);
        view.getFtfSalario().setEditable(false);
        view.getTfFaltas().setEditable(false);
        view.getCheckBoxFuncionarioMes().setEnabled(false);
        view.getTfDataAdmissao().setEditable(false);
        view.getTfDistanciaTrabalho().setEditable(false);
    }

    private void ativaCampos() {
        view.getCbCargo().setEditable(true);
        view.getTfNome().setEditable(true);
        view.getFtfSalario().setEditable(true);
        view.getTfFaltas().setEditable(true);
        view.getCheckBoxFuncionarioMes().setEnabled(true);
        view.getTfDataAdmissao().setEditable(false);
        view.getTfDistanciaTrabalho().setEditable(true);
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
            this.cargoService = new CargoService();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(view,
                    "Erro ao carregar serviços necessários."
                    + ex.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initCampos(Funcionario funcionario) {
        view.getCbCargo().setSelectedItem(
                this.funcionario.getCargo().getNome());

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

    private void populaCargos() {
        try {
            listaCargos = cargoService.buscarTodos();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(view,
                    "Ocorreu um erro ao carregar os cargos.\n\n"
                    + ex.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComboBox() {
        ListIterator<Cargo> iterator = listaCargos.listIterator();

        while (iterator.hasNext()) {
            Cargo cargo = iterator.next();
            view.getCbCargo().addItem(cargo.getNome());
        }

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
                    fechar();
                    new BuscarFuncionarioPresenter();
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

    private void obterCampos() {
        this.funcionario.setNome(view.getTfNome().getText());
        this.funcionario.setSalarioBase(Double.valueOf(
                view.getFtfSalario().getText()));
        this.funcionario.setDistanciaTrabalho(Double.valueOf(
                view.getTfDistanciaTrabalho().getText()));
        for (Cargo cargo : listaCargos) {
            if (view.getCbCargo().getSelectedItem().toString()
                    .equals(cargo.getNome())) {
                this.funcionario.setCargo(cargo);
            }
        }
    }

    private void editar() {
        ativaCampos();
        view.getBtnEditar().setText("Salvar Alterações");
        view.getBtnEditar().addActionListener((ActionEvent e) -> {
            atualizar();
        });
    }

    private void atualizar() {
        obterCampos();
        try {
            funcionarioService.atualizar(this.funcionario);
            cargoService.atualizar(this.funcionario);
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(view,
                    "Erro ao atualizar o funcionário.\n\n"
                    + ex.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            fechar();
            new BuscarFuncionarioPresenter();
        }
    }
}
