/*
 * Logger.java
 *
 * Created on February 10, 2007, 12:42 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber.logger;

import java.io.*;

/**
 *
 * @author vdoni
 */
public class Logger {
    
    private static java.util.logging.Logger logger=null;
    

    public static java.util.logging.Logger getLogger(){
        if (logger==null){
            try {
                java.util.logging.LogManager lm=java.util.logging.LogManager.getLogManager();
                java.io.FileInputStream fis=new java.io.FileInputStream("logs/logsconfig.properties");        
                lm.readConfiguration(fis);                
            } catch (java.io.IOException ex1){
                System.out.println(ex1);
            }        
            logger=java.util.logging.Logger.getAnonymousLogger();                        
        }
        return logger;
    } 
    
}
