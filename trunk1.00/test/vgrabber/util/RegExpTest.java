package vgrabber.util;



import junit.framework.*;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/*
 * RegExpTest.java
 * JUnit based test
 *
 * Created on 26 Апрель 2007 г., 19:42
 */

/**
 *
 * @author vdoni
 */
public class RegExpTest extends TestCase {
    
    public RegExpTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(RegExpTest.class);
        
        return suite;
    }

    /**
     * Test of getPhoneNumbers method, of class RegExp.
     */
    public void testGetPhoneNumbersValueNull() {
        System.out.println("getPhoneNumbers(null)");        
        String value = "";        
        ArrayList<String> expResult = null;
        ArrayList<String> result = RegExp.getPhoneNumbers(value);
        assertEquals(expResult, result);               
    }
    public void testGetPhoneNumbersValue1() {
        System.out.println("getPhoneNumbers(ddd-ddd-dd)");        
        String value = "text 2 rer 123-456-789af fe";        
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("123-456-789");
        ArrayList<String> result = RegExp.getPhoneNumbers(value);
        assertEquals(expResult, result);               
    }
    public void testGetPhoneNumbersValue2() {
        System.out.println("getPhoneNumbers(d-ddd-dd-dd)");        
        String value = "text 2 rer 0-691-85-567af fe";        
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("0-691-85-567");
        ArrayList<String> result = RegExp.getPhoneNumbers(value);
        assertEquals(expResult, result);               
    } 
    public void testGetPhoneNumbersValue3() {
        System.out.println("getPhoneNumbers(ddd-ddd)");        
        String value = "text 2 rer 522-045af fe";        
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("522-045");
        ArrayList<String> result = RegExp.getPhoneNumbers(value);
        assertEquals(expResult, result);               
    }    
    public void testGetPhoneNumbersValue4() {
        System.out.println("getPhoneNumbers(dd-dd-dd)");        
        String value = "text 2 rer 27-89-15af fe";        
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("27-89-15");
        ArrayList<String> result = RegExp.getPhoneNumbers(value);
        assertEquals(expResult, result);               
    }    
    public void testGetPhoneNumbersValue5() {
        System.out.println("getPhoneNumbers(d-dd-dd)");        
        String value = "text 2 rer 7-89-15af fe";        
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("7-89-15");
        ArrayList<String> result = RegExp.getPhoneNumbers(value);
        assertEquals(expResult, result);               
    }        
    public void testGetPhoneNumbersValue6() {
        System.out.println("getPhoneNumbers(d-ddd-d-dd-dd)");        
        String value = "text 2 rer 0-268-2-49-34 af fe";        
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("0-268-2-49-34");
        ArrayList<String> result = RegExp.getPhoneNumbers(value);
        assertEquals(expResult, result);               
    }        
    public void testGetPhoneNumbersValue7() {
        System.out.println("getPhoneNumbers(d-ddd-dd-ddd)");        
        String value = "text 2 rer 0-268-23-890af fe";        
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("0-268-23-890");
        ArrayList<String> result = RegExp.getPhoneNumbers(value);
        assertEquals(expResult, result);               
    }            
    public void testGetPhoneNumbersValue8() {
        System.out.println("getPhoneNumbers(d-dddddddd)");        
        String value = "text 2 rer 0-69185567af fe";        
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("0-69185567");
        ArrayList<String> result = RegExp.getPhoneNumbers(value);
        assertEquals(expResult, result);                       
    }            
    public void testGetPricesValueNull(){
        System.out.println("get{Prices(empty string)");        
        String value = "";        
        ArrayList<String> expResult = null;        
        ArrayList<String> result = RegExp.getPrices(value);
        assertEquals(expResult, result);                               
    }
    public void testGetPricesValueValuta(){
        System.out.println("get{Prices(manipulari cu valuta)");        
        String value = " 1 euro 100euro, 1.000 $ 22 222$ 121,222евро, 323.333 евро, $1000, $10 100, $ 12,122, $ 1 112 122 t12,234$ref 200 mii euro";        
        ArrayList<String> expResult = new ArrayList<String>();        
        expResult.add(" 1 euro");
        expResult.add(" 100euro");
        expResult.add(" 1.000 $");
        expResult.add(" 22 222$");
        expResult.add(" 121,222евро");
        expResult.add(" 323.333 евро");
        expResult.add(" $1000");
        expResult.add(" $10 100");
        expResult.add(" $ 12,122");
        expResult.add(" $ 1 112 122");
        expResult.add("12,234$");
        expResult.add(" 200 mii euro");
        
        ArrayList<String> result = RegExp.getPrices(value);
        assertEquals(expResult, result);                               
    }    
    public void testGetPricesValue2(){
        System.out.println("get{Prices(ddd.ddd euro)");        
        String value = "1,16 ha Suruceni 136.000 euro. 23-26-87, 0-690-92-080.";        
        ArrayList<String> expResult = new ArrayList<String>();        
        expResult.add(" 136.000 euro");
        ArrayList<String> result = RegExp.getPrices(value);
        assertEquals(expResult, result);                               
    }        
    public void testGetPricesValue3(){
        System.out.println("get{Prices(dd тыс. евро.)");        
        String value = "12 тыс. евро. 75-96-67.";        
        ArrayList<String> expResult = new ArrayList<String>();        
        expResult.add("12 тыс. евро");
        ArrayList<String> result = RegExp.getPrices(value);
        assertEquals(expResult, result);                               
    }            
    public void testGetPricesValue4(){
        System.out.println("get{Prices(1$)");        
        String value = "16 соток Грушево 7400 евро, проект, титул, трасса. 53-05-24.";        
        ArrayList<String> expResult = new ArrayList<String>();        
        expResult.add(" 7400 евро");
        ArrayList<String> result = RegExp.getPrices(value);
        assertEquals(expResult, result);                               
    }            
}

