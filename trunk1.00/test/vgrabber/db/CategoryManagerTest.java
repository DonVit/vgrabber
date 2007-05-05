/*
 * CategoryManagerTest.java
 * JUnit based test
 *
 * Created on March 7, 2007, 9:41 AM
 */

package vgrabber.db;

import junit.framework.*;
import java.util.*;
import java.sql.*;
import vgrabber.common.*;

/**
 *
 * @author vdoni
 */
public class CategoryManagerTest extends TestCase {
    
    public CategoryManagerTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(CategoryManagerTest.class);
        
        return suite;
    }

    /**
     * Test of getCategoriesToDownload method, of class vgrabber.db.CategoryManager.
     */
    public void testGetCategoriesToDownload() {
        System.out.println("getCategoriesToDownload");
        
        ArrayList<Category> expResult = null;
        ArrayList<Category> result = CategoryManager.getCategoriesToDownload();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getCategories method, of class vgrabber.db.CategoryManager.
     */
    public void testGetCategories() {
        System.out.println("getCategories");
        
        ArrayList<Category> expResult = null;
        ArrayList<Category> result = CategoryManager.getCategories();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of exists method, of class vgrabber.db.CategoryManager.
     */
    public void testExists() {
        System.out.println("exists");
        
        Category category = null;
        
        boolean expResult = true;
        boolean result = CategoryManager.exists(category);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getCategory method, of class vgrabber.db.CategoryManager.
     */
    public void testGetCategory() {
        System.out.println("getCategory");
        
        int category_id = 0;
        
        Category expResult = null;
        Category result = CategoryManager.getCategory(category_id);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of addCategory method, of class vgrabber.db.CategoryManager.
     */
    public void testAddCategory() {
        System.out.println("addCategory");
        
        Category category = null;
        
        boolean expResult = true;
        boolean result = CategoryManager.addCategory(category);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of updCategory method, of class vgrabber.db.CategoryManager.
     */
    public void testUpdCategory() {
        System.out.println("updCategory");
        
        Category category = null;
        
        boolean expResult = true;
        boolean result = CategoryManager.updCategory(category);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
