/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mack.data;

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
    
    private static DataAccess dbInstance;
    
    
    private DataAccess(){
        
    }
    public static synchronized DataAccess getInstance(){
        if(dbInstance==null)
            dbInstance=new DataAccess();
        return dbInstance;
    }

    
    public  Connection getConnection() throws ClassNotFoundException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/cidadesDB";
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
    
    public boolean closeConnection(Connection conn) throws SQLException{
        if(!conn.isClosed()){
            conn.close();
            return true;
        }            
        return false;
    }
    
}
