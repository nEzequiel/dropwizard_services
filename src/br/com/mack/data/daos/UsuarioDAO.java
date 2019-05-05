/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mack.data.daos;

import br.com.mack.data.DataAccess;
import br.com.mack.data.TDAO;
import br.com.mack.domain.Usuario;
import br.com.mack.exception.DatabaseCommandException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ezequiel
 */
public class UsuarioDAO implements TDAO<Usuario> {
    private Connection conn;
    private DataAccess database;
    
    public UsuarioDAO(DataAccess dbAccess) throws ClassNotFoundException{
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
    public int insert(Usuario usuario) throws Exception {
        int retorno=0;
        String sqlCommand="INSERT INTO Usuario (nome,sobrenome,email,telefone,login,senha) values(?,?,?,?,?,?)";
        
        PreparedStatement stm=conn.prepareStatement(sqlCommand);
        stm.setString(1,usuario.getNome());
        stm.setString(2,usuario.getSobrenome());
        stm.setString(3,usuario.getEmail());
        stm.setString(4,usuario.getTelefone());
        stm.setString(5,usuario.getLogin());
        stm.setString(6,usuario.getSenha());
        
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
    public int update(Usuario usuario, Long id) throws Exception {
        int retorno=0;
        String sqlCommand="Update Usuario set nome=?,sobrenome=?,email=?,telefone=?,login=?,senha=? where id=?";
        
        PreparedStatement stm=conn.prepareStatement(sqlCommand);
        stm.setString(1,usuario.getNome());
        stm.setString(2,usuario.getSobrenome());
        stm.setString(3,usuario.getEmail());
        stm.setString(4,usuario.getTelefone());
        stm.setString(5,usuario.getLogin());
        stm.setString(6,usuario.getSenha());
        stm.setInt(7, id.intValue());
        
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
    public List<Usuario> toList() throws Exception {
        List<Usuario> usuarios=null;
        String sqlCommand="select * from Usuario";
        try (PreparedStatement stm = conn.prepareStatement(sqlCommand)) {
            try {
                ResultSet rs= query(stm);
                usuarios=new ArrayList();
                
                while(rs.next()){
                    int id=rs.getInt("id");
                    String nome=rs.getString("nome");
                    String sobrenome=rs.getString("sobrenome");
                    String email=rs.getString("email");
                    String telefone=rs.getString("telefone");
                    String login=rs.getString("login");
                    String senha=rs.getString("senha");
                    usuarios.add(new Usuario(id,nome,sobrenome,email,telefone,login,senha));
                }
            }
            catch (Exception ex) {
                throw new DatabaseCommandException("Erro ao executar o comando sql"+ex.getMessage());
            }
            stm.close();
        }
        return usuarios;
    }
    @Override
    public int delete(Long id) throws Exception {
        int retorno=0;
        String sqlCommand="Delete from Usuario where id=?";
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
    public Usuario get(int id) throws Exception {
        Usuario usuario=null;
        String sqlCommand="select * from Usuario where id=?";
        PreparedStatement stm=conn.prepareStatement(sqlCommand);
        stm.setInt(1,id);
        
        try {
            ResultSet rs=query(stm);
            
            while(rs.next()){
                String nome=rs.getString("nome");
                String sobrenome=rs.getString("sobrenome");
                String email=rs.getString("email");
                String telefone=rs.getString("telefone");
                String login=rs.getString("login");
                String senha=rs.getString("senha");
                usuario=new Usuario(id,nome,sobrenome,email,telefone,login,senha);
            }
        } 
        catch (Exception ex) {
            throw new DatabaseCommandException("Erro ao executar o comando sql"+ex.getMessage());
        }
       
        stm.close();
        
        return usuario;
    }
}
