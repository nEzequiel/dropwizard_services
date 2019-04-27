/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mack.service.resources;

import br.com.mack.domain.Avaliacao;
import br.com.mack.service.TResource;
import io.dropwizard.jersey.params.LongParam;
import java.util.List;
import javax.ws.rs.core.Response;

/**
 *
 * @author ezequiel
 */
public class AvaliacaoResource implements TResource<Avaliacao>{

    @Override
    public List<Avaliacao> read() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Avaliacao readThis(LongParam id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Avaliacao readLast() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Avaliacao update(LongParam id, Avaliacao entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Avaliacao create(Avaliacao entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response delete(LongParam id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
