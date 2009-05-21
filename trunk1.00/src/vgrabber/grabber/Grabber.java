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
                        //Pattern pattern = Pattern.compile("<TD width=95%>.*?</TD>");
                        Pattern pattern = Pattern.compile("(?s)<td align=left valign=center>.+?</TD>");
                        Matcher matcher = pattern.matcher(value);            
                        
                        boolean hasMessages=false;
                        while (matcher.find()) {                
                            hasMessages=true;
                            messages.add(new vgrabber.common.Message(matcher.group().replace("<td align=left valign=center>","").replace("</TD>",""),edition.getId(),category.getId()));
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
                vgrabber.db.MessageManager.addMessage(msg);
            }    
            ShowTime(ds,"Store to DB");        
            return messages;
        }
        public static ArrayList<vgrabber.common.Edition> GrabEditions(){
        ArrayList<vgrabber.common.Edition> editions=new ArrayList<vgrabber.common.Edition>();        
        try {            
            URL url=new URL("http://www.makler.md");    
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET"); 
            //con.setRequestProperty("Referer","http://www.makler.md");
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
            //old version
            //Pattern pattern = Pattern.compile("<select name=\"issue\" align=\"absmiddle\" style=\"width: 121px;\">.*?</select>");                                                                                              
            
            Pattern pattern = Pattern.compile("(?s)<select name=\"issue\" align=\"absmiddle\" style=\"width: 125px;\">.+?</select>");                                                                                              
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
        public static ArrayList<vgrabber.common.Category> GrabCategories(){
        ArrayList<vgrabber.common.Category> categories=new ArrayList<vgrabber.common.Category>();        
        try {            
            URL url=new URL(" http://www.makler.md");    
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET"); 
            //con.setRequestProperty("Referer","http://www.makler.md");
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
            
            //Grabb Categories
            //Pattern selectboxpattern = Pattern.compile("<select name=\"rubric\".*?</select>");
            //Pattern pattern = Pattern.compile("(?s)<select name=\"issue\" align=\"absmiddle\" style=\"width: 121px;\">.+?</select>");   
            
            //Pattern selectboxpattern = Pattern.compile("(?s)<select name=\"rubric\" align=\"absmiddle\" style=\"width: 250px;\" onFocus=\" cancelLaggedSubmit\\(\\)\" onChange=\"loadSubrubricMenu\\(this\\[this.selectedIndex\\]\\.value,Menu\\);laggedSubmit\\(2000\\)\">.+?</select>");
            Pattern selectboxpattern = Pattern.compile("(?s)<select name=\"rubric\" align=\"absmiddle\" style=\"width: 250px;\" onFocus=\" cancelLaggedSubmit\\(\\)\" onChange=\"loadSubrubricMenu\\(this\\[this.selectedIndex\\]\\.value,Menu\\);laggedSubmit\\(2000\\)\">.+?</select>");
            Matcher selectboxmatcher = selectboxpattern.matcher(value);        
        
            Pattern itempattern = Pattern.compile("<option value=.*?</option>");
            Matcher itemmatcher;        
            //String cat;             
            //String[] catval;             
            while (selectboxmatcher.find ()) {
                itemmatcher=itempattern.matcher(selectboxmatcher.group());
                while(itemmatcher.find()){
                    if (!itemmatcher.group().contains("selected")){
                        categories.add(getCategoryFromString(itemmatcher.group()));                            
                    }
                }
            }            

            //Grabb SubCategories
            //Pattern pattern = Pattern.compile("Menu\\[i\\]\\s=\\snew\\sparent\\.Item\\(.*?;i\\+\\+;");
            Pattern pattern = Pattern.compile("Menu\\[i\\]\\s=\\snew\\sItem\\(.*?;i\\+\\+;");
            Matcher matcher = pattern.matcher(value);
            String cat;             
            String[] catval;             
            while (matcher.find ()) {
                cat=matcher.group();                       
                catval=cat.substring(cat.indexOf("(")+1,cat.indexOf(")")).split(",");
                categories.add(new vgrabber.common.Category(Integer.parseInt(catval[0]),Integer.parseInt(catval[1]),catval[2].replace("\"","")));
                //editions.add(getEditionFromString(matcher1.group()));                                
            }                                                            
            } catch (java.net.UnknownHostException ex1) {
                vgrabber.logger.Logger.getLogger().info(ex1.toString());                
            } catch (java.io.IOException ex2){       
                vgrabber.logger.Logger.getLogger().info(ex2.toString());                
            }  
            return categories;
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
            int day=Integer.parseInt(str.substring (23,25));
            //read edition month
            int month=Integer.parseInt(str.substring(26,28));
            //read edition year
            int year=Integer.parseInt(str.substring(29,31))+2000;                        
            //read edition note
            String strdate="20"+str.substring(29,31)+"."+str.substring(26,28)+"."+str.substring(23,25);
            java.util.Date date;
            //parse date
            java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd.MM.yy");
            try {            
            date=sdf.parse(str.substring (23,31));                        
            String note=str.substring(23,str.indexOf ("</option>"));                                    
            edition=new vgrabber.common.Edition(id,date, note);
            } catch (java.text.ParseException ex1) {
                vgrabber.logger.Logger.getLogger().info(ex1.toString());                
            }
            return edition;                
        }
        /**
        <option value=234>?????? ?????? ? ?????????</option>
        <option value=230>?????????</option>
        <option value=253>??????????</option>
        */
        public static vgrabber.common.Category getCategoryFromString(String str){                        
            //Read category id
            int id=Integer.parseInt(str.substring(str.indexOf("=")+1,str.indexOf(">")));              
            //Read category paretn id
            int parent_id=0;                        
            //read category name
            String str1=str.substring(str.indexOf(">")+1);            
            String name=str1.substring(0,str1.indexOf("<"));
            return new vgrabber.common.Category(id, parent_id, name);                
        }
        public static void ShowTime(long ds,String action){
            long de=java.util.Calendar.getInstance().getTimeInMillis();            
            java.util.Date dif=new java.util.Date();
            dif.setTime(de-ds); 
            java.text.SimpleDateFormat sf=new java.text.SimpleDateFormat("mm.ss.SSS");
            vgrabber.logger.Logger.getLogger().info(action+" tooks "+sf.format(dif));                            
        }
        
        /*
<Request 11>POST http://www.nomer.org/kishinev/ HTTP/1.1
<Request 11>Host: www.nomer.org
<Request 11>User-Agent: Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.8.0.11) Gecko/20070312 Firefox/1.5.0.11
//<Request 11>Accept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*\/*;q=0.5
//<Request 11>Accept-Language: en-us,en;q=0.5
<Request 11>Accept-Encoding: gzip,deflate
//<Request 11>Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.7
<Request 11>Keep-Alive: 300
<Request 11>Proxy-Connection: keep-alive
<Request 11>Referer: http://www.nomer.org/kishinev/
<Request 11>Cookie: hotlog=1
<Request 11>Content-Type: application/x-www-form-urlencoded
<Request 11>Content-Length: 38
<Request 11>
<Request 11>abonent=&phone=554462&street=&nd=&nkv=
         
         */
        
        public static String GrabPhoneInfo(String phonenumber){
        String phoneinfo="";     
        
        try {     
           //System.getProperties().put("proxySet","true");
           //System.getProperties().put("proxyPort","3128");
           //System.getProperties().put("proxyHost","localhost"); 
            
            URL url=new URL("http://www.nomer.org/kishinev/");    
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST"); 
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727; .NET CLR 1.1.4322)");
            con.setRequestProperty("Accept","text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");                        
            con.setRequestProperty("Referer","http://www.nomer.org/kishinev/");            
            con.setRequestProperty("Cookie","hotlog=1");                                                       
            con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");                        
                                    
            con.connect();  
            DataOutputStream dos=new DataOutputStream(con.getOutputStream());
            dos.writeBytes("abonent=&phone="+phonenumber+"&street=&nd=&nkv=");
            
            
            String value="";            
            java.io.InputStream is=con.getInputStream();
            java.io.ByteArrayOutputStream bStrim=new java.io.ByteArrayOutputStream();
            int ch;
            while ((ch = is.read()) != EOF_CH) {                                
                if (ch!=CARRIAGE_RETURN_CH){
                    bStrim.write(ch);       
                }
            }
            value=new String(bStrim.toByteArray(),"UTF-8");            
            value=value.replace("\n","");
            
            //Grabb Phonetable
            Pattern tablepattern = Pattern.compile("<table class='vivodDannih'>.*?</table>");
            Matcher tablematcher = tablepattern.matcher(value);        
                                        
            while (tablematcher.find ()) {
                phoneinfo=tablematcher.group();
            }            
                                                           
            } catch (java.net.UnknownHostException ex1) {
                vgrabber.logger.Logger.getLogger().info(ex1.toString());                
            } catch (java.io.IOException ex2){       
                vgrabber.logger.Logger.getLogger().info(ex2.toString());                
            }  
            return phoneinfo;
        }           
        
}
