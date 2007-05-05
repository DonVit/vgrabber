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
import vgrabber.common.Category;

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
        java.sql.Statement psCategory=vgrabber.db.Connection.GetConnection().createStatement();        
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
    public static ArrayList<Category> getCategories(){
        ArrayList<Category> categories=new ArrayList<Category>();
        try{        
        String sql="SELECT id, parent_id, nume, toupdate FROM categorie";
        //java.sql.PreparedStatement psCategory=makler.db.Connection.GetConnection().prepareStatement(sql);        
        java.sql.Statement psCategory=vgrabber.db.Connection.GetConnection().createStatement();        
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
    public static boolean UpdCategory(Category category){        
        boolean updated=false;
        try{        
        String sql="UPDATE categorie SET nume=?, toupdate =? WHERE (id = ?)";
        java.sql.PreparedStatement psCategory=vgrabber.db.Connection.GetConnection().prepareStatement(sql);                
            
        psCategory.setString(1,category.getName());
        psCategory.setBoolean(2,category.getToUpdate());
        psCategory.setInt(3,category.getId());    
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
