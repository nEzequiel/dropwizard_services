/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mack.service.resources;


import br.com.mack.data.daos.AvaliacaoDAO;
import br.com.mack.domain.Avaliacao;
import br.com.mack.service.TResource;
import io.dropwizard.jersey.params.LongParam;
import java.sql.SQLException;
import java.util.List;
import io.dropwizard.jersey.*;
import io.dropwizard.jersey.params.*;
import javax.ws.rs.*;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ezequiel
 */
@Path("/avaliacao")
@Produces(MediaType.APPLICATION_JSON)
public class AvaliacaoResource implements TResource<Avaliacao>{

    
    private final AvaliacaoDAO avaliacaoDb;
    
    public AvaliacaoResource(AvaliacaoDAO dao){
        this.avaliacaoDb=dao;
    }
    
    @GET
    @Path("/getAll/{idPonto}")
    public List<Avaliacao> read(@PathParam("idPonto") LongParam idPonto) {
        try{
            return avaliacaoDb.toList(idPonto.get().intValue());
        }catch(Exception ex){
            throw new WebApplicationException("Não foi possivel listar os comentários !!!",404);   
        }
    }
    
    @GET
    @Override
    public List<Avaliacao> read() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @GET
    @Override
    @Path("{id}")
    public Avaliacao readThis(@PathParam("id") LongParam id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @PUT
    @Override
    @Path("{id}")
    public Avaliacao update(@PathParam("id") LongParam id, Avaliacao avaliacao)  {
        try {
            avaliacaoDb.update(avaliacao,id.get());
            return avaliacao;
        } catch (Exception ex) {
            throw new WebApplicationException("Não foi possivel atualizar o avaliacao !!!",500);   
        }
    }
    
    @POST
    @Override
    public Avaliacao create(Avaliacao avaliacao) {
        try {
            avaliacaoDb.insert(avaliacao);
            return avaliacao;
        } catch (Exception ex) {
            throw new WebApplicationException("Não foi possivel inserir o avaliacao !!!",500);   
        }
    }

    @DELETE
    @Override
    @Path("{id}")
    public Response delete( @PathParam("id") LongParam id) {
        try {
            avaliacaoDb.delete(id.get());
            return Response.ok().build();
            
        } catch (Exception ex) {
            throw new WebApplicationException("Avaliacao de id: "+id.get()+" não encontrado!",404);
        }
    }
    
}
