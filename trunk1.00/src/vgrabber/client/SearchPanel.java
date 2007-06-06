/*
 * SearchPanel.java
 *
 * Created on February 27, 2007, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber.client;

import java.awt.*;
import javax.swing.*;
import vgrabber.common.*;


/**
 *
 * @author vdoni
 */
public class SearchPanel extends JPanel implements java.awt.event.ActionListener{
    
    private final int labelwidth=150;
    private final int textfieldwidth=160;
    private final int buttonwidth=75;    
    private final int combowidth=100;
    private final int sizebetweencomp=10;
    
    private JPanel toppanel;
    
    private JPanel topsubpanel;
    private JLabel searchlabel;
    //private JTextField searchcriteria;
    private JComboBox searchcriteria;
    private JLabel startlabel;
    private JComboBox startedition;
    private JLabel endlabel;
    private JComboBox endedition;
    private JButton searchbutton;
    
    private JPanel centerpanel;    
    private JTable resulttable;
    private JScrollPane resultscrollpane;
    private MessageContactsPanel mcp;
    
    public SearchPanel() {
        init();
    }
    private void init(){
        this.setLayout(new BorderLayout());
        SpringLayout springlayout=new SpringLayout();
        toppanel=new JPanel();
        toppanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Search"));
                
        searchlabel=new JLabel("Search criteria:");
        toppanel.add(searchlabel);        
        
        searchcriteria=new JComboBox();
        searchcriteria.setEditable(true);        
        searchcriteria.setPrototypeDisplayValue("WWWWWWWWWWWWWWWWWWWWWWWWWWWWW");                
        toppanel.add(searchcriteria);        
        
        
        startlabel=new JLabel("From:");
        toppanel.add(startlabel);
        startedition=new JComboBox();
        for (vgrabber.common.Edition ed:vgrabber.db.EditionManager.GetAllEditions()){
            startedition.addItem(ed);
        }      
        toppanel.add(startedition);
        

        endlabel=new JLabel("To:");
        toppanel.add(endlabel);
        endedition=new JComboBox();
        for (vgrabber.common.Edition ed:vgrabber.db.EditionManager.GetAllEditions()){
            endedition.addItem(ed);
        }                 
        toppanel.add(endedition);
        
        searchbutton=new JButton("Search");
        searchbutton.addActionListener(this);
        //toppanel.add(searchbutton);
        toppanel.add(searchbutton);
        
        this.add(toppanel,java.awt.BorderLayout.NORTH);
        
        centerpanel=new JPanel(new java.awt.BorderLayout());
        centerpanel.setBorder(BorderFactory.createTitledBorder("Search Results:"));
        //resulttable=new JTable();
        //resultscrollpane=new JScrollPane(resulttable);
        //centerpanel.add(resultscrollpane,java.awt.BorderLayout.CENTER);
        mcp=new MessageContactsPanel();
        centerpanel.add(mcp);
        this.add(centerpanel,java.awt.BorderLayout.CENTER);
    }
    
    public void actionPerformed(java.awt.event.ActionEvent e){                         
        String searchwords=searchcriteria.getSelectedItem().toString();
        Edition startediton=(Edition)this.startedition.getSelectedItem();
        Edition endediton=(Edition)this.endedition.getSelectedItem();
        mcp.setMessages(vgrabber.db.MessageManager.searchMessages(searchwords,startediton,endediton));
    }
}
