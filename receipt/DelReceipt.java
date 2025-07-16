package receipt;

import utility.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.text.*;

public class DelReceipt extends JDialog implements ActionListener
{
    //Label for Heading
    JLabel head;

    //Labels
    JLabel lreno,lredate,lrerollno,lreamt,lredetail;

    //Textfields
    JTextField tredate,trerollno,treamt,tredetail;

    //Comboxes
    JComboBox bxreno;

    //Buttons
    JButton del,back;

    //DB
    DB ref;

    //temp storage
    int reno,rerollno,reamt;
    String redetail;
    Date redate;
    int cnt;

    //Font for label and button
    Font lfont,tfont;

    //To store rows fetched from table
    ResultSet rs;
    
    SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");

    public DelReceipt(JFrame frm,String title,boolean state,DB ref)
    {
        super(frm,"Delete Receipt",state);

        this.ref=ref;

        //heading
        head=new JLabel("Delete Receipt");
        head.setFont(new Font("Times New Roman", Font.BOLD, 40));

        //crating labels  lreno,lredate,lrerollno,lreamt,lretype,lredetail,lreacct
        lreno=new JLabel("Receipt No");
        lredate=new JLabel("Date");
        lrerollno=new JLabel("Roll No");
        lreamt=new JLabel("Amount");
        lredetail=new JLabel("Details");

        //creating textfields;
        tredate=new JTextField(10);
        trerollno=new JTextField(10);
        treamt=new JTextField(10);
        tredetail=new JTextField(20);

        tredate.setEditable(false);
        trerollno.setEditable(false);
        treamt.setEditable(false);
        tredetail.setEditable(false);

        //creating textfield treno,treamt;
        bxreno=new JComboBox();
        //adding receipt numbers to Combobox;
        try
        {
            rs=ref.predis.executeQuery();
            while(rs.next())
            {
                bxreno.addItem(""+rs.getInt(1));
            }
        }
        catch(Exception e){}

        bxreno.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                reno = Integer.parseInt(""+bxreno.getSelectedItem());

                try
                {
                    ref.preser.setInt(1,reno);

                    rs=ref.preser.executeQuery();

                    if(rs.next())
                    {
                        redate=rs.getDate(2);
                        String tmpdate= dateFormat.format(redate);
                        tredate.setText(tmpdate);

                        trerollno.setText(""+rs.getInt(3));
                        treamt.setText(""+rs.getInt(4));
                        tredetail.setText(rs.getString(6));

                          
                    }
                }
                catch(Exception e1){}
            }
        });
        


        //Creating Button 
        del=new JButton("DELETE");
        back=new JButton("BACK");

        del.addActionListener(this);
        back.addActionListener(this);

        //Creating fonts;
        lfont = new Font("Seriff", Font.BOLD, 15);
        tfont = new Font("Seriff", Font.PLAIN, 15);

        lreno.setFont(lfont);
        lredate.setFont(lfont);
        lrerollno.setFont(lfont);
        lreamt.setFont(lfont);
        lredetail.setFont(lfont);

        bxreno.setFont(tfont);
        tredate.setFont(tfont);
        trerollno.setFont(tfont);
        treamt.setFont(tfont);
        tredetail.setFont(tfont);

        setLayout(null);

        head.setBounds(100, 30, 300, 45);

        lreno.setBounds(50,110,100,30);
        lredate.setBounds(50,160,100,30);;
        lrerollno.setBounds(50,210,100,30);
        lreamt.setBounds(50,260,100,30);
        lredetail.setBounds(50,310,100,30);

        bxreno.setBounds(200,110,150,30);
        tredate.setBounds(200,160,150,30);
        trerollno.setBounds(200,210,150,30);
        treamt.setBounds(200,260,150,30);
        tredetail.setBounds(200,310,150,30);

        del.setBounds(100, 390, 100, 30);
        back.setBounds(300, 390, 100, 30);

        add(head);
        add(lreno);
        add(bxreno);
        add(lredate);
        add(tredate);
        add(lrerollno);
        add(trerollno);
        add(lreamt);
        add(treamt);
        add(lredetail);
        add(tredetail);

        add(del);
        add(back);

        setSize(500,500);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setVisible(true);
        
    }

    public void actionPerformed(ActionEvent e)
    {
        JButton b=(JButton)e.getSource();

        if(b==del)
        {
            try
            {
                ref.predel.setInt(1,reno);

                ref.predel.executeUpdate();

                //refreshing receipt no combobox
                rs=ref.predis.executeQuery();
                while(rs.next())
                {
                    bxreno.addItem(""+rs.getInt(1));
                }
            }
            catch(Exception e1){}

            setVisible(false);
        }

        if(b==back)
        {
            setVisible(false);
        }
    }
}

