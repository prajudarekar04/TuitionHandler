package city;

import utility.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ModCity extends JDialog implements ActionListener,FocusListener
{
    JLabel head;
    JLabel lctnm,lctid;

    JTextField tctnm;

    JComboBox tctid;

    JButton mod,back,clr;

    String cnm;
    int cid,cnt;

    DB ref;
    ResultSet rs;

    Font font1,font2;

    public ModCity(JFrame frm,String title,boolean state,DB ref)
    {
        super(frm,"Modify City",state);
        this.ref=ref;

        head=new JLabel("MODIFY CITY");
        head.setFont(new Font("Times New Roman",Font.BOLD,30));

        font1=new Font("Seriff",Font.BOLD,15);

        lctnm=new JLabel("City Name");
        lctid=new JLabel("City id");

        lctnm.setFont(font1);
        lctid.setFont(font1);


       
        tctid=new JComboBox();

        tctnm=new JTextField(10);
        tctnm.addFocusListener(this);

        font2=new Font("Seriff",Font.PLAIN,15);
        tctid.setFont(font2);
        tctnm.setFont(font2);
        //for adding id's of city in combo box
        try
        {
            rs=ref.pctdis.executeQuery();
            while(rs.next())
            {
                tctid.addItem(""+rs.getInt(1));
            }
        }
        catch(Exception e1){}

        //after selecting the id from combo box
        tctid.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                cid=Integer.parseInt(""+tctid.getSelectedItem());
                try
                {
                    mod.setEnabled(false);
                    //searching that id int the table
                    ref.pctser.setInt(1,cid);
                    rs=ref.pctser.executeQuery();
                    if(rs.next())
                    {
                        //get that from table
                        cnm=rs.getString(2);
                        cid=rs.getInt(1);
                        //setting the textfield
                        tctid.setSelectedItem(""+cid);
                        tctnm.setText(cnm);
                    }
                }
                catch(Exception e6){System.out.println("not mod");}
            }
        });

        mod=new JButton("MODIFY");
        clr=new JButton("CLEAR");
        back=new JButton("BACK");

        mod.addActionListener(this);
        clr.addActionListener(this);
        back.addActionListener(this);

        setLayout(null);

        head.setBounds(130,30,300,40);

        lctid.setBounds(60,100,120,40);
        lctnm.setBounds(60,150,120,40);

        tctid.setBounds(250,110,150,30);
        tctnm.setBounds(250,160,150,30);

        mod.setBounds(40,230,100,30);
        clr.setBounds(190,230,100,30);
        back.setBounds(340,230,100,30);

        add(head);
        add(lctnm);
        add(tctnm);
        add(lctid);
        add(tctid);
        add(mod);
        add(clr);
        add(back);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500,400);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        JButton b=(JButton)e.getSource();
        if(b==mod)
        {
           // cnm=tctnm.getText();
            try
            {
                rs = ref.pctcnt.executeQuery();
                    if (rs.next())
                        cnt = rs.getInt(1);
                    cnt+=1;
                //inserting modified values into the table
                ref.pctmod.setInt(2,cid);
                ref.pctmod.setString(1,cnm);
                ref.pctmod.executeUpdate();

                setVisible(false);   
            }
            catch(Exception e1)
            {
                System.out.println(e1.getMessage());
            }
        }
        if(b==clr)
        {
            //clearing the text
            tctnm.setText("");
            mod.setEnabled(false);
        }
        if(b==back)
            setVisible(false);
    }
    public void focusLost(FocusEvent e)
    {
        try{
           
            cnm=tctnm.getText();
            if(cnm.equals(""))
            {
                  tctnm.requestFocus();
                  return;
            }
        }
        catch(Exception e1)
        {
        }
        JTextField t=(JTextField)e.getSource();
        if(t.equals(tctnm))
        {
            mod.setEnabled(true);
        }
    }
    public void focusGained(FocusEvent e)
    {}
}
