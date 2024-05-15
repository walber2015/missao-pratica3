/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastrobd;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaJuridicaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Walber
 */
public class CadastroBD {

    static PessoaFisicaDAO pfDAO = new PessoaFisicaDAO();
    static PessoaJuridicaDAO pjDAO = new PessoaJuridicaDAO();

    public static void main(String[] args) throws SQLException {
        menu();
    }

    public static void menu() throws SQLException {
        System.out.println("=================\n 1-Incluir Pessoa\n 2-Alterar Pessoa \n 3-Excluir Pessoa \n 4-Buscar pelo Id \n 5-Exibir Todos \n 0-Finalizar Programa\n ==================");
        Scanner leitor = new Scanner(System.in);
        int opcaoDoUsuario = leitor.nextInt();
        if (opcaoDoUsuario >= 1 && opcaoDoUsuario <= 5) {

            System.out.println(" F-Pessoa Fisica \n J-Pessoa Juridica");
            String opcaoDoUsuario2 = leitor.next();
            if (opcaoDoUsuario == 1 && opcaoDoUsuario2.equals("F")) {
                incluirPessoaFisica();
                menu();
            } else if (opcaoDoUsuario == 1 && opcaoDoUsuario2.equals("J")) {
                incluirPessoaJuridica();
                menu();
            } else if (opcaoDoUsuario == 2 && opcaoDoUsuario2.equals("F")) {
                alterarPessoaFisica();
                menu();
            } else if (opcaoDoUsuario == 2 && opcaoDoUsuario2.equals("J")) {
                alterarPessoaJuridica();
                menu();
            } else if (opcaoDoUsuario == 3 && opcaoDoUsuario2.equals("F")) {
                excluirPessoaFisica();
                menu();
            } else if (opcaoDoUsuario == 3 && opcaoDoUsuario2.equals("J")) {
                excluirPessoaJuridica();
                menu();
            } else if (opcaoDoUsuario == 4 && opcaoDoUsuario2.equals("F")) {
                obterPeloIdPessoaFisica();
                menu();
            } else if (opcaoDoUsuario == 4 && opcaoDoUsuario2.equals("J")) {
                obterPeloIdPessoaJuridica();
                menu();
            } else if (opcaoDoUsuario == 5 && opcaoDoUsuario2.equals("F")) {
                obterTodosPessoaFisica();
                menu();
            } else if (opcaoDoUsuario == 5 && opcaoDoUsuario2.equals("J")) {
                obterTodosPessoaJuridica();
                menu();
            }
        } else if (opcaoDoUsuario == 0) {
            System.out.println("Finalizando o programa");
        }
    }

    public static void incluirPessoaFisica() throws SQLException {
        Scanner leitor = new Scanner(System.in);
        System.out.println("nome:");
        String nomeProprio = leitor.next();
        System.out.println("logradouro:");
        String logradouro = leitor.next();
        System.out.println("cidade:");
        String cidade = leitor.next();
        System.out.println("estado:");
        String estado = leitor.next();
        System.out.println("telefone");
        String telefone = leitor.next();
        System.out.println("email:");
        String email = leitor.next();
        System.out.println("CPF:");
        String cpf = leitor.next();

        PessoaFisica pessoa = new PessoaFisica(cpf, 0, nomeProprio, logradouro, cidade, estado, telefone, email);
        pfDAO.inserir(pessoa);
        System.out.println("Pessoa Fisica incluida");
    }

    public static void incluirPessoaJuridica() throws SQLException {

        Scanner leitor = new Scanner(System.in);
        System.out.println("nome:");
        String nomeProprio = leitor.next();
        System.out.println("logradouro:");
        String logradouro = leitor.next();
        System.out.println("cidade:");
        String cidade = leitor.next();
        System.out.println("estado:");
        String estado = leitor.next();
        System.out.println("telefone");
        String telefone = leitor.next();
        System.out.println("email:");
        String email = leitor.next();
        System.out.println("CNPJ:");
        String cnpj = leitor.next();
        PessoaJuridica pessoa = new PessoaJuridica(cnpj, 0, nomeProprio, logradouro, cidade, estado, telefone, email);
        pjDAO.inserir(pessoa);
        System.out.println("Pessoa Juridica incluida");
    }

