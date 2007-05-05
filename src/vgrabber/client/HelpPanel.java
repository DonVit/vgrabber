/*
 * HelpPanel.java
 *
 * Created on January 28, 2007, 10:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber.client;

import java.awt.Font;
import javax.swing.*;
import java.awt.Font.*;
/**
 *
 * @author VDoni
 */


public class HelpPanel extends JPanel{
    
    /** Creates a new instance of HelpPanel */    
    private javax.swing.JLabel helpbody=null;    
    public HelpPanel() {
        this.setLayout(new java.awt.BorderLayout());
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        this.init();
    }
    public void init() {
        helpbody=new javax.swing.JLabel();
        helpbody.setVerticalAlignment(javax.swing.JLabel.TOP);
 
        String html="<html>"+
        "  <body>"+
        "    <font size=+1>Constatari facute consultind Maklerul:</font>"+
        "    <ul>"+
        "    <li><font color=\"blue\">Mai mult de 60% din anunturile din Makler se repeta.</font>"+
        "    <li><font color=\"blue\">Multi din cei ce publica fac speculatii.</font>"+
        "    </ul>"+
        "    <font size=+1>Apare necesitatea de a vedea:</font>"+
        "    <ul>"+
        "    <li><font color=\"blue\">Doar anunturile noi aparute intr-o perioada anumita.</font>"+
        "    <li><font color=\"blue\">De vazut mai multa informatie despre cel ce ofera/cere.</font>"+
        "    </ul>    "+
        "    <font size=+1>Aceasta aplicatie ofera aceste doua necesitati.</font>    "+        
        "  </body>"+
        "</html>";
        
        helpbody.setText(html);
        helpbody.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        add(helpbody, java.awt.BorderLayout.CENTER);
    }
    
}
