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
public class CategoriesPanel extends JPanel{
    
    /** Creates a new instance of CategoriesPanel */
    private javax.swing.JScrollPane categoriesscroolpane=null;
    private javax.swing.JTable categoriestable=null;
    private java.util.ArrayList<vgrabber.common.Category> categories=null;
    
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
        add(categoriesscroolpane,java.awt.BorderLayout.CENTER);                        
    }
    
}
