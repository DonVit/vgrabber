package vgrabber.client;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import vgrabber.common.Contact;

public class ContactsTableModel extends AbstractTableModel {
        private String[] columnNames = {"ID","Name"};
        private java.util.ArrayList<vgrabber.common.Contact> data;
        public ContactsTableModel(ArrayList<vgrabber.common.Contact> contacts) {
            data = contacts;            
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
            case 0:return ((Contact)data.get(row)).getId();                
            case 1:return ((Contact)data.get(row)).getPhone();                
            default:return ((Contact)data.get(row)).getId();
            }            
        }
        public void setValueAt(Object value, int row, int col){                        
            /*
            switch (col){
            case 0:((numismat.db.User)data.get(row)).id=(Integer)value;                
            case 1:((numismat.db.User)data.get(row)).name=(String)value;
            case 2:((numismat.db.User)data.get(row)).family=(String)value;                                    
            case 7:((numismat.db.User)data.get(row)).invalid=(Boolean)value;                                                  
            }      
            try{
            numismat.db.UserManager.UpdUser(((numismat.db.User)data.get(row)));
            } catch (java.sql.SQLException ex) {
                
            }
             */
        }
        /*
        public Class getColumnClass(int c) {
            return (c==1)?String.class:getValueAt(0, c).getClass();
        } 
         */       
        public Class getClassName(int col){
            //return this.getValueAt(0,col).getClass();
            //if (col==7)
            return Boolean.class;
            //else 
            //return getValueAt(0, col).getClass();
        }
        public boolean isCellEditable(int row, int col){
            return (col==7)?true:false;
        }
}