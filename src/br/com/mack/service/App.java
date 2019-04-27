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
import br.com.mack.data.daos.PontoTuristicoDAO;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import java.sql.Connection;

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
    }
}
