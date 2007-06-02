/*
 * MessageContactsPanel.java
 *
 * Created on 02 June 2007, 16:33
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/*
 * MessagesPanel.java
 *
 * Created on December 4, 2006, 8:28 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber.client;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import vgrabber.common.Contact;


public class MessageContactsPanel extends JPanel{
    private JSplitPane splitpane=null;
    private JPanel toppanel=null;
    private JPanel bottompanel=null;
    
    private JPanel messagespanel=null;
    private JTable messagestable=null;
    private javax.swing.JScrollPane messagestablescrollpane=null;
    
    private JSplitPane splitpanebottom=null;
    
    private JPanel contactspanel=null;
    private JTable contactstable=null;
    private javax.swing.JScrollPane contactstablescrollpane=null;
    
    private JPanel contactmessagespanel=null;
    private JTable contactmessagestable=null;    
    private javax.swing.JScrollPane contactmessagestablescrollpane=null;
    
    private ArrayList<vgrabber.common.Message> messages=new ArrayList<vgrabber.common.Message>();
    private ArrayList<vgrabber.common.Contact> contacts=null;
    private ArrayList<vgrabber.common.Message> contactmessages=null;
    private String title=null;

    public MessageContactsPanel() {        
        super(new GridLayout(1,0));                
        this.init();           
    }        
    public MessageContactsPanel(ArrayList<vgrabber.common.Message> messages) {        
        super(new GridLayout(1,0));        
        this.messages=messages;
        this.init();           
    }    

    public void init(){  
        //Messages Panel        
        messagespanel=new JPanel(new GridLayout(1,0));
        messagespanel.setBorder(BorderFactory.createTitledBorder("Anunturi:"));                
        messagestable=new JTable();                
        TableCellRenderer tr = new MyCellRender();
        messagestable.setDefaultRenderer(Object.class,tr);
        
        messagestablescrollpane=new JScrollPane(messagestable);                        
        messagestablescrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);                
        messagespanel.add(messagestablescrollpane);        
        messagestable.getSelectionModel().addListSelectionListener(new MessageSelectionListener(this));
        
        //Contacts Panel
        contactspanel=new JPanel(new GridLayout(1,0));
        contactspanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Contacte:"));        
        contactstable=new JTable();                                
        contactstable.getSelectionModel().addListSelectionListener(new ContactSelectionListener(this));
        
                                                        
        contactstablescrollpane=new JScrollPane(contactstable);                        
        contactstablescrollpane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);                
        contactspanel.add(contactstablescrollpane);
                        
        
        //Contact Messages Panel
        contactmessagespanel=new JPanel(new GridLayout(1,0));
        contactmessagespanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Anunturi per Contact:"));        
        //this.contactmessagestable=new JTable(new makler.client.MessagesTableModel(messages));                                
        contactmessagestable=new JTable();                                     
        
        contactmessagestablescrollpane=new JScrollPane(contactmessagestable);                        
        contactmessagestablescrollpane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);                
        contactmessagespanel.add(contactmessagestablescrollpane);
                               
        toppanel=new javax.swing.JPanel(new java.awt.BorderLayout());
        toppanel.add(messagespanel, BorderLayout.CENTER);
                
        splitpanebottom=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitpanebottom.setTopComponent(contactspanel);
        splitpanebottom.setBottomComponent(contactmessagespanel);
        splitpanebottom.setResizeWeight(0.2);
                
        splitpane=new javax.swing.JSplitPane(javax.swing.JSplitPane.VERTICAL_SPLIT);
        splitpane.setTopComponent(toppanel);
        splitpane.setBottomComponent(splitpanebottom);
        splitpane.setResizeWeight(0.5);
        
        fillMessagesTable();        
        
        add(splitpane);        
    }
    public void setMessages(ArrayList<vgrabber.common.Message> messages){
        this.messages=messages;
        fillMessagesTable();      
    }
    private void fillMessagesTable(){
        //this.messages=vgrabber.db.MessageManager.getMessagesByEditonAndCategory(((vgrabber.common.Edition)editioncombobox.getSelectedItem()),((vgrabber.common.Category)categorycombobox.getSelectedItem()));                                
        messagestable.setModel(new vgrabber.client.MessagesTableModel(messages));
        messagestable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);        
        messagestable.getColumnModel().getColumn(0).setMaxWidth(60);                       
        messagestable.getColumnModel().getColumn(1).setMaxWidth(60);          
        messagestable.getColumnModel().getColumn(2).setMaxWidth(60); 
        if (messages.size()>0){
            messagestable.getSelectionModel().setSelectionInterval(0,0);
        }
        
    }
    private void fillContactsTable(vgrabber.common.Message message){   
        this.contacts=message.getContacts();
        this.contactstable.setModel(new vgrabber.client.ContactsTableModel(this.contacts));
        this.contactstable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.contactstable.getColumnModel().getColumn(0).setMaxWidth(80);     
        this.contactstable.getColumnModel().getColumn(1).setMinWidth(100);    
        this.contactstable.getColumnModel().getColumn(1).setMaxWidth(140);    
        if (this.contacts.size()>0){
            this.contactstable.getSelectionModel().setSelectionInterval(0,0);
        }
    }    
    private void fillContactMessagesTable(vgrabber.common.Contact contact){            
         this.contactmessages=vgrabber.db.MessageManager.getMessagesByContact(contact);
         this.contactmessagestable.setModel(new vgrabber.client.ContactMessagesTabelModel(this.contactmessages));
         this.contactmessagestable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);        
         this.contactmessagestable.getColumnModel().getColumn(0).setMaxWidth(30);                       
         this.contactmessagestable.getColumnModel().getColumn(1).setMaxWidth(40);      
         this.contactmessagestable.getColumnModel().getColumn(2).setMaxWidth(40);      
         this.contactmessagestable.getColumnModel().getColumn(3).setMaxWidth(100);      
         if (this.contactmessages.size()>0){
            this.contactmessagestable.getSelectionModel().setSelectionInterval(0,0);
         }
    }     
    class MessageSelectionListener implements javax.swing.event.ListSelectionListener {
        private MessageContactsPanel msgp;
        public MessageSelectionListener(MessageContactsPanel msgp){
            this.msgp=msgp;
        }
        public void valueChanged(ListSelectionEvent e){
                    if (e.getValueIsAdjusting()) return;

                    ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                    if (!lsm.isSelectionEmpty()) {
                        int selectedRow = lsm.getMinSelectionIndex();                        
                        msgp.fillContactsTable(msgp.messages.get(selectedRow));                                                
                    }
        }
    }
    class ContactSelectionListener implements javax.swing.event.ListSelectionListener {
        private MessageContactsPanel msgp;
        public ContactSelectionListener(MessageContactsPanel msgp){
            this.msgp=msgp;
            
        }
        public void valueChanged(ListSelectionEvent e){
                    if (e.getValueIsAdjusting()) return;
                    ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                    if (!lsm.isSelectionEmpty()) {
                        int selectedRow = lsm.getMinSelectionIndex();                        
                        msgp.fillContactMessagesTable(msgp.contacts.get(selectedRow));                                                
                    }
        }
    }                     
}
