/*
 * Category.java
 *
 * Created on November 21, 2006, 6:54 PM
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

public class Category{
	private int id;
        private int parentid;        
	private String name;
        private boolean toupdate;
	public Category(int id, int parentid, String name){
	this.id=id;
        this.parentid=parentid;        
	this.name=name;	
        this.toupdate=false;
	}        
	public Category(int id, int parentid, String name, boolean toupdate){
	this.id=id;
        this.parentid=parentid;        
	this.name=name;	
        this.toupdate=toupdate;
	}
	public int getId(){
		return this.id;
	} 
	public void setId(int id){
		this.id=id;
	}
	public int getParentId(){
		return this.parentid;
	} 
	public void setParentId(int parentid){
		this.parentid=parentid;
	}        
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
        public String toString(){
            return this.name;
        }
	public boolean getToUpdate(){
		return this.toupdate;
	}        
	public void setToUpdate(boolean toupdate){
		this.toupdate=toupdate;
	}
        public boolean CompareTo(Category category){
            if (this.id==category.getId()) {
               return true; 
            } else {
               return false;
            }                    
        }
}