/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author ezequiel
 */
public class DataAccess {
    
    public Connection OpenDb() throws ClassNotFoundException{
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String url="jdbc:derby://localhost:1527/BDProjeto";
            String user="ezequiel";
            String pass="123456";
            Connection conn=DriverManager.getConnection(url,user,pass);
            return conn;
        }
        catch(SQLException ex){
            System.out.println("Não foi possivel efetuar a conexao"+ex.getMessage());
        }
        return null;
    }
    
    public boolean CloseDb(Connection conn) throws SQLException{
        if(!conn.isClosed()){
            conn.close();
            return true;
        }            
        return false;
    }
    
    public ResultSet Query(String query) throws SQLException, ClassNotFoundException{
        Connection conn=OpenDb();
        PreparedStatement stm=conn.prepareStatement(query);
        ResultSet rs=stm.executeQuery();
        return rs;
    }
    
    public int Update(String query) throws SQLException, ClassNotFoundException{
        Connection conn=OpenDb();
        PreparedStatement stm=conn.prepareStatement(query);
        int retorno=stm.executeUpdate();
        CloseDb(conn);
        return retorno;
    }
    
}
