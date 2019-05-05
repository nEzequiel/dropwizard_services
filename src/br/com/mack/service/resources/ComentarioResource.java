/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mack.service.resources;

import br.com.mack.data.daos.ComentarioDAO;
import br.com.mack.domain.Comentario;
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
@Path("/comentario")
@Produces(MediaType.APPLICATION_JSON)
public class ComentarioResource implements TResource<Comentario> {

    private final ComentarioDAO comentarioDb;
    
    public ComentarioResource(ComentarioDAO dao){
        this.comentarioDb=dao;
    }
    
    @GET
    @Path("/getAll/{idPonto}")
    public List<Comentario> read(@PathParam("idPonto") LongParam idPonto) {
        try{
            return comentarioDb.toList(idPonto.get().intValue());
        }catch(Exception ex){
            throw new WebApplicationException("Não foi possivel listar os comentários !!!",404);   
        }
    }
    
    @GET
    @Override
    public List<Comentario> read() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @GET
    @Override
    @Path("{id}")
    public Comentario readThis(@PathParam("id") LongParam id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @PUT
    @Override
    @Path("{id}")
    public Comentario update(@PathParam("id") LongParam id, Comentario comentario)  {
        try {
            comentarioDb.update(comentario,id.get());
            return comentario;
        } catch (Exception ex) {
            throw new WebApplicationException("Não foi possivel atualizar o comentario !!!",500);   
        }
    }
    
    @POST
    @Override
    public Comentario create(Comentario comentario) {
        try {
            comentarioDb.insert(comentario);
            return comentario;
        } catch (Exception ex) {
            throw new WebApplicationException("Não foi possivel inserir",500);   
        }
    }

    @DELETE
    @Override
    @Path("{id}")
    public Response delete( @PathParam("id") LongParam id) {
        try {
            comentarioDb.delete(id.get());
            return Response.ok().build();
            
        } catch (Exception ex) {
            throw new WebApplicationException("Comentario de id: "+id.get()+" não encontrado!",404);
        }
    }
    
}
