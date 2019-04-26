/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author ezequiel
 */
public class PontoTuristico{
    public String nome;
    public int idCidade;
    public String rua;
    public int numero;
    public String bairro;
    public int cep;
    public Date abertura;
    public Date fechamento;
    public Cidade cidade;
}