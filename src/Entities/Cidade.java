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
public class Cidade {
    public int id;
    public String nome;
    public int idEstado;
    public int idPais;
    public int populacao;
    public Estado estado;
    public Pais pais;
    
    public Cidade(){
        
    }
    public Cidade(String nome,int estado,int pais, int populacao){
        this.nome=nome;
        this.idEstado=estado;
        this.idPais=pais;
        this.populacao=populacao;
    }
    
    public Cidade(int id,String nome,int estado,int pais, int populacao){
        this.id=id;
        this.nome=nome;
        this.idEstado=estado;
        this.idPais=pais;
        this.populacao=populacao;
    }
}
