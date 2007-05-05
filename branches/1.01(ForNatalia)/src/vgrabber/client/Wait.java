

package vgrabber.client;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Wait extends JDialog
{  
    private final int fw=160;
    private final int fh=60;    
    public Wait(JFrame owner)
   {  
      super(owner, "About DialogTest", false);      
      setUndecorated(true);
      Container contentPane = getContentPane();
      contentPane.setLayout(new java.awt.BorderLayout());
      JPanel pn=new JPanel(new BorderLayout());
      pn.setBorder(BorderFactory.createLineBorder(Color.BLUE));
      contentPane.add(pn,java.awt.BorderLayout.CENTER);
      JLabel lb=new JLabel("Wait please...");
      lb.setHorizontalAlignment(JLabel.CENTER);
      pn.add(lb,BorderLayout.CENTER); 
      setPreferredSize(new java.awt.Dimension(this.fw,this.fh));                
      //setSize(fw, fh);
      centerScreen();      
      pack();
   }
    public void centerScreen() {
        java.awt.Dimension dim = getToolkit().getScreenSize();
        java.awt.Rectangle abounds = getBounds();
        //setLocation((dim.width - abounds.width) / 2,(dim.height - abounds.height) / 2);
        setLocation((dim.width - this.fw) / 2,(dim.height - this.fh) / 2);             
    }          
}

