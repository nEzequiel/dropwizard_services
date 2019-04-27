/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mack.service.resources;

/**
 *
 * @author ezequiel
 */
import br.com.mack.data.daos.CidadeDAO;
import br.com.mack.data.daos.PontoTuristicoDAO;
import br.com.mack.domain.Avaliacao;
import br.com.mack.domain.Comentario;
import br.com.mack.domain.PontoTuristico;
import br.com.mack.service.TResource;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import io.dropwizard.jersey.*;
import io.dropwizard.jersey.params.*;
import java.util.*;

@Path("/pontoturistico")
@Produces(MediaType.APPLICATION_JSON)
public class PontoTuristicoResource implements TResource<PontoTuristico> {
    private final PontoTuristicoDAO pontoDb;
    
    public PontoTuristicoResource(PontoTuristicoDAO dao){
        this.pontoDb=dao;
    }

    @GET
    public List<PontoTuristico> read() {
        try{
            return pontoDb.toList();
        }catch(Exception ex){
            throw new WebApplicationException("Não foi possivel listar os pontos turisticos",404);
        }
    }

    @GET
    @Path("{id}")
    public PontoTuristico readThis(@PathParam("id") LongParam id) {
        try{
            return pontoDb.get(id.get().intValue());
        }catch(Exception ex){
            throw new WebApplicationException("Não foi possivel listar os pontos turisticos",404);
        }
            
    }

    @GET
    @Path("/last")
    public PontoTuristico readLast() {
        try{
            return pontoDb.last();
        }catch(Exception ex){
            throw new WebApplicationException("Não foi possivel listar os pontos turisticos",404);
        }
    }

    @PUT
    @Path("{id}")
    public PontoTuristico update(@PathParam("id") LongParam id, PontoTuristico ponto) {
        try{
            pontoDb.update(ponto, id.get());
            return ponto;
        }catch(Exception ex){
            throw new WebApplicationException("Não foi possivel listar os pontos turisticos",404);
        }
    }

    @POST
    public PontoTuristico create(PontoTuristico ponto)  {
        try{
            pontoDb.insert(ponto);
            return ponto;
        }catch(Exception ex){
            throw new WebApplicationException("Não foi possivel listar os pontos turisticos",404);
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") LongParam id) {
        try{
            pontoDb.delete(id.get());
            return Response.ok().build();
        }catch(Exception ex){
            throw new WebApplicationException("Não foi possivel listar os pontos turisticos",404);
        }
    }
    
    @GET
    @Path("{id}/comentarios")
    public List<Comentario> readComentarios(@PathParam("id") LongParam id) {
        try{
            return null;
        }catch(Exception ex){
            throw new WebApplicationException("Não foi possivel listar os pontos turisticos",404);
        }
            
    }
    
    @GET
    @Path("{id}/avaliacoes")
    public List<Comentario> readAvaliacoes(@PathParam("id") LongParam id) {
        try{
            return null;
        }catch(Exception ex){
            throw new WebApplicationException("Não foi possivel listar os pontos turisticos",404);
        }
            
    }
    
    @POST
    @Path("{id}/comentario")
    public Comentario createComentario(@PathParam("id") LongParam id, Comentario coment) {
        try{
            return null;
        }catch(Exception ex){
            throw new WebApplicationException("Não foi possivel listar os pontos turisticos",404);
        }
            
    }
    
    @POST
    @Path("{id}/avaliacao")
    public Avaliacao createAvaliacao(@PathParam("id") LongParam id, Avaliacao aval) {
        try{
            return null;
        }catch(Exception ex){
            throw new WebApplicationException("Não foi possivel listar os pontos turisticos",404);
        }
            
    }
}
