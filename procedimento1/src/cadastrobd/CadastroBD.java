/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastrobd;

import cadastro.model.util.ConectorBD;
import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaJuridicaDAO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Walber
 */
public class CadastroBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        PessoaFisica pf=new PessoaFisica("1111111111", 0, "Walber", "Rua lauro sodre", "abaetetuba", "Pa","1234-456", "walber2013ba@gmail.com");
        PessoaFisicaDAO pfDAO=new PessoaFisicaDAO();
        pfDAO.inserir(pf);
        System.out.println("Pessoa Fisica Inserida");
        PessoaFisica pfEditada=new PessoaFisica("22222222", 5,"Lucca", "Rua lauro sobre","Abaetetuba", "Pa", "33456578", "walber2013ba@gmail.com");
        pfDAO.alterar(pfEditada);
        System.out.println("Pessoa Fisica Alterada");
        ArrayList<PessoaFisica> pessoasFisicas = pfDAO.getPessoas();
        for (int i = 0; i < pessoasFisicas.size(); i++) {
            pessoasFisicas.get(i).exibir();
            
        }
        pfDAO.excluir(4);
        System.out.println("Pessoa Fisica Excluida");
        
        PessoaJuridica pj = new PessoaJuridica("33333333333333", 0, "Fintech","Rua lauro sodre", "abaetetuba", "Pa","1234-456", "fintech@gmail.com");
        PessoaJuridicaDAO pjDAO = new PessoaJuridicaDAO();
        pjDAO.inserir(pj);
        System.out.println("Pessoa Juridica Inserida");
        PessoaJuridica pjEditada = new PessoaJuridica("444444444444444444", 1,"Inovation 2D", "Rua lauro sobre","Abaetetuba", "Pa", "33456578", "inovation2d@gmail.com");
        pjDAO.alterar(pjEditada);
        System.out.println("Pessoa Juridica alterada");
        ArrayList<PessoaJuridica> pessoasJuridicas = pjDAO.getPessoas();
        for (int i = 0; i < pessoasJuridicas.size(); i++) {
            pessoasJuridicas.get(i).exibir();
            
        }
        pjDAO.excluir(14);
        System.out.println("Pessoa Juridica excluida");
    }
    
}
