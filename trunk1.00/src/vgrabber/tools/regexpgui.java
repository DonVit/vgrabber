/*
 * regexpgui.java
 *
 * Created on 23 Март 2007 г., 19:59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber.tools;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

/**
 *
 * @author vdoni
 */
public class regexpgui extends JFrame{
    
    private JPanel toppanel;
    private JLabel regexplabel;
    private JTextField regexpfield;
    private JButton regexpbutton;
    
    private JPanel centerpanel;
    private JScrollPane scrollvalue;
    private JTextArea value;
    private JScrollPane scrollresult;
    private JTextArea result;
    private JSplitPane splitpanebottom;
    
    private JPanel bottompanel;
    private JButton clearbutton;
           
    public regexpgui() {
        init();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);         
        setTitle("RegExp Evaluator");
    }

    
    private void init() {
        getContentPane().setLayout(new BorderLayout());
       //setLayout(new BorderLayout());
       
       
       regexplabel=new JLabel("Reg. Expr.");
       regexpfield=new JTextField();
       regexpbutton=new JButton("Evaluate");
       regexpbutton.addActionListener(new EvaluateExpressin());
       toppanel=new JPanel(new BorderLayout());
       toppanel.add(regexplabel, BorderLayout.WEST);
       toppanel.add(regexpfield, BorderLayout.CENTER);
       toppanel.add(regexpbutton, BorderLayout.EAST);
       
       value=new JTextArea();   
       scrollvalue=new JScrollPane(value);
       result=new JTextArea();
       scrollresult=new JScrollPane(result);
       centerpanel=new JPanel(new BorderLayout());
       centerpanel.add(scrollvalue,BorderLayout.CENTER);
       centerpanel.add(scrollresult,BorderLayout.CENTER);
       
       splitpanebottom=new javax.swing.JSplitPane(javax.swing.JSplitPane.VERTICAL_SPLIT);
       splitpanebottom.setTopComponent(scrollvalue);
       splitpanebottom.setBottomComponent(scrollresult);
       splitpanebottom.setResizeWeight(0.2);
       
       bottompanel=new JPanel(new BorderLayout());
       clearbutton=new JButton("Clear Result");
       clearbutton.addActionListener(new ClearResult());
       bottompanel.add(clearbutton,BorderLayout.EAST);
       
       add(toppanel,BorderLayout.NORTH);
       add(splitpanebottom,BorderLayout.CENTER);
       add(bottompanel,BorderLayout.SOUTH);
       
       
       pack();
    }
    class EvaluateExpressin implements java.awt.event.ActionListener{
        public void actionPerformed(ActionEvent e) {
            Pattern pattern = Pattern.compile(regexpfield.getText());        
            Matcher matcher = pattern.matcher(value.getText());
            String c;
            while (matcher.find()) {            
                if (result.getText()==""){
                    result.setText(matcher.group());
                } else {
                    result.setText(result.getText()+"\n"+matcher.group());
                }
            }                             
        }        
    }
    
    class ClearResult implements java.awt.event.ActionListener{
        public void actionPerformed(ActionEvent e) {
            result.setText("");
        }        
    }
    
    public static void main(String[] args){
        regexpgui f=new regexpgui();
        f.setVisible(true);
    }
    
}
