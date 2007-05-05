/*
 * mysqlconn.java
 *
 * Created on March 7, 2007, 12:03 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber.tools;

import java.util.*;
import java.sql.*;

/**
 *
 * @author vdoni
 */
public class mysqlconn {
    private static java.sql.Connection connection=null;
    /** Creates a new instance of mysqlconn */
    public mysqlconn() {
    }
    public static void main(String[] args){        
        //if (connection==null){
        String url = "jdbc:mysql://localhost/imobil5" ;
        //String url = "jdbc:odbc:imobil;UID=sa;PWD=as";        
        //String url = "jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=imobil2";
        try {       
        Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");                        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Class.forName("com.mysql.jdbc.Driver") ;                                    
        //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver") ;                                
        //connection = java.sql.DriverManager.getConnection(url);
        //Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
        //Class.forName("net.sourceforge.jtds.jdbc.Driver");        
        //String url = "jdbc:jtds:sqlserver://localhost:1433/northwind";
        Properties props = new Properties();
        props.put("user", "root");
        props.put("password", "");            
        connection = DriverManager.getConnection(url, props);
        } catch(java.lang.IllegalAccessException e) {
            System.out.println(e.toString());                        
        } catch(java.lang.InstantiationException e) {
            System.out.println(e.toString());                
        } catch(ClassNotFoundException e) {
            System.out.println(e.toString());
        }
        catch(java.sql.SQLException e) {
            System.out.println(e.toString());
        }        
                
        
    }
    public static void main1(String args[]) {
    Connection con = null;

    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      con = DriverManager.getConnection("jdbc:mysql:///test",
        "root", "secret");

      if(!con.isClosed())
        System.out.println("Successfully connected to " +
          "MySQL server using TCP/IP...");

    } catch(Exception e) {
      System.err.println("Exception: " + e.getMessage());
    } finally {
      try {
        if(con != null)
          con.close();
          } catch(SQLException e) {}
    }
  }   
}
