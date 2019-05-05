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
public class Avaliacao{
    private int id;
    private Usuario usuario;
    private PontoTuristico ponto;
    private Date data;
    private int valor;
    
    public Avaliacao(){}
    
    public Avaliacao(int id, Usuario usuario, PontoTuristico ponto, Date data, int valor){
        this.id=id;
        this.usuario=usuario;
        this.ponto=ponto;
        this.data=data;
        this.valor=valor;
    }
    
    public Avaliacao( Usuario usuario, PontoTuristico ponto, Date data, int valor){
        this.usuario=usuario;
        this.ponto=ponto;
        this.data=data;
        this.valor=valor;
    }
    
    public Avaliacao(int id){
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

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}