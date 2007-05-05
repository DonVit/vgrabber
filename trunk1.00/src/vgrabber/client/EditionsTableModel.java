package vgrabber.client;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import vgrabber.common.Contact;
import vgrabber.common.Edition;

public class EditionsTableModel extends AbstractTableModel {
        private String[] columnNames = {"NR","ID","Date","Note"};
        private java.util.ArrayList<vgrabber.common.Edition> data;
        public EditionsTableModel(ArrayList<vgrabber.common.Edition> editions) {
            data = editions;            
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
            case 1:return ((Edition)data.get(row)).getId();                
            case 2:return ((Edition)data.get(row)).getDate();                
            case 3:return ((Edition)data.get(row)).getNote();                                                                              
            default:return ((Edition)data.get(row)).getId();
            }            
        }
        public void setValueAt(Object value, int row, int col){                        
            
            //switch (col){
            //case 3:((mgrabber.db.Edition)data.get(row)).id=(Integer)value;                
            //}      
            
        }
        
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        } 
                
        public Class getClassName(int col){
            return this.getValueAt(0,col).getClass();
            //if (col==7)
            //return Boolean.class;
            //else 
            //return getValueAt(0, col).getClass();
        }
        public boolean isCellEditable(int row, int col){
            return (col==3)?true:false;
        }
}