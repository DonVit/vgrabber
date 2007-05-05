/*
 * Makler.java
 *
 * Created on November 21, 2006, 6:56 PM
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

public class MGrabber{
	private int id;
	private String name;
	private ArrayList<Message> messages;
	public MGrabber(){		
	
	}
	public ArrayList<Edition> CheckNewEditions(){
		return null;
	}
	public int GetEditions(){
		return this.id;
	} 
	public void SetId(int id){
		this.id=id;
	}
	public String GetName(){
		return this.name;
	}
	public void SetName(String name){
		this.name=name;
	}
	public ArrayList<Message> GetMessages(){
		return messages;
	}
	public void SetMessages(ArrayList<Message> messages){
		
	}
	public void AddMessage(Message message){
		messages.add(message);
	}
	public void RemMessage(Message message){
		
	}
	
}