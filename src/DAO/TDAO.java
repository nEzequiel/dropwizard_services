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
    public int Insert(T obj);
    public int Update(T obj,Long id);
    public List<T> ToList();
    public List<T> ToList(T obj);
    public T Last();
    public int Delete(Long id);
    public T Get(int id); 
    
}
