/*
 * MyCellRender.java
 *
 * Created on 19 Март 2007 г., 18:49
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber.client;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author vdoni
 */
public class MyCellRender implements javax.swing.table.TableCellRenderer{
    
    public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();
    
    public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column) {
        Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(table, value,isSelected, hasFocus, row, column);
        Color foreground, background;
        foreground = Color.BLUE;
        /*
        if (((vgrabber.common.Message)value).getInterested()){
            background = Color.GREEN;
            renderer.setBackground(background);
        }
        /*
        if (isSelected) {
            foreground = Color.YELLOW;
            background = Color.GREEN;
        } else {
         
            if (row % 2 == 0) {
                foreground = Color.BLUE;
                background = Color.WHITE;
            } else {
                foreground = Color.WHITE;
                background = Color.BLUE;
            }
        }
         */
        renderer.setForeground(foreground);
        
        return renderer;
    }
    public MyCellRender() { 
    }
    
}
