/*
 * FavoritesPanel.java
 *
 * Created on January 28, 2007, 5:22 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber.client;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author VDoni
 */
public class FavoritesPanel extends JPanel{
    
    private javax.swing.JSplitPane mainsplitpane;
    
    private javax.swing.JPanel toppanel;
    private javax.swing.JTable favoritetable;
    private javax.swing.JScrollPane scroolpane;    
    private java.util.ArrayList<vgrabber.common.Message> favoritemessages;
        
    private javax.swing.JPanel bottompanel;
    private javax.swing.JSplitPane bottonsplitpane;
    private javax.swing.JTable contactstable;
    private javax.swing.JScrollPane contactsscroolpane;    
    private java.util.ArrayList<vgrabber.common.Contact> contacts;
    
    private javax.swing.JTable contactmessagestable;
    private javax.swing.JScrollPane contactmessagesscroolpane;    
    private java.util.ArrayList<vgrabber.common.Message> contactmessages;
    
    
    /** Creates a new instance of FavoritesPanel */
    public FavoritesPanel() {
        this.setLayout(new java.awt.BorderLayout());
        this.setBorder(javax.swing.BorderFactory.createTitledBorder("Favorites"));
        this.init();
    }
    
    public void init() {
        //top panel
        favoritemessages=vgrabber.db.MessageManager.getFavoriteMessages();
        favoritetable=new javax.swing.JTable(new vgrabber.client.MessagesTableModel(favoritemessages));
        favoritetable.getColumnModel().getColumn(0).setMaxWidth(60);
        favoritetable.getColumnModel().getColumn(1).setMaxWidth(60);
        favoritetable.getColumnModel().getColumn(2).setMaxWidth(60);        
        favoritetable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        favoritetable.setColumnSelectionInterval(0,0);
        favoritetable.getSelectionModel().addListSelectionListener(new MessageSelectionListener());
        
        scroolpane=new javax.swing.JScrollPane(favoritetable);
        
        toppanel=new JPanel(new BorderLayout());
        toppanel.add(scroolpane, java.awt.BorderLayout.CENTER);
                               
        //bottom panel
        contactstable=new JTable();
        contactstable.getSelectionModel().addListSelectionListener(new ContactSelectionListener());
        contactsscroolpane=new JScrollPane(contactstable);        
        

        contactmessagestable=new JTable();
        contactmessagesscroolpane=new JScrollPane(contactmessagestable);
        
        bottonsplitpane=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        bottonsplitpane.setTopComponent(contactsscroolpane);
        bottonsplitpane.setBottomComponent(contactmessagesscroolpane);
        bottonsplitpane.setResizeWeight(0.15);
        
        bottompanel=new JPanel(new BorderLayout());
        bottompanel.add(bottonsplitpane,BorderLayout.CENTER);
        
        mainsplitpane=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        mainsplitpane.setTopComponent(toppanel);
        mainsplitpane.setBottomComponent(bottompanel);
        mainsplitpane.setResizeWeight(0.3);
        add(mainsplitpane);
    }
    private void fillContactsTable(vgrabber.common.Message message){   
        contacts=message.getContacts();
        contactstable.setModel(new vgrabber.client.ContactsTableModel(contacts));
        contactstable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        contactstable.getColumnModel().getColumn(0).setMaxWidth(80);     
        contactstable.getColumnModel().getColumn(1).setMinWidth(100);    
        contactstable.getColumnModel().getColumn(1).setMaxWidth(140);    
        if (contacts.size()>0){
            contactstable.getSelectionModel().setSelectionInterval(0,0);
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
        
        public MessageSelectionListener(){        
        }
        public void valueChanged(ListSelectionEvent e){
                    if (e.getValueIsAdjusting()) return;

                    ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                    if (!lsm.isSelectionEmpty()) {
                        int selectedRow = lsm.getMinSelectionIndex();                                                
                        fillContactsTable(favoritemessages.get(selectedRow));                                                
                    }
        }
    }
    class ContactSelectionListener implements javax.swing.event.ListSelectionListener {        
        public ContactSelectionListener(){                        
        }
        public void valueChanged(ListSelectionEvent e){
                    if (e.getValueIsAdjusting()) return;
                    ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                    if (!lsm.isSelectionEmpty()) {
                        int selectedRow = lsm.getMinSelectionIndex();                        
                        fillContactMessagesTable(contacts.get(selectedRow));                                                
                    }
        }
    }        
}
