/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mack.data.daos;

import br.com.mack.data.DataAccess;
import br.com.mack.data.TDAO;
import br.com.mack.domain.Cidade;
import br.com.mack.domain.PontoTuristico;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
    public ResultSet query(PreparedStatement stm) throws SQLException, ClassNotFoundException{
        ResultSet rs=stm.executeQuery();
        return rs;
    }
    
    public int execute(PreparedStatement stm) throws SQLException, ClassNotFoundException{
        int retorno=stm.executeUpdate();
        return retorno;
    }
    
    @Override
    public int insert(PontoTuristico ponto) throws Exception {
        int retorno=0;
        String sqlCommand="INSERT INTO PontoTuristico "
                +"(nome,cidade,rua,numero,bairro,cep,abertura,fechamento)"+
                " values(?,?,?,?,?,?,?,?)";
        
        PreparedStatement stm=conn.prepareStatement(sqlCommand);
        stm.setString(1,ponto.getNome());
        stm.setInt(2,ponto.getCidade().getId());
        stm.setString(3,ponto.getRua());
        stm.setInt(4,ponto.getNumero());
        stm.setString(5,ponto.getBairro());
        stm.setInt(6,ponto.getCep());
        stm.setTime(7,ponto.getAbertura());
        stm.setTime(8,ponto.getFechamento());
        
        try{
           retorno=execute(stm); 
        }catch(Exception ex){
            System.out.println("Erro ao inserir!!!" +ex.getMessage());
        }
        
        stm.close();
        
        return retorno;
    }

    @Override
    public int update(PontoTuristico ponto, Long id) throws Exception {
        int retorno=0;
        String sqlCommand="UPDATE PontoTuristico "
                +"set nome=?,cidade=?,rua=?, numero=?, bairro=?,cep=?,"+
                "abertura=?, fechamento=? where id=?";
                
        
        PreparedStatement stm=conn.prepareStatement(sqlCommand);
        stm.setString(1,ponto.getNome());
        stm.setInt(2,ponto.getCidade().getId());
        stm.setString(3,ponto.getRua());
        stm.setInt(4,ponto.getNumero());
        stm.setString(5,ponto.getBairro());
        stm.setInt(6,ponto.getCep());
        stm.setTime(7,ponto.getAbertura());
        stm.setTime(8,ponto.getFechamento());
        stm.setLong(9,id);
        
        try{
           retorno=execute(stm); 
        }catch(Exception ex){
            System.out.println("Erro ao atualizar!!!" +ex.getMessage());
        }
        
        
        stm.close();
        
        return retorno;
    }

    @Override
    public List<PontoTuristico> toList() throws Exception {
        List<PontoTuristico> pontos=null;
        String sqlCommand="select * from PontoTuristico";
        PreparedStatement stm=conn.prepareStatement(sqlCommand);
        
        try {
            ResultSet rs=query(stm);
            pontos=new ArrayList();
            
            while(rs.next()){
                int id=rs.getInt("id");
                String nome=rs.getString("nome");
                Cidade cidade=new Cidade(rs.getInt("cidade"));
                int numero=rs.getInt("numero");
                int cep=rs.getInt("cep");
                String rua=rs.getString("rua");
                String bairro=rs.getString("bairro");
                String abertura=rs.getString("abertura");
                String fechamento=rs.getString("fechamento");
            
                pontos.add(new PontoTuristico(id,nome,cidade,rua,numero,bairro,cep,abertura,fechamento));
            }
        } 
        catch (Exception ex) {
            System.out.println("Erro ao Buscar "+ex.getMessage());
        }
               
        stm.close();
        
        return pontos;
    }

    @Override
    public List<PontoTuristico> toList(PontoTuristico ponto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PontoTuristico last() throws Exception {
        PontoTuristico ponto=null;
        String sqlCommand="select * from PontoTuristico order by id desc fetch first 1 rows only";
        PreparedStatement stm=conn.prepareStatement(sqlCommand);            

        try {
            ResultSet rs=query(stm);
            rs.next();
            
            int id=rs.getInt("id");
            String nome=rs.getString("nome");
            Cidade cidade=new Cidade(rs.getInt("cidade"));
            String rua=rs.getString("numero");
            String bairro=rs.getNString("bairro");
            int numero=rs.getInt("numero");
            int cep=rs.getInt("cep");
            String abertura=rs.getString("abertura");
            String fechamento=rs.getString("fechamento");
            
            ponto =new PontoTuristico(id,nome,cidade,rua,numero,bairro,cep,abertura,fechamento);
        } 
        catch (Exception ex) {
            System.out.println("Erro ao buscar "+ex.getMessage());
        }
                
        stm.close();
        return ponto;
    }

    @Override
    public int delete(Long id) throws Exception {
        int retorno=0;
        String sqlCommand="Delete from PontoTuristico where id=?";
        PreparedStatement stm=conn.prepareStatement(sqlCommand); 
        stm.setLong(1,id);
        
        try {
            retorno=execute(stm);
        } 
        catch (Exception ex) {
            System.out.println("Erro ao excluir "+ex.getMessage());
        }
        
        stm.close();
        
        return retorno;
    }

    @Override
    public PontoTuristico get(int idPonto) throws Exception {
        PontoTuristico ponto=null;
        String sqlCommand="select * from PontoTuristico where id=?";
        PreparedStatement stm=conn.prepareStatement(sqlCommand);
        stm.setInt(1,idPonto);
        
        try {
            ResultSet rs=query(stm);
            
            while(rs.next()){
                int id=rs.getInt("id");
                String nome=rs.getString("nome");
                Cidade cidade=new Cidade(rs.getInt("cidade"));
                String rua=rs.getString("numero");
                String bairro=rs.getString("bairro");
                int numero=rs.getInt("numero");
                int cep=rs.getInt("cep");
                String abertura=rs.getString("abertura");
                String fechamento=rs.getString("fechamento");
                
                ponto =new PontoTuristico(id,nome,cidade,rua,numero,bairro,cep,abertura,fechamento);
            }
        } 
        catch (Exception ex) {
            System.out.println("Erro ao Buscar "+ex.getMessage());
        }

        
        stm.close();
        
        return ponto;
    }

}
