/*
 * Main.java
 *
 * Created on November 21, 2006, 6:50 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber;

import java.util.*;

import vgrabber.util.*;
import vgrabber.common.*;
import vgrabber.db.*;


/**
 *
 * @author vdoni
 */
public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws java.io.UnsupportedEncodingException {
        // TODO code application logic here
        //makler.db.EditionManager.GetEdition(949);    
        
        /*
        
        makler.common.Edition ed=new makler.common.Edition(1,(new java.util.Date()),"test");
        makler.db.EditionManager.AddEdition(ed);
        java.util.Date d=new java.util.Date(2001,11,11);        
        ed.setDate(d);
        ed.setNote("note added");
        makler.db.EditionManager.UpdEdition(ed);
        
        
        makler.common.Message msg=new makler.common.Message("message test", 1,1);
        makler.common.Message msg1=makler.db.MessageManager.AddMessage(msg);
        msg1.setAnunt("asdfasd asdf la la");
        //msg1.setEdition_id(969);
        msg1.setCategory_id(2);
        makler.db.MessageManager.UpdMessage(msg1);
        makler.db.MessageManager.DelMessage(msg1);
        makler.db.EditionManager.DelEdition(ed);
        System.out.println(msg1.getId());
        
        
        makler.common.Edition ed=makler.db.EditionManager.GetLastEdition();
        //System.out.println(ed.getId());
        //System.out.println(ed.getDate());
        
        Message msg=new Message("34-34-34",1,1);
        for (Contact cnt:msg.getContacts()){
            System.out.println(cnt.getId());
        }
         */
        //ArrayList<makler.common.Edition> eds=makler.db.EditionManager.GetEditionsToDownload();
        //for (makler.common.Edition edi:eds){
        //makler.grabber.Gabber.Grab(edi);
        //}
        /*
        ArrayList<Category> c=makler.db.CategoryManager.getCategoriesToDownload();
        
        for(Category c1:c){
            
            //String value=c1.getName();            
            System.out.println(c1.getId()+" "+c1.getName());
            
            String text = new String(c1.getName().getBytes(), "Unicode");
            
            System.out.println(c1.getId()+" "+text);
        }
        
        
        for(Message msg:makler.db.MessageManager.GetMessagesByEditon(makler.db.EditionManager.GetEditionById(1572))){
        makler.db.MessageManager.UpdMessage(msg);    
        }         
        
        for (Edition edition:makler.db.EditionManager.GetAllEditions()){
        for(Message msg:makler.db.MessageManager.GetMessagesByEditon(edition)){
        makler.db.MessageManager.UpdMessage(msg);    
        }
        }
         */
        //for (Edition ed:makler.db.EditionManager.GetEditionsToDownload()){
            //makler.grabber.Gabber.Grab(ed);
        //}
        //makler.grabber.Gabber.Grab(new Edition(1577,java.util.Calendar.getInstance().getTime(),""));
        /*
        mgrabber.common.Config cnf=mgrabber.config.ConfigManager.geConfig();
        cnf.setServerType("s1");
        cnf.setServerName("s2");
        cnf.setDataBase("s3");
        cnf.setUserName("s4");
        cnf.setPassword("pass");
        mgrabber.config.ConfigManager.setConfig(cnf);
         */
        //vgrabber.common.Edition e=vgrabber.grabber.Grabber.getEditionFromString("<option value =\"1617\">04.01.07 ?2 ??.</option>");
        //System.out.println(e.toString());
        //boolean b=vgrabber.db.DBManager.CreateDBObjects();
        /*
        vgrabber.logger.Logger.getLogger().info("Info message");
        vgrabber.logger.Logger.getLogger().config("Config message");
        vgrabber.logger.Logger.getLogger().fine("Fine message");
        vgrabber.logger.Logger.getLogger().finer("Finer message");
        vgrabber.logger.Logger.getLogger().finest("Finest message");
        vgrabber.logger.Logger.getLogger().severe("Severe message");
        vgrabber.logger.Logger.getLogger().warning("Warning message");
        java.util.logging.Logger lg=vgrabber.logger.Logger.getLogger();
        lg.info("Ceva interesant");
        */
        //vgrabber.logger.Logger.getLogger().info("Start");
        /*
        vgrabber.client.WaitFrame wf=new vgrabber.client.WaitFrame();
        wf.setVisible(true);
        //wf.show();
        try {
            java.lang.Thread.sleep(5000);
        } catch (java.lang.InterruptedException ex1) {        
        }
        wf.dispose();
         
            java.util.ArrayList<vgrabber.common.Category> c=vgrabber.grabber.Grabber.GrabCategories1();
            for (vgrabber.common.Category cat:c){
                System.out.println(cat.getId()+" "+cat.getParentId()+" "+cat.toString());
            }
         */
        //vgrabber.common.Category c1=vgrabber.db.CategoryManager.getCategory(288);
        
        java.util.ArrayList<vgrabber.common.Category> cs=vgrabber.grabber.Grabber.GrabCategories();
        for (vgrabber.common.Category c:cs){
            vgrabber.db.CategoryManager.addCategory(c);
        }        
        /*
        java.util.ArrayList<Message> msgs=MessageManager.getMessage();
        for(Message msg:msgs){
            java.util.ArrayList<String> ps=RegExp.getPrices(msg.getAnunt());            
            String pps="";
            for(String p:ps){
                pps+=p;
            }
        msg.setPrice(pps);
        MessageManager.updMessage(msg);            
        }
        */
        
       //String s=vgrabber.grabber.Grabber.GrabPhoneInfo("444444");
        
        vgrabber.logger.Logger.getLogger().info("Appliction started");                        
        vgrabber.client.MainFrame mf=new vgrabber.client.MainFrame();
        mf.setVisible(true);        
    }
    
}
