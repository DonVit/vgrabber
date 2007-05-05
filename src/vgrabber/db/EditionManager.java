/*
 * EditionManager.java
 *
 * Created on November 21, 2006, 6:59 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber.db;

/**
 *
 * @author vdoni
 */
import java.util.*;
import java.sql.*;
import vgrabber.common.Category;
import vgrabber.common.Edition;
import vgrabber.common.Message;

public class EditionManager {
    
    /** Creates a new instance of EditionManager */
 
    public EditionManager() {        
    }
    public static Edition GetEditionById(int edition_id){
        Edition edition=null;
        try{        
        String sql="SELECT id, data, note FROM editie WHERE (id = ?)";
        java.sql.PreparedStatement psEditions=vgrabber.db.Connection.getConnection().prepareStatement(sql);
        psEditions.setInt(1,edition_id);
        psEditions.execute();
        java.sql.ResultSet rsEditions=psEditions.getResultSet();
        while (rsEditions.next()){        
        edition=new Edition(rsEditions.getInt("id"),rsEditions.getDate("data"),rsEditions.getString("note"));                
        }
        }
        catch (java.sql.SQLException ex){
            System.out.println(ex.toString());
        }
        finally{            
            return edition;
        }
    }
    public static ArrayList<Edition> GetAllEditions(){
        ArrayList<Edition> editions=new ArrayList<Edition>();
        try{        
        String sql="SELECT id, data, note FROM editie order by id desc";
        java.sql.PreparedStatement psEditions=vgrabber.db.Connection.getConnection().prepareStatement(sql);        
        psEditions.execute();
        java.sql.ResultSet rsEditions=psEditions.getResultSet();
        while (rsEditions.next()){        
        Edition edition=new Edition(rsEditions.getInt("id"),rsEditions.getDate("data"),rsEditions.getString("note"));        
        editions.add(edition);
        }
        }
        catch (java.sql.SQLException ex){
            System.out.println(ex.toString());
        }
        finally{            
            return editions;
        }
    }
    public static Edition GetLastEdition(){
        Edition edition=null;
        try{        
        String sql="SELECT top 1 id, data, note FROM editie order by id desc";
        java.sql.PreparedStatement psEdition=vgrabber.db.Connection.getConnection().prepareStatement(sql);        
        psEdition.execute();
        java.sql.ResultSet rsEdition=psEdition.getResultSet();
        while (rsEdition.next()){        
        edition=new Edition(rsEdition.getInt("id"),rsEdition.getDate("data"),rsEdition.getString("note"));        
        }      
        }
        catch (java.sql.SQLException ex){
            System.out.println(ex.toString());
        }
        finally{            
            return edition;
        }
    }        
    public static ArrayList<Edition> GetEditionsToDownload(){
        ArrayList<Edition> editions=new ArrayList<Edition>();
        
        Edition lastedition=GetLastEdition();
        java.util.Date lasteditiondate=lastedition.getDate();
        int lasteditionid=lastedition.getId();
        
        java.util.Calendar calendar_curentdate=java.util.Calendar.getInstance();
        calendar_curentdate.setFirstDayOfWeek(calendar_curentdate.MONDAY);        
        calendar_curentdate.set(calendar_curentdate.HOUR_OF_DAY,0);
        calendar_curentdate.set(calendar_curentdate.MINUTE,0);
        calendar_curentdate.set(calendar_curentdate.SECOND,0);
        calendar_curentdate.set(calendar_curentdate.MILLISECOND,0);
        
        
        java.util.Calendar calendar_lasteditiondate=java.util.Calendar.getInstance();
        calendar_lasteditiondate.setFirstDayOfWeek(calendar_lasteditiondate.MONDAY);
        calendar_lasteditiondate.setTime(lasteditiondate);
        calendar_lasteditiondate.set(calendar_lasteditiondate.HOUR_OF_DAY,0);
        calendar_lasteditiondate.set(calendar_lasteditiondate.MINUTE,0);
        calendar_lasteditiondate.set(calendar_lasteditiondate.SECOND,0);
        calendar_lasteditiondate.set(calendar_lasteditiondate.MILLISECOND,0);                
        
        
        
        while (calendar_curentdate.compareTo(calendar_lasteditiondate)>0){
            
            calendar_lasteditiondate.add(calendar_lasteditiondate.DAY_OF_YEAR,1);
            
            if (calendar_lasteditiondate.get(calendar_lasteditiondate.DAY_OF_WEEK)==calendar_lasteditiondate.TUESDAY){
            lasteditionid+=4;
            editions.add(new vgrabber.common.Edition(lasteditionid,calendar_lasteditiondate.getTime(),""));                            
            }
            if (calendar_lasteditiondate.get(calendar_lasteditiondate.DAY_OF_WEEK)==calendar_lasteditiondate.THURSDAY){
            lasteditionid+=3;
            editions.add(new vgrabber.common.Edition(lasteditionid,calendar_lasteditiondate.getTime(),""));                
            }
            if (calendar_lasteditiondate.get(calendar_lasteditiondate.DAY_OF_WEEK)==calendar_lasteditiondate.FRIDAY){
            lasteditionid+=1;
            editions.add(new vgrabber.common.Edition(lasteditionid,calendar_lasteditiondate.getTime(),""));    
            }

            
        }
        
        return editions;
    }                
    public static ArrayList<Message> GetEditionMessagesByCategory(Edition edition, Category category){
        ArrayList<Message> messages=new ArrayList<Message>();
        try{        
        String sql="SELECT id, anunt, interested FROM anunt WHERE (editie_id = ?) AND (categorie_id = ?)";
        java.sql.PreparedStatement psMessages=vgrabber.db.Connection.getConnection().prepareStatement(sql);        
        psMessages.setInt(1,edition.getId());
        psMessages.setInt(2,category.getId());
        psMessages.execute();
        java.sql.ResultSet rsMessages=psMessages.getResultSet();
        while (rsMessages.next()){        
        Message message=new Message(rsMessages.getInt("id"),rsMessages.getString("anunt"), edition.getId(),category.getId(),rsMessages.getBoolean("interested"));        
        messages.add(message);
        }
        }
        catch (java.sql.SQLException ex){
            System.out.println(ex.toString());
        }
        finally{            
            return messages;
        }
    }    
    public static boolean AddEdition(Edition edition){        
        boolean added=false;
        try{        
        String sql="INSERT INTO editie (id, data, note) VALUES (?, ?, ?)";
        java.sql.PreparedStatement psEdition=vgrabber.db.Connection.getConnection().prepareStatement(sql);
        psEdition.setInt(1,edition.getId());
        psEdition.setString(2,(new java.sql.Date(edition.getDate().getTime())).toString());        
        psEdition.setString(3,edition.getNote());
        psEdition.execute();
        added=true;
        }
        catch (java.sql.SQLException ex){
            System.out.println(ex.toString());
        }
        finally{            
            return added;
        }        
    }
    public static boolean UpdEdition(Edition edition){        
        boolean updated=false;
        try{        
        String sql="UPDATE editie SET data = ?, note = ? WHERE (id = ?)";
        java.sql.PreparedStatement psEdition=vgrabber.db.Connection.getConnection().prepareStatement(sql);        
        //psEdition.setString(1,(new java.sql.Date(System.currentTimeMillis())).toString());        
        psEdition.setString(1,(new java.sql.Date(edition.getDate().getTime())).toString());        
        psEdition.setString(2,edition.getNote());
        psEdition.setInt(3,edition.getId());
        psEdition.execute();
        updated=true;
        }
        catch (java.sql.SQLException ex){
            System.out.println(ex.toString());
        }
        finally{            
            return updated;
        }        
    }
    public static boolean DelEdition(Edition edition){        
        boolean deleted=false;
        try{        
        String sql="DELETE FROM  contact_anunt WHERE anunt_id IN (SELECT id FROM anunt WHERE anunt.editie_id = ?) DELETE FROM anunt WHERE (editie_id = ?) DELETE FROM editie WHERE (id = ?)";        
        java.sql.PreparedStatement psEdition=vgrabber.db.Connection.getConnection().prepareStatement(sql);        
        psEdition.setInt(1,edition.getId());
        psEdition.setInt(2,edition.getId());
        psEdition.setInt(3,edition.getId());
        System.out.println(sql);        
        psEdition.execute();
        deleted=true;
        }
        catch (java.sql.SQLException ex){
            System.out.println(ex.toString());
        }
        finally{            
            return deleted;
        }        
    }    
}
