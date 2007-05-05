/*
 * Message.java
 *
 * Created on November 21, 2006, 6:53 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber.common;

/**
 *
 * @author vdoni
 */
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Message {
	private int id;    
	private String message;
        private int edition_id;
        private int category_id;
        private boolean interested;

	public Message(String message, int edition_id, int category_id){
                this.id=0;
		this.message=message;
                this.edition_id=edition_id;
                this.category_id=category_id;
                this.interested=false;
	}        
	public Message(int id, String message, int edition_id, int category_id, boolean interested){
                this.id=id;
		this.message=message;
                this.edition_id=edition_id;
                this.category_id=category_id;
                this.interested=interested;
	}        

        public int getId(){
	return this.id;
	}
	public void setId(int id){
		this.id=id;		
	}        
   	public String getAnunt(){
	return this.message;
	}
	public void setAnunt(String message){
		this.message=message;		
	}
   	public int getEdition_id(){
	return this.edition_id;
	}        
   	public void setEdition_id(int edition_id){
	this.edition_id=edition_id;
	}        
        
   	public int getCategory_id(){
	return this.category_id;
	}        
   	public void setCategory_id(int category_id){
	this.category_id=category_id;
	}  
   	public boolean getInterested(){
	return this.interested;
	}        
   	public void setInterested(boolean interested){
	this.interested=interested;
	}         
	public ArrayList<Contact> getContacts(){
    	ArrayList<Contact> contacts=new ArrayList<Contact>();
 	Pattern pattern = Pattern.compile("([\\d]{1,3})(\\-[\\d]{1,3}){1,4}([\\d]{1,10})");
	Matcher matcher = pattern.matcher(this.message);
	while (matcher.find()){
                if (matcher.group().replace("-","").length()>4)
		contacts.add(new Contact(matcher.group()));
	}		            
	return contacts;
	}        
}