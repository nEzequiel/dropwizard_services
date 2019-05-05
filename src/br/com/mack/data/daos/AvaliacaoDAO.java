/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mack.data.daos;

import br.com.mack.data.DataAccess;
import br.com.mack.data.TDAO;
import br.com.mack.domain.Avaliacao;
import br.com.mack.domain.PontoTuristico;
import br.com.mack.domain.Usuario;
import br.com.mack.exception.DatabaseCommandException;
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
public class AvaliacaoDAO implements TDAO<Avaliacao> {
    private Connection conn;
    private DataAccess database;
    
    public AvaliacaoDAO(DataAccess dbAccess) throws ClassNotFoundException{
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
    public int insert(Avaliacao avaliacao) throws Exception {
        int retorno=0;
        String sqlCommand="INSERT INTO Avaliacao (usuario,pontoTuristico,dataAvaliacao,valor) values(?,?,?,?)";
        
        PreparedStatement stm=conn.prepareStatement(sqlCommand);
        stm.setInt(1,avaliacao.getUsuario().getId());
        stm.setInt(2,avaliacao.getPonto().getId());
        stm.setDate(3,avaliacao.getData());
        stm.setInt(4,avaliacao.getValor());
        
        try {
            retorno=execute(stm);
        } 
        catch (Exception ex) {
            throw new DatabaseCommandException("Erro ao executar o comando sql"+ex.getMessage());
        }
               
        stm.close();
        
        return retorno;
    }

    @Override
    public int update(Avaliacao avaliacao, Long id) throws Exception {
        int retorno=0;
        String sqlCommand="Update Avaliacao set usuario=?,pontoTuristico=?,dataAvaliacao=?,valor=? where id=?";
        
        PreparedStatement stm=conn.prepareStatement(sqlCommand);
        stm.setInt(1,avaliacao.getUsuario().getId());
        stm.setInt(2,avaliacao.getPonto().getId());
        stm.setDate(3,avaliacao.getData());
        stm.setInt(4,avaliacao.getValor());
        stm.setInt(5,id.intValue());
        
        try {
            retorno=execute(stm);
        } 
        catch (Exception ex) {
            throw new DatabaseCommandException("Erro ao executar o comando sql"+ex.getMessage());
        }
               
        stm.close();
        
        return retorno;
    }
    
    public List<Avaliacao> toList(int idPonto) throws Exception {
        List<Avaliacao> avaliacoes=null;
        String sqlCommand="select * from Avaliacao where pontoTuristico=?";
        PreparedStatement stm=conn.prepareStatement(sqlCommand);
        stm.setInt(1, idPonto);
        try {
            ResultSet rs=query(stm);
            avaliacoes=new ArrayList();
            
            while(rs.next()){
                
                int id=rs.getInt("id");
                Usuario usuario=new Usuario(rs.getInt("usuario"));
                PontoTuristico ponto=new PontoTuristico(rs.getInt("pontoTuristico"));
                Date data=rs.getDate("dataAvaliacao");
                int valor=rs.getInt("valor");
            
                avaliacoes.add(new Avaliacao(id,usuario,ponto,data,valor));
            }
        } 
        catch (SQLException | ClassNotFoundException ex) {
            throw new DatabaseCommandException("Erro na busca dos dados!!!"+ex.getMessage());
        }
               
        stm.close();
        
        return avaliacoes;
    }
    
    @Override
    public List<Avaliacao> toList() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Long id) throws Exception {
        int retorno=0;
        String sqlCommand="Delete from Avaliacao where id=?";
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
    public Avaliacao get(int id) throws Exception {
        Avaliacao avaliacao=null;
        String sqlCommand="select * from Avaliacao where id=?";
        PreparedStatement stm=conn.prepareStatement(sqlCommand);
        stm.setInt(1, id);
        try {
            ResultSet rs=query(stm);
            
            while(rs.next()){
                Usuario usuario=new Usuario(rs.getInt("usuario"));
                PontoTuristico ponto=new PontoTuristico(rs.getInt("pontoTuristico"));
                Date data=rs.getDate("dataAvaliacao");
                int valor=rs.getInt("valor");
            
                avaliacao=new Avaliacao(id,usuario,ponto,data,valor);
            }
        } 
        catch (Exception ex) {
            throw new DatabaseCommandException("Erro na busca dos dados!!!"+ex.getMessage());
        }
               
        stm.close();
        
        return avaliacao;
    }

    
}
