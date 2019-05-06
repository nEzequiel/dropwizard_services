/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mack.service;

import br.com.mack.service.resources.CidadeResource;
import br.com.mack.service.resources.PontoTuristicoResource;
import br.com.mack.data.daos.CidadeDAO;
import br.com.mack.data.DataAccess;
import br.com.mack.data.daos.AvaliacaoDAO;
import br.com.mack.data.daos.ComentarioDAO;
import br.com.mack.data.daos.PontoTuristicoDAO;
import br.com.mack.data.daos.UsuarioDAO;
import br.com.mack.service.resources.AvaliacaoResource;
import br.com.mack.service.resources.ComentarioResource;
import br.com.mack.service.resources.UsuarioResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import java.sql.Connection;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import org.eclipse.jetty.servlets.CrossOriginFilter;

/**
 *
 * @author ezequiel
 */
public class App extends Application<Configuration>{
    public static void main(String[] args) throws Exception{
        new App().run(new String[] {"server"});
    }
    @Override
    public void run(Configuration configuration, Environment environment) throws ClassNotFoundException{
        //Ponto global de acesso ao banco de dados
        DataAccess globalAccess=DataAccess.getInstance();
        
        //Instância e injeção do contexto de banco de dados de cidades.
        CidadeDAO cidadeData=new CidadeDAO(globalAccess);
        environment.jersey().register(new CidadeResource(cidadeData));
 
        PontoTuristicoDAO pontoDAO=new PontoTuristicoDAO(globalAccess);
        environment.jersey().register(new PontoTuristicoResource(pontoDAO));
        
        UsuarioDAO usuarioDAO=new UsuarioDAO(globalAccess);
        environment.jersey().register(new UsuarioResource(usuarioDAO));
        
        AvaliacaoDAO avaliacaoDAO=new AvaliacaoDAO(globalAccess);
        environment.jersey().register(new AvaliacaoResource(avaliacaoDAO));
        
        ComentarioDAO comentarioDAO=new ComentarioDAO(globalAccess);
        environment.jersey().register(new ComentarioResource(comentarioDAO));
     
        
        
        final FilterRegistration.Dynamic cors =
        environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }
    
    
}
