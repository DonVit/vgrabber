/*
 * NewMessagesPanel.java
 *
 * Created on December 21, 2006, 8:43 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber.client;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author vdoni
 */
public class NewMessagesPanel extends  javax.swing.JPanel {
    
    private javax.swing.JPanel filterpanel=null;  
    private javax.swing.JLabel editionlabel=null;
    private javax.swing.JComboBox editioncombobox=null;
    private javax.swing.JLabel categorylabel=null;
    private javax.swing.JComboBox categorycombobox=null;
    private javax.swing.JLabel regionlabel=null;
    private javax.swing.JComboBox regioncombobox=null;
    
    private javax.swing.JPanel messagespanel=null;
    private javax.swing.JTable messagestable=null;
    private javax.swing.JScrollPane messagestablescrollpane=null;

    private ArrayList<vgrabber.common.Message> messages=null;
    
    public NewMessagesPanel() {
        super(new java.awt.BorderLayout());        
        this.init();
    }
    public void init(){
        //Filter Panel        
        this.filterpanel=new JPanel(new java.awt.GridLayout(0,8));        
        this.filterpanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtru:"));        
        
        //Edition
        this.editionlabel=new JLabel("Editie: ");
        this.editionlabel.setHorizontalAlignment(javax.swing.JLabel.RIGHT);        
        this.filterpanel.add(this.editionlabel);        
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
         
        
        //Messages Table
        this.messagestable=new JTable();
        //Message Table ScrollPane
        this.messagestablescrollpane=new JScrollPane(this.messagestable);
        this.messagestablescrollpane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);                
        //Messages Panel            
        this.messagespanel=new JPanel(new GridLayout(1,0));
        this.messagespanel.setBorder(BorderFactory.createTitledBorder("Anunturi:"));                
        this.messagespanel.add(this.messagestablescrollpane);        
        this.fillMessagesTable();
        
        
        this.add(this.filterpanel, java.awt.BorderLayout.NORTH);
        this.add(this.messagespanel, java.awt.BorderLayout.CENTER);
    }
    private void fillMessagesTable(){
        this.messages=vgrabber.db.MessageManager.GetNewMessagesByEditon(((vgrabber.common.Edition)editioncombobox.getSelectedItem()),((vgrabber.common.Category)categorycombobox.getSelectedItem()));                                
        this.messagestable.setModel(new vgrabber.client.MessagesTableModel(messages));
        this.messagestable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);        
        this.messagestable.getColumnModel().getColumn(0).setMaxWidth(60);                       
        this.messagestable.getColumnModel().getColumn(1).setMaxWidth(60);          
        this.messagestable.getColumnModel().getColumn(2).setMaxWidth(60);                  
        this.messagestable.getSelectionModel().setSelectionInterval(0,0);
    }
    
class ComboBoxOnChange implements java.awt.event.ActionListener{
        private NewMessagesPanel msgp;
        public ComboBoxOnChange(NewMessagesPanel msgp){
            this.msgp=msgp;            
        }
        public void actionPerformed(ActionEvent e) {
            this.msgp.fillMessagesTable();
        }
    }                     
}
