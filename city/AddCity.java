package city;

import utility.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddCity extends JDialog implements ActionListener,FocusListener
{
    JLabel head;
    JLabel lctnm,lctid;

    JTextField tctnm,tctid;

    JButton add,back,clr;

    String cnm;
    int cid,cnt;

    DB ref;

    Font font1,font2;
    ResultSet rs;

    public AddCity(JFrame frm,String title,boolean state,DB ref)
    {
        super(frm,title,state);

        this.ref=ref;

        head=new JLabel("ADD CITY");
        head.setFont(new Font("Times New Roman",Font.BOLD,40));

        font1=new Font("Seriff",Font.BOLD,15);

        lctnm=new JLabel("City Name");
        lctid=new JLabel("City id");

        lctnm.setFont(font1);
        lctid.setFont(font1);

        tctnm=new JTextField(10);
        tctid=new JTextField(10);
        tctid.setEditable(false);
        tctnm.addFocusListener(this);
        font2=new Font("Seriff",Font.PLAIN,15);
        tctnm.setFont(font2);
        tctid.setFont(font2);

        add=new JButton("ADD");
        clr=new JButton("CLEAR");
        back=new JButton("BACK");

        add.setEnabled(false);

        add.addActionListener(this);
        clr.addActionListener(this);
        back.addActionListener(this);

        try
        {
            rs=ref.pctcnt.executeQuery();
            while(rs.next())
            {
                cnt=rs.getInt(1);
                cnt++;
                tctid.setText(""+cnt);
            }
        }
        catch(Exception e){}

        setLayout(null);

        head.setBounds(130,30,300,40);

        lctid.setBounds(70,100,120,40);
        lctnm.setBounds(70,150,120,40);

        tctid.setBounds(250,110,150,30);
        tctnm.setBounds(250,160,150,30);

        add.setBounds(40,230,100,30);
        clr.setBounds(190,230,100,30);
        back.setBounds(340,230,100,30);

        add(head);
        add(lctnm);add(tctnm);
        add(lctid);add(tctid);
        add(add);add(clr);add(back);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500,400);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        JButton b=(JButton)e.getSource();
        if(b==add)
        {
            try
            {
                //taking data from textfield
                cid=Integer.parseInt(tctid.getText());
                //cnm=tctnm.getText();
                //executing the query
                ref.pctadd.setInt(1,cid);
                ref.pctadd.setString(2,cnm);
                ref.pctadd.setInt(3,1);
                ref.pctadd.executeUpdate();
            }
            catch(Exception e1)
            {
                System.out.println(e1.getMessage());
            }

            setVisible(false);
        }
        if(b==clr)
        {
            //clearing the textfields
            tctnm.setText("");
            add.setEnabled(false);
            tctnm.requestFocus();
        }
        if(b==back)
            setVisible(false);
    }
    public void focusLost(FocusEvent e){
        try{
           
            cnm=tctnm.getText();
            if(cnm.equals(""))
            {
                  tctnm.requestFocus();
                  return;
            }
            else
            {
                add.setEnabled(true);
                add.requestFocus();
            }
        }
        catch(Exception e1)
        {
        }
    }
    public void focusGained(FocusEvent e){}
}

