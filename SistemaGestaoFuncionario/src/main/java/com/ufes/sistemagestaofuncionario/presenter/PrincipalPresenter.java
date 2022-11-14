package com.ufes.sistemagestaofuncionario.presenter;

import com.ufes.sistemagestaofuncionario.model.Funcionario;
import com.ufes.sistemagestaofuncionario.persistencia.dao.cargo.CargoDAO;
import com.ufes.sistemagestaofuncionario.persistencia.dao.salario.SalarioDAO;
import com.ufes.sistemagestaofuncionario.persistencia.repository.cargo.CargoRepository;
import com.ufes.sistemagestaofuncionario.persistencia.repository.funcionario.FuncionarioRepository;
import com.ufes.sistemagestaofuncionario.service.calculadora.CalculadoraBonusService;
import com.ufes.sistemagestaofuncionario.service.calculadora.CalculadoraSalarioService;
import com.ufes.sistemagestaofuncionario.service.leitorarquivo.LeitorArquivoCSVService;
import com.ufes.sistemagestaofuncionario.view.PrincipalView;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PrincipalPresenter {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException, Exception {
        //new PrincipalView();
        //LeitorArquivoCSVService l = new LeitorArquivoCSVService();
        //List<Funcionario> funcionarios = l.lerArquivoFuncionario("/home/eloy/Documents/projetos/sistema_gestao_funcionario/ddl/funcionarios.csv");
        
        FuncionarioRepository f = new FuncionarioRepository();
        Funcionario func = f.buscarPorId(5L);
        CalculadoraBonusService cbs = new CalculadoraBonusService();
        func = cbs.calcular(func);
        CalculadoraSalarioService css = new CalculadoraSalarioService();
        func = css.calcularSalario(func);
        System.out.println(func.getSalarioTotal());
        SalarioDAO dao = new SalarioDAO();
        dao.criar(func);
  
    }
}
