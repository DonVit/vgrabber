/*
 * DBManager.java
 *
 * Created on February 4, 2007, 2:29 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber.db;

import java.sql.PreparedStatement;
import java.io.File;
import java.io.FileInputStream;
import java.util.logging.LogRecord;
/**
 *
 * @author VDoni
 */
public class DBManager {
    
    /** Creates a new instance of DBManager */
    public DBManager() {
    }
    
    public static boolean CreateDBObjects(){
        boolean result=true;
        //java.util.logging.LogManager lm=java.util.logging.LogManager.getLogManager();
        //java.util.logging.Handler hd=new java.util.logging.
        //java.util.logging.Logger log=java.util.logging.Logger.getLogger("sql.log");
        
        try {       
                                    
            String sql="";
            String servertype="MSSQL";
            java.io.FileInputStream fis;
            if (vgrabber.config.ConfigManager.geConfig().getServerType().equals("MSSQL")){
                fis=new java.io.FileInputStream("config/mssqldbobjects.sql");
            } else {
                fis=new java.io.FileInputStream("config/mysqldbobjects.sql");
            }
            java.io.InputStreamReader isr=new java.io.InputStreamReader(fis,"unicode");
            java.io.BufferedReader br=new java.io.BufferedReader(isr);
            String line;
            
            while ((line=br.readLine())!=null){
                sql+=line+" ";
            }
            //log.info(sql);
            java.sql.Connection conn=vgrabber.db.Connection.getConnection();
            
            java.sql.PreparedStatement ps=conn.prepareStatement(sql);
            ps.execute();                        
            
        } catch (java.sql.SQLException ex2){
           result=false;
           System.out.println(ex2);           
        } catch (java.io.FileNotFoundException ex3){
            result=false;
            System.out.println(ex3);            
        } catch (java.io.UnsupportedEncodingException ex4){
            result=false;
            System.out.println(ex4);            
        } catch (java.io.IOException ex5){
            result=false;
            System.out.println(ex5);            
        }
        return result;
        
    }
}
