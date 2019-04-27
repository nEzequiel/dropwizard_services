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
public class Cidade {
    private int id;
    private String nome;
    private Estado estado;
    private Pais pais;
    private int populacao;
    
    public Cidade(){
        
    }
    
    public Cidade(int id){
        this.id=id;
    }
    public Cidade(String nome,Estado estado,Pais pais, int populacao){
        this.nome=nome;
        this.estado=estado;
        this.pais=pais;
        this.populacao=populacao;
    }
    
    public Cidade(int id,String nome,Estado estado,Pais pais, int populacao){
        this.id=id;
        this.nome=nome;
        this.estado=estado;
        this.pais=pais;
        this.populacao=populacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public int getPopulacao() {
        return populacao;
    }

    public void setPopulacao(int populacao) {
        this.populacao = populacao;
    }

}
