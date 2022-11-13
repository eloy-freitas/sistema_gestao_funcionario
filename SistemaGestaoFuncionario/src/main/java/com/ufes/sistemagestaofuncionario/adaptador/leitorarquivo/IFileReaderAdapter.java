package com.ufes.sistemagestaofuncionario.adaptador.leitorarquivo;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import java.io.IOException;
import java.util.List;


public interface IFileReaderAdapter {
    List<Funcionario> readFuncionarioFile() throws IOException;

}
