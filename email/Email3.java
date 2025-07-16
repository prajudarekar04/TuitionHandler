package email;

import utility.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Email3 extends JDialog implements ActionListener
{
    JLabel l1;
    JButton sel,send;

    String path;
    String to,from,subject,message;

    public Email3(JFrame prnt,String title,boolean state)
    {
        super(prnt,title,state);
                
        sel=new JButton("SELECT");
        send=new JButton("SEND");

        sel.addActionListener(this);
        send.addActionListener(this);

        setLayout(new FlowLayout());

        add(sel);
        add(send);

        setVisible(true);
        setSize(200,200);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent e)
    {
        JButton b=(JButton)e.getSource();

        if(b==sel)
        {
            JFileChooser file = new JFileChooser();
            file.setMultiSelectionEnabled(true);
            file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            file.setFileHidingEnabled(false);
            if (file.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
            {
                File f = file.getSelectedFile();
                path = f.getPath();
            }
        }
        else
        {
            GEmailSender gEmailSender =new GEmailSender();

            to="prathameshbasapure321@gmail.com";
            from="prathameshb4544@gmail.com"; 
            subject="Trial Email";
            message="This is a example email";

            boolean b1 = gEmailSender.sendEmail(to, from, subject, message);

            if(b1)
            {
                System.out.println("Success");
            }
            else
            {
                System.out.println("Error");
            }

            System.exit(0);

        }
    }
}