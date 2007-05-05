/*
 * TestFrame.java
 *
 * Created on January 18, 2007, 10:42 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber;

import javax.swing.*;

/**
 *
 * @author VDoni
 */
public class TestFrame extends JFrame{
    
    private javax.swing.JPanel mainpanel;
    
    private javax.swing.JPanel toppanel;
    private javax.swing.JLabel label1;
    private javax.swing.JTextField text1;
    private javax.swing.JLabel label2;
    private javax.swing.JTextField text2;
    
    private javax.swing.JPanel bottompanel;    
    
    public TestFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                
        this.setTitle("MGrabber");
        this.init();
        this.pack();
        this.setExtendedState(MAXIMIZED_BOTH);          
    }
    
    public static void main(String[] args) {
        vgrabber.TestFrame tf=new vgrabber.TestFrame();
        tf.show();
    }
    private void init() {
        //toppanel=new JPanel(null);
        //toppanel=new JPanel();        
        javax.swing.SpringLayout toppanellayout=new javax.swing.SpringLayout();
        toppanel=new JPanel(toppanellayout);
        //toppanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Top Panel"));
        toppanel.setBackground(java.awt.Color.BLUE);
        
        label1=new javax.swing.JLabel("Label 1");
        text1=new javax.swing.JTextField();
        
        SpringLayout.Constraints label1cons=toppanellayout.getConstraints(label1);
        label1cons.setX(Spring.constant(50));
        label1cons.setY(Spring.constant(50));
        
        SpringLayout.Constraints text1cons=toppanellayout.getConstraints(text1);
        text1cons.setX(Spring.sum(Spring.constant(10),label1cons.getConstraint(SpringLayout.EAST)));
        text1cons.setY(Spring.constant(50));
        text1cons.setWidth(Spring.constant(60));
                
        toppanel.add(label1);
        toppanel.add(text1);
        
        
        //bottompanel=new javax.swing.JPanel();
        javax.swing.SpringLayout bottompanellayout=new javax.swing.SpringLayout();
        bottompanel=new javax.swing.JPanel(bottompanellayout);
        //bottompanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Bottom Panel"));
        bottompanel.setBackground(java.awt.Color.RED);
        
        //mainpanel=new javax.swing.JPanel(null);
        //mainpanel=new javax.swing.JPanel();
        javax.swing.SpringLayout mainpanellayout=new javax.swing.SpringLayout();
        mainpanel=new javax.swing.JPanel(mainpanellayout);
        //mainpanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Main Panel"));
        mainpanel.setBackground(java.awt.Color.PINK);        

        //mainpanellayout.putConstraint(SpringLayout.NORTH,toppanel,10,SpringLayout.NORTH,mainpanel);
        //mainpanellayout.putConstraint(SpringLayout.WEST,toppanel,10,SpringLayout.WEST,mainpanel);
        //mainpanellayout.putConstraint(SpringLayout.EAST,toppanel,10,SpringLayout.EAST,mainpanel);
        //mainpanellayout.putConstraint(SpringLayout.SOUTH,toppanel,10,SpringLayout.SOUTH,mainpanel);
        SpringLayout.Constraints toppanelcons=mainpanellayout.getConstraints(toppanel);
        toppanelcons.setX(Spring.constant(5));
        toppanelcons.setY(Spring.constant(5));
        //toppanelcons.setWidth(Spring.width(mainpanel));
        //toppanelcons.setWidth(Spring.constant(500));
        toppanelcons.setHeight(Spring.constant(50));
        
        SpringLayout.Constraints bottompanelcons=mainpanellayout.getConstraints(bottompanel);
        bottompanelcons.setX(Spring.constant(5));
        bottompanelcons.setY(Spring.sum(Spring.constant(5),toppanelcons.getConstraint(SpringLayout.SOUTH)));
        //bottompanelcons.setWidth(Spring.width(mainpanel));
        //bottompanelcons.setWidth(Spring.width(toppanel));
        //bottompanelcons.setWidth(Spring.constant(mainpanellayout.EAST));
        //bottompanelcons.setWidth(Spring.constant(100));
        bottompanelcons.setWidth(Spring.width(toppanel));
        bottompanelcons.setWidth(Spring.constant(50, 200, 500));
        bottompanelcons.setHeight(Spring.constant(30));
        
        
        //mainpanellayout.putConstraint(SpringLayout.NORTH,mainpanel,50,SpringLayout.NORTH,toppanel);
        //mainpanellayout.putConstraint(SpringLayout.WEST,mainpanel,50,SpringLayout.WEST,toppanel);
        mainpanellayout.putConstraint(SpringLayout.EAST,mainpanel,5,SpringLayout.EAST,toppanel);
        //mainpanellayout.putConstraint(SpringLayout.EAST,toppanel,50,SpringLayout.EAST,mainpanel);
        
        //mainpanellayout.putConstraint(SpringLayout.SOUTH,mainpanel,0,SpringLayout.SOUTH,toppanel);
        //mainpanellayout.putConstraint(SpringLayout.EAST,toppanel,5,SpringLayout.EAST,bottompanel);
        

        
        //mainpanellayout.putConstraint(SpringLayout.SOUTH,toppanel,5,SpringLayout.NORTH,bottompanel);
        //mainpanellayout.putConstraint(SpringLayout.WEST,toppanel,5,SpringLayout.WEST,bottompanel);
        
       // mainpanellayout.putConstraint(SpringLayout.SOUTH,mainpanel,5,SpringLayout.SOUTH,bottompanel);
        
        
        mainpanel.add(toppanel);
        mainpanel.add(bottompanel);
        
        this.add(mainpanel);                
    }
}
