/*
 * ContactManager.java
 *
 * Created on November 24, 2006, 4:47 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber.db;
import java.util.*;
import java.sql.*;
import vgrabber.common.Contact;
import vgrabber.common.Message;

/**
 *
 * @author vdoni
 */
public class ContactManager {
    
    /** Creates a new instance of ContactManager */
    public ContactManager() {
    }
    public static Contact getContactById(int id){
        Contact contact=null;
        try{        
        String sql="SELECT id, phone, note FROM contact WHERE (id = ?)";
        java.sql.PreparedStatement psContact=vgrabber.db.Connection.getConnection().prepareStatement(sql);
        psContact.setInt(1,id);
        psContact.execute();
        java.sql.ResultSet rsContact=psContact.getResultSet();
        while (rsContact.next()){        
        contact=new Contact(rsContact.getString("id"),rsContact.getString("phone"),rsContact.getString("note"));        
        }
        }
        catch (java.sql.SQLException ex){
            System.out.println(ex.toString());
        }
        finally{            
            return contact;
        }
    }
    public static boolean ContactExists(Contact contact){
        boolean result=false;
        try{        
        String sql="SELECT id, phone, note FROM contact WHERE (id = ?)";
        java.sql.PreparedStatement psContact=vgrabber.db.Connection.getConnection().prepareStatement(sql);
        psContact.setString(1,contact.getId());
        psContact.execute();
        java.sql.ResultSet rsContact=psContact.getResultSet();
        while (rsContact.next()){        
        result=true;
        }
        }
        catch (java.sql.SQLException ex){
            System.out.println(ex.toString());
        }
        finally{            
            return result;
        }
    }
    
    public static ArrayList<Contact> getContactsByMessageId(Message message){
        ArrayList<Contact> contacts=new ArrayList<Contact>();
        try{        
        String sql="SELECT id, phone, note FROM contact WHERE (id = ?)";
        java.sql.PreparedStatement psContact=vgrabber.db.Connection.getConnection().prepareStatement(sql);
        psContact.setInt(1,message.getId());
        psContact.execute();
        java.sql.ResultSet rsContact=psContact.getResultSet();
        while (rsContact.next()){        
        Contact contact=new Contact(rsContact.getString("id"),rsContact.getString("phone"),rsContact.getString("note"));        
        contacts.add(contact);
        }
        }
        catch (java.sql.SQLException ex){
            System.out.println(ex.toString());
        }
        finally{            
            return contacts;
        }
    }    
    public static boolean AddContact(Contact contact){                
        boolean added=false;
        try{        
        String sql="insert into contact(id, phone, note) values(?,?,?)";
        java.sql.PreparedStatement psContact=vgrabber.db.Connection.getConnection().prepareStatement(sql);
        psContact.setString(1,contact.getId());
        psContact.setString(2,contact.getPhone());
        psContact.setString(3,contact.getNote());
        psContact.execute();
        added=true;
        }
        catch (java.sql.SQLException ex){
            System.out.println(ex.toString());
        }
        finally{            
            return added;
        }        
    }
    public static boolean UpdContact(Contact contact){        
        boolean updated=false;
        try{        
        String sql="UPDATE contact SET id =?, phone =?, note =? WHERE (id = ?)";
        java.sql.PreparedStatement psContact=vgrabber.db.Connection.getConnection().prepareStatement(sql);                
        psContact.setString(1,contact.getId());        
        psContact.setString(2,contact.getPhone());
        psContact.setString(3,contact.getNote());
        psContact.setString(4,contact.getId());
        psContact.execute();
        updated=true;
        }
        catch (java.sql.SQLException ex){
            System.out.println(ex.toString());
        }
        finally{            
            return updated;
        }        
    }
    public static boolean DelContact(Contact contact){        
        boolean deleted=false;
        try{        
        String sql="delete contact WHERE (id = ?)";
        java.sql.PreparedStatement psContact=vgrabber.db.Connection.getConnection().prepareStatement(sql);        
        psContact.setString(1,contact.getId());
        psContact.execute();
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
