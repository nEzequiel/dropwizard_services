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
public class Usuario {
    private int id;
    private String nome;
    private String sobrenome;
    private String email;
    private String telefone;
    private String login;
    private String senha;

    
    public Usuario(){
        
    }
    
    public Usuario(int id,String nome,String sobrenome,String email,String telefone,String login,String senha){
        this.id=id;
        this.nome=nome;
        this.sobrenome=sobrenome;
        this.email=email;
        this.telefone=telefone;
        this.login=login;
        this.senha=senha;
    }
    
    
    public Usuario(String nome,String sobrenome,String email,String telefone,String login,String senha){
        this.nome=nome;
        this.sobrenome=sobrenome;
        this.email=email;
        this.telefone=telefone;
        this.login=login;
        this.senha=senha;
    }
    
    public Usuario(int id){
        this.id=id;
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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
