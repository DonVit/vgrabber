/*
 * HelpPanel.java
 *
 * Created on January 28, 2007, 10:57 PM
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


public class HelpPanel extends JPanel{
    
    /** Creates a new instance of HelpPanel */
    private javax.swing.JLabel help=null;    
    public HelpPanel() {
        this.setBorder(javax.swing.BorderFactory.createTitledBorder("About application:"));
        this.init();
    }
    public void init() {
        help=new javax.swing.JLabel();
        help.setText("When you have time add please some help here.");        
        add(help);
    }
    
}
