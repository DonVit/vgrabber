/*
 * DBConfig.java
 *
 * Created on January 25, 2007, 11:10 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber.common;

/**
 *
 * @author VDoni
 */
public class Config {
    
    private String servertype; 
    private String servername;
    private String database;    
    private String username;    
    private String password;    
    
    public Config() {
        servertype=""; 
        servername="";
        database="";    
        username="";    
        password="";            
    }
    
    public void setServerType(String servertype){
        this.servertype=servertype;
    }
    public String getServerType(){
        return this.servertype;
    }
    public void setServerName(String servername){
        this.servername=servername;
    }
    public String getServerName(){
        return this.servername;
    }
    public void setDataBase(String database){
        this.database=database;
    }
    public String getDatabase(){
        return this.database;
    }    
    public void setUserName(String username){
        this.username=username;
    }
    public String getUserName(){
        return this.username;
    }        
    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return this.password;
    }            
}
