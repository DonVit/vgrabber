/*
 * CategoryTest.java
 * JUnit based test
 *
 * Created on March 6, 2007, 8:36 PM
 */

package vgrabber.common;

import junit.framework.*;
import java.util.*;

/**
 *
 * @author vdoni
 */
public class CategoryTest extends TestCase {
    
    public CategoryTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(CategoryTest.class);
        
        return suite;
    }

    /**
     * Test of getId method, of class vgrabber.common.Category.
     */
    public void testGetId() {
        System.out.println("getId");
        
        Category instance = null;
        
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class vgrabber.common.Category.
     */
    public void testSetId() {
        System.out.println("setId");
        
        int id = 0;
        Category instance = null;
        
        instance.setId(id);
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getParentId method, of class vgrabber.common.Category.
     */
    public void testGetParentId() {
        System.out.println("getParentId");
        
        Category instance = null;
        
        int expResult = 0;
        int result = instance.getParentId();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setParentId method, of class vgrabber.common.Category.
     */
    public void testSetParentId() {
        System.out.println("setParentId");
        
        int parentid = 0;
        Category instance = null;
        
        instance.setParentId(parentid);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class vgrabber.common.Category.
     */
    public void testGetName() {
        System.out.println("getName");
        
        Category instance = null;
        
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class vgrabber.common.Category.
     */
    public void testSetName() {
        System.out.println("setName");
        
        String name = "";
        Category instance = null;
        
        instance.setName(name);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class vgrabber.common.Category.
     */
    public void testToString() {
        System.out.println("toString");
        
        Category instance = null;
        
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getToUpdate method, of class vgrabber.common.Category.
     */
    public void testGetToUpdate() {
        System.out.println("getToUpdate");
        
        Category instance = null;
        
        boolean expResult = true;
        boolean result = instance.getToUpdate();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setToUpdate method, of class vgrabber.common.Category.
     */
    public void testSetToUpdate() {
        System.out.println("setToUpdate");
        
        boolean toupdate = true;
        Category instance = null;
        
        instance.setToUpdate(toupdate);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of CompareTo method, of class vgrabber.common.Category.
     */
    public void testCompareTo() {
        System.out.println("CompareTo");
        
        Category category = null;
        Category instance = null;
        
        boolean expResult = true;
        boolean result = instance.CompareTo(category);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
