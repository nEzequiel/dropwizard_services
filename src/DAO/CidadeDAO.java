/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Cidade;
import Entities.Estado;
import Entities.Pais;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ezequiel
 */
public class CidadeDAO implements TDAO<Cidade> {

    private Connection conn;
    private DataAccess database;
    
    public CidadeDAO(DataAccess dbAccess) throws ClassNotFoundException{
        this.database=dbAccess;
        this.conn=dbAccess.getConnection();
    }
    
    public ResultSet Query(PreparedStatement stm) throws SQLException, ClassNotFoundException{
        ResultSet rs=stm.executeQuery();
        return rs;
    }
    
    public int Execute(PreparedStatement stm) throws SQLException, ClassNotFoundException{
        
        int retorno=stm.executeUpdate();
        return retorno;
    }
    
    
    @Override
    public int Insert(Cidade obj) throws SQLException {
        int retorno=0;
        String sqlCommand="INSERT INTO CIDADE "+"(nome,estado,pais,populacao) values(?,?,?,?)";
        
        PreparedStatement stm=conn.prepareStatement(sqlCommand);
        stm.setString(1,obj.nome);
        stm.setInt(2,obj.idEstado);
        stm.setInt(3,obj.idPais);
        stm.setInt(4,obj.populacao);
        
        try {
            retorno=Execute(stm);
        } 
        catch (Exception ex) {
            System.out.println("Erro ao inserir "+ex.getMessage());
        }
        
        database.closeConnection(conn);
        stm.close();
        
        return retorno;
    }

    @Override
    public int Update(Cidade obj,Long id) throws SQLException {
        int retorno=0;
        String sqlCommand="Update CIDADE set nome=?, estado=?, pais=?, populacao=? where id=?";

        PreparedStatement stm=conn.prepareStatement(sqlCommand);
        stm.setString(1,obj.nome);
        stm.setInt(2,obj.idEstado);
        stm.setInt(3,obj.idPais);
        stm.setInt(4,obj.populacao);
        stm.setLong(5,id);
            
        try {
            retorno=Execute(stm);
        } 
        catch (Exception ex) {
            System.out.println("Erro ao atualizar "+ex.getMessage());
        }
        
        database.closeConnection(conn);
        stm.close();
        
        return retorno;
    }
    @Override
    public Cidade Get(int id) throws SQLException {
        Cidade cidade=null;
        String sqlCommand="select * from Cidade where id=?";
        PreparedStatement stm=conn.prepareStatement(sqlCommand);
        stm.setInt(1,id);
        
        try {
            ResultSet rs=Query(stm);
            
            while(rs.next()){
                String nome=rs.getString("nome");
                int estado=rs.getInt("estado");
                int pais=rs.getInt("pais");
                int populacao=rs.getInt("populacao");
                cidade =new Cidade(id,nome,estado,pais,populacao);
            }
        } 
        catch (Exception ex) {
            System.out.println("Erro ao Buscar "+ex.getMessage());
        }

        database.closeConnection(conn);
        stm.close();
        
        return cidade;
    }
    
    @Override
    public List<Cidade> ToList() throws SQLException {
        List<Cidade> cidade=null;
        String sqlCommand="select * from Cidade";
        PreparedStatement stm=conn.prepareStatement(sqlCommand);
        
        try {
            ResultSet rs= rs=Query(stm);
            cidade=new ArrayList();
            
            while(rs.next()){
                int id=rs.getInt("id");
                String nome=rs.getString("nome");
                int estado=rs.getInt("estado");
                int pais=rs.getInt("pais");
                int populacao=rs.getInt("populacao");
                cidade.add(new Cidade(id,nome,estado,pais,populacao));
            }
        } 
        catch (Exception ex) {
            System.out.println("Erro ao Buscar "+ex.getMessage());
        }
        
        database.closeConnection(conn);
        stm.close();
        
        return cidade;
    }

