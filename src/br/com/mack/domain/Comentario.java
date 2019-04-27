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
public class Comentario {
    public int id;
    public Usuario usuario;
    public PontoTuristico ponto;
    public String data;
    public String texto;
}