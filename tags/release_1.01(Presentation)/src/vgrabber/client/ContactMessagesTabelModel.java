/*
 * ContactMessagesTabelModel.java
 *
 * Created on December 21, 2006, 10:52 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber.client;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author vdoni
 */
public class ContactMessagesTabelModel extends AbstractTableModel {
    private String[] columnNames = {"NR","ID","Editie","Categorie","Anunt"};
    private ArrayList<vgrabber.common.Message> messages;
    public ContactMessagesTabelModel(ArrayList<vgrabber.common.Message> messages) {
        this.messages=messages;
    }
    public int getRowCount(){
        return this.messages.size();
    }
    public int getColumnCount(){
        return this.columnNames.length;
    }    
    public Object getValueAt(int row, int col){        
            switch (col){
            case 0:return row+1;                
            case 1:return messages.get(row).getId();                
            case 2:return messages.get(row).getEdition_id();                
            case 3:return vgrabber.db.CategoryManager.getCategory(messages.get(row).getCategory_id()).getName();                            
            case 4:return messages.get(row).getAnunt();                            
            default:return messages.get(row).getId();
            }       
    }
    public String getColumnName(int col) {
        return columnNames[col];
    }    
    
}
