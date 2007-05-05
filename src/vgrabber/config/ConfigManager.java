/*
 * DBConfigManager.java
 *
 * Created on January 25, 2007, 11:20 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber.config;

import java.io.FileInputStream;

/**
 *
 * @author VDoni
 */
public class ConfigManager {
    
    /** Creates a new instance of DBConfigManager */
    public ConfigManager() {
    }
    public static vgrabber.common.Config geConfig(){
        vgrabber.common.Config cnf=new vgrabber.common.Config();
        java.util.Properties prop=new java.util.Properties();        
        try {
        java.io.FileInputStream fis=new java.io.FileInputStream("config/Config.properties");
        prop.load(fis);
        fis.close();
        cnf.setServerType(prop.getProperty("ServerType"));
        cnf.setServerName(prop.getProperty("ServerName"));
        cnf.setDataBase(prop.getProperty("DataBase"));
        cnf.setUserName(prop.getProperty("UserName"));
        cnf.setPassword(prop.getProperty("Password"));
        } catch (java.io.IOException ioex){
            System.out.println(ioex);            
        } finally {
        return cnf;
        }
    }
    public static boolean setConfig(vgrabber.common.Config cnf) {
        boolean result=true;
        java.util.Properties prop=new java.util.Properties();        
        prop.setProperty("ServerType",cnf.getServerType());
        prop.setProperty("ServerName",cnf.getServerName());
        prop.setProperty("DataBase",cnf.getDatabase());
        prop.setProperty("UserName",cnf.getUserName());
        prop.setProperty("Password",cnf.getPassword());               
        try {                
        java.io.File f=new java.io.File("config/Config.properties");                    
        java.io.FileOutputStream fos=new java.io.FileOutputStream(f);                
        prop.store(fos,"VGrabber Configs");
        fos.close();        
        } catch (java.io.IOException ioex){
            System.out.println(ioex);            
            result=false;
        } finally {
            return result;
        }        
    } 
}
