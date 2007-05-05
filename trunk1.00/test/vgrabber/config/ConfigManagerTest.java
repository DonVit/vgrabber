/*
 * ConfigManagerTest.java
 * JUnit based test
 *
 * Created on March 7, 2007, 10:20 AM
 */

package vgrabber.config;

import junit.framework.*;
import java.io.FileInputStream;

/**
 *
 * @author vdoni
 */
public class ConfigManagerTest extends TestCase {
    
    public ConfigManagerTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(ConfigManagerTest.class);
        
        return suite;
    }

    /**
     * Test of geConfig method, of class vgrabber.config.ConfigManager.
     */
    public void testGeConfig() {
        System.out.println("geConfig");
        
        vgrabber.common.Config expResult = null;
        vgrabber.common.Config result = ConfigManager.geConfig();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setConfig method, of class vgrabber.config.ConfigManager.
     */
    public void testSetConfig() {
        System.out.println("setConfig");
        
        vgrabber.common.Config cnf = null;
        
        boolean expResult = true;
        boolean result = ConfigManager.setConfig(cnf);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
