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

import br.com.mack.data.daos.UsuarioDAO;
import br.com.mack.domain.Usuario;
import br.com.mack.service.TResource;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import io.dropwizard.jersey.*;
import io.dropwizard.jersey.params.*;
import java.util.*;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource implements TResource<Usuario>
{
    private final UsuarioDAO usuarioDb;
    
    public UsuarioResource(UsuarioDAO dao){
        this.usuarioDb=dao;
    }
    
    @GET
    public List<Usuario> read() {
        try{
            return usuarioDb.toList();
        }catch(Exception ex){
            throw new WebApplicationException("Não foi possivel listar os usuarios",404);
        }
    }

    @GET
    @Path("{id}")
    public Usuario readThis(@PathParam("id") LongParam id) {
        try{
            return usuarioDb.get(id.get().intValue());
        }catch(Exception ex){
            throw new WebApplicationException("Não foi possivel listar os usuarios",404);
        }
            
    }

    @PUT
    @Path("{id}")
    public Usuario update(@PathParam("id") LongParam id, Usuario usuario) {
        try{
            usuarioDb.update(usuario, id.get());
            return usuario;
        }catch(Exception ex){
            throw new WebApplicationException("Não foi possivel listar os usuarios ",404);
        }
    }

    @POST
    public Usuario create(Usuario usuario)  {
        try{
            usuarioDb.insert(usuario);
            return usuario;
        }catch(Exception ex){
            throw new WebApplicationException("Não foi possivel listar os usuarios",404);
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") LongParam id) {
        try{
            usuarioDb.delete(id.get());
            return Response.ok().build();
        }catch(Exception ex){
            throw new WebApplicationException("Não foi possivel listar os usuarios ",404);
        }
    }
}
