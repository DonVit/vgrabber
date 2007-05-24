/*
 * PhoneInfoPanel.java
 *
 * Created on 24 May 2007, 09:32
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vgrabber.client;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.*;

/**
 *
 * @author vdoni
 */
public class PhoneInfoPanel extends JPanel implements java.awt.event.ActionListener {
    
    private JPanel toppanel;
    private JTextField phone;
    private JButton search;
    
    private JPanel centerpanel;
    private JLabel phoneinfo;
    public PhoneInfoPanel() {
        init();
        //String s=vgrabber.grabber.Grabber.GrabPhoneInfo("444444");
    }
    public PhoneInfoPanel(String phone) {
        init();
        phoneinfo.setText(getInfo(phone));        
    }    
    
    private void init(){
        this.setLayout(new BorderLayout());
        toppanel=new JPanel(new BorderLayout());
        toppanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Phone"));
        phone=new JTextField();
        toppanel.add(phone,BorderLayout.CENTER);
        search=new JButton("Search");
        search.addActionListener(this);
        toppanel.add(search,BorderLayout.EAST);
        
        centerpanel=new JPanel();
        centerpanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Phone Info"));
        
        phoneinfo=new JLabel();
        centerpanel.add(phoneinfo,BorderLayout.CENTER);
        
        this.add(toppanel,BorderLayout.NORTH);
        this.add(centerpanel,BorderLayout.CENTER);
    }
    public void actionPerformed(java.awt.event.ActionEvent e){         
        phoneinfo.setText(getInfo(phone.getText()));
    }
    private String getInfo(String phone){
        String s=vgrabber.grabber.Grabber.GrabPhoneInfo(phone.replace("-",""));
        if (s==""){
            s="Nu este informatie pe acest numar !";
        }
        String style="<style>table.vivodDannih { border-collapse: collapse;font-size: 9pt;font-family: 'Lucida Grande', Verdana, Arial, Sans-Serif;font: normal; }		      table.vivodDannih td  { padding: 10px 10px 10px 20px; border: 0px; font: normal Verdana, Arial, Helvetica, sans-serif;}table.vivodDannih th {background: #6e99bf;color: white;height: 30px;text-align: center;font-size: 10pt;padding: 5px 10px 5px 10px;font: normal;border-left: 1px solid #87CEFA;}</style>";
        return "<html>"+style+"<body>"+s+"</body></html>";        

    }
}
