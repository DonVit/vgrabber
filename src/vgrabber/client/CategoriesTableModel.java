package vgrabber.client;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import vgrabber.common.Category;
import vgrabber.common.Contact;

public class CategoriesTableModel extends AbstractTableModel {
        private String[] columnNames = {"ID","ParentID","To Download","Denumire"};
        private java.util.ArrayList<vgrabber.common.Category> data;
        public CategoriesTableModel(ArrayList<vgrabber.common.Category> categories) {
            data = categories;            
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
            case 0:return ((Category)data.get(row)).getId();                
            case 1:return ((Category)data.get(row)).getParentId();                
            case 2:return ((Category)data.get(row)).getToUpdate();                
            case 3:return ((Category)data.get(row)).getName();                            
            default:return ((Category)data.get(row)).getId();
            }            
        }
        public void setValueAt(Object value, int row, int col){      
            
            switch (col){
            case 2:((vgrabber.common.Category)data.get(row)).setToUpdate((Boolean)value);                                    
            //case 3:((mgrabber.common.Category)data.get(row)).setName((String)value);                                                
            } 
            vgrabber.db.CategoryManager.UpdCategory(((vgrabber.common.Category)data.get(row)));
        }
        
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        } 
               
        //public Class getClassName(int col){
            //return this.getValueAt(0,col).getClass();
            //if (col==7)
            //return Boolean.class;
            //else 
            //return getValueAt(0, col).getClass();
        //}
        public boolean isCellEditable(int row, int col){
            return ((col==2)||(col==3))?true:false;            
        }
}