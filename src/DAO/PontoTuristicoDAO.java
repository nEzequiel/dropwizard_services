/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.PontoTuristico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ezequiel
 */
public class PontoTuristicoDAO implements TDAO<PontoTuristico> {
    private Connection conn;
    private DataAccess database;
    
    public PontoTuristicoDAO(DataAccess dbAccess) throws ClassNotFoundException{
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
    public int Insert(PontoTuristico ponto) throws Exception {
        int retorno=0;
        String sqlCommand="INSERT INTO PontoTuristico "
                +"(nome,cidade,rua,numero,bairro,cep,abertura,fechamento)"+
                " values(?,?,?,?,?,?,?,?)";
        
        PreparedStatement stm=conn.prepareStatement(sqlCommand);
        stm.setString(1,ponto.nome);
        stm.setInt(2,ponto.idCidade);
        stm.setString(3,ponto.rua);
        stm.setInt(4,ponto.numero);
        stm.setString(5,ponto.bairro);
        stm.setInt(6,ponto.cep);
        stm.setDate(7,ponto.abertura);
        stm.setDate(8,ponto.fechamento);
        
        try{
           retorno=Execute(stm); 
        }catch(Exception ex){
            System.out.println("Erro ao inserir!!!" +ex.getMessage());
        }
        
        database.closeConnection(conn);
        stm.close();
        
        return retorno;
    }

    @Override
    public int Update(PontoTuristico ponto, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PontoTuristico> ToList() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PontoTuristico> ToList(PontoTuristico ponto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PontoTuristico Last() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int Delete(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PontoTuristico Get(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
