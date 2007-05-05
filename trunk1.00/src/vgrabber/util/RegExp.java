package vgrabber.util;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegExp {
    public static ArrayList<String> getPhoneNumbers(String value) {
        ArrayList<String> result=new ArrayList<String>();
        
 	Pattern pattern = Pattern.compile("([\\d]{1,3})(\\-[\\d]{1,3}){1,4}([\\d]{1,10})");                                                   
	Matcher matcher = pattern.matcher(value);
	while (matcher.find()){
                result.add(matcher.group());
                //if (matcher.group().replace("-","").length()>4)
		//contacts.add(new Contact(matcher.group()));
	}		            
	
        if(result.isEmpty()){
            return null;            
        } else {
            return result;
        }                
    }
    public static ArrayList<String> getPrices(String value) {
        if (value==null){
            return null;
        }
        if (value==""){
            return null;
        }        
        ArrayList<String> result=new ArrayList<String>();    
        Pattern pattern = Pattern.compile("(((((\\.|\\,|\\s)?)([\\d]{1,10}))+)(\\s*+)((тыс.)*+|(т)*+|(т.)*+|(mii)*+)(\\s*+)(\\$|euro|евро))|((\\.|\\,|\\s)?)(\\$|euro|евро)((((\\.|\\,|\\s)?+)([\\d]{1,10}))+)");                                                   
	Matcher matcher = pattern.matcher(value);
	while (matcher.find()){
                result.add(matcher.group());
	}		            
        return result;
    }
}