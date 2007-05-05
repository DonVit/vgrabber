/*
 * Grabber.java
 *
 * Created on November 24, 2006, 4:13 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber.grabber;

import java.util.*;
import java.util.regex.*;
import java.net.*;
import java.io.*;
import vgrabber.common.Category;
import vgrabber.common.Edition;

/**
 *
 * @author vdoni
 */
public class Grabber {
 private static final int EOF_CH = -1;   
 private static final int CARRIAGE_RETURN_CH = 13; 
    /**
     * Creates a new instance of Grabber
     */
    public Grabber() {
    }	
        public static ArrayList<vgrabber.common.Message> GrabEdition(Edition edition){
        
            //System.getProperties().put("proxySet","true");
            //System.getProperties().put("proxyPort","3128");
            //System.getProperties().put("proxyHost","localhost");            
                    
            ArrayList<vgrabber.common.Message> messages=new ArrayList<vgrabber.common.Message>();
        
            if (vgrabber.db.EditionManager.GetEditionById(edition.getId())==null){
                vgrabber.db.EditionManager.AddEdition(edition);
            }
            //get time before grabb starts
            long ds=java.util.Calendar.getInstance().getTimeInMillis();
        
            for (Category category:vgrabber.db.CategoryManager.getCategoriesToDownload(edition)){
                int page=0;
                while(true){
                    page++;
                    try {       
                        ds=java.util.Calendar.getInstance().getTimeInMillis();
                        URL url=new URL("http://makler.md/rubrview.php?edition=1&rubric="+category.getId()+"&issue="+edition.getId()+"&ads_pp=500&p="+page);	
                        HttpURLConnection con=(HttpURLConnection)url.openConnection();
                        con.setRequestMethod("GET"); 
                        con.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727; .NET CLR 1.1.4322)");            
                        con.connect();             
                        ShowTime(ds,"Connection to server");
                        
                        //If Server response code is different from 200
                        //then break the while and go to next category
                        if (con.getResponseCode()!=con.HTTP_OK){
                            break;
                        }
           	   	
                        ds=java.util.Calendar.getInstance().getTimeInMillis();                                   
                        String value="";
                        java.io.InputStream is;
                        java.io.ByteArrayOutputStream bStrim;
                        is=con.getInputStream();                                         
                        bStrim=new java.io.ByteArrayOutputStream();
                        int ch;
                        while ((ch = is.read()) != EOF_CH) {                                
                            if (ch!=CARRIAGE_RETURN_CH){
                                bStrim.write(ch);       
                            }
                        }
                        is.close();
                        value=new String(bStrim.toByteArray(),"windows-1251");
                        value=value.replace("\n","");
                        ShowTime(ds,"Read from buffer");
                             
                        ds=java.util.Calendar.getInstance().getTimeInMillis();
                        Pattern pattern = Pattern.compile("<TD width=95%>.*?</TD>");
                        Matcher matcher = pattern.matcher(value);            
                        
                        boolean hasMessages=false;
                        while (matcher.find()) {                
                            hasMessages=true;
                            messages.add(new vgrabber.common.Message(matcher.group().replace("<TD width=95%>","").replace("</TD>",""),edition.getId(),category.getId()));
                        }                    
                        //Check if the curent page has no messages then this means
                        //that this is empty page and previous page was the last 
                        //So if hasMessages is false is better to break the cicle on this 
                        //category and go the next
                        if (!hasMessages){
                            break;
                        }                        
                        ShowTime(ds,"Grab messages");            
                        
                    } catch (IOException ex1) {
                        vgrabber.logger.Logger.getLogger().info(ex1.toString());                                                                
                    }                                   
                }
            }
            //Save to DB
            //get time before store to db
            ds=java.util.Calendar.getInstance().getTimeInMillis();            
            for(vgrabber.common.Message msg:messages){
                vgrabber.db.MessageManager.AddMessage(msg);
            }    
            ShowTime(ds,"Store to DB");        
            return messages;
        }
        public static ArrayList<vgrabber.common.Edition> GrabEditions(){
        ArrayList<vgrabber.common.Edition> editions=new ArrayList<vgrabber.common.Edition>();        
        try {            
            URL url=new URL(" http://www.makler.md/top.php");    
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET"); 
            con.setRequestProperty("Referer","http://www.makler.md");
            con.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727; .NET CLR 1.1.4322)");
            con.connect();  
            
            String value="";            
            java.io.InputStream is=con.getInputStream();
            java.io.ByteArrayOutputStream bStrim=new java.io.ByteArrayOutputStream();
            int ch;
            while ((ch = is.read()) != EOF_CH) {                                
                if (ch!=CARRIAGE_RETURN_CH){
                    bStrim.write(ch);       
                }
            }
            value=new String(bStrim.toByteArray(),"windows-1251");            
            value=value.replace("\n","");
            
            Pattern pattern = Pattern.compile("<select name=\"issue\" align=\"absmiddle\" style=\"width: 121px;\">.*?</select>");
            Matcher matcher = pattern.matcher(value);
            
            Pattern pattern1 = Pattern.compile("<option value =.*?</option>");
            Matcher matcher1;
            
            while (matcher.find ()) {
                matcher1=pattern1.matcher(matcher.group());
                while (matcher1.find()){
                    editions.add(getEditionFromString(matcher1.group()));                    
                } 
            }            
            } catch (java.net.UnknownHostException ex1) {
                vgrabber.logger.Logger.getLogger().info(ex1.toString());                
            } catch (java.io.IOException ex2){       
                vgrabber.logger.Logger.getLogger().info(ex2.toString());                
            }  
            return editions;
        }        
        /**
        This method creates Edition object from the lines below
        <option value =\"1617\"> 04.01.07 ?2 ??.</option>
        <option value =\"1648\">30.01.07 ?13 ??.</option>
        <option value =\"1646\">26.01.07 ?12 ??.</option>
         */
        public static vgrabber.common.Edition getEditionFromString(String str){
            vgrabber.common.Edition edition=null;
            //Read edition id
            int id=Integer.parseInt(str.substring(16,20));            
            //read edition day
            int day=Integer.parseInt(str.substring (22,24));
            //read edition month
            int month=Integer.parseInt(str.substring(25,27));
            //read edition year
            int year=Integer.parseInt(str.substring(28,30))+2000;                        
            //read edition note
            String strdate="20"+str.substring(28,30)+"."+str.substring(25,27)+"."+str.substring(22,24);
            java.util.Date date;
            //parse date
            java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd.MM.yy");
            try {            
            date=sdf.parse(str.substring (22,30));                        
            String note=str.substring(22,str.indexOf ("</option>"));                                    
            edition=new vgrabber.common.Edition(id,date, note);
            } catch (java.text.ParseException ex1) {
                vgrabber.logger.Logger.getLogger().info(ex1.toString());                
            }
            return edition;                
        }
        public static void ShowTime(long ds,String action){
            long de=java.util.Calendar.getInstance().getTimeInMillis();            
            java.util.Date dif=new java.util.Date();
            dif.setTime(de-ds); 
            java.text.SimpleDateFormat sf=new java.text.SimpleDateFormat("mm.ss.SSS");
            vgrabber.logger.Logger.getLogger().info(action+" tooks "+sf.format(dif));                            
        }

        
}
