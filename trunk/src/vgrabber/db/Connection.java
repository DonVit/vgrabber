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
    
    private static java.sql.Connection connection=null;
    /** Creates a new instance of Connection */    
    public Connection() {
    }
    public static java.sql.Connection GetConnection(){        
        vgrabber.common.Config cnf=vgrabber.config.ConfigManager.geConfig();
        //if (connection==null){
        //String url = "jdbc:mysql://localhost/imobil" ;
        //String url = "jdbc:odbc:imobil;UID=sa;PWD=as";        
        String url = "jdbc:microsoft:sqlserver://"+cnf.getServerName()+":1433;DatabaseName="+cnf.getDatabase();
        try {        
        ///Class.forName("com.mysql.jdbc.Driver") ;                                    
        //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver") ;                                
        //connection = java.sql.DriverManager.getConnection(url);
        Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
        //Class.forName("net.sourceforge.jtds.jdbc.Driver");        
        //String url = "jdbc:jtds:sqlserver://localhost:1433/northwind";
        Properties props = new Properties();
        props.put("user", cnf.getUserName());        
        props.put("password", cnf.getPassword());            
        connection = DriverManager.getConnection(url, props);
        } catch(ClassNotFoundException e) {
            System.out.println(e.toString());
        }
        catch(java.sql.SQLException e) {
            System.out.println(e.toString());
        }        
        finally {
        return connection;
        }
    }
}
