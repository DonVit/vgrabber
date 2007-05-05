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
import vgrabber.common.Contact;


public class MessagesPanel extends javax.swing.JPanel{
    private JSplitPane splitpane=null;
    private JPanel toppanel=null;
    private JPanel bottompanel=null;
    
    private JPanel filterpanel=null;  
    private javax.swing.JLabel editionlabel=null;
    private javax.swing.JComboBox editioncombobox=null;
    private javax.swing.JLabel categorylabel=null;
    private javax.swing.JComboBox categorycombobox=null;
    private javax.swing.JLabel regionlabel=null;
    private javax.swing.JComboBox regioncombobox=null;
    
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
    
    private ArrayList<vgrabber.common.Message> messages=null;
    private ArrayList<vgrabber.common.Contact> contacts=null;
    private ArrayList<vgrabber.common.Message> contactmessages=null;
    private String title=null;

    public MessagesPanel() {
        super(new GridLayout(1,0));        
        this.init();           
    }    

    public void init(){  
        //Filter Panel        
        filterpanel=new JPanel(new GridLayout(0,8));                                
        filterpanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtru:"));          
        
        //Edition
        editionlabel=new JLabel("Editie: ");
        editionlabel.setHorizontalAlignment(javax.swing.JLabel.RIGHT);        
        filterpanel.add(this.editionlabel);        
        
        this.editioncombobox=new JComboBox();        
        for (vgrabber.common.Edition ed:vgrabber.db.EditionManager.GetAllEditions()){
            this.editioncombobox.addItem(ed);
        }        
        this.editioncombobox.addActionListener(new ComboBoxOnChange(this));                
        this.filterpanel.add(this.editioncombobox);
       
        //Category
        this.categorylabel=new JLabel("Categorie: ");
        this.categorylabel.setHorizontalAlignment(javax.swing.JLabel.RIGHT);        
        this.filterpanel.add(this.categorylabel);
        this.categorycombobox=new JComboBox();
        for (vgrabber.common.Category category:vgrabber.db.CategoryManager.getCategoriesToDownload()){
            this.categorycombobox.addItem(category);
        }        
        this.categorycombobox.addActionListener(new ComboBoxOnChange(this));  
        this.filterpanel.add(this.categorycombobox);
        
        //Region
        
        this.regionlabel=new JLabel("Regiune: ");
        this.regionlabel.setHorizontalAlignment(javax.swing.JLabel.RIGHT);        
        this.regionlabel.setEnabled(false);
        this.filterpanel.add(this.regionlabel);
        this.regioncombobox=new JComboBox();
        for (vgrabber.common.Region region:vgrabber.db.RegionManager.getRegions()){
            this.regioncombobox.addItem(region);
        }        
        this.regioncombobox.setEnabled(false);
        this.filterpanel.add(this.regioncombobox);
                                
        //Messages Panel        
        this.messagespanel=new JPanel(new GridLayout(1,0));
        this.messagespanel.setBorder(BorderFactory.createTitledBorder("Anunturi:"));                
        this.messagestable=new JTable();                
        javax.swing.table.TableCellRenderer tr = new MyCellRender();
        this.messagestable.setDefaultRenderer(Object.class,tr);
        
        this.messagestablescrollpane=new JScrollPane(this.messagestable);                        
        this.messagestablescrollpane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);                
        this.messagespanel.add(this.messagestablescrollpane);
        
        this.messagestable.getSelectionModel().addListSelectionListener(new MessageSelectionListener(this));
        
        //Contacts Panel
        this.contactspanel=new JPanel(new GridLayout(1,0));
        this.contactspanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Contacte:"));        
        this.contactstable=new JTable();                                
        this.contactstable.getSelectionModel().addListSelectionListener(new ContactSelectionListener(this));
        
                                                        
        this.contactstablescrollpane=new JScrollPane(this.contactstable);                        
        this.contactstablescrollpane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);                
        this.contactspanel.add(this.contactstablescrollpane);
                        
        
        //Contact Messages Panel
        this.contactmessagespanel=new JPanel(new GridLayout(1,0));
        this.contactmessagespanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Anunturi per Contact:"));        
        //this.contactmessagestable=new JTable(new makler.client.MessagesTableModel(messages));                                
        this.contactmessagestable=new JTable();                                     
        
        this.contactmessagestablescrollpane=new JScrollPane(this.contactmessagestable);                        
        this.contactmessagestablescrollpane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);                
        this.contactmessagespanel.add(this.contactmessagestablescrollpane);
                               
        this.toppanel=new javax.swing.JPanel(new java.awt.BorderLayout());
        this.toppanel.add(filterpanel, BorderLayout.NORTH);
        this.toppanel.add(this.messagespanel, BorderLayout.CENTER);
                
        this.splitpanebottom=new javax.swing.JSplitPane(javax.swing.JSplitPane.VERTICAL_SPLIT);
        this.splitpanebottom.setTopComponent(this.contactspanel);
        this.splitpanebottom.setBottomComponent(this.contactmessagespanel);
        this.splitpanebottom.setResizeWeight(0.2);
                
        this.splitpane=new javax.swing.JSplitPane(javax.swing.JSplitPane.VERTICAL_SPLIT);
        this.splitpane.setTopComponent(this.toppanel);
        this.splitpane.setBottomComponent(this.splitpanebottom);
        this.splitpane.setResizeWeight(0.5);
        
        this.fillMessagesTable();        
        
        this.add(this.splitpane);        
    }

    private void fillMessagesTable(){
        this.messages=vgrabber.db.MessageManager.getMessagesByEditonAndCategory(((vgrabber.common.Edition)editioncombobox.getSelectedItem()),((vgrabber.common.Category)categorycombobox.getSelectedItem()));                                
        this.messagestable.setModel(new vgrabber.client.MessagesTableModel(messages));
        this.messagestable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);        
        this.messagestable.getColumnModel().getColumn(0).setMaxWidth(60);                       
        this.messagestable.getColumnModel().getColumn(1).setMaxWidth(60);          
        this.messagestable.getColumnModel().getColumn(2).setMaxWidth(60); 
        if (this.messages.size()>0){
            this.messagestable.getSelectionModel().setSelectionInterval(0,0);
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
        private MessagesPanel msgp;
        public MessageSelectionListener(MessagesPanel msgp){
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
        private MessagesPanel msgp;
        public ContactSelectionListener(MessagesPanel msgp){
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
    class ComboBoxOnChange implements java.awt.event.ActionListener{
        private MessagesPanel msgp;
        public ComboBoxOnChange(MessagesPanel msgp){
            this.msgp=msgp;            
        }
        public void actionPerformed(ActionEvent e) {
            this.msgp.fillMessagesTable();
        }
    }                 
}
