/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author ezequiel
 */
public class Pais {
    private int id;
    private String nome;
    
    public Pais(){
        
    }
    
    public Pais(int id,String nome){
        this.id=id;
        this.nome=nome;
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
