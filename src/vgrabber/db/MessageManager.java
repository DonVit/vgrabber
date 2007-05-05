/*
 * MessageManager.java
 *
 * Created on November 24, 2006, 3:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber.db;

import java.util.*;
import java.sql.*;
import vgrabber.common.Contact;
import vgrabber.common.Category;
import vgrabber.common.Edition;
import vgrabber.common.Message;

/**
 *
 * @author vdoni
 */
public class MessageManager {
    
    /** Creates a new instance of MessageManager */
    public MessageManager() {
    }
    public static ArrayList<Message> GetMessage(int id){
        ArrayList<Message> messages=new ArrayList<Message>();
        try{        
        String sql="SELECT id, message, editie_id, categorie_id, interested FROM anunt WHERE (id = ?)";
        java.sql.PreparedStatement psMessages=vgrabber.db.Connection.getConnection().prepareStatement(sql);
        psMessages.setInt(1,id);
        psMessages.execute();
        java.sql.ResultSet rsMessages=psMessages.getResultSet();        
        while (rsMessages.next()){        
        Message message=new Message(rsMessages.getInt("id"),rsMessages.getString("anunt"),rsMessages.getInt("editie_id"),rsMessages.getInt("categorie_id"),rsMessages.getBoolean("interested"));        
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
    public static ArrayList<Message> GetMessagesByContact(Contact contact){
        ArrayList<Message> messages=new ArrayList<Message>();
        try{        
        String sql="SELECT anunt.id, anunt.editie_id, anunt.categorie_id, categorie.nume AS categorie_nume, anunt.anunt, anunt.interested FROM anunt INNER JOIN contact_anunt ON anunt.id = contact_anunt.anunt_id INNER JOIN categorie ON anunt.categorie_id = categorie.id WHERE (contact_anunt.contact_id = ?) ORDER BY anunt.editie_id DESC";
        java.sql.PreparedStatement psMessages=vgrabber.db.Connection.getConnection().prepareStatement(sql);
        psMessages.setString(1,contact.getId());
        psMessages.execute();
        java.sql.ResultSet rsMessages=psMessages.getResultSet();
        while (rsMessages.next()){        
        Message message=new Message(rsMessages.getInt("id"),rsMessages.getString("anunt"),rsMessages.getInt("editie_id"),rsMessages.getInt("categorie_id"),rsMessages.getBoolean("interested"));        
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
    public static ArrayList<Message> GetMessagesByEditon(Edition edition){
        ArrayList<Message> messages=new ArrayList<Message>();
        try{        
        String sql="SELECT id, categorie_id, anunt, interested FROM anunt WHERE editie_id = ?";
        java.sql.PreparedStatement psMessages=vgrabber.db.Connection.getConnection().prepareStatement(sql);
        psMessages.setInt(1,edition.getId());
        psMessages.execute();
        java.sql.ResultSet rsMessages=psMessages.getResultSet();
        while (rsMessages.next()){        
        Message message=new Message(rsMessages.getInt("id"),rsMessages.getString("anunt"),edition.getId(),rsMessages.getInt("categorie_id"),rsMessages.getBoolean("interested"));        
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
    public static ArrayList<Message> GetMessagesByEditonAndCategory(Edition edition, Category category){
        ArrayList<Message> messages=new ArrayList<Message>();
        try{        
        String sql="SELECT id, categorie_id, anunt, interested FROM anunt WHERE editie_id = ? and categorie_id=?";
        java.sql.PreparedStatement psMessages=vgrabber.db.Connection.getConnection().prepareStatement(sql);
        psMessages.setInt(1,edition.getId());
        psMessages.setInt(2,category.getId());
        psMessages.execute();
        java.sql.ResultSet rsMessages=psMessages.getResultSet();
        while (rsMessages.next()){        
        Message message=new Message(rsMessages.getInt("id"),rsMessages.getString("anunt"),edition.getId(),rsMessages.getInt("categorie_id"),rsMessages.getBoolean("interested"));        
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
    public static ArrayList<Message> GetNewMessagesByEditon(Edition edition, Category category){
        ArrayList<Message> messages=new ArrayList<Message>();
        try{        
        //String sql="select tb1.id, (select categorie_id from anunt where id=tb1.id)as categorie_id, (select anunt from anunt where id=tb1.id )as anunt, (select interested from anunt where id=tb1.id )as interested from (select anunt.id, contact_anunt.contact_id from anunt inner join contact_anunt on anunt.id=contact_anunt.anunt_id where editie_id=?) tb1 where tb1.contact_id not in (select contact_anunt.contact_id from anunt inner join contact_anunt on anunt.id=contact_anunt.anunt_id where editie_id<>?) group by tb1.id";     
        String sql="SELECT id, (SELECT categorie_id FROM anunt WHERE id = tb1.id) AS categorie_id,(SELECT anunt FROM anunt WHERE id = tb1.id) AS anunt,(SELECT     interested FROM          anunt WHERE      id = tb1.id) AS interested FROM         (SELECT     anunt.id, contact_anunt.contact_id FROM anunt INNER JOIN contact_anunt ON anunt.id = contact_anunt.anunt_id WHERE editie_id =? AND categorie_id =?) tb1 WHERE (contact_id NOT IN (SELECT contact_anunt.contact_id FROM anunt INNER JOIN contact_anunt ON anunt.id = contact_anunt.anunt_id WHERE editie_id <> ? AND categorie_id = ?)) GROUP BY id"; 
        java.sql.PreparedStatement psMessages=vgrabber.db.Connection.getConnection().prepareStatement(sql); 
        psMessages.setInt(1,edition.getId());
        psMessages.setInt(2,category.getId());
        psMessages.setInt(3,edition.getId()); 
        psMessages.setInt(4,category.getId());        
        psMessages.execute();
            
        java.sql.ResultSet rsMessages=psMessages.getResultSet();
        while (rsMessages.next()){        
        Message message=new Message(rsMessages.getInt("id"),rsMessages.getString("anunt"),edition.getId(),rsMessages.getInt("categorie_id"),rsMessages.getBoolean("interested"));        
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
    public static ArrayList<Message> GetFavoriteMessages(){
        ArrayList<Message> messages=new ArrayList<Message>();
        try{        
        String sql="SELECT id, editie_id, categorie_id, anunt,interested FROM anunt WHERE interested = 1";
        java.sql.PreparedStatement psMessages=vgrabber.db.Connection.getConnection().prepareStatement(sql);        
        psMessages.execute();
        java.sql.ResultSet rsMessages=psMessages.getResultSet();
        while (rsMessages.next()){        
        Message message=new Message(rsMessages.getInt("id"),rsMessages.getString("anunt"),rsMessages.getInt("editie_id"),rsMessages.getInt("categorie_id"),rsMessages.getBoolean("interested"));        
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
    public static Message AddMessage(Message message){                
        try{        
        String sql="INSERT INTO anunt (editie_id, categorie_id, anunt) VALUES (?, ?, ?)";
        java.sql.PreparedStatement psMessage=vgrabber.db.Connection.getConnection().prepareStatement(sql);
        psMessage.setInt(1,message.getEdition_id());
        psMessage.setInt(2,message.getCategory_id());
        psMessage.setString(3,message.getAnunt());
        psMessage.execute();
        sql="select top 1 id from anunt order by id desc";
        java.sql.PreparedStatement psMessageLastId=vgrabber.db.Connection.getConnection().prepareStatement(sql);
        psMessageLastId.execute();
        java.sql.ResultSet rsMessageLastId=psMessageLastId.getResultSet();
        while (rsMessageLastId.next()){
            message.setId(rsMessageLastId.getInt("id"));
        }        
        
        // Add Message Contacts           
        for (Contact contact:message.getContacts()){
            if (!vgrabber.db.ContactManager.ContactExists(contact))
            vgrabber.db.ContactManager.AddContact(contact);
            sql="INSERT INTO contact_anunt (contact_id, anunt_id) VALUES (?,?)";
            java.sql.PreparedStatement psContact=vgrabber.db.Connection.getConnection().prepareStatement(sql);
            psContact.setString(1,contact.getId());
            psContact.setInt(2,message.getId());            
            psContact.execute();            
        }
        
        }
        catch (java.sql.SQLException ex){
            System.out.println(ex.toString());
        }
        finally{            
            return message;
        }        
    }
    public static boolean UpdMessage(Message message){        
        boolean updated=false;
        try{        
        String sql="UPDATE anunt SET editie_id =?, categorie_id =?, anunt =?, interested=? WHERE (id = ?)";
        java.sql.PreparedStatement psMessage=vgrabber.db.Connection.getConnection().prepareStatement(sql);                
        psMessage.setInt(1,message.getEdition_id());        
        psMessage.setInt(2,message.getCategory_id());
        psMessage.setString(3,message.getAnunt());
        psMessage.setBoolean(4,message.getInterested());        
        psMessage.setInt(5,message.getId());
        psMessage.execute();

        //Remove Message Contacts
        sql="DELETE contact_anunt WHERE anunt_id=?";
        java.sql.PreparedStatement psContactRem=vgrabber.db.Connection.getConnection().prepareStatement(sql);            
        psContactRem.setInt(1,message.getId());            
        psContactRem.execute();            
        
        // Add Message Contacts           
        for (Contact contact:message.getContacts()){
            if (!vgrabber.db.ContactManager.ContactExists(contact))
            vgrabber.db.ContactManager.AddContact(contact);
            sql="INSERT INTO contact_anunt (contact_id, anunt_id) VALUES (?,?)";
            java.sql.PreparedStatement psContact=vgrabber.db.Connection.getConnection().prepareStatement(sql);
            psContact.setString(1,contact.getId());
            psContact.setInt(2,message.getId());            
            psContact.execute();            
        }        
        
        updated=true;
        }
        catch (java.sql.SQLException ex){
            System.out.println(ex.toString());
        }
        finally{            
            return updated;
        }        
    }
    public static boolean DelMessage(Message message){        
        boolean deleted=false;
        try{        
        String sql="delete anunt WHERE (id = ?)";
        java.sql.PreparedStatement psMessage=vgrabber.db.Connection.getConnection().prepareStatement(sql);        
        psMessage.setInt(1,message.getId());
        psMessage.execute();
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
