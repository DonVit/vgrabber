/*
 * CategoriesPanel.java
 *
 * Created on January 28, 2007, 1:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber.client;

import javax.swing.*;
import vgrabber.common.Category;
/**
 *
 * @author VDoni
 */
public class CategoriesPanel extends JPanel implements java.awt.event.ActionListener{
    
    /** Creates a new instance of CategoriesPanel */
    private javax.swing.JPanel centerpanel=null;
    private javax.swing.JScrollPane categoriesscroolpane=null;
    private javax.swing.JTable categoriestable=null;
    private java.util.ArrayList<vgrabber.common.Category> categories=null;
    private javax.swing.JButton getcategoriesbutton=null;
    private javax.swing.JPanel bottomsubpanel=null;
    
    public CategoriesPanel()  {
        super(new java.awt.BorderLayout());
        this.setBorder(javax.swing.BorderFactory.createTitledBorder("Categories to Download:"));
        this.init();        
    }
    
    public void init(){
        categories=vgrabber.db.CategoryManager.getCategories();
        categoriestable=new javax.swing.JTable(new vgrabber.client.CategoriesTableModel(categories));
        categoriestable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);        
        categoriestable.getColumnModel().getColumn(0).setMaxWidth(80);                       
        categoriestable.getColumnModel().getColumn(1).setMaxWidth(80);          
        categoriestable.getColumnModel().getColumn(2).setMaxWidth(90);                  
        categoriestable.getSelectionModel().setSelectionInterval(0,0);        
        categoriesscroolpane=new javax.swing.JScrollPane(categoriestable);
        
        getcategoriesbutton=new javax.swing.JButton("Get Categories");
        getcategoriesbutton.addActionListener(this);
        bottomsubpanel=new javax.swing.JPanel(new java.awt.BorderLayout());
        bottomsubpanel.add(getcategoriesbutton,java.awt.BorderLayout.EAST);
        //SpringLayout.Constraints savebuttoncons=centerPanelLayout.getConstraints(savebutton);
        //getcategoriesbutton.setX(Spring.sum(Spring.constant(-buttonwidth),passwordcons.getConstraint(SpringLayout.EAST)));
        //getcategoriesbutton.setY(Spring.sum(Spring.constant(sizebetweencomp*2),createdatabasecons.getConstraint(SpringLayout.SOUTH)));
        //getcategoriesbutton.setWidth(Spring.constant(buttonwidth));               
        
        
        
        //add(categoriesscroolpane,java.awt.BorderLayout.CENTER); 
        centerpanel=new javax.swing.JPanel(new java.awt.BorderLayout());
        centerpanel.add(categoriesscroolpane,java.awt.BorderLayout.CENTER);
        centerpanel.add(bottomsubpanel,java.awt.BorderLayout.SOUTH);
        add(centerpanel,java.awt.BorderLayout.CENTER); 
        
    }
    
    public void actionPerformed(java.awt.event.ActionEvent ae) {
        java.util.ArrayList<vgrabber.common.Category> cs=vgrabber.grabber.Grabber.GrabCategories();
        for (vgrabber.common.Category c:cs){
            vgrabber.db.CategoryManager.addCategory(c);
        } 
    }
    
}
