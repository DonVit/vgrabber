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
    
    private vgrabber.client.MessagesPanel messagepanel=null;
    public MainFrame() {
        //UIManager.setLookAndFeel()
        //try {
        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                
        this.setTitle("MGrabber");
        this.init();
        this.pack();
        this.setExtendedState(MAXIMIZED_BOTH);    
        /*
        } 
        catch (java.lang.ClassNotFoundException ex){                    
        }
        catch (java.lang.InstantiationException ex1){                    
        }
        catch (java.lang.IllegalAccessException ex2){                    
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex3){                    
        }     
         */           
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
        button.setToolTipText("Download");        
        button.addActionListener(this);        
        this.toolbar.add(button);
        
        //Separator
        this.toolbar.addSeparator();        

        //Search button
        imageURL = MainFrame.class.getResource("images/view.gif");                        
        icon=new javax.swing.ImageIcon(imageURL);
        button=new javax.swing.JButton(icon);        
        button.setName("View");        
        button.setToolTipText("View");        
        button.addActionListener(this);        
        this.toolbar.add(button);
        
        //Search button
        imageURL = MainFrame.class.getResource("images/search.gif");                        
        icon=new javax.swing.ImageIcon(imageURL);
        button=new javax.swing.JButton(icon);        
        button.setName("Search");        
        button.setToolTipText("Search");        
        button.addActionListener(this);        
        this.toolbar.add(button);

        //Favorits button
        imageURL = MainFrame.class.getResource("images/favorites.jpg");                        
        icon=new javax.swing.ImageIcon(imageURL);
        button=new javax.swing.JButton(icon);        
        button.setName("Favorites");        
        button.setToolTipText("Favorites");        
        button.addActionListener(this);        
        this.toolbar.add(button);
        
        //Separator
        this.toolbar.addSeparator();        

        //Categories button
        imageURL = MainFrame.class.getResource("images/list.gif");                        
        icon=new javax.swing.ImageIcon(imageURL);
        button=new javax.swing.JButton(icon);        
        button.setName("Categories");        
        button.setToolTipText("Categories");        
        button.addActionListener(this);        
        this.toolbar.add(button);
        
        //Configurations button
        imageURL = MainFrame.class.getResource("images/config.gif");                        
        icon=new javax.swing.ImageIcon(imageURL);
        button=new javax.swing.JButton(icon);        
        button.setName("Configurations");        
        button.setToolTipText("Configurations");        
        button.addActionListener(this);        
        this.toolbar.add(button);

        
        //Separator
        this.toolbar.addSeparator();        
        //Help button
        imageURL = MainFrame.class.getResource("images/help.gif");                        
        icon=new javax.swing.ImageIcon(imageURL);
        button=new javax.swing.JButton(icon);
        button.setName("Help");
        button.setToolTipText("Help");        
        button.addActionListener(this);
        this.toolbar.add(button);        

        //Separator
        this.toolbar.addSeparator();        
        //Close button
        imageURL = MainFrame.class.getResource("images/close.gif");                        
        icon=new javax.swing.ImageIcon(imageURL);
        button=new javax.swing.JButton(icon);
        button.setName("Close");
        button.setToolTipText("Close");        
        button.addActionListener(this);
        this.toolbar.add(button);        
        
        
        this.panel=new javax.swing.JPanel(new java.awt.GridLayout(1,0));        
        this.tabbedpane=new javax.swing.JTabbedPane();                
        
        //this.tabbedpane.addTab("Tab2",this.messagepanel=new MessagesPanel());
        this.panel.add(this.tabbedpane);
        
        //Create and Add Status Bar
        this.statuspanel=new javax.swing.JPanel(new java.awt.GridLayout(1,0));        
        this.statuspanel.add(new JLabel("Status Bar"));
        
        
        this.getContentPane().setLayout(new java.awt.BorderLayout());
        this.add(this.toolbar, java.awt.BorderLayout.PAGE_START);
        this.add(this.panel, java.awt.BorderLayout.CENTER);
        this.add(this.statuspanel, java.awt.BorderLayout.PAGE_END);
    }
    public void actionPerformed(ActionEvent e){
        if (((JButton)e.getSource()).getName()=="Download"){
            this.addNewTab("Download",new DownloadPanel());
            //this.tabbedpane.addTab("Search",this.messagepanel=new MessagesPanel());   
            //this.tabbedpane.setSelectedIndex(this.tabbedpane.getTabCount()-1);
        }        
        if (((JButton)e.getSource()).getName()=="Search"){
            this.addNewTab("Search",new MessagesPanel());
            //this.tabbedpane.addTab("Search",this.messagepanel=new MessagesPanel());   
            //this.tabbedpane.setSelectedIndex(this.tabbedpane.getTabCount()-1);
        }
        if (((JButton)e.getSource()).getName()=="View"){
            this.addNewTab("View",new NewMessagesPanel());
            //this.tabbedpane.addTab("Search",this.messagepanel=new MessagesPanel());   
            //this.tabbedpane.setSelectedIndex(this.tabbedpane.getTabCount()-1);
        }     
        if (((JButton)e.getSource()).getName()=="Favorites"){
            this.addNewTab("Favorites",new FavoritesPanel());
            //this.tabbedpane.addTab("Search",this.messagepanel=new MessagesPanel());   
            //this.tabbedpane.setSelectedIndex(this.tabbedpane.getTabCount()-1);
        }           
        if (((JButton)e.getSource()).getName()=="Categories"){
            this.addNewTab("Categories",new CategoriesPanel());
            //this.tabbedpane.addTab("Search",this.messagepanel=new MessagesPanel());   
            //this.tabbedpane.setSelectedIndex(this.tabbedpane.getTabCount()-1);
        }                   
        if (((JButton)e.getSource()).getName()=="Configurations"){
            this.addNewTab("Configurations",new ConfigPanel());
            //this.tabbedpane.addTab("Search",this.messagepanel=new MessagesPanel());   
            //this.tabbedpane.setSelectedIndex(this.tabbedpane.getTabCount()-1);
        }
        if (((JButton)e.getSource()).getName()=="Help"){
            this.addNewTab("Help",new HelpPanel());
            //this.tabbedpane.addTab("Search",this.messagepanel=new MessagesPanel());   
            //this.tabbedpane.setSelectedIndex(this.tabbedpane.getTabCount()-1);
        }          
        if (((JButton)e.getSource()).getName()=="Close"){            
            if(javax.swing.JOptionPane.showConfirmDialog(null,"Are you sure you want to close application ?", "Exit",javax.swing.JOptionPane.YES_NO_OPTION)==0){
                System.exit(0);
            }
        }        
    }
    public void addNewTab(String tabTitle, java.awt.Component component){
            this.tabbedpane.addTab(tabTitle,component);   
            this.tabbedpane.setSelectedIndex(this.tabbedpane.getTabCount()-1);        
    }
}
