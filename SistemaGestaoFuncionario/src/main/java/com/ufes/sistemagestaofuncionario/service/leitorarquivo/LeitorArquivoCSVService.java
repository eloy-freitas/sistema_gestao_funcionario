package com.ufes.sistemagestaofuncionario.service.leitorarquivo;

import com.ufes.sistemagestaofuncionario.adaptador.leitorarquivo.FileReaderCSVAdapter;
import com.ufes.sistemagestaofuncionario.adaptador.leitorarquivo.IFileReaderAdapter;
import com.ufes.sistemagestaofuncionario.model.Funcionario;
import java.io.IOException;
import java.util.List;


public class LeitorArquivoCSVService implements ILeitorArquivoService{

    private IFileReaderAdapter fileReader;
    public LeitorArquivoCSVService() {
    }
    
    @Override
    public List<Funcionario> lerArquivoFuncionario(String nomeArquivo) throws IOException {
        this.fileReader = new FileReaderCSVAdapter(nomeArquivo);
        return fileReader.readFuncionarioFile();
                
    }
    
}
