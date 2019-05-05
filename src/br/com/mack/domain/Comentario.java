/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mack.domain;

import java.sql.Date;

/**
 *
 * @author ezequiel
 */
public class Comentario {
    private int id;
    private Date data;
    private String texto;
    private Usuario usuario;
    private PontoTuristico ponto;

    public Comentario(int id,Usuario usuario, PontoTuristico ponto, Date data, String texto){
        this.usuario=usuario;
        this.ponto=ponto;
        this.data=data;
        this.texto=texto;
        this.id=id;
    }
    
    public Comentario(Usuario usuario, PontoTuristico ponto, Date data, String texto){
        this.usuario=usuario;
        this.ponto=ponto;
        this.data=data;
        this.texto=texto;
    }
    
    public Comentario(){
        
    }
    
    public Comentario(int id){
        this.id=id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PontoTuristico getPonto() {
        return ponto;
    }

    public void setPonto(PontoTuristico ponto) {
        this.ponto = ponto;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}