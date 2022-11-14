package com.ufes.sistemagestaofuncionario.presenter;

import com.ufes.sistemagestaofuncionario.adaptador.leitorarquivo.FileReaderCSVAdapter;
import com.ufes.sistemagestaofuncionario.adaptador.leitorarquivo.IFileReaderAdapter;
import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario.service.FuncionarioService;
import com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario.service.IFuncionarioService;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class CarregarArquivoPresenter {
    
    private JFileChooser fileChooser;
    private Path path;
    private IFileReaderAdapter leitorCSV;
    private IFuncionarioService funcionarioService;
    
    public CarregarArquivoPresenter(){
        initServices();
        initFileChooser();
    }
    
    private void initFileChooser(){
        fileChooser = new JFileChooser();
        int opt = fileChooser.showOpenDialog(fileChooser.getParent());
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        if(opt == JFileChooser.CANCEL_OPTION)
            new PrincipalPresenter();
        
        this.path = fileChooser.getSelectedFile().toPath();
        lerArquivo();
    }
    
    private void initServices(){
        try {
            funcionarioService = new FuncionarioService();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(fileChooser,
                    "Erro ao iniciar serviços.\n\n"
                            + ex.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void lerArquivo(){
        try {
            leitorCSV = new FileReaderCSVAdapter(this.path.toString());
            List<Funcionario> listaFuncionarios =
                    leitorCSV.readFuncionarioFile();
            
            funcionarioService.salvar(listaFuncionarios);
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            JOptionPane.showMessageDialog(fileChooser,
                    "Erro ao ler arquivo.\n\n"
                            + ex.getMessage(),
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
