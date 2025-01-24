// package batch;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;

// import utility.*;
package batch;

import java.awt.event.*;
import utility.*;
import java.sql.*;
import java.text.SimpleDateFormat;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.table.*;
public class ModBatch extends JDialog implements ActionListener,FocusListener
{
    JLabel head;
    JLabel lbtid, lbtnm, lbtstdate, lbteddate, lbttime;

    JTextField  tbtnm, tbttime;

    JComboBox cbtid;

    NJDatePicker tbtstdate, tbteddate;

    JButton mod, clr, back;

    Font font1;

    DB ref;

    ResultSet rs;

    String nm,time,bid;
    Date sdate,edate,d1,d2;
    int cnt,id;

    SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");

    public ModBatch(JFrame frm,String title,boolean state,DB ref) 
    {
        super(frm,"MOD Batch",state);

        this.ref=ref;

        head = new JLabel("MOD BATCH");
        head.setFont(new Font("Times New Roman", Font.BOLD, 40));
        
        lbtid = new JLabel("ID");
        lbtnm = new JLabel("Name");
        lbtstdate = new JLabel("Start Date");
        lbteddate = new JLabel("End Date");
        lbttime = new JLabel("Timing");

        cbtid=new JComboBox();

        tbtnm = new JTextField(10);
        tbttime = new JTextField(10);

        tbtnm.addFocusListener(this);
        tbttime.addFocusListener(this);
        tbtnm.setEditable(false);
        tbttime.setEditable(false);


    
        tbtstdate = new NJDatePicker();
        tbteddate = new NJDatePicker();
        tbtstdate.dateField.addFocusListener(this);
        tbteddate.dateField.addFocusListener(this);


        tbtstdate.dp.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                sdate=(Date)tbtstdate.getSqlDate();
            }
        });

        tbteddate.dp.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                edate=(Date)tbteddate.getSqlDate();
            }
        });

        mod = new JButton("MODIFY");
        clr = new JButton("CLEAR");
        back = new JButton("BACK");

        mod.addActionListener(this);
        clr.addActionListener(this);
        back.addActionListener(this);
        mod.setEnabled(false);

        font1=new Font("Seriff", Font.BOLD, 15);

        lbtid.setFont(font1);
        lbtnm.setFont(font1);
        lbtstdate.setFont(font1);
        lbteddate.setFont(font1);
        lbttime.setFont(font1);

        setLayout(null);

         
        try
        {
           rs=ref.pbtdis.executeQuery();
           while(rs.next())
           {
               cbtid.addItem(""+rs.getInt(1));
           }
        }
        catch(Exception e){}

        cbtid.addActionListener(new ActionListener() 
        {
           public void actionPerformed(ActionEvent e)
           {
               id=Integer.parseInt(""+cbtid.getSelectedItem());
               try
               {
                    mod.setEnabled(false);

                    tbtnm.setEditable(true);
                    tbttime.setEditable(true);

                    ref.pbtser.setInt(1,id);
                    rs=ref.pbtser.executeQuery();
               
                    if(rs.next())
                    {
                        id=rs.getInt(1);
                        nm=rs.getString(2);
                        sdate=rs.getDate(3);
                        edate=rs.getDate(4);
                        time=rs.getString(5);

                        String tmpdate1=dateFormat2.format(sdate);
                        String tmpdate2=dateFormat2.format(edate);

                        tbtstdate.dateField.setText(tmpdate1);
                        tbteddate.dateField.setText(tmpdate2);

                        cbtid.setSelectedItem(""+id);
                        tbtnm.setText(nm);
                        tbttime.setText(time);
                    }
               }
               catch(Exception e1){}
           }
        });

        head.setBounds(100, 30, 300, 40);
        lbtid.setBounds(50, 110, 150, 30);
        lbtnm.setBounds(50, 160, 150, 30);
        lbtstdate.setBounds(50, 210, 150, 30);
        lbteddate.setBounds(50, 260, 150, 30);
        lbttime.setBounds(50, 310, 150, 30);

        cbtid.setBounds(250, 110, 150, 30);
        tbtnm.setBounds(250, 160, 150, 30);
        tbtstdate.setBounds(250, 210, 200, 30);
        tbteddate.setBounds(250, 260, 200, 30);
        tbttime.setBounds(250, 310, 150, 30);

        mod.setBounds(50, 390, 100, 30);
        clr.setBounds(200, 390, 100, 30);
        back.setBounds(350, 390, 100, 30);

        add(head);
        add(lbtid);
        add(cbtid);
        add(lbtnm);
        add(tbtnm);
        add(lbtstdate);
        add(tbtstdate);
        add(lbteddate);
        add(tbteddate);
        add(lbttime);
        add(tbttime);
        add(mod);
        add(clr);
        add(back);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setSize(500, 500);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e)
    {
        JButton b=(JButton)e.getSource();
        if(b==mod)
        {
            nm=tbtnm.getText();
           // time=tbttime.getText();

            try
            {
                rs = ref.pbtcnt.executeQuery();
                if (rs.next())
                    cnt = rs.getInt(1);
                cnt+=1;
                
                ref.pbtmod.setString(1,nm);
                ref.pbtmod.setDate(2,sdate);
                ref.pbtmod.setDate(3,edate);
                ref.pbtmod.setString(4,time);
                ref.pbtmod.setInt(5,id);
                ref.pbtmod.executeUpdate();
            }
            catch(Exception e1){}

            setVisible(false);
        }
        
        if(b==clr)
        {
            //clrearing all textfields
            tbtstdate.dateField.setText("");
            tbteddate.dateField.setText("");
            tbttime.setText("");
            tbtnm.setText("");
            mod.setEnabled(false);
            tbtnm.setEditable(false);
            tbttime.setEditable(false);

            cbtid.requestFocus();
        }

        if(b==back)
        {
            setVisible(false);
        }
            //setVisible(false);

    }

    public void focusLost(FocusEvent e){
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
                mod.setEnabled(true);
                mod.requestFocus();
            }
        }
        catch(Exception e1){}
    
    }

    public void focusGained(FocusEvent e){}

   
}