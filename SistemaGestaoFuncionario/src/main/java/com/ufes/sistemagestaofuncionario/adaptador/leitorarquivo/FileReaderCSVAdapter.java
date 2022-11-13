package com.ufes.sistemagestaofuncionario.adaptador.leitorarquivo;

import com.opencsv.CSVReader;
import com.ufes.sistemagestaofuncionario.model.Cargo;
import com.ufes.sistemagestaofuncionario.model.Funcionario;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class FileReaderCSVAdapter implements IFileReaderAdapter{
    
    private CSVReader csvReader;
    private FileReader filereader;
    public FileReaderCSVAdapter(String nomeArquivo) throws FileNotFoundException {
        if (!nomeArquivo.toLowerCase().endsWith("csv")) {
            throw new FileNotFoundException("Informe um arquivo CSV válido");
        }
        this.filereader = new FileReader(nomeArquivo);
        this.csvReader = new CSVReader(this.filereader);
    }

    @Override
    public List<Funcionario> readFuncionarioFile() throws IOException
    {
        List<Funcionario> funcionarios = new ArrayList<>();
        try {
            String[] nextRecord;
            csvReader.readNext();
            String nome;
            Cargo cargo = null;
            double salarioBase;
            double distanciaTrabalho;
            double tempoServico;
            LocalDate dataAdmissao;
            while ((nextRecord = csvReader.readNext()) != null) {
                nome = nextRecord[1];
                cargo = new Cargo(nextRecord[2]);
                salarioBase = Double.parseDouble(nextRecord[3]);
                distanciaTrabalho = Double.parseDouble(nextRecord[4]);
                tempoServico = Double.parseDouble(nextRecord[5]);
                dataAdmissao = LocalDate.parse(nextRecord[6]);
                funcionarios.add(new Funcionario(
                        nome,
                        cargo,
                        salarioBase, 
                        distanciaTrabalho, 
                        tempoServico, 
                        dataAdmissao
                    )
                );
            }
            return funcionarios;
        }
        catch (IOException io) {
            throw new IOException(io.getMessage());
        }
    }
}
