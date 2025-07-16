package city;

import utility.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DelCity extends JDialog implements ActionListener
{
    JLabel head;
    JLabel lctnm,lctid,lctmsg;

    JTextField tctnm;
    JComboBox tctid;

    JButton del,back;

    String cnm;
    int cid,cnt;

    DB ref;

    Font font1,font2;

    ResultSet rs;

    public DelCity(JFrame frm,String title,boolean state,DB ref)
    {
        super(frm,"Delete City",state);

        this.ref=ref;

        head=new JLabel("DELETE CITY");
        head.setFont(new Font("Times New Roman",Font.BOLD,30));

        font1=new Font("Seriff",Font.BOLD,15);

        lctnm=new JLabel("City Name");
        lctid=new JLabel("City Id");
        lctmsg=new JLabel("");
        lctmsg.setVisible(false);

        lctnm.setFont(font1);
        lctid.setFont(font1);
        lctmsg.setFont(font1);

        tctnm=new JTextField(10);
        tctnm.setEditable(false);
        tctid=new JComboBox();

        font2=new Font("Seriff",Font.PLAIN,15);
        tctnm.setFont(font2);
        tctid.setFont(font2);

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


        del=new JButton("DELETE");
        back=new JButton("BACK");

        del.addActionListener(this);
        back.addActionListener(this);
        del.setEnabled(false);

        //after selecting the id from combo box
        tctid.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                cid=Integer.parseInt(""+tctid.getSelectedItem());
                try
                {
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

                        del.setEnabled(true);
                    }
                }
                catch(Exception e6){System.out.println("not del");}
            }
        });

        

        setLayout(null);

        head.setBounds(130,20,300,40);

        lctid.setBounds(60,100,120,40);
        lctnm.setBounds(60,150,120,40);
        lctmsg.setBounds(150,200,300,30);

        tctid.setBounds(250,100,150,30);
        tctnm.setBounds(250,150,150,30);

        del.setBounds(100,250,100,30);
        back.setBounds(300,250,100,30);

        add(head);
        add(lctnm);
        add(tctnm);
        add(lctid);
        add(lctmsg);
        add(tctid);
        add(del);
        add(back);

        addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
            }
		});
        setSize(500,400);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        JButton b=(JButton)e.getSource();
        if(b==del)
        {
            try
            {
                String nm=tctnm.getText();

                ref.pstserct.setString(1,nm);
                rs=ref.pstserct.executeQuery();

                if(!rs.next())
                {
                    ref.pctdel.setInt(1,cid);
                    ref.pctdel.executeUpdate();
                    
                    setVisible(false);
                }   
                else
                {
                    lctmsg.setText("City "+nm+" can't be deleted"); 
                    lctmsg.setForeground(Color.RED); 
                    lctmsg.setVisible(true);
                }
                
            }
            catch(Exception e1)
            {
                System.out.println("hII"+e1.getMessage());
            }
        }
        if(b==back)
            setVisible(false);
    }
}

