/*
 * Edition.java
 *
 * Created on November 21, 2006, 6:55 PM
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

public class Edition{
	private int id;
	private Date date;        
        private String note;
	private ArrayList<Category> categories;
	public Edition(int id, Date date, String note){
	this.id=id;
	this.date=date;	
        this.note=note;		
	}
	public int getId(){
		return this.id;
	} 
	public void setId(int id){
		this.id=id;
	}
	public String getNote(){
		return this.note;
	}
	public void setNote(String note){
		this.note=note;
	}
	public Date getDate(){
		return this.date;
	}
	public void setDate(Date date){
		this.date=date;
	}
        public String toString(){
            return this.date+"("+this.id+")";
        }

        
	public ArrayList<Category> getCategories(){
		return categories;
	}
	public void setCategories(ArrayList<Category> categories){
		
	}
	public void addCategory(Category category){
		categories.add(category);
	}
	public void remCategory(Category category){
		
	}
	
}
