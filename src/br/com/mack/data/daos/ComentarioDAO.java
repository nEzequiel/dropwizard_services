/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mack.data.daos;

import br.com.mack.data.DataAccess;
import br.com.mack.data.TDAO;
import br.com.mack.domain.Comentario;
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
public class ComentarioDAO implements TDAO<Comentario> {
    private Connection conn;
    private DataAccess database;
    
    public ComentarioDAO(DataAccess dbAccess) throws ClassNotFoundException{
        this.database=dbAccess;
        this.conn=dbAccess.getConnection();
    }
    
    public ResultSet query(PreparedStatement stm) throws SQLException, ClassNotFoundException{
        ResultSet rs=stm.executeQuery();
        return rs;
    }
    
    public int execute(PreparedStatement stm) throws SQLException, ClassNotFoundException, DatabaseCommandException{
        try{
            int retorno=stm.executeUpdate();
            return retorno;
        }
        catch(Exception ex){
            throw new DatabaseCommandException("Erro ao executar o comando sql"+ex.getMessage());
        }
    }

    @Override
    public int insert(Comentario comentario) throws Exception {
        int retorno=0;
        String sqlCommand="Insert into Comentario (usuario,pontoTuristico,dataAvaliacao,texto) values(?,?,?,?)";
        PreparedStatement stm= conn.prepareStatement(sqlCommand);
        stm.setInt(1, comentario.getUsuario().getId());
        stm.setInt(2,comentario.getPonto().getId());
        stm.setDate(3, comentario.getData());
        stm.setString(4,comentario.getTexto());
        try{
            retorno=execute(stm);
        }catch(Exception ex){
            throw new DatabaseCommandException("Erro ao executar o comando sql"+ex.getMessage());
        }
        return retorno;
    }   

    @Override
    public int update(Comentario comentario, Long id) throws Exception {
        int retorno=0;
        String sqlCommand="Update Comentario set usuario=?, pontoTuristico=?,dataAvaliacao=?,texto=? where id=? ";
        PreparedStatement stm= conn.prepareStatement(sqlCommand);
        stm.setInt(1, comentario.getUsuario().getId());
        stm.setInt(2,comentario.getPonto().getId());
        stm.setDate(3, comentario.getData());
        stm.setString(4,comentario.getTexto());
        stm.setInt(5,id.intValue());
        try{
            retorno=execute(stm);
        }catch(SQLException | ClassNotFoundException ex){
            throw new DatabaseCommandException("Erro ao executar o comando sql"+ex.getMessage());
        }
        return retorno;
    }

    public List<Comentario> toList(int idPonto) throws Exception {
        List<Comentario> comentarios=null;
        String sqlCommand="select * from Comentario where pontoTuristico=? order by id desc";
        PreparedStatement stm=conn.prepareStatement(sqlCommand);
        stm.setInt(1, idPonto);
        try {
            ResultSet rs=query(stm);
            comentarios=new ArrayList();
            
            while(rs.next()){
                
                int id=rs.getInt("id");
                Usuario usuario=new Usuario(rs.getInt("usuario"));
                PontoTuristico ponto=new PontoTuristico(rs.getInt("pontoTuristico"));
                Date data=rs.getDate("dataAvaliacao");
                String texto=rs.getString("texto");
            
                comentarios.add(new Comentario(id,usuario,ponto,data,texto));
            }
        } 
        catch (SQLException | ClassNotFoundException ex) {
            throw new DatabaseCommandException("Erro ao executar o comando sql"+ex.getMessage());
        }
               
        stm.close();
        
        return comentarios;
    }


    @Override
    public int delete(Long id) throws Exception {
        int retorno=0;
        String sqlCommand="Delete from Comentario where id=?";
        PreparedStatement stm=conn.prepareStatement(sqlCommand); 
        stm.setLong(1,id);
        
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
    public Comentario get(int id) throws Exception {
        Comentario comentario=null;
        String sqlCommand="select * from Comentario where id=?";
        PreparedStatement stm=conn.prepareStatement(sqlCommand);            
        stm.setInt(1, id);
        try {
            ResultSet rs=query(stm);
            rs.next();

            Usuario usuario=new Usuario(rs.getInt("usuario"));
            PontoTuristico ponto=new PontoTuristico(rs.getInt("pontoTuristico"));
            Date data=rs.getDate("dataAvaliacao");
            String texto=rs.getString("texto");
            comentario=new Comentario(id,usuario,ponto,data,texto);
        } 
        catch (Exception ex) {
            throw new DatabaseCommandException("Erro ao executar o comando sql"+ex.getMessage());
        }
                
        stm.close();
        return comentario;
    }

    @Override
    public List<Comentario> toList() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
