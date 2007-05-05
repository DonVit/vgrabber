/*
 * Connection.java
 *
 * Created on November 21, 2006, 6:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber.db;

import java.util.*;
import java.sql.*;
import vgrabber.logger.*;
/**
 *
 * @author vdoni
 */
import java.sql.*;

/**
 *
 * @author vdoni
 */
public class Connection {        
    /** Creates a new instance of Connection */    
    private static java.sql.Connection connection=null;      
    private static String url="";      
    public Connection() {
    }
    public static java.sql.Connection getConnection() {        
        //check if there already connection created
        if (connection==null){
        try { 
        //read conection details from config file    
        vgrabber.common.Config cnf=vgrabber.config.ConfigManager.geConfig();            
        if (cnf.getServerType().equals("MSSQL")){
            //connection string
            url = "jdbc:microsoft:sqlserver://"+cnf.getServerName()+":1433;DatabaseName="+cnf.getDatabase();        
            //connection properties
            Properties props = new Properties();
            props.put("user", cnf.getUserName());        
            props.put("password", cnf.getPassword());                         
            Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");            
            connection = DriverManager.getConnection(url, props);                    
        } else {
            Class.forName("com.mysql.jdbc.Driver"); 
            //connection string
            url = "jdbc:mysql://"+cnf.getServerName()+"/"+cnf.getDatabase();
            //connection properties
            Properties props = new Properties();
            props.put("user", cnf.getUserName());
            props.put("password", cnf.getPassword());            
                                               
            connection = DriverManager.getConnection(url, props);                                    
        }
        
        } catch(ClassNotFoundException ex1) {
            Logger.getLogger().info(ex1.toString());                                    
        } catch (java.sql.SQLException ex2) {
            Logger.getLogger().info(ex2.toString());                                
        }
        }
        return connection;
    }
    public static void setConnection(java.sql.Connection conn) {
        connection=conn;        
    }
}
