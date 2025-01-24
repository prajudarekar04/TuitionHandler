package batch;

import java.awt.event.*;
import utility.*;
import java.sql.*;
import java.text.SimpleDateFormat;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.table.*;

public class AddBatch extends JDialog implements ActionListener, FocusListener 
{
    //heading label
    JLabel head;

    JLabel lbtid, lbtnm, lbtstdate, lbteddate, lbttime;

    JTextField tbtid, tbtnm, tbttime;

    //Date picker
    NJDatePicker tbtstdate, tbteddate;

    JButton add, clr, back;

    //font for labels
    Font lfont,tfont;

    //to store all data temporarily
    Date d1, d2;

    int sstate, id, cnt = 0;

    String name, sdate, edate, time;

    //to store rows fetched from table
    ResultSet rs;

    //to execute queries
    DB ref;

    AddBatch ab;
    public AddBatch(JFrame frm, String title, boolean state,DB ref) 
    {
        super(frm, "ADD Batch", state);

        this.ref=ref;

        ab=this;

        //heading
        head = new JLabel("ADD BATCH");
        head.setFont(new Font("Times New Roman", Font.BOLD, 40));

        //creating labels
        lbtid = new JLabel("ID");
        lbtnm = new JLabel("Name");
        lbtstdate = new JLabel("Start Date");
        lbteddate = new JLabel("End Date");
        lbttime = new JLabel("Timing");

        //font for labels and textfield
        lfont = new Font("Seriff", Font.BOLD, 15);
        tfont = new Font("Seriff", Font.PLAIN, 15);

        //setting font to all labels
        lbtid.setFont(lfont);
        lbtnm.setFont(lfont);
        lbtstdate.setFont(lfont);
        lbteddate.setFont(lfont);
        lbttime.setFont(lfont);

        //id text field
        tbtid = new JTextField(10);
        tbtid.setEditable(false);  //cant edit tbtid

        //id will be automatically calculated based on count of ids in the table
        try
        {
            rs = ref.pbtcnt.executeQuery();
            if (rs.next())
                cnt = rs.getInt(1);
            cnt+=1;
            tbtid.setText(""+cnt);
        }
        catch(Exception e){}
        

        //textfield object creation
        tbtnm = new JTextField(10);
        tbttime = new JTextField(10);
        tbttime.addFocusListener(this);
        tbtnm.addFocusListener(this);

        //datepicker object creation
        tbtstdate = new NJDatePicker();
        tbteddate = new NJDatePicker();
        tbtstdate.dateField.addFocusListener(this);
        tbteddate.dateField.addFocusListener(this);
        tbtid.setFont(tfont);
        tbtnm.setFont(tfont);
        tbtstdate.dateField.setFont(tfont);
        tbteddate.dateField.setFont(tfont);
        tbttime.setFont(tfont);

        //Jbutton object creation
        add = new JButton("ADD");
        add.setEnabled(false); // can,t access add button till all data is inputed
        clr = new JButton("CLR");
        back = new JButton("BACK");

        //registering action listener
        add.addActionListener(this);
        clr.addActionListener(this);
        back.addActionListener(this);

        
        add.setFont(lfont);
        clr.setFont(lfont);
        back.setFont(lfont);

        //default layout set to null
        setLayout(null);

        //setting bounds
        head.setBounds(100, 30, 300, 40);
        lbtid.setBounds(50, 110, 100, 30);
        lbtnm.setBounds(50, 160, 100, 30);
        lbtstdate.setBounds(50, 210, 100, 30);
        lbteddate.setBounds(50, 260, 100, 30);
        lbttime.setBounds(50, 310, 100, 30);

        tbtid.setBounds(200, 110, 150, 30);
        tbtnm.setBounds(200, 160, 150, 30);
        tbtstdate.setBounds(200, 210, 200, 30);
        tbteddate.setBounds(200, 260, 200, 30);
        tbttime.setBounds(200, 310, 150, 30);

        add.setBounds(50, 390, 100, 30);
        clr.setBounds(200, 390, 100, 30);
        back.setBounds(350, 390, 100, 30);

        //adding all componenets
        add(head);
        add(lbtid);
        add(tbtid);
        add(lbtnm);
        add(tbtnm);
        add(lbtstdate);
        add(tbtstdate);
        add(lbteddate);
        add(tbteddate);
        add(lbttime);
        add(tbttime);
        add(add);
        add(clr);
        add(back);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setSize(500, 500);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) 
    {
        JButton b = (JButton) e.getSource(); // tnm,snm,pno,mail,ayr;

        if (b == add) 
        {
            try 
            {
                //fetching data from all textfield
                id = Integer.parseInt("" + tbtid.getText());
              //  name = tbtnm.getText();
                //d1 = (Date) tbtstdate.getSqlDate();
               // d2 = (Date) tbteddate.getSqlDate();
               // time = tbttime.getText();

                //giving data to sql query
                ref.pbtadd.setInt(1,id);
                ref.pbtadd.setString(2,name);
                ref.pbtadd.setDate(3,d1);
                ref.pbtadd.setDate(4,d2);
                ref.pbtadd.setString(5,time);
                ref.pbtadd.setInt(6,1);
                ref.pbtadd.executeUpdate();

                setVisible(false);
                
            } 
            catch (Exception e3) {}
        }
        if(b==clr)
        {
            //clrearing all textfields
            tbtnm.setText("");
            tbtstdate.dateField.setText("");
            tbteddate.dateField.setText("");
            tbttime.setText("");
            add.setEnabled(false);
        }
        if(b==back)
        {
            setVisible(false);
        }
           
    }

    public void focusLost(FocusEvent e) 
    {
        try
        {
            //System.out.println("ho");
            name = tbtnm.getText();
            if(tbtnm.getText().equals(""))
            {
                // System.out.println("holl");
                tbtnm.requestFocus();
                return;
            }
        }
        catch(Exception e1){}
        
        try
        {
            d1 = (Date) tbtstdate.getSqlDate();
            if(tbtstdate.dateField.getText().equals(""))
            {
                tbtstdate.dateField.requestFocus();
                return;
            }
        }    
        catch(Exception e1){}

        try
        {
            d2 = (Date) tbteddate.getSqlDate();
            if(tbteddate.dateField.getText().equals(""))
            {
                tbteddate.dateField.requestFocus();
                return;
            }
        }
        catch(Exception e1){}

        try
        {
            time = tbttime.getText();
            if(tbttime.getText().equals(""))
            {
                tbttime.requestFocus();
                return;  
            }
            else
            {
                add.setEnabled(true);
                add.requestFocus();
            }
        }
        catch(Exception e1){}

        JTextField t=(JTextField)e.getSource();

        if(t==tbttime)
        {
            //after inputing last data field ....add button will be enabled
            add.setEnabled(true);
        }

    }

    public void focusGained(FocusEvent e) {
    }
}