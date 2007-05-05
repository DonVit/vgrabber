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

public class Region{
	private int id;
	private String name;
        private String[] filteritems;
	public Region(int id, String name, String[] filteritems){
	this.id=id;
	this.name=name;	
        this.filteritems=filteritems;
	}
        /**
        * @author vdoni
        * aceasta metoda face  ceva 
        */
	public int getId(){
		return this.id;
	} 
	public void setId(int id){
		this.id=id;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	public String[] getFilterItems(){
		return this.filteritems;
	}
	public void setFilterItems(String[] filteritems){
		this.filteritems=filteritems;
	}
        public String toString(){
            return this.name;
        }
	
}