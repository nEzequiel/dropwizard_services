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
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import io.dropwizard.jersey.params.*;
import java.util.*;

public interface TResource<T> {
    @GET
    public List<T> read();
    @GET
    public T readThis(@PathParam("id") LongParam id);
    @GET
    public T readLast();
    @PUT
    public T update(@PathParam("id") LongParam id,T entity);
    @POST
    public T create(T entity);
    @DELETE
    public Response delete(@PathParam("id") LongParam id);
}
