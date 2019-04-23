/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

/**
 *
 * @author ezequiel
 */
public class App extends Application<Configuration>{
    public static void main(String[] args) throws Exception{
        new App().run(new String[] {"server"});
    }
    @Override
    public void run(Configuration configuration, Environment environment){
        environment.jersey().register(new CidadeResource());
    }
}
