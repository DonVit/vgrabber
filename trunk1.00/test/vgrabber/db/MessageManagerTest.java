/*
 * MessageManagerTest.java
 * JUnit based test
 *
 * Created on March 13, 2007, 11:19 AM
 */

package vgrabber.db;

import junit.framework.*;
import java.util.*;
import java.sql.*;
import vgrabber.common.Contact;
import vgrabber.common.Category;
import vgrabber.common.Edition;
import vgrabber.common.Message;

/**
 *
 * @author vdoni
 */
public class MessageManagerTest extends TestCase {
    
    public MessageManagerTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(MessageManagerTest.class);
        
        return suite;
    }

    /**
     * Test of getMessage method, of class vgrabber.db.MessageManager.
     */
    public void testGetMessage() {
        System.out.println("getMessage");
        
        int id = 0;
        
        ArrayList<Message> expResult = null;
        ArrayList<Message> result = MessageManager.getMessage(id);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessagesByContact method, of class vgrabber.db.MessageManager.
     */
    public void testGetMessagesByContact() {
        System.out.println("getMessagesByContact");
        
        Contact contact = null;
        
        ArrayList<Message> expResult = null;
        ArrayList<Message> result = MessageManager.getMessagesByContact(contact);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessagesByEditon method, of class vgrabber.db.MessageManager.
     */
    public void testGetMessagesByEditon() {
        System.out.println("getMessagesByEditon");
        
        Edition edition = null;
        
        ArrayList<Message> expResult = null;
        ArrayList<Message> result = MessageManager.getMessagesByEditon(edition);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessagesByEditonAndCategory method, of class vgrabber.db.MessageManager.
     */
    public void testGetMessagesByEditonAndCategory() {
        System.out.println("getMessagesByEditonAndCategory");
        
        Edition edition = null;
        Category category = null;
        
        ArrayList<Message> expResult = null;
        ArrayList<Message> result = MessageManager.getMessagesByEditonAndCategory(edition, category);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchMessages method, of class vgrabber.db.MessageManager.
     */
    public void testSearchMessages() {
        System.out.println("searchMessages");
        
        Message msg =new Message("12-34-56",EditionManager.GetAllEditions().get(0).getId(),CategoryManager.getCategories().get(0).getId());
        Message addedmsg = MessageManager.addMessage(msg);

        
        String criteria = "12-34-56";
        
        ArrayList<Message> expResult = null;
        ArrayList<Message> result = MessageManager.searchMessages(criteria);
        assertEquals(addedmsg.getAnunt(), result.get(0).getAnunt());
        
        MessageManager.delMessage(addedmsg);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getNewMessagesByEditon method, of class vgrabber.db.MessageManager.
     */
    public void testGetNewMessagesByEditon() {
        System.out.println("getNewMessagesByEditon");
        
        Edition edition = null;
        Category category = null;
        
        ArrayList<Message> expResult = null;
        ArrayList<Message> result = MessageManager.getNewMessagesByEditon(edition, category);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFavoriteMessages method, of class vgrabber.db.MessageManager.
     */
    public void testGetFavoriteMessages() {
        System.out.println("getFavoriteMessages");
        
        ArrayList<Message> expResult = null;
        ArrayList<Message> result = MessageManager.getFavoriteMessages();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMessage method, of class vgrabber.db.MessageManager.
     */
    public void testAddMessage() {
        System.out.println("addMessage");
        
        Message message = null;
        
        Message expResult = null;
        Message result = MessageManager.addMessage(message);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updMessage method, of class vgrabber.db.MessageManager.
     */
    public void testUpdMessage() {
        System.out.println("updMessage");
        
        Message message = null;
        
        boolean expResult = true;
        boolean result = MessageManager.updMessage(message);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delMessage method, of class vgrabber.db.MessageManager.
     */
    public void testDelMessage() {
        System.out.println("delMessage");
        
        Message message = null;
        
        boolean expResult = true;
        boolean result = MessageManager.delMessage(message);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
