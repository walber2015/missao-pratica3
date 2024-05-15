/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastro.model.util;
import  java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author Walber
 */
public class SequenceManager {
    public static int getValue(String nomeSequence) throws SQLException{
        String sql="select next value for "+ nomeSequence+" as proximoValor";
        Connection conn=ConectorBD.getConnection();
        PreparedStatement ps=ConectorBD.getPrepared(sql);
        ResultSet rs=ConectorBD.getSelect(ps);
        if(rs.next()){
            int proximoValor=rs.getInt("proximoValor");
            return proximoValor;
            
        }
        return -1;
    }
}
