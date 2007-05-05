/*
 * FavoritesPanel.java
 *
 * Created on January 28, 2007, 5:22 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber.client;

import javax.swing.*;
/**
 *
 * @author VDoni
 */
public class FavoritesPanel extends JPanel{
    
    private javax.swing.JTable favoritetable=null;
    private javax.swing.JScrollPane scroolpane=null;
    private java.util.ArrayList<vgrabber.common.Message> favoritemessages=null;
    /** Creates a new instance of FavoritesPanel */
    public FavoritesPanel() {
        this.setLayout(new java.awt.BorderLayout());
        this.setBorder(javax.swing.BorderFactory.createTitledBorder("Favorites"));
        this.init();
    }
    
    public void init() {
        favoritemessages=vgrabber.db.MessageManager.GetFavoriteMessages();
        favoritetable=new javax.swing.JTable(new vgrabber.client.MessagesTableModel(favoritemessages));
        favoritetable.getColumnModel().getColumn(0).setMaxWidth(60);
        favoritetable.getColumnModel().getColumn(1).setMaxWidth(60);
        favoritetable.getColumnModel().getColumn(2).setMaxWidth(60);        
        favoritetable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        favoritetable.setColumnSelectionInterval(0,0);
        
        scroolpane=new javax.swing.JScrollPane(favoritetable);
        
        add(scroolpane, java.awt.BorderLayout.CENTER);
    }
}
