/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mack.data;


import java.sql.PreparedStatement;
import java.util.List;


/**
 *
 * @author ezequiel
 */
public interface TDAO<T> {
    public int insert(T obj) throws Exception;
    public int update(T obj,Long id) throws Exception;
    public List<T> toList() throws Exception;
    public int delete(Long id) throws Exception;
    public T get(int id) throws Exception; 
    
}
