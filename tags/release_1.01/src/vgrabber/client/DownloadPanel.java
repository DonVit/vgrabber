/*
 * DownloadPanel.java
 *
 * Created on January 28, 2007, 1:29 PM
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
public class DownloadPanel extends JPanel{
    
    /** Creates a new instance of DownloadPanel */
    private javax.swing.JPanel toppanel=null;
    
    private javax.swing.JLabel editionlabel=null;
    private javax.swing.JTextField edition=null;

    private javax.swing.JLabel datelabel=null;
    private javax.swing.JTextField date=null;

    private javax.swing.JLabel notelabel=null;
    private javax.swing.JTextArea note=null;
    
    private javax.swing.JButton download=null;    
    
    
    private javax.swing.JPanel centerpanel=null;
    
    private javax.swing.JTable edtionstable=null;
    private javax.swing.JScrollPane scrollpane=null;
    
    private java.util.ArrayList<vgrabber.common.Edition> editions=null;
    private java.util.ArrayList<vgrabber.common.Edition> editionstodownload=null;
    
    public DownloadPanel() {
        this.setLayout(new java.awt.BorderLayout());
        this.init();        
    }
    
    public void init() {
        editionstodownload=new java.util.ArrayList<vgrabber.common.Edition>();
        toppanel=new javax.swing.JPanel();
        toppanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Editions to download:"));
        editions=vgrabber.grabber.Grabber.GrabEditions();
        edtionstable=new javax.swing.JTable(new vgrabber.client.EditionsTableModel(editions));
        edtionstable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);        
        edtionstable.setColumnSelectionInterval(0,0);
        edtionstable.getSelectionModel().addListSelectionListener(new EditionSelectionListener(this));
        edtionstable.getColumnModel().getColumn(0).setMaxWidth(60);
        edtionstable.getColumnModel().getColumn(1).setMaxWidth(100);        
        edtionstable.getColumnModel().getColumn(3).setMaxWidth(70);        
        scrollpane=new javax.swing.JScrollPane(edtionstable);
        toppanel.add(scrollpane);
        
        download=new javax.swing.JButton("Download");
        download.addActionListener(new DownloadActionListener(this));
        toppanel.add(download);
        
        
        centerpanel=new javax.swing.JPanel();
        centerpanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Downloaded editions:"));
        
        add(toppanel, java.awt.BorderLayout.CENTER);
        //add(centerpanel, java.awt.BorderLayout.CENTER);
    }

    class EditionSelectionListener implements javax.swing.event.ListSelectionListener {
        private vgrabber.client.DownloadPanel dep;
        public EditionSelectionListener(vgrabber.client.DownloadPanel dep){
            this.dep=dep;
        }
        public void valueChanged(javax.swing.event.ListSelectionEvent e){
                    if (e.getValueIsAdjusting()) return;

                    ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                    if (!lsm.isSelectionEmpty()) {                 
                        int selectedRow = lsm.getMinSelectionIndex();                        
                        //msgp.fillContactsTable(msgp.messages.get(selectedRow));                        
                        //msgp.contactstable.getSelectionModel().setSelectionInterval(0,0);
                        int value=dep.editions.get(selectedRow).getId();
                        editionstodownload.add(dep.editions.get(selectedRow));
                        System.out.println("Row " + selectedRow+ " is now selected. Edition ID is "+value);            
                    }
        }
    }
    
    class DownloadActionListener implements java.awt.event.ActionListener {
        private vgrabber.client.DownloadPanel dep;
        public DownloadActionListener(vgrabber.client.DownloadPanel dep){
            this.dep=dep;
        }
        public void actionPerformed(java.awt.event.ActionEvent e) {
            for (vgrabber.common.Edition ed:editionstodownload){                
                if (vgrabber.grabber.Grabber.GrabEdition(ed)){
                    System.out.println(ed.getId()+" - downloaded ");
                }
            }
        }

    }
    
}
