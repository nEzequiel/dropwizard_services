/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Cidade;
import Entities.Estado;
import Entities.Pais;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ezequiel
 */
public class CidadeDAO implements TDAO<Cidade> {
    private DataAccess db=new DataAccess();

    @Override
    public int Insert(Cidade obj) {
        String strQuery="INSERT INTO CIDADE "+"(nome,estado,pais,populacao) values('"+obj.nome
                +"',"+obj.estado+","+obj.pais+","+obj.populacao+")";
        try {
            return db.Update(strQuery);
        } 
        catch (Exception ex) {
            System.out.println("Erro ao inserir "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public int Update(Cidade obj,Long id) {
        String strQuery="Update CIDADE set nome='"+obj.nome
                +"', estado="+obj.estado+", pais="
                +obj.pais+", populacao="+obj.populacao+" where id="+id;
        try {
            return db.Update(strQuery);
        } 
        catch (Exception ex) {
            System.out.println("Erro ao atualizar "+ex.getMessage());
        }
        return 0;
    }
    @Override
    public Cidade Get(int id) {
        String strQuery="select * from Cidade where id="+id;
        
        try {
            Cidade cidade;
            ResultSet rs= rs=db.Query(strQuery);
            while(rs.next()){
                String nome=rs.getString("nome");
                int estado=rs.getInt("estado");
                int pais=rs.getInt("pais");
                int populacao=rs.getInt("populacao");
                cidade =new Cidade(id,nome,estado,pais,populacao);
                return cidade;
            }
        } 
        catch (Exception ex) {
            System.out.println("Erro ao Buscar "+ex.getMessage());
        }
        return null;
    }
    @Override
    public List<Cidade> ToList() {
        String strQuery="select * from Cidade";
        
        try {
            List<Cidade> cidade=new ArrayList();
            ResultSet rs= rs=db.Query(strQuery);
            while(rs.next()){
                int id=rs.getInt("id");
                String nome=rs.getString("nome");
                int estado=rs.getInt("estado");
                int pais=rs.getInt("pais");
                int populacao=rs.getInt("populacao");
                cidade.add(new Cidade(id,nome,estado,pais,populacao));
            }
            return cidade;
        } 
        catch (Exception ex) {
            System.out.println("Erro ao Buscar "+ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Cidade> ToList(Cidade obj) {
         String strQuery="select * from Cidade where Cast(id as text) like '"
                 +obj.id+"%' or nome Like '"
                 +obj.nome+" or estado Like "+obj.estado+"% or pais Like "
                 +obj.pais+" or populacao Like "+obj.populacao+"";
         
        List<Cidade> cidade=new ArrayList();
        try {
            ResultSet rs=db.Query(strQuery);
            while(rs.next()){
                int id=rs.getInt("id");
                String nome=rs.getString("nome");
                int estado=rs.getInt("estado");
                int pais=rs.getInt("pais");
                int populacao=rs.getInt("populacao");
                cidade.add(new Cidade(id,nome,estado,pais,populacao));
            }
        } 
        catch (Exception ex) {
            System.out.println("Erro ao Buscar "+ex.getMessage());
        }
        return cidade;
    }

    @Override
    public Cidade Last() {
        String strQuery="select * from Cidade order by id desc fetch first 1 rows only";

        try {
            ResultSet rs=db.Query(strQuery);
            rs.next();
            int id=rs.getInt("id");
            String nome=rs.getString("nome");
            int estado=rs.getInt("estado");
            int pais=rs.getInt("pais");
            int populacao=rs.getInt("populacao");
            return new Cidade(id,nome,estado,pais,populacao);
        } 
        catch (Exception ex) {
            System.out.println("Erro ao buscar "+ex.getMessage());
        }
        return null;
    }

    @Override
    public int Delete(Long id) {
        String strQuery="Delete from Cidade where id="+id;
        try {
            return db.Update(strQuery);
        } 
        catch (Exception ex) {
            System.out.println("Erro ao excluir "+ex.getMessage());
        }
        return 0;
    }
    
    public List<Estado> ListEstados(int idPais) {
        String strQuery="select * from Estado where pais="+idPais;
        
        try {
            List<Estado> estados=new ArrayList();
            ResultSet rs= rs=db.Query(strQuery);
            while(rs.next()){
                int id=rs.getInt("id");
                String nome=rs.getString("nome");
                int pais=rs.getInt("pais");
                estados.add(new Estado(id,nome,pais));
            }
            return estados;
        } 
        catch (Exception ex) {
            System.out.println("Erro ao Buscar "+ex.getMessage());
        }
        return null;
    }
    
    public List<Pais> ListPaises() {
        String strQuery="select * from Pais";
        
        try {
            List<Pais> paises=new ArrayList();
            ResultSet rs= rs=db.Query(strQuery);
            while(rs.next()){
                int id=rs.getInt("id");
                String nome=rs.getString("nome");
                paises.add(new Pais(id,nome));
            }
            return paises;
        } 
        catch (Exception ex) {
            System.out.println("Erro ao Buscar "+ex.getMessage());
        }
        return null;
    }
}
