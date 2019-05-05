/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mack.domain;

/**
 *
 * @author ezequiel
 */
public class Estado {
    
    private int id;
    private String nome;
    private Pais pais;
    
    public Estado(){
        
    }
    
    public Estado(int id){
        this.id=id;
    }
    
    public Estado(int id,String nome, Pais pais){
        this.id=id;
        this.nome=nome;
        this.pais=pais;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }
}