    @Override
    public List<Cidade> ToList(Cidade obj) throws SQLException {
        String sqlCommand="select * from Cidade where Cast(id as text) like '"
                +obj.id+"%' or nome Like '"
                +obj.nome+" or estado Like "+obj.estado+"% or pais Like "
                +obj.pais+" or populacao Like "+obj.populacao+"";
        PreparedStatement stm=conn.prepareStatement(sqlCommand);
        //add
        List<Cidade> cidade=new ArrayList();

        try {
            ResultSet rs=Query(stm);
            
            while(rs.next()){
                int id=rs.getInt("id");
                String nome=rs.getString("nome");
                int estado=rs.getInt("estado");
                int pais=rs.getInt("pais");
                int populacao=rs.getInt("populacao");
                cidade.add(new Cidade(id,nome,estado,pais,populacao));
            }
        } 
        catch (Exception ex) {
            System.out.println("Erro ao Buscar "+ex.getMessage());
        }
        
        database.closeConnection(conn);
        stm.close();
        
        return cidade;
    }

    @Override
    public Cidade Last() throws SQLException {
        Cidade cidade=null;
        String sqlCommand="select * from Cidade order by id desc fetch first 1 rows only";
        PreparedStatement stm=conn.prepareStatement(sqlCommand);            

        try {
            ResultSet rs=Query(stm);
            rs.next();

            int id=rs.getInt("id");
            String nome=rs.getString("nome");
            int estado=rs.getInt("estado");
            int pais=rs.getInt("pais");
            int populacao=rs.getInt("populacao");
            cidade=new Cidade(id,nome,estado,pais,populacao);
        } 
        catch (Exception ex) {
            System.out.println("Erro ao buscar "+ex.getMessage());
        }
        
        database.closeConnection(conn);
        stm.close();
        return cidade;
    }

    @Override
    public int Delete(Long id) throws SQLException {
        int retorno=0;
        String sqlCommand="Delete from Cidade where id=?";
        PreparedStatement stm=conn.prepareStatement(sqlCommand); 
        stm.setLong(1,id);
        
        try {
            retorno=Execute(stm);
        } 
        catch (Exception ex) {
            System.out.println("Erro ao excluir "+ex.getMessage());
        }
        
        database.closeConnection(conn);
        stm.close();
        
        return retorno;
    }
    
    public List<Estado> ListEstados(int idPais) throws SQLException {
        List<Estado> estados=null;
        String sqlCommand="select * from Estado where pais=?";
        PreparedStatement stm=conn.prepareStatement(sqlCommand); 
        stm.setInt(1,idPais);
        
        try {
            ResultSet rs= rs=Query(stm);
            estados=new ArrayList();
            
            while(rs.next()){
                int id=rs.getInt("id");
                String nome=rs.getString("nome");
                Pais pais=GetPais(idPais);
                estados.add(new Estado(id,nome,pais));
            }
        } 
        catch (Exception ex) {
            System.out.println("Erro ao Buscar "+ex.getMessage());
        }
        
        database.closeConnection(conn);
        stm.close();
        return estados;
    }
    
    public List<Pais> ListPaises() throws SQLException {
        List<Pais> paises=null;
        String sqlCommand="select * from Pais";
        PreparedStatement stm=conn.prepareStatement(sqlCommand); 

        try {
            ResultSet rs= rs=Query(stm);
            paises=new ArrayList();
            
            while(rs.next()){
                int id=rs.getInt("id");
                String nome=rs.getString("nome");
                paises.add(new Pais(id,nome));
            }
        } 
        catch (Exception ex) {
            System.out.println("Erro ao Buscar "+ex.getMessage());
        }
        
        database.closeConnection(conn);
        stm.close();
        
        return paises;
    }
    
    public Pais GetPais(int id) throws SQLException {
        Pais pais=null;
        String sqlCommand="select * from Pais where id="+id;
        PreparedStatement stm=conn.prepareStatement(sqlCommand); 
        stm.setInt(1,id);

        try {
            ResultSet rs= rs=Query(stm);
            rs.next();
            String nome=rs.getString("nome");
            pais=new Pais(id,nome);
        } 
        catch (Exception ex) {
            System.out.println("Erro ao Buscar "+ex.getMessage());
        }
        
        database.closeConnection(conn);
        stm.close();
        
        return pais;
    }
}
