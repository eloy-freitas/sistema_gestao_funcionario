package com.ufes.sistemagestaofuncionario.service.leitorarquivo;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import java.io.IOException;
import java.util.List;

public interface ILeitorArquivoService {
    List<Funcionario> lerArquivoFuncionario(String nomeArquivo) throws IOException;
}
