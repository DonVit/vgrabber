/*
 * SearchPanel.java
 *
 * Created on February 27, 2007, 6:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber.client;
import javax.swing.*;

/**
 *
 * @author vdoni
 */
public class SearchPanel extends JPanel implements java.awt.event.ActionListener{
    
    private JPanel toppanel;
    
    private JLabel searchlabel;
    //private JTextField searchcriteria;
    private JComboBox searchcriteria;
    private JButton searchbutton;
    
    private JPanel centerpanel;    
    private JTable resulttable;
    private JScrollPane resultscrollpane;
    
    public SearchPanel() {
        init();
    }
    private void init(){
        this.setLayout(new java.awt.BorderLayout());
        toppanel=new JPanel(new java.awt.BorderLayout());
        toppanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Search"));
        
        searchlabel=new JLabel("Enter search criteria:");
        toppanel.add(searchlabel,java.awt.BorderLayout.WEST);
        
        //searchcriteria=new JTextField();        
        //toppanel.add(searchcriteria,java.awt.BorderLayout.CENTER);
        
        searchcriteria=new JComboBox();
        searchcriteria.setEditable(true);
        toppanel.add(searchcriteria,java.awt.BorderLayout.CENTER);
        
        searchbutton=new JButton("Search");
        searchbutton.addActionListener(this);
        toppanel.add(searchbutton,java.awt.BorderLayout.EAST);
        
        this.add(toppanel,java.awt.BorderLayout.NORTH);
        
        centerpanel=new JPanel(new java.awt.BorderLayout());
        centerpanel.setBorder(BorderFactory.createTitledBorder("Search Results:"));
        resulttable=new JTable();
        resultscrollpane=new JScrollPane(resulttable);
        centerpanel.add(resultscrollpane,java.awt.BorderLayout.CENTER);
        this.add(centerpanel,java.awt.BorderLayout.CENTER);
    }
    
    public void actionPerformed(java.awt.event.ActionEvent e){                 
        searchcriteria.addItem(searchcriteria.getSelectedItem());
        resulttable.setModel(new ContactMessagesTabelModel(vgrabber.db.MessageManager.searchMessages(searchcriteria.getSelectedItem().toString())));       
        resulttable.getColumnModel().getColumn(0).setPreferredWidth(30);                       
        resulttable.getColumnModel().getColumn(0).setMaxWidth(60);      
        resulttable.getColumnModel().getColumn(1).setPreferredWidth(40);      
        resulttable.getColumnModel().getColumn(1).setMaxWidth(60);      
        resulttable.getColumnModel().getColumn(2).setPreferredWidth(40);      
        resulttable.getColumnModel().getColumn(2).setMaxWidth(60); 
        resulttable.getColumnModel().getColumn(3).setPreferredWidth(100);      
        resulttable.getColumnModel().getColumn(3).setMaxWidth(200);      
    }
}