    public static void alterarPessoaFisica() throws SQLException {
        System.out.println("id:");
        Scanner leitor = new Scanner(System.in);
        int numeroId = leitor.nextInt();
        PessoaFisica pessoa = pfDAO.getPessoa(numeroId);
        pessoa.exibir();
        System.out.println("nome:");
        String nomeProprio = leitor.next();
        System.out.println("logradouro:");
        String logradouro = leitor.next();
        System.out.println("cidade:");
        String cidade = leitor.next();
        System.out.println("estado:");
        String estado = leitor.next();
        System.out.println("telefone");
        String telefone = leitor.next();
        System.out.println("email:");
        String email = leitor.next();
        System.out.println("CPF:");
        String cpf = leitor.next();

        PessoaFisica pessoaAlterada = new PessoaFisica(cpf, numeroId, nomeProprio, logradouro, cidade, estado, telefone, email);
        pfDAO.alterar(pessoaAlterada);
        System.out.println(" A pessoa foi alterada");

    }

    public static void alterarPessoaJuridica() throws SQLException {
        System.out.println("id:");
        Scanner leitor = new Scanner(System.in);
        int numeroId = leitor.nextInt();
        PessoaJuridica pessoa = pjDAO.getPessoa(numeroId);
        pessoa.exibir();
        System.out.println("nome:");
        String nomeProprio = leitor.next();
        System.out.println("logradouro:");
        String logradouro = leitor.next();
        System.out.println("cidade:");
        String cidade = leitor.next();
        System.out.println("estado:");
        String estado = leitor.next();
        System.out.println("telefone");
        String telefone = leitor.next();
        System.out.println("email:");
        String email = leitor.next();
        System.out.println("CNPJ:");
        String cnpj = leitor.next();
        PessoaJuridica pessoaAlterada = new PessoaJuridica(cnpj, numeroId, nomeProprio, logradouro, cidade, estado, telefone, email);
        pjDAO.alterar(pessoaAlterada);
        System.out.println(" A pessoa foi alterada");
    }

    public static void excluirPessoaFisica() throws SQLException {
        System.out.println("id:");
        Scanner leitor = new Scanner(System.in);
        int numeroId = leitor.nextInt();
        pfDAO.excluir(numeroId);
        System.out.println(" A pessoa foi excluida");
    }

    public static void excluirPessoaJuridica() throws SQLException {
        System.out.println("id:");
        Scanner leitor = new Scanner(System.in);
        int numeroId = leitor.nextInt();
        pjDAO.excluir(numeroId);
        System.out.println(" A pessoa foi excluida");
    }

    public static void obterPeloIdPessoaFisica() throws SQLException {
        System.out.println("id:");
        Scanner leitor = new Scanner(System.in);
        int numeroId = leitor.nextInt();
        PessoaFisica pessoa = pfDAO.getPessoa(numeroId);
        pessoa.exibir();

    }

    public static void obterPeloIdPessoaJuridica() throws SQLException {
        System.out.println("id:");
        Scanner leitor = new Scanner(System.in);
        int numeroId = leitor.nextInt();
        PessoaJuridica pessoa = pjDAO.getPessoa(numeroId);
        pessoa.exibir();
    }

    public static void obterTodosPessoaFisica() throws SQLException {
        ArrayList<PessoaFisica> pessoas = pfDAO.getPessoas();
        for (int i = 0; i < pessoas.size(); i++) {
            pessoas.get(i).exibir();

        }

    }

    public static void obterTodosPessoaJuridica() throws SQLException {
        ArrayList<PessoaJuridica> pessoas = pjDAO.getPessoas();
        for (int i = 0; i < pessoas.size(); i++) {
            pessoas.get(i).exibir();
        }
    }
}
