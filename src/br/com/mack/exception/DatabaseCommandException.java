/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mack.exception;

/**
 *
 * @author ezequiel
 */
public class DatabaseCommandException extends Exception{
    
    public DatabaseCommandException(String erro){
        super(erro);
    }
    
    public DatabaseCommandException(String erro,Throwable causa){
        super(erro,causa);
    }
}
