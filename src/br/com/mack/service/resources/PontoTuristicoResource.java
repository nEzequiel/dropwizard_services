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
    @Path("/cidade/{id}")
    public List<PontoTuristico> readCidadePontos(@PathParam("id") LongParam id) {
        try{
            return pontoDb.listCidadePontos(id.get().intValue());
        }catch(Exception ex){
            throw new WebApplicationException("Não foi possivel listar os pontos turisticos",404);
        }
    }
    
    @GET
    @Path("/catalog/{lastId}/{search}")
    public List<PontoTuristico> readPontos(@PathParam("search") String nome,@PathParam("lastId") LongParam lastId){
        try{
            return pontoDb.listCatalog(nome,lastId.get().intValue());
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
}
