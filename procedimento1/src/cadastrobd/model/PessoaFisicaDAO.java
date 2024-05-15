/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model;

import cadastro.model.util.ConectorBD;
import cadastro.model.util.SequenceManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Walber
 */
public class PessoaFisicaDAO {

    public void inserir(PessoaFisica pf) throws SQLException {
        int idPessoa = SequenceManager.getValue("idPessoa_Sequence");
        int idPessoaFisica = SequenceManager.getValue("idPessoaFisica");
        if (idPessoa != -1 && idPessoaFisica != -1) {
            String sqlPessoa = "insert into Pessoa(idPessoa,nome,logradouro, cidade, estado, telefone, email) values(?,?,?,?,?,?,?)";
            Connection conn = ConectorBD.getConnection();
            PreparedStatement psPessoa = ConectorBD.getPrepared(sqlPessoa);
            psPessoa.setInt(1, idPessoa);
            psPessoa.setString(2, pf.getNome());
            psPessoa.setString(3, pf.getLogradouro());
            psPessoa.setString(4, pf.getCidade());
            psPessoa.setString(5, pf.getEstado());
            psPessoa.setString(6, pf.getTelefone());
            psPessoa.setString(7, pf.getEmail());
            psPessoa.execute();

            String sqlPessoaFisica = "insert into PessoaFisica(idPessoaFisica, cpf, idPessoa)values(?,?,?)";
            PreparedStatement psPessoaFisica = ConectorBD.getPrepared(sqlPessoaFisica);
            psPessoaFisica.setInt(1, idPessoaFisica);
            psPessoaFisica.setString(2, pf.getCpf());
            psPessoaFisica.setInt(3, idPessoa);
            psPessoaFisica.execute();
            psPessoa.close();
            psPessoaFisica.close();
            conn.close();
        }
    }

    public void excluir(int id) throws SQLException {
        String sqlPessoaFisica = "delete from PessoaFisica where idPessoa=?";
        Connection conn = ConectorBD.getConnection();
        PreparedStatement psPessoaFisica = ConectorBD.getPrepared(sqlPessoaFisica);
        psPessoaFisica.setInt(1, id);
        psPessoaFisica.execute();

        String sqlPessoa = "delete from Pessoa where idPessoa=?";

        PreparedStatement psPessoa = ConectorBD.getPrepared(sqlPessoa);
        psPessoa.setInt(1, id);
        psPessoa.execute();

        psPessoa.close();
        psPessoaFisica.close();
        conn.close();
    }

    public void alterar(PessoaFisica pf) throws SQLException {
        String sqlPessoa = "update Pessoa set nome=?,logradouro=?, cidade=?,estado=?,telefone=?,email=? where idPessoa=?";
        Connection conn = ConectorBD.getConnection();
        PreparedStatement psPessoa = ConectorBD.getPrepared(sqlPessoa);
        psPessoa.setString(1, pf.getNome());
        psPessoa.setString(2, pf.getLogradouro());
        psPessoa.setString(3, pf.getCidade());
        psPessoa.setString(4, pf.getEstado());
        psPessoa.setString(5, pf.getTelefone());
        psPessoa.setString(6, pf.getEmail());
        psPessoa.setInt(7, pf.getId());

        psPessoa.execute();
        String sqlPessoaFisica = "update PessoaFisica set cpf=? where idPessoa=?";
        PreparedStatement psPessoaFisica = ConectorBD.getPrepared(sqlPessoaFisica);

        psPessoaFisica.setString(1, pf.getCpf());

        psPessoaFisica.setInt(2, pf.getId());

        psPessoaFisica.execute();
        psPessoa.close();
        psPessoaFisica.close();
        conn.close();
    }

    public PessoaFisica getPessoa(int id) throws SQLException {
        String sql = "select * from Pessoa p inner join PessoaFisica pf on p.idPessoa=pf.idPessoa where p.id=?";
        Connection conn = ConectorBD.getConnection();
        PreparedStatement ps = ConectorBD.getPrepared(sql);
        ps.setInt(1, id);
        ResultSet rs = ConectorBD.getSelect(ps);
        PessoaFisica pf = null;
        if (rs.next() == true) {
            int idPessoa = rs.getInt("idPessoa");
            String nome = rs.getString("nome");
            String logradouro = rs.getString("logradouro");
            String cidade = rs.getString("cidade");
            String estado = rs.getString("estado");
            String telefone = rs.getString("telefone");
            String email = rs.getString("email");
            String cpf = rs.getString("cpf");
            pf = new PessoaFisica(cpf, id, nome, logradouro, cidade, estado, telefone, email);

        }
        return pf;
    }

    public ArrayList<PessoaFisica> getPessoas() throws SQLException {
        String sql = "select * from Pessoa p inner join PessoaFisica pf on p.idPessoa=pf.idPessoa ";
        Connection conn = ConectorBD.getConnection();
        PreparedStatement ps = ConectorBD.getPrepared(sql);
        ResultSet rs = ConectorBD.getSelect(ps);
        PessoaFisica pf = null;
        ArrayList<PessoaFisica> pessoas = new ArrayList();
        while (rs.next() == true) {
            int idPessoa = rs.getInt("idPessoa");
            String nome = rs.getString("nome");
            String logradouro = rs.getString("logradouro");
            String cidade = rs.getString("cidade");
            String estado = rs.getString("estado");
            String telefone = rs.getString("telefone");
            String email = rs.getString("email");
            String cpf = rs.getString("cpf");
            pf = new PessoaFisica(cpf, idPessoa, nome, logradouro, cidade, estado, telefone, email);
            pessoas.add(pf);

        }
        return pessoas;
    }
}