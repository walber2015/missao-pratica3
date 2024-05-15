/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastro.model.util;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author Walber
 */
public class ConectorBD {
    
static  String usuario = "loja";
static  String senha = "loja";
static  String url = "jdbc:sqlserver://localhost:1433;databaseName=loja;trustServerCertificate=true;";
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, usuario, senha);
    }
    public static PreparedStatement getPrepared(String sql) throws SQLException{
        Connection conn=getConnection();
        PreparedStatement ps=conn.prepareStatement(sql);
        return ps;
    }
    public static ResultSet getSelect(PreparedStatement ps) throws SQLException{
        ResultSet rs=ps.executeQuery();
        return rs;
    }
}
