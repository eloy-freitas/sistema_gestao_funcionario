package com.ufes.sistemagestaofuncionario.presenter;

import com.ufes.sistemagestaofuncionario.model.Cargo;
import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.persistencia.repository.cargo.service.CargoService;
import com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario.service.FuncionarioService;
import com.ufes.sistemagestaofuncionario.presenter.manterfuncionariostate.EdicaoState;
import com.ufes.sistemagestaofuncionario.presenter.manterfuncionariostate.InclusaoState;
import com.ufes.sistemagestaofuncionario.presenter.manterfuncionariostate.ManterFuncionarioPresenterState;
import com.ufes.sistemagestaofuncionario.presenter.manterfuncionariostate.VisualizacaoState;
import com.ufes.sistemagestaofuncionario.utils.conversores.ConversorCalendar;
import com.ufes.sistemagestaofuncionario.view.funcionario.ManterFuncionarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JOptionPane;

public class ManterFuncionarioPresenter {

    private ManterFuncionarioView view;
    private FuncionarioService funcionarioService;
    private CargoService cargoService;
    private List<Cargo> cargos;
    private Funcionario funcionario;
    private ManterFuncionarioPresenterState estado;

    public ManterFuncionarioPresenter() {
        view = new ManterFuncionarioView();
        view.getTfDataAdmissao().setEnabled(false);
        this.estado = new InclusaoState(this);
    }
    
    public ManterFuncionarioPresenter(Funcionario funcionario) {
        view = new ManterFuncionarioView();
        this.funcionario = funcionario;
        view.getTfDataAdmissao().setEnabled(false);
        this.estado = new VisualizacaoState(this);
    }
    
    public void initServices() {
        try {
            funcionarioService = new FuncionarioService();
            cargoService = new CargoService();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(view,
                    "Erro ao carregar dados iniciais necessários\n\n"
                    + ex.getMessage(),
                    "Erro de Inicialização",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void initListeners() {
        // Botão fechar
        view.getBtnCancelar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
            }
        });

        // Botão salvar
        view.getBtnSalvar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvar();
            }
        });
        
        // Botão editar
        view.getBtnEditar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizar();
            }
        });

    }

    public void populaCargos() {
        try {
            cargos = cargoService.buscarTodos();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(view,
                    "Ocorreu um erro ao carregar os cargos.\n\n"
                    + ex.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void initComboBox() {
        ListIterator<Cargo> iterator = cargos.listIterator();

        while (iterator.hasNext()) {
            Cargo cargo = iterator.next();
            view.getCbCargo().addItem(cargo.getNome());
        }

    }

    public void fechar() {
        view.dispose();
    }

    public Funcionario obterCampos(){
        Funcionario funcionario = null;
        try{
            funcionario = estado.obterCampos();
        }catch(Exception e){
            JOptionPane.showMessageDialog(view,
                    "Falha ao obter os campos do formulpario\n\n",
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
        
        return funcionario;
    }
    
    private void salvar()   {
        try {
            estado.salvar();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(view,
                    "Ocorreu um erro ao salvar o funcionário.\n\n"
                    + ex.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e){
            JOptionPane.showMessageDialog(view,
                    "Falha ao iniciar janela\n\n"
                    + e.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void initCampos() {
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
    
    private void atualizar(){
        try {
            estado.editar();
        } catch (Exception e){
            JOptionPane.showMessageDialog(view,
                    "Falha ao iniciar janela\n\n"
                    + e.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public ManterFuncionarioView getView() {
        return view;
    }

    public FuncionarioService getFuncionarioService() {
        return funcionarioService;
    }

    public CargoService getCargoService() {
        return cargoService;
    }

    public void setEstado(ManterFuncionarioPresenterState estado) {
        this.estado = estado;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public List<Cargo> getCargos() {
        return cargos;
    }
    
    
    
    
    
}
