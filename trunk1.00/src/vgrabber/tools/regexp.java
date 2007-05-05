/*
 * regexp.java
 *
 * Created on March 6, 2007, 2:14 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber.tools;

import java.util.*;
import java.util.regex.*;
/**
 *
 * @author vdoni
 */
public class regexp {
    
    /**
     * Creates a new instance of regexp
     */
    public regexp() {
    }
    public static void main(String[] arg){
        //String value="Menu[i] = new parent.Item(15,230,\"???????? ??????????\",2);i++;";
        String value="<option value=223>????????-??????????</option>";        
        //Pattern pattern = Pattern.compile("Menu\\[i\\]\s\\=\snew\sparent\.Item.*?;i\+\+;");
        Pattern pattern = Pattern.compile("<option value=.*?</option>");
        
        Matcher matcher = pattern.matcher(value);
        String c;
        while (matcher.find()) {
            c=matcher.group();
            System.out.println(c);
            //System.out.println(c.substring(c.indexOf("("),c.indexOf(")")));
            //System.out.println(matcher.group().s);
            //categories.add(new vgrabber.common.Category(Integer.parseInt(c[0]),Integer.parseInt(c[1]),c[2].replace("\"","")));
            //editions.add(getEditionFromString(matcher1.group()));
        }
        
        //System.out.println("test");
    }
}
