/*
 * ConfigTest.java
 * JUnit based test
 *
 * Created on March 6, 2007, 8:46 PM
 */

package vgrabber.common;

import junit.framework.*;

/**
 *
 * @author vdoni
 */
public class ConfigTest extends TestCase {
    
    public ConfigTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(ConfigTest.class);
        
        return suite;
    }

    /**
     * Test of setServerType method, of class vgrabber.common.Config.
     */
    public void testSetServerType() {
        System.out.println("setServerType");
        
        String servertype = "";
        Config instance = new Config();
        
        instance.setServerType(servertype);
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getServerType method, of class vgrabber.common.Config.
     */
    public void testGetServerType() {
        System.out.println("getServerType");
        
        Config instance = new Config();
        
        String expResult = "";
        String result = instance.getServerType();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setServerName method, of class vgrabber.common.Config.
     */
    public void testSetServerName() {
        System.out.println("setServerName");
        
        String servername = "";
        Config instance = new Config();
        
        instance.setServerName(servername);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getServerName method, of class vgrabber.common.Config.
     */
    public void testGetServerName() {
        System.out.println("getServerName");
        
        Config instance = new Config();
        
        String expResult = "";
        String result = instance.getServerName();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDataBase method, of class vgrabber.common.Config.
     */
    public void testSetDataBase() {
        System.out.println("setDataBase");
        
        String database = "";
        Config instance = new Config();
        
        instance.setDataBase(database);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDatabase method, of class vgrabber.common.Config.
     */
    public void testGetDatabase() {
        System.out.println("getDatabase");
        
        Config instance = new Config();
        
        String expResult = "";
        String result = instance.getDatabase();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUserName method, of class vgrabber.common.Config.
     */
    public void testSetUserName() {
        System.out.println("setUserName");
        
        String username = "";
        Config instance = new Config();
        
        instance.setUserName(username);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserName method, of class vgrabber.common.Config.
     */
    public void testGetUserName() {
        System.out.println("getUserName");
        
        Config instance = new Config();
        
        String expResult = "";
        String result = instance.getUserName();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPassword method, of class vgrabber.common.Config.
     */
    public void testSetPassword() {
        System.out.println("setPassword");
        
        String password = "";
        Config instance = new Config();
        
        instance.setPassword(password);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class vgrabber.common.Config.
     */
    public void testGetPassword() {
        System.out.println("getPassword");
        
        Config instance = new Config();
        
        String expResult = "";
        String result = instance.getPassword();
        //assertEquals(expResult, result);
        assertEquals(2, 3);
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
