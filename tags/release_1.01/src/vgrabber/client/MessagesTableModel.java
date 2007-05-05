package vgrabber.client;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class MessagesTableModel extends AbstractTableModel {
        private String[] columnNames = {"NR","ID","Favorite","Name"};
        private java.util.ArrayList<vgrabber.common.Message> data;
        public MessagesTableModel(ArrayList<vgrabber.common.Message> messages) {
            data = messages;            
        }
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.size();
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            switch (col){
            case 0:return row+1;                
            case 1:return data.get(row).getId();                
            case 2:return data.get(row).getInterested();                            
            case 3:return data.get(row).getAnunt();                
            default:return data.get(row).getId();
            }            
        }
        public void setValueAt(Object value, int row, int col){                                    
            switch (col){                        
            case 2:((vgrabber.common.Message)data.get(row)).setInterested((Boolean)value);                                    
            }      
            vgrabber.db.MessageManager.UpdMessage(((vgrabber.common.Message)data.get(row)));
        }
        
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        } 
        /*        
        public Class getClassName(int col){
            //return this.getValueAt(0,col).getClass();
            //if (col==7)
            return Boolean.class;
            //else 
            //return getValueAt(0, col).getClass();
        }
        */
        public boolean isCellEditable(int row, int col){
            return (col==2)?true:false;
        }
}