/*
 * CategoryManager.java
 *
 * Created on November 28, 2006, 3:55 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber.db;
import java.util.*;
import java.sql.*;
import vgrabber.common.*;

/**
 *
 * @author vdoni
 */
public class CategoryManager {
    
    /**
     * Creates a new instance of CategoryManager
     */
    public CategoryManager() {
    }
    public static ArrayList<Category> getCategoriesToDownload(){
        ArrayList<Category> categories=new ArrayList<Category>();
        try{        
        String sql="SELECT id, parent_id, nume FROM categorie WHERE (toupdate = 1)";
        //java.sql.PreparedStatement psCategory=makler.db.Connection.GetConnection().prepareStatement(sql);        
        java.sql.Statement psCategory=vgrabber.db.Connection.getConnection().createStatement();        
        psCategory.executeQuery(sql);
        java.sql.ResultSet rsCategory=psCategory.getResultSet();
        while (rsCategory.next()){                        
        categories.add(new Category(rsCategory.getInt("id"),rsCategory.getInt("parent_id"),rsCategory.getString("nume"),true));
        }
        }
        catch (java.sql.SQLException ex){
            System.out.println(ex.toString());
        }
        finally{            
        return categories;        
        }
    }
    public static ArrayList<Category> getCategoriesToDownload(Edition edition){
        ArrayList<Category> categories=new ArrayList<Category>();
        try{        
        String sql="SELECT id, parent_id, nume FROM categorie WHERE (id NOT IN (SELECT categorie_id FROM anunt WHERE (editie_id = ?) GROUP BY categorie_id)) AND (toupdate = 1)";
        java.sql.PreparedStatement psCategory=vgrabber.db.Connection.getConnection().prepareStatement(sql);        
        //java.sql.Statement psCategory=vgrabber.db.Connection.getConnection().createStatement();        
        psCategory.setInt(1,edition.getId());
        psCategory.execute();
        java.sql.ResultSet rsCategory=psCategory.getResultSet();
        while (rsCategory.next()){                        
        categories.add(new Category(rsCategory.getInt("id"),rsCategory.getInt("parent_id"),rsCategory.getString("nume"),true));
        }
        }
        catch (java.sql.SQLException ex){
            System.out.println(ex.toString());
        }
        finally{            
        return categories;        
        }
    }    
    public static ArrayList<Category> getCategories(){
        ArrayList<Category> categories=new ArrayList<Category>();
        try{        
        String sql="SELECT id, parent_id, nume, toupdate FROM categorie";
        //java.sql.PreparedStatement psCategory=makler.db.Connection.GetConnection().prepareStatement(sql);        
        java.sql.Statement psCategory=vgrabber.db.Connection.getConnection().createStatement();        
        psCategory.executeQuery(sql);
        java.sql.ResultSet rsCategory=psCategory.getResultSet();
        while (rsCategory.next()){                        
        categories.add(new Category(rsCategory.getInt("id"),rsCategory.getInt("parent_id"),rsCategory.getString("nume"),rsCategory.getBoolean("toupdate")));
        }
        }
        catch (java.sql.SQLException ex){
            System.out.println(ex.toString());
        }
        finally{            
        return categories;        
        }
    }    
    public static boolean exists(Category category){
        Category cat=getCategory(category.getId());
        if (cat==null){
            return false;
        } else {
            return true;
        }
    }
    public static Category getCategory(int category_id){
        Category category=null;
        try{        
            String sql="SELECT id, parent_id, nume, toupdate FROM categorie where id=?";
            //java.sql.PreparedStatement psCategory=makler.db.Connection.GetConnection().prepareStatement(sql);        
            java.sql.PreparedStatement psCategory=vgrabber.db.Connection.getConnection().prepareStatement(sql);        
            psCategory.setInt(1,category_id);
            psCategory.execute();
            java.sql.ResultSet rsCategory=psCategory.getResultSet();
            while (rsCategory.next()){                        
                category=new Category(rsCategory.getInt("id"),rsCategory.getInt("parent_id"),rsCategory.getString("nume"),rsCategory.getBoolean("toupdate"));
            }
        } catch (java.sql.SQLException ex){
            System.out.println(ex.toString());
        }                    
        return category;                
    }        
    public static boolean addCategory(Category category){        
        boolean added=false;
        if (exists(category)){
            updCategory(category);
        } else {                    
            try{        
                String sql="INSERT INTO categorie (id, parent_id, nume) VALUES (?,?,?)";
                java.sql.PreparedStatement psCategory=vgrabber.db.Connection.getConnection().prepareStatement(sql);                
            
                psCategory.setInt(1,category.getId());
                if (category.getParentId()==0) {
                    psCategory.setNull(2,java.sql.Types.INTEGER);
                } else {
                    psCategory.setInt(2,category.getParentId());
                }
                psCategory.setString(3,category.getName());    
                psCategory.execute();
                added=true;
            } catch (java.sql.SQLException ex){
                System.out.println(ex.toString());
            }        
        }
        return added;        
    }    
    public static boolean updCategory(Category category){        
        boolean updated=false;
        try{        
        String sql="UPDATE categorie SET parent_id=?, nume=?, toupdate =? WHERE (id = ?)";
        java.sql.PreparedStatement psCategory=vgrabber.db.Connection.getConnection().prepareStatement(sql);                        
        if (category.getParentId()==0){
            psCategory.setNull(1,java.sql.Types.INTEGER);            
        } else {
            psCategory.setInt(1,category.getParentId());               
        }        
        psCategory.setString(2,category.getName());
        psCategory.setBoolean(3,category.getToUpdate());
        psCategory.setInt(4,category.getId());    
        psCategory.execute();
        updated=true;
        }
        catch (java.sql.SQLException ex){
            System.out.println(ex.toString());
        }
        finally{            
            return updated;
        }        
    }
    
}
