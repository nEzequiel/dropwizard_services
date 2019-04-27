/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mack.data.daos;

import br.com.mack.data.DataAccess;
import br.com.mack.data.TDAO;
import br.com.mack.domain.Avaliacao;
import java.sql.Connection;
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

    @Override
    public int insert(Avaliacao obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Avaliacao obj, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Avaliacao> toList() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Avaliacao> toList(Avaliacao obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Avaliacao last() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Avaliacao get(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
