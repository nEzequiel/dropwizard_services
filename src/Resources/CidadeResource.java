/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

/**
 *
 * @author ezequiel
 */
import DAO.CidadeDAO;
import Entities.Cidade;
import Entities.Estado;
import Entities.Pais;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import io.dropwizard.jersey.*;
import io.dropwizard.jersey.params.*;
import java.util.*;

@Path("/cidade")
@Produces(MediaType.APPLICATION_JSON)
public class CidadeResource implements TResource<Cidade> {
    public CidadeDAO cidadeDb;
    
    public CidadeResource(CidadeDAO dao){
        this.cidadeDb=dao;
    }
    
    @GET
    public List<Cidade> read(){
        try{
            return cidadeDb.ToList();
        }catch(Exception ex){
            System.out.println("Erro");
        }
        return null;
    }
    
    
    
    @GET
    @Path("{id}")
    public Cidade readThis(@PathParam("id") LongParam id){
        try{
            Cidade cidade= cidadeDb.Get(id.get().intValue());
            if(cidade==null){
                throw new WebApplicationException("Cidade de id: "+id.get()+" não encontrada!",404);
            }else{
                return cidade;
            }
        }catch(Exception ex){
            System.out.println("Erro");
        }
        return null;
    }
    
    @GET
    @Path("/last")
    public Cidade readLast(){
        try{
            return cidadeDb.Last();
        }catch(Exception ex){
            System.out.println("Erro");
        }
        return null;
    }
   
    @POST
    public Cidade create(Cidade cidade){
        if(cidadeDb.Insert(cidade)==0){
            throw new WebApplicationException("Não foi possivel inserir a cidade de "+cidade.nome+"!!",404);
        }
        return cidade;
    }
    
    @PUT 
    @Path("{id}")
    public Cidade update (@PathParam("id") LongParam id, Cidade cidade){
        if(cidadeDb.Update(cidade,id.get())==0){
            throw new WebApplicationException("Cidade de id"+id+" não encontrada!!",404);
        }
        return cidade;
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") LongParam id){
        if(cidadeDb.Delete(id.get())==0){
            throw new WebApplicationException("Cidade de id: "+id.get()+" não encontrada!",404); 
        }
        return Response.ok().build();
    }
    
    
    @GET
    @Path("/estados/{idPais}")
    public List<Estado> readEstados(@PathParam("idPais") LongParam idPais){
        try{
            return cidadeDb.ListEstados(idPais.get().intValue());
        }catch(Exception ex){
            System.out.println("Erro");
        }
        return null;
    }
    
    
    @GET
    @Path("/paises")
    public List<Pais> readPaises(){
        try{
            return cidadeDb.ListPaises();
        }catch(Exception ex){
            System.out.println("Erro");
        }
        return null;
    }

}
