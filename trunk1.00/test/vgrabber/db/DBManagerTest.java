/*
 * DBManagerTest.java
 * JUnit based test
 *
 * Created on March 7, 2007, 10:20 AM
 */

package vgrabber.db;

import junit.framework.*;
import java.sql.PreparedStatement;
import java.io.File;
import java.io.FileInputStream;
import java.util.logging.LogRecord;

/**
 *
 * @author vdoni
 */
public class DBManagerTest extends TestCase {
    
    public DBManagerTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(DBManagerTest.class);
        
        return suite;
    }

    /**
     * Test of CreateDBObjects method, of class vgrabber.db.DBManager.
     */
    public void testCreateDBObjects() {
        System.out.println("CreateDBObjects");
        
        boolean expResult = true;
        boolean result = DBManager.CreateDBObjects();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
