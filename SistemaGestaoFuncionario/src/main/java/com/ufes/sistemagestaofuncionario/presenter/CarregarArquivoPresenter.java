package com.ufes.sistemagestaofuncionario.presenter;

import com.ufes.sistemagestaofuncionario.adaptador.leitorarquivo.FileReaderCSVAdapter;
import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.persistencia.repository.cargo.service.CargoService;
import com.ufes.sistemagestaofuncionario.persistencia.repository.cargo.service.ICargoService;
import com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario.service.FuncionarioService;
import com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario.service.IFuncionarioService;
import com.ufes.sistemagestaofuncionario.service.leitorarquivo.ILeitorArquivoService;
import com.ufes.sistemagestaofuncionario.service.leitorarquivo.LeitorArquivoCSVService;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class CarregarArquivoPresenter {

    private JFileChooser fileChooser;
    private Path path;
    private ILeitorArquivoService leitorArquivoService;
    private IFuncionarioService funcionarioService;
    private ICargoService cargoService;

    public CarregarArquivoPresenter() {
        initServices();
        initFileChooser();
    }

    private void initFileChooser() {
        fileChooser = new JFileChooser();
        int opt = fileChooser.showOpenDialog(fileChooser.getParent());
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if (opt == JFileChooser.CANCEL_OPTION) {
            new PrincipalPresenter();
        }

        this.path = fileChooser.getSelectedFile().toPath();
        lerArquivo();
    }

    private void initServices() {
        try {
            funcionarioService = new FuncionarioService();
            cargoService = new CargoService();
            leitorArquivoService = new LeitorArquivoCSVService();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(fileChooser,
                    "Erro ao iniciar serviços.\n\n"
                    + ex.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void lerArquivo() {
        try {
            List<Funcionario> listaFuncionarios
                    = leitorArquivoService.lerArquivoFuncionario(this.path.toString());

            funcionarioService.salvar(listaFuncionarios);
            cargoService.criar(listaFuncionarios);
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            JOptionPane.showMessageDialog(fileChooser,
                    "Erro ao ler arquivo.\n\n"
                    + ex.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
