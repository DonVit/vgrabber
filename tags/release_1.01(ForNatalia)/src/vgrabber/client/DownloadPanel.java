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
import vgrabber.client.SwingWorker;

/**
 *
 * @author VDoni
 */
public class DownloadPanel extends JPanel{
    
    /** Creates a new instance of DownloadPanel */
    private javax.swing.JPanel toppanel=null;
    
    private javax.swing.JTable edtionstodownloadtable=null;
    private javax.swing.JScrollPane edtionstodownloadscrollpane=null;
    private javax.swing.JPanel topsubpanel=null;    
    private javax.swing.JLabel downloadlabel=null;
    private javax.swing.JButton download=null;
    
        
    private javax.swing.JPanel bottompanel=null;
    
    private javax.swing.JTable edtionstable=null;
    private javax.swing.JScrollPane edtionsscrollpane=null;
    
    private javax.swing.JPanel bottomsubpanel=null;
    private javax.swing.JLabel removelabel=null;
    private javax.swing.JButton remove=null;
    
    private javax.swing.JSplitPane splitpane=null;
    
    private java.util.ArrayList<vgrabber.common.Edition> editionstodownload=null;
    private int editionstodownloadmin=0;
    private int editionstodownloadmax=0;
    
    private java.util.ArrayList<vgrabber.common.Edition> editions=null;
    private int editionsmin=0;
    private int editionsmax=0;
    private vgrabber.client.MainFrame mf;
    
    
    
    public DownloadPanel(vgrabber.client.MainFrame mf) {
        this.mf=mf;
        this.setLayout(new java.awt.BorderLayout());
        this.init();        
    }
    
    public void init() {        
        toppanel=new javax.swing.JPanel(new java.awt.BorderLayout());
        toppanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Editions on WEB to download:"));
        
        edtionstodownloadtable=new javax.swing.JTable();
        edtionstodownloadscrollpane=new javax.swing.JScrollPane(edtionstodownloadtable);
        toppanel.add(edtionstodownloadscrollpane, java.awt.BorderLayout.CENTER);
        
        downloadlabel=new javax.swing.JLabel();
        download=new javax.swing.JButton("Download");        
        download.addActionListener(new EditionsToDownloadActionListener(this));        
        topsubpanel=new javax.swing.JPanel(new java.awt.BorderLayout());
        topsubpanel.add(downloadlabel,java.awt.BorderLayout.CENTER);
        topsubpanel.add(download,java.awt.BorderLayout.EAST);
        toppanel.add(topsubpanel, java.awt.BorderLayout.SOUTH);
                
        bottompanel=new javax.swing.JPanel(new java.awt.BorderLayout());
        bottompanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Downloaded editions:"));
        
        edtionstable=new javax.swing.JTable();
        edtionsscrollpane=new javax.swing.JScrollPane(edtionstable);
        bottompanel.add(edtionsscrollpane, java.awt.BorderLayout.CENTER);
        
        removelabel=new javax.swing.JLabel();        
        remove=new javax.swing.JButton("Remove");
        remove.addActionListener(new EditionsActionListener(this));
        bottomsubpanel=new javax.swing.JPanel(new java.awt.BorderLayout());
        bottomsubpanel.add(removelabel, java.awt.BorderLayout.CENTER);
        bottomsubpanel.add(remove, java.awt.BorderLayout.EAST);
        bottompanel.add(bottomsubpanel, java.awt.BorderLayout.SOUTH);
        
        splitpane=new javax.swing.JSplitPane(javax.swing.JSplitPane.VERTICAL_SPLIT);
        splitpane.setTopComponent(toppanel);
        splitpane.setBottomComponent(bottompanel);
        splitpane.setResizeWeight(0.5);
        add(splitpane);
        
        fillEditionsToDownloadTable();
        fillEditionsTable();
    }
    
    private void fillEditionsToDownloadTable(){ 
        //uncomment when net is avilable
        editionstodownload=vgrabber.grabber.Grabber.GrabEditions();
        //editionstodownload=vgrabber.db.EditionManager.GetAllEditions();                
        edtionstodownloadtable.setModel(new vgrabber.client.EditionsTableModel(editionstodownload));
        edtionstodownloadtable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);        
        edtionstodownloadtable.getColumnModel().getColumn(0).setMaxWidth(60);        
        edtionstodownloadtable.getColumnModel().getColumn(1).setMaxWidth(60);
        edtionstodownloadtable.getColumnModel().getColumn(2).setMinWidth(100);                
        edtionstodownloadtable.getColumnModel().getColumn(2).setMaxWidth(140);        
        edtionstodownloadtable.setColumnSelectionInterval(0,0);
        edtionstodownloadtable.getSelectionModel().addListSelectionListener(new EditionToDownloadSelectionListener(this));                
    }
    private void fillEditionsTable(){     
        editions=vgrabber.db.EditionManager.GetAllEditions();                
        edtionstable.setModel(new vgrabber.client.EditionsTableModel(editions));
        edtionstable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);        
        edtionstable.getColumnModel().getColumn(0).setMaxWidth(60);
        edtionstable.getColumnModel().getColumn(1).setMaxWidth(60);        
        edtionstable.getColumnModel().getColumn(2).setMinWidth(100);                
        edtionstable.getColumnModel().getColumn(2).setMaxWidth(140);        
        edtionstable.setColumnSelectionInterval(0,0);
        edtionstable.getSelectionModel().addListSelectionListener(new EditionSelectionListener(this));                
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
                        dep.editionsmin = lsm.getMinSelectionIndex();                        
                        dep.editionsmax = lsm.getMaxSelectionIndex();                        
                        if (dep.editionsmin!=dep.editionsmax){                            
                            dep.removelabel.setText("Click the button to remove editions begin with number "+(dep.editionsmin+1)+" to "+(dep.editionsmax+1));
                        } else {
                            dep.removelabel.setText("Click the button to remove edition with number "+(dep.editionsmax+1));
                        }
                    }
        }
    }
    class EditionToDownloadSelectionListener implements javax.swing.event.ListSelectionListener {
        private vgrabber.client.DownloadPanel dep;
        public EditionToDownloadSelectionListener(vgrabber.client.DownloadPanel dep){
            this.dep=dep;
        }
        public void valueChanged(javax.swing.event.ListSelectionEvent e){
                    if (e.getValueIsAdjusting()) return;
                    ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                    if (!lsm.isSelectionEmpty()) {                 
                        dep.editionstodownloadmin = lsm.getMinSelectionIndex();                        
                        dep.editionstodownloadmax = lsm.getMaxSelectionIndex();                        
                        if (dep.editionstodownloadmin!=dep.editionstodownloadmax){                            
                            dep.downloadlabel.setText("Click the button to download editions begin with number "+(dep.editionstodownloadmin+1)+" to "+(dep.editionstodownloadmax+1));
                        } else {
                            dep.downloadlabel.setText("Click the button to download edition with number "+(dep.editionstodownloadmin+1));
                        }
                    }
        }
    }
    
    class EditionsToDownloadActionListener implements java.awt.event.ActionListener {
        private vgrabber.client.DownloadPanel dep;
        public EditionsToDownloadActionListener(vgrabber.client.DownloadPanel dep){
            this.dep=dep;
        }
        public void actionPerformed(java.awt.event.ActionEvent e) {
            if(javax.swing.JOptionPane.showConfirmDialog(null,"Are you sure you want to Download selected edition(s) ?", "Download",javax.swing.JOptionPane.YES_NO_OPTION)==0){
            SwingWorker worker = new SwingWorker() {
                    Wait wd;
                    public Object construct() {
                        wd =new Wait((JFrame)DownloadPanel.this.mf);                        
                        wd.setVisible(true);                
                
                        for (int i=dep.editionstodownloadmin;i<=dep.editionstodownloadmax;i++ ){                
                            if (vgrabber.grabber.Grabber.GrabEdition(dep.editionstodownload.get(i))!=null){
                                System.out.println("Edition "+dep.editionstodownload.get(i).getId()+" was downloaded");                        
                            } else {
                                System.out.println("No messages where dlownloaded for Edition "+dep.editionstodownload.get(i).getId());                        
                            }                                        
                        }
                        dep.fillEditionsTable();                                
                        return null;
                    }
                    public void finished() {
                        wd.dispose();
                    }
                };
                
            }
        }
    }
    class EditionsActionListener implements java.awt.event.ActionListener {
        private vgrabber.client.DownloadPanel dep;
        public EditionsActionListener(vgrabber.client.DownloadPanel dep){
            this.dep=dep;
        }
        public void actionPerformed(java.awt.event.ActionEvent e) {
            if(javax.swing.JOptionPane.showConfirmDialog(null,"Are you sure you want to Remove selected edition(s) ?", "Deletion",javax.swing.JOptionPane.YES_NO_OPTION)==0){            
                
            SwingWorker worker = new SwingWorker() {
                    Wait wd;
                    public Object construct() {
                        wd =new Wait((JFrame)DownloadPanel.this.mf);
                        wd.setVisible(true);
                
                for (int i=dep.editionsmin;i<=dep.editionsmax;i++ ){                
                    if (vgrabber.db.EditionManager.DelEdition(dep.editions.get(i))){
                        System.out.println("Edition "+dep.editions.get(i).getId()+" was deleted.");
                    }                    
                } 
                dep.fillEditionsTable();
                        
                        
                        return null;
                    }
                    public void finished() {
                        wd.dispose();
                    }
                };
               
                
                /*
                for (int i=dep.editionsmin;i<=dep.editionsmax;i++ ){                
                    if (vgrabber.db.EditionManager.DelEdition(dep.editions.get(i))){
                        System.out.println("Edition "+dep.editions.get(i).getId()+" was deleted.");
                    }                    
                } 
                dep.fillEditionsTable();
                 */                               

            }
        }

    }    
}
