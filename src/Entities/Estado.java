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
public class Estado {
    private int id;
    private String nome;
    private int pais;
    
    public Estado(){
        
    }
    
    public Estado(int id,String nome, int pais){
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
