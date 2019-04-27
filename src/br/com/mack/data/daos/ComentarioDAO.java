/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mack.data.daos;

import br.com.mack.data.DataAccess;
import br.com.mack.data.TDAO;
import br.com.mack.domain.Comentario;
import java.sql.Connection;
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

    @Override
    public int insert(Comentario obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Comentario obj, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Comentario> toList() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Comentario> toList(Comentario obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Comentario last() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Comentario get(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
