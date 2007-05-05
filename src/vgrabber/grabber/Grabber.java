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
    
    /**
     * Creates a new instance of Grabber
     */
    public Grabber() {
    }	
        public static boolean GrabEdition(Edition edition){
        boolean result=true;
        vgrabber.db.EditionManager.AddEdition(edition);
	for (Category category:vgrabber.db.CategoryManager.getCategoriesToDownload()){
            for(int page=1;page<=2;page++){
            try {            
            URL url=new URL("http://makler.md/rubrview.php?edition=1&rubric="+category.getId()+"&issue="+edition.getId()+"&ads_pp=500&p="+page);	
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET"); 
            con.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727; .NET CLR 1.1.4322)");            
            con.connect();             
           	   	
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),"windows-1251"));
            String value="";
            String inputLine="";

            while ((inputLine = in.readLine()) != null) 
                value+=inputLine;
            in.close();   	
   	
            Pattern pattern = Pattern.compile("<TD width=95%>.*?</TD>");

            Matcher matcher = pattern.matcher(value);

            
            while (matcher.find()) {
                vgrabber.db.MessageManager.AddMessage(new vgrabber.common.Message(matcher.group().replace("<TD width=95%>","").replace("</TD>",""),edition.getId(),category.getId()));                	
            }            
   	} 
   	catch (IOException ex1) 
   	{
            result=false;
   	}    
    }
    }        
    return result;
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
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),"windows-1251")); 
            String value="";
            String inputLine="";

            while ((inputLine = in.readLine()) != null) 
                value+=inputLine;
            in.close();        
                   
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
       } 
       catch (IOException ex1) 
       {       
            System.out.println(ex1);
       }                    
return editions;
//ArrayList<mgrabber.common.Edition> eds=new ArrayList<mgrabber.common.Edition>();                
//eds.add(new mgrabber.common.Edition(1,java.util.Calendar.getInstance().getTime(),"ceva1"));        
//eds.add(new mgrabber.common.Edition(2,java.util.Calendar.getInstance().getTime(),"ceva2"));        
//eds.add(new mgrabber.common.Edition(3,java.util.Calendar.getInstance().getTime(),"ceva3"));        
//return eds;
}        
        /**
        This method creates Edition object from the line below
        <option value =\"1617\"> 04.01.07 ?2 ??.</option>
        <option value =\"1648\">30.01.07 ?13 ??.</option>
        <option value =\"1646\">26.01.07 ?12 ??.</option>
         */
        public static vgrabber.common.Edition getEditionFromString(String str){
            vgrabber.common.Edition edition=null;
            int id=Integer.parseInt(str.substring(16,20));
            
            int day=Integer.parseInt(str.substring (22,24));
            int month=Integer.parseInt(str.substring(25,27));
            int year=Integer.parseInt(str.substring(28,30))+2000;                        
            String strdate="20"+str.substring(28,30)+"."+str.substring(25,27)+"."+str.substring(22,24);
            java.util.Date date;
            //java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy.MM.dd");
            java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd.MM.yy");
            try {
            //date=sdf.parse(year+"."+month+"."+day);            
            date=sdf.parse(str.substring (22,30));            
            
            String note=str.substring(22,str.indexOf ("</option>"));                        
            
            edition=new vgrabber.common.Edition(id,date, note);
            } catch (java.text.ParseException ex1) {
                System.out.println(ex1);
            } finally {
                return edition;    
            }
        }

        
}
