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
public class DelBatch extends JDialog implements ActionListener,FocusListener
{
    JLabel head;
    JLabel lbtid, lbtnm, lbtstdate, lbteddate, lbttime,lbtmesg;

    JTextField  tbtnm, tbttime;

    JComboBox cbtid;

    NJDatePicker tbtstdate, tbteddate;

    JButton del,back;

    Font font1;

    DB ref;

    ResultSet rs;

    String nm,time,bid;
    Date sdate,edate;    
    int cnt,id;

    SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");

    public DelBatch(JFrame frm,String title,boolean state,DB ref) 
    {
        super(frm,title,state);

        this.ref=ref;

        head = new JLabel("DEL BATCH");
        head.setFont(new Font("Times New Roman", Font.BOLD, 40));
        
        lbtid = new JLabel("ID");
        lbtnm = new JLabel("Name");
        lbtstdate = new JLabel("Start Date");
        lbteddate = new JLabel("End Date");
        lbttime = new JLabel("Timing");
        lbtmesg=new JLabel("");

        lbtmesg.setVisible(false);

        cbtid=new JComboBox();

        tbtnm = new JTextField(10);
        tbttime = new JTextField(10);
        tbtnm.addFocusListener(this);
        tbttime.addFocusListener(this);
        tbtnm.setEditable(false);
        tbttime.setEditable(false);
        tbtstdate = new NJDatePicker();
        tbteddate = new NJDatePicker();
    
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
        del = new JButton("DELETE");
        back = new JButton("BACK");
        del.setEnabled(false);

        del.addActionListener(this);
        back.addActionListener(this);

        font1=new Font("Seriff", Font.BOLD, 15);

        lbtid.setFont(font1);
        lbtnm.setFont(font1);
        lbtstdate.setFont(font1);
        lbteddate.setFont(font1);
        lbttime.setFont(font1);
        lbtmesg.setFont(font1);
        setLayout(null);

         
         try{
            rs=ref.pbtdis.executeQuery();
            while(rs.next())
            {
                cbtid.addItem(""+rs.getInt(1));
            }
         }
         catch(Exception e){
            System.out.println(""+e);
         }

         cbtid.addActionListener(new ActionListener() 
         {
            public void actionPerformed(ActionEvent e)
            {
                del.setEnabled(true);
                id=Integer.parseInt(""+cbtid.getSelectedItem());
                try{
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
                catch(Exception e1){
                    System.out.println(""+e);
                }
            }
         });

        
        head.setBounds(130, 30, 300, 40);
        lbtid.setBounds(50, 110, 150, 30);
        lbtnm.setBounds(50, 160, 150, 30);
        lbtstdate.setBounds(50, 210, 150, 30);
        lbteddate.setBounds(50, 260, 150, 30);
        lbttime.setBounds(50, 310, 150, 30);
        lbtmesg.setBounds(150,360,300,30);

        cbtid.setBounds(250, 110, 150, 30);
        tbtnm.setBounds(250, 160, 150, 30);
        tbtstdate.setBounds(250, 210, 200, 30);
        tbteddate.setBounds(250, 260, 200, 30);
        tbttime.setBounds(250, 310, 150, 30);


        del.setBounds(100, 440, 100, 30);
        back.setBounds(300, 440, 100, 30);

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
        add(lbtmesg);
        add(del);
        add(back);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setSize(500, 550);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e)
    {
        JButton b=(JButton)e.getSource();
        if(b==del)
        {
            nm=tbtnm.getText();
            try
            {
                ref.pstserbt.setString(1,nm);
                rs=ref.pstserbt.executeQuery();

                if(!rs.next())
                {
                    ref.pbtdel.setInt(1,id);
                    ref.pbtdel.executeUpdate();
                    setVisible(false);
                }  
                else
                { 
                    lbtmesg.setText("Batch "+nm+" can't be deleted"); 
                    lbtmesg.setForeground(Color.RED); 
                    lbtmesg.setVisible(true);
                }  
            }
            catch(Exception e1){
                System.out.println(""+e1);
            }
        }
        else
            setVisible(false);
    }

    public void focusLost(FocusEvent e){}

    public void focusGained(FocusEvent e){}

}