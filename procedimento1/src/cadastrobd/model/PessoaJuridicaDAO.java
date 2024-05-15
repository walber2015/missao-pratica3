/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model;

import cadastro.model.util.ConectorBD;
import cadastro.model.util.SequenceManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Walber
 */
public class PessoaJuridicaDAO {
     public void inserir(PessoaJuridica pj) throws SQLException {
        int idPessoa = SequenceManager.getValue("idPessoa_Sequence");
        int idPessoaJuridica = SequenceManager.getValue("idPessoaJuridica");
        if (idPessoa != -1 && idPessoaJuridica != -1) {
            String sqlPessoa = "insert into Pessoa(idPessoa,nome,logradouro, cidade, estado, telefone, email) values(?,?,?,?,?,?,?)";
            Connection conn = ConectorBD.getConnection();
            PreparedStatement psPessoa = ConectorBD.getPrepared(sqlPessoa);
            psPessoa.setInt(1, idPessoa);
            psPessoa.setString(2, pj.getNome());
            psPessoa.setString(3, pj.getLogradouro());
            psPessoa.setString(4, pj.getCidade());
            psPessoa.setString(5, pj.getEstado());
            psPessoa.setString(6, pj.getTelefone());
            psPessoa.setString(7, pj.getEmail());
            psPessoa.execute();

            String sqlPessoaJuridica = "insert into PessoaJuridica(idPessoaJuridica, cnpj, idPessoa)values(?,?,?)";
            PreparedStatement psPessoaJuridica = ConectorBD.getPrepared(sqlPessoaJuridica);
            psPessoaJuridica.setInt(1, idPessoaJuridica);
            psPessoaJuridica.setString(2, pj.getCnpj());
            psPessoaJuridica.setInt(3, idPessoa);
            psPessoaJuridica.execute();
            psPessoa.close();
            psPessoaJuridica.close();
            conn.close();
        }
    }

    public void excluir(int id) throws SQLException {
        String sqlPessoaJuridica = "delete from PessoaJuridica where idPessoa=?";
        Connection conn = ConectorBD.getConnection();
        PreparedStatement psPessoaJuridica = ConectorBD.getPrepared(sqlPessoaJuridica);
        psPessoaJuridica.setInt(1, id);
        psPessoaJuridica.execute();

        String sqlPessoa = "delete from Pessoa where idPessoa=?";

        PreparedStatement psPessoa = ConectorBD.getPrepared(sqlPessoa);
        psPessoa.setInt(1, id);
        psPessoa.execute();

        psPessoa.close();
        psPessoaJuridica.close();
        conn.close();
    }

    public void alterar(PessoaJuridica pj) throws SQLException {
        String sqlPessoa = "update Pessoa set nome=?,logradouro=?, cidade=?,estado=?,telefone=?,email=? where idPessoa=?";
        Connection conn = ConectorBD.getConnection();
        PreparedStatement psPessoa = ConectorBD.getPrepared(sqlPessoa);
        psPessoa.setString(1, pj.getNome());
        psPessoa.setString(2, pj.getLogradouro());
        psPessoa.setString(3, pj.getCidade());
        psPessoa.setString(4, pj.getEstado());
        psPessoa.setString(5, pj.getTelefone());
        psPessoa.setString(6, pj.getEmail());
        psPessoa.setInt(7, pj.getId());

        psPessoa.execute();
        String sqlPessoaJuridica = "update PessoaJuridica set cnpj=? where idPessoa=?";
        PreparedStatement psPessoaJuridica = ConectorBD.getPrepared(sqlPessoaJuridica);

        psPessoaJuridica.setString(1, pj.getCnpj());

        psPessoaJuridica.setInt(2, pj.getId());

        psPessoaJuridica.execute();
        psPessoa.close();
        psPessoaJuridica.close();
        conn.close();
    }

    public PessoaJuridica getPessoa(int id) throws SQLException {
        String sql = "select * from Pessoa p inner join PessoaJuridica pj on p.idPessoa=pj.idPessoa where p.id=?";
        Connection conn = ConectorBD.getConnection();
        PreparedStatement ps = ConectorBD.getPrepared(sql);
        ps.setInt(1, id);
        ResultSet rs = ConectorBD.getSelect(ps);
        PessoaJuridica pj = null;
        if (rs.next() == true) {
            int idPessoa = rs.getInt("idPessoa");
            String nome = rs.getString("nome");
            String logradouro = rs.getString("logradouro");
            String cidade = rs.getString("cidade");
            String estado = rs.getString("estado");
            String telefone = rs.getString("telefone");
            String email = rs.getString("email");
            String cnpj = rs.getString("cnpj");
            pj = new PessoaJuridica(cnpj, id, nome, logradouro, cidade, estado, telefone, email);

        }
        return pj;
    }

    public ArrayList<PessoaJuridica> getPessoas() throws SQLException {
        String sql = "select * from Pessoa p inner join PessoaJuridica pj on p.idPessoa=pj.idPessoa ";
        Connection conn = ConectorBD.getConnection();
        PreparedStatement ps = ConectorBD.getPrepared(sql);
        ResultSet rs = ConectorBD.getSelect(ps);
        PessoaJuridica pj = null;
        ArrayList<PessoaJuridica> pessoas = new ArrayList();
        while (rs.next() == true) {
            int idPessoa = rs.getInt("idPessoa");
            String nome = rs.getString("nome");
            String logradouro = rs.getString("logradouro");
            String cidade = rs.getString("cidade");
            String estado = rs.getString("estado");
            String telefone = rs.getString("telefone");
            String email = rs.getString("email");
            String cnpj = rs.getString("cnpj");
            pj = new PessoaJuridica(cnpj, idPessoa, nome, logradouro, cidade, estado, telefone, email);
            pessoas.add(pj);

        }
        return pessoas;
    }
}
