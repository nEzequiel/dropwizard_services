/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mack.domain;

import java.sql.Time;

import java.text.ParseException;


/**
 *
 * @author ezequiel
 */
public class PontoTuristico{
    private int id;
    private String nome;
    private Cidade cidade;
    private String rua;
    private int numero;
    private String bairro;
    private int cep;
    private Time abertura;
    private Time fechamento;

    public PontoTuristico(){
        
    }
    public PontoTuristico(int id){
        this.id=id;
    }
    
    public PontoTuristico(int id,String nome,Cidade cidade,String rua,int numero,String bairro,int cep,String abertura,String fechamento){
        this.id=id;
        this.nome=nome;
        this.cidade=cidade;
        this.rua=rua;
        this.numero=numero;
        this.bairro=bairro;
        this.cep=cep;
        this.abertura=Time.valueOf("23:23:23");
        this.fechamento=Time.valueOf("23:23:23");
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public Time getAbertura() {
        return abertura;
    }

    public void setAbertura(Time abertura) {
        this.abertura = abertura;
    }

    public Time getFechamento() {
        return fechamento;
    }

    public void setFechamento(Time fechamento) {
        this.fechamento = fechamento;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}