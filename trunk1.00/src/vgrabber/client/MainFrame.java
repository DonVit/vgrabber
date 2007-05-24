/*
 * MainFrame.java
 *
 * Created on December 4, 2006, 7:11 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber.client;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import javax.swing.*;
/**
 *
 * @author vdoni
 */
public class MainFrame extends JFrame implements ActionListener{
    private javax.swing.JToolBar toolbar;
    private javax.swing.JPanel panel;
    private javax.swing.JTabbedPane tabbedpane;
    private javax.swing.JPanel statuspanel;
    private javax.swing.JLabel wl;
    
    private vgrabber.client.MessagesPanel messagepanel=null;
    public MainFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                
        this.setTitle("VGrabber");
        this.init();
        this.pack();
        this.setExtendedState(MAXIMIZED_BOTH);                        
    }
    private void init(){
        this.toolbar=new javax.swing.JToolBar();        
        this.toolbar.setFloatable(false);
        this.toolbar.setRollover(false);

        //Download button
        java.net.URL imageURL = MainFrame.class.getResource("images/download.gif");                        
        javax.swing.Icon icon=new javax.swing.ImageIcon(imageURL);
        javax.swing.JButton button=new javax.swing.JButton(icon);        
        button.setName("Download");        
        button.setToolTipText("Descarca");        
        button.addActionListener(this);        
        this.toolbar.add(button);
        
        //Separator
        this.toolbar.addSeparator();        

        //Filter button
        
        imageURL = MainFrame.class.getResource("images/filter.gif");                        
        icon=new javax.swing.ImageIcon(imageURL);
        button=new javax.swing.JButton(icon);        
        button.setName("Filter");        
        button.setToolTipText("Anunturi unicale");        
        button.addActionListener(this);        
        this.toolbar.add(button);           
        
        //Search button
        imageURL = MainFrame.class.getResource("images/view.gif");                        
        icon=new javax.swing.ImageIcon(imageURL);
        button=new javax.swing.JButton(icon);        
        button.setName("View");        
        button.setToolTipText("Vizualizare anunturi");        
        button.addActionListener(this);        
        this.toolbar.add(button);
        
        //Search button
        imageURL = MainFrame.class.getResource("images/search.gif");                        
        icon=new javax.swing.ImageIcon(imageURL);
        button=new javax.swing.JButton(icon);        
        button.setName("Search");        
        button.setToolTipText("Cautare anunturi");        
        button.addActionListener(this);        
        this.toolbar.add(button);
                     
        //Favorits button
        imageURL = MainFrame.class.getResource("images/favorites.jpg");                        
        icon=new javax.swing.ImageIcon(imageURL);
        button=new javax.swing.JButton(icon);        
        button.setName("Favorites");        
        button.setToolTipText("Anunturi favorite");        
        button.addActionListener(this);        
        this.toolbar.add(button);
        
        //Separator
        this.toolbar.addSeparator();        
        //Phone info button
        imageURL = MainFrame.class.getResource("images/phone.jpg");                        
        icon=new javax.swing.ImageIcon(imageURL);
        button=new javax.swing.JButton(icon);
        button.setName("PhoneInfo");
        button.setToolTipText("Phone Info");        
        button.addActionListener(this);
        this.toolbar.add(button);           
        
        //Separator
        this.toolbar.addSeparator();      
        //Print button
        imageURL = MainFrame.class.getResource("images/print.jpg");                        
        icon=new javax.swing.ImageIcon(imageURL);
        button=new javax.swing.JButton(icon);        
        button.setName("Print");        
        button.setToolTipText("Imprima");        
        button.addActionListener(this);        
        this.toolbar.add(button);
        
        //Separator
        this.toolbar.addSeparator();           

        //Categories button
        imageURL = MainFrame.class.getResource("images/list.gif");                        
        icon=new javax.swing.ImageIcon(imageURL);
        button=new javax.swing.JButton(icon);        
        button.setName("Categories");        
        button.setToolTipText("Categorii");        
        button.addActionListener(this);        
        this.toolbar.add(button);
        
        //Configurations button
        imageURL = MainFrame.class.getResource("images/config.gif");                        
        icon=new javax.swing.ImageIcon(imageURL);
        button=new javax.swing.JButton(icon);        
        button.setName("Configurations");        
        button.setToolTipText("Configurari");        
        button.addActionListener(this);        
        this.toolbar.add(button);     
        
        //Separator
        this.toolbar.addSeparator();        
        //Help button
        imageURL = MainFrame.class.getResource("images/help.gif");                        
        icon=new javax.swing.ImageIcon(imageURL);
        button=new javax.swing.JButton(icon);
        button.setName("Help");
        button.setToolTipText("Ajutor");        
        button.addActionListener(this);
        this.toolbar.add(button);       
        
        //Separator
        this.toolbar.addSeparator();        
        //Close current tab
        imageURL = MainFrame.class.getResource("images/close.gif");                        
        icon=new javax.swing.ImageIcon(imageURL);
        button=new javax.swing.JButton(icon);
        button.setName("CloseTab");
        button.setToolTipText("Inchide");        
        button.addActionListener(this);
        this.toolbar.add(button);              
        
        this.panel=new javax.swing.JPanel(new java.awt.GridLayout(1,0));        
        this.tabbedpane=new javax.swing.JTabbedPane();                
        
        //this.tabbedpane.addTab("Tab2",this.messagepanel=new MessagesPanel());
        this.panel.add(this.tabbedpane);
        
        //Create and Add Status Bar
        this.statuspanel=new javax.swing.JPanel(new java.awt.GridLayout(1,0));        
        this.statuspanel.add(new JLabel("Bara de Stare:"));
        wl=new javax.swing.JLabel();
        this.statuspanel.add(wl);
        
        this.getContentPane().setLayout(new java.awt.BorderLayout());
        this.add(this.toolbar, java.awt.BorderLayout.PAGE_START);
        this.add(this.panel, java.awt.BorderLayout.CENTER);
        this.add(this.statuspanel, java.awt.BorderLayout.PAGE_END);
    }
    public void actionPerformed(ActionEvent e){

        if (((JButton)e.getSource()).getName()=="Download"){
            this.addTab("Descarca",new DownloadPanel(this));
        } 
        if (((JButton)e.getSource()).getName()=="Filter"){
            this.addTab("Filter",new NewMessagesPanel());
        }                     
        if (((JButton)e.getSource()).getName()=="View"){
            this.addTab("View",new MessagesPanel());
        }     
        if (((JButton)e.getSource()).getName()=="Search"){
            this.addTab("Search",new SearchPanel());
        }        
        if (((JButton)e.getSource()).getName()=="Favorites"){
            this.addTab("Favorites",new FavoritesPanel());
        }           
        if (((JButton)e.getSource()).getName()=="Categories"){
            this.addTab("Categories",new CategoriesPanel());
        }                   
        if (((JButton)e.getSource()).getName()=="Configurations"){
            this.addTab("Configurations",new ConfigPanel());
        }
        if (((JButton)e.getSource()).getName()=="PhoneInfo"){
            this.addTab("PhoneInfo",new PhoneInfoPanel());
        }             
        if (((JButton)e.getSource()).getName()=="Help"){
            this.addTab("Help",new HelpPanel());
        }          
        if (((JButton)e.getSource()).getName()=="CloseTab"){
            this.remTab();
        }                  
      
    }
    public void addTab(String tabTitle, java.awt.Component component){
        this.tabbedpane.addTab(tabTitle,component);   
        this.tabbedpane.setSelectedIndex(this.tabbedpane.getTabCount()-1);             
    }
    public void remTab(){  
        if (this.tabbedpane.getTabCount()!=0){
            this.tabbedpane.remove(this.tabbedpane.getSelectedIndex());        
        } else {
           if(javax.swing.JOptionPane.showConfirmDialog(null,"Are you sure you want to close application ?", "Exit",javax.swing.JOptionPane.YES_NO_OPTION)==0){
                vgrabber.logger.Logger.getLogger().info("Appliction closed");                                                        
                System.exit(0);
            }            
        }
    }

}
