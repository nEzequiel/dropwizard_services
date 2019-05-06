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
import br.com.mack.domain.Cidade;
import br.com.mack.domain.Estado;
import br.com.mack.domain.Pais;
import br.com.mack.service.TResource;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import io.dropwizard.jersey.*;
import io.dropwizard.jersey.params.*;
import java.sql.SQLException;
import java.util.*;


@Path("/cidade")
@Produces(MediaType.APPLICATION_JSON)
public class CidadeResource implements TResource<Cidade> {
    private final CidadeDAO cidadeDb;
    
    public CidadeResource(CidadeDAO dao){
        this.cidadeDb=dao;
    }
    
    @GET
    @Override
    public List<Cidade> read(){
        try{
            return cidadeDb.toList();
        }catch(Exception ex){
           throw new WebApplicationException("Não foi possivel listar os pontos turisticos",404);
        }
    }
    
    @GET
    @Path("/catalog/{lastId}/{search}")
    public List<Cidade> readCatalog(@PathParam("search") String nome,@PathParam("lastId") LongParam lastId){
        try{
            return cidadeDb.listCatalog(nome,lastId.get().intValue());
        }catch(Exception ex){
           throw new WebApplicationException("Não foi possivel listar os pontos turisticos",404);
        }
    }
    
    @GET
    @Path("{id}")
    @Override
    public Cidade readThis(@PathParam("id") LongParam id){
        try{
            Cidade cidade= cidadeDb.get(id.get().intValue());
            if(cidade==null){
                throw new WebApplicationException("Cidade de id: "+id.get()+" não encontrada!",404);
            }else{
                return cidade;
            }
        }catch(Exception ex){
            throw new WebApplicationException("Não foi possivel listar os pontos turisticos",404);
        }

    }
   
    @POST
    @Override
    public Cidade create(Cidade cidade) {
        try {
            cidadeDb.insert(cidade);
            return cidade;
        } catch (Exception ex) {
            throw new WebApplicationException("Não foi possivel inserir a cidade de "+cidade.getNome()+"!!",404);   
        }
    }
    
    @PUT 
    @Path("{id}")
    @Override
    public Cidade update (@PathParam("id") LongParam id, Cidade cidade) {
        try {
            cidadeDb.update(cidade,id.get());
            return cidade;
        } catch (Exception ex) {
             throw new WebApplicationException("Cidade de id"+id+" não encontrada!!",404);
        }
    }
    
    @DELETE
    @Path("{id}")
    @Override
    public Response delete(@PathParam("id") LongParam id) {
        try {
            cidadeDb.delete(id.get());
            return Response.ok().build();
            
        } catch (SQLException ex) {
            throw new WebApplicationException("Cidade de id: "+id.get()+" não encontrada!",404);
        }
    }
    
    
    @GET
    @Path("/estados/{idPais}")
    public List<Estado> readEstados(@PathParam("idPais") LongParam idPais) {
        try{
            return cidadeDb.listEstados(idPais.get().intValue());
        }catch(Exception ex){
            System.out.println("Erro");
        }
        return null;
    }
    
    
    @GET
    @Path("/paises")
    public List<Pais> readPaises(){
        try{
            return cidadeDb.listPaises();
        }catch(Exception ex){
            throw new WebApplicationException("Não foi possivel listar os pontos turisticos",404);
        }
    }

}
