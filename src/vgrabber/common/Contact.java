/*
 * Contact.java
 *
 * Created on November 21, 2006, 6:52 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber.common;

/**
 *
 * @author vdoni
 */
public class Contact {	
        private String id;
	private String phone;
        private String note;
	public Contact(String phone){
                this.phone=phone;		
                this.id=this.phone.replace("-","");		
                this.note="";		                
	}
        
	public Contact(String phone, String note){
                //this.id=Integer.parseInt(this.phone.replace("-",""));
                this.id=this.phone.replace("-","");
		this.phone=phone;		
                this.note=note;		                
	}
        public Contact(String id, String phone, String note){
                this.id=id;
		this.phone=phone;		
	}
   	public String getId(){
	return this.id;
	}        
   	public String getPhone(){
	return this.phone;
	}
   	public String getNote(){
	return this.note;
	}        
}
