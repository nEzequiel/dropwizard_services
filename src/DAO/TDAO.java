/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import java.sql.PreparedStatement;
import java.util.List;


/**
 *
 * @author ezequiel
 */
public interface TDAO<T> {
    public int Insert(T obj) throws Exception;
    public int Update(T obj,Long id) throws Exception;
    public List<T> ToList() throws Exception;
    public List<T> ToList(T obj) throws Exception;
    public T Last() throws Exception;
    public int Delete(Long id) throws Exception;
    public T Get(int id) throws Exception; 
    
}
