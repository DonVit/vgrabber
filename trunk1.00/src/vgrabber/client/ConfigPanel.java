/*
 * DBConfigPanel.java
 *
 * Created on December 30, 2006, 7:58 PM
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
public class ConfigPanel extends JPanel implements java.awt.event.ActionListener{
    
    private final int labelwidth=150;
    private final int textfieldwidth=160;
    private final int buttonwidth=75;    
    private final int sizebetweencomp=10;
    private final String[] servertypes={"MSSQL","MySQL"};
    
    private javax.swing.JPanel centerpanel=null;
    
    private javax.swing.JLabel servertypelable=null;
    private javax.swing.JComboBox servertype=null;
    
    private javax.swing.JLabel servernamelable=null;    
    private javax.swing.JTextField servername=null;
    
    private javax.swing.JLabel dbnamelable=null;    
    private javax.swing.JTextField dbname=null;
    
    private javax.swing.JLabel usernamelable=null;    
    private javax.swing.JTextField username=null;
    
    private javax.swing.JLabel passwordlable=null;    
    private javax.swing.JPasswordField password=null;
    
    private javax.swing.JLabel createdatabaselable=null;    
    private javax.swing.JCheckBox createdatabase=null;    
    
    private javax.swing.JButton savebutton=null;
   
    private vgrabber.common.Config cnf=null;
    
    public ConfigPanel() {
        super(new java.awt.BorderLayout());
        this.init();
    }
    private void init(){
        
        //Get configs
        cnf=vgrabber.config.ConfigManager.geConfig();
        
        javax.swing.SpringLayout centerPanelLayout=new javax.swing.SpringLayout();        
        centerpanel=new javax.swing.JPanel(centerPanelLayout);
        centerpanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Base Properties"));        
        
        servertypelable=new javax.swing.JLabel("Server Type:");        
        servertypelable.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        servertypelable.setVerticalAlignment(javax.swing.JLabel.CENTER);
        javax.swing.SpringLayout.Constraints servertypelablecons=centerPanelLayout.getConstraints(servertypelable);
        servertypelablecons.setX(Spring.constant(sizebetweencomp));
        servertypelablecons.setY(Spring.constant(sizebetweencomp));               
        servertypelablecons.setWidth(Spring.constant(labelwidth));               
        centerpanel.add(servertypelable);
        
        servertype=new javax.swing.JComboBox(servertypes);        
        servertype.setSelectedItem(cnf.getServerType());        
        SpringLayout.Constraints servertypecons=centerPanelLayout.getConstraints(servertype);
        servertypecons.setX(Spring.sum(Spring.constant(sizebetweencomp), servertypelablecons.getConstraint(SpringLayout.EAST)));
        servertypecons.setY(Spring.constant(sizebetweencomp));                
        servertypecons.setWidth(Spring.constant(textfieldwidth));                       
        centerpanel.add(this.servertype);
        
        servernamelable=new javax.swing.JLabel("Server Name:");                
        servernamelable.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        servernamelable.setVerticalAlignment(javax.swing.JLabel.CENTER);
        javax.swing.SpringLayout.Constraints servernamelablecons=centerPanelLayout.getConstraints(servernamelable);
        servernamelablecons.setX(Spring.constant(sizebetweencomp));
        servernamelablecons.setY(Spring.sum(Spring.constant(sizebetweencomp),servertypecons.getConstraint(SpringLayout.SOUTH)));               
        servernamelablecons.setWidth(Spring.constant(labelwidth));               
        centerpanel.add(servernamelable);
        
        servername=new javax.swing.JTextField();        
        servername.setText(cnf.getServerName());
        SpringLayout.Constraints servernamecons=centerPanelLayout.getConstraints(servername);
        servernamecons.setX(Spring.sum(Spring.constant(sizebetweencomp), servernamelablecons.getConstraint(SpringLayout.EAST)));
        servernamecons.setY(Spring.sum(Spring.constant(sizebetweencomp),servertypecons.getConstraint(SpringLayout.SOUTH)));                
        servernamecons.setWidth(Spring.constant(textfieldwidth));          
        centerpanel.add(this.servername);
        
        dbnamelable=new javax.swing.JLabel("DataBase Name:");        
        dbnamelable.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        dbnamelable.setVerticalAlignment(javax.swing.JLabel.CENTER);                
        SpringLayout.Constraints dbnamelabelcons=centerPanelLayout.getConstraints(dbnamelable);
        dbnamelabelcons.setX(Spring.constant(sizebetweencomp));
        dbnamelabelcons.setY(Spring.sum(Spring.constant(sizebetweencomp),servernamecons.getConstraint(SpringLayout.SOUTH)));
        dbnamelabelcons.setWidth(Spring.constant(labelwidth));               
        centerpanel.add(dbnamelable);
        
        dbname=new javax.swing.JTextField();
        dbname.setText(cnf.getDatabase());
        SpringLayout.Constraints dbnamecons=centerPanelLayout.getConstraints(dbname);
        dbnamecons.setX(Spring.sum(Spring.constant(sizebetweencomp),dbnamelabelcons.getConstraint(SpringLayout.EAST)));
        dbnamecons.setY(Spring.sum(Spring.constant(sizebetweencomp),servernamecons.getConstraint(SpringLayout.SOUTH)));
        dbnamecons.setWidth(Spring.constant(textfieldwidth));               
        centerpanel.add(dbname);
        
        usernamelable=new javax.swing.JLabel("User Name:");  
        usernamelable.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        usernamelable.setVerticalAlignment(javax.swing.JLabel.CENTER);        
        SpringLayout.Constraints usernamelabelcons=centerPanelLayout.getConstraints(usernamelable);
        usernamelabelcons.setX(Spring.constant(sizebetweencomp));
        usernamelabelcons.setY(Spring.sum(Spring.constant(sizebetweencomp),dbnamecons.getConstraint(SpringLayout.SOUTH)));
        usernamelabelcons.setWidth(Spring.constant(labelwidth));               
        centerpanel.add(usernamelable);
        
        username=new javax.swing.JTextField();        
        username.setText(cnf.getUserName());
        SpringLayout.Constraints usernamecons=centerPanelLayout.getConstraints(username);
        usernamecons.setX(Spring.sum(Spring.constant(sizebetweencomp),usernamelabelcons.getConstraint(SpringLayout.EAST)));
        usernamecons.setY(Spring.sum(Spring.constant(sizebetweencomp),dbnamecons.getConstraint(SpringLayout.SOUTH)));
        usernamecons.setWidth(Spring.constant(textfieldwidth));               
        centerpanel.add(username);
        
        passwordlable=new javax.swing.JLabel("Password:");          
        passwordlable.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        passwordlable.setVerticalAlignment(javax.swing.JLabel.CENTER);        
        SpringLayout.Constraints passwordlablecons=centerPanelLayout.getConstraints(passwordlable);
        passwordlablecons.setX(Spring.constant(sizebetweencomp));
        passwordlablecons.setY(Spring.sum(Spring.constant(sizebetweencomp),usernamecons.getConstraint(SpringLayout.SOUTH)));
        passwordlablecons.setWidth(Spring.constant(labelwidth));               
        centerpanel.add(passwordlable);
        
        password=new javax.swing.JPasswordField();    
        password.setText(cnf.getPassword());        
        SpringLayout.Constraints passwordcons=centerPanelLayout.getConstraints(password);
        passwordcons.setX(Spring.sum(Spring.constant(sizebetweencomp),passwordlablecons.getConstraint(SpringLayout.EAST)));
        passwordcons.setY(Spring.sum(Spring.constant(sizebetweencomp),usernamecons.getConstraint(SpringLayout.SOUTH)));
        passwordcons.setWidth(Spring.constant(textfieldwidth));               
        centerpanel.add(password);

        createdatabaselable=new javax.swing.JLabel("Create DataBase Objects:");  
        createdatabaselable.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        createdatabaselable.setVerticalAlignment(javax.swing.JLabel.CENTER);        
        SpringLayout.Constraints createdatabaselablecons=centerPanelLayout.getConstraints(createdatabaselable);
        createdatabaselablecons.setX(Spring.constant(sizebetweencomp));
        createdatabaselablecons.setY(Spring.sum(Spring.constant(sizebetweencomp),passwordcons.getConstraint(SpringLayout.SOUTH)));
        createdatabaselablecons.setWidth(Spring.constant(labelwidth));               
        centerpanel.add(createdatabaselable);        
        
        createdatabase=new javax.swing.JCheckBox();        
        //createdatabase.setBackground(java.awt.Color.BLUE);
        SpringLayout.Constraints createdatabasecons=centerPanelLayout.getConstraints(createdatabase);
        createdatabasecons.setX(Spring.sum(Spring.constant(sizebetweencomp),createdatabaselablecons.getConstraint(SpringLayout.EAST)));
        createdatabasecons.setY(Spring.sum(Spring.constant(sizebetweencomp),passwordcons.getConstraint(SpringLayout.SOUTH)));
        //createdatabasecons.setWidth(Spring.constant(20));                       
        centerpanel.add(createdatabase);
                
        savebutton=new javax.swing.JButton("Save");
        savebutton.addActionListener(this);
        SpringLayout.Constraints savebuttoncons=centerPanelLayout.getConstraints(savebutton);
        savebuttoncons.setX(Spring.sum(Spring.constant(-buttonwidth),passwordcons.getConstraint(SpringLayout.EAST)));
        savebuttoncons.setY(Spring.sum(Spring.constant(sizebetweencomp*2),createdatabasecons.getConstraint(SpringLayout.SOUTH)));
        savebuttoncons.setWidth(Spring.constant(buttonwidth));               
        centerpanel.add(savebutton);        
        
        this.add(this.centerpanel,java.awt.BorderLayout.CENTER);
    }
    public void actionPerformed(java.awt.event.ActionEvent ae) {
        vgrabber.common.Config cnf=new vgrabber.common.Config();
        cnf.setServerType(this.servertype.getSelectedItem().toString());
        cnf.setServerName(this.servername.getText());
        cnf.setDataBase(this.dbname.getText());
        cnf.setUserName(this.username.getText());
        cnf.setPassword(this.password.getText());
        
        if (vgrabber.config.ConfigManager.setConfig(cnf)) {
            if (this.createdatabase.isSelected()){
                if (vgrabber.db.DBManager.CreateDBObjects()){                    
                    javax.swing.JOptionPane.showMessageDialog(this, "Changes were saved succesfully !");                                                        
                } else {                                
                    javax.swing.JOptionPane.showMessageDialog(this, "Error, DB Objects creation failed. Check logs for more info !");
                }            
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Changes were saved succesfully !");
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Error, DB properties weren't saved. Check logs for more info !");
        }
    }
    
}
