package trade;

import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.table.*;

import java.sql.*;

import utility.*;
import utility.DB;

public class DelTrade extends JDialog implements ActionListener {
    // heading label
    JLabel head;

    JLabel ltrid, ltrnm,ltrmsg;

    JTextField ttrnm;

    JButton del, back;

    JComboBox jcb;

    // font for labels
    Font h = new Font("Times New Roman", Font.BOLD, 40);
    Font t = new Font("Seriff", Font.PLAIN, 15);
    Font l = new Font("Seriff", Font.BOLD, 15);

    int cnt;
    // to store rows fetched from table
    ResultSet rs;

    // to execute queries
    DB ref;

    int trid;
    String trnm;

    public DelTrade(JFrame frm, String title, boolean state, DB ref) 
    {
        super(frm, "Del Trade", state);

        this.ref = ref;

        // heading
        head = new JLabel("Delete Trade");
        head.setFont(h);

        // creating labels
        ltrid = new JLabel("Trade ID");
        ltrnm = new JLabel("Trade Name");
        ltrmsg=new JLabel("");
        ltrmsg.setVisible(false);

        // setting font to all labels
        ltrid.setFont(l);
        ltrnm.setFont(l);
        ltrmsg.setFont(l);

        ttrnm = new JTextField(10);
        ttrnm.setFont(t);
        ttrnm.setEditable(false);
        jcb = new JComboBox();
        jcb.setFont(t);

        try {

            rs = ref.ptrdis.executeQuery();
            while (rs.next()) {

                jcb.addItem("" + rs.getInt(1));
            }
        } catch (Exception e) {
        }

        // Jbutton object creation
        del = new JButton("DELETE");
        back = new JButton("BACK");

        // registering action listener
        del.addActionListener(this);
        back.addActionListener(this);

        del.setEnabled(false);

        //open combo box on clicking on id

        jcb.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {

                try 
                {
                    trid = Integer.parseInt("" + jcb.getSelectedItem());
                    ref.ptrser.setInt(1, trid);
                    rs = ref.ptrser.executeQuery();

                    if (rs.next()) 
                    {
                        ttrnm.setText(rs.getString(2));
                    }

                    del.setEnabled(true);;
                } 
                catch (Exception e1) {}

            }
        });
        
        // default layout set to null
        setLayout(null);

        // setting bounds
        head.setBounds(100, 30, 300, 40);
        ltrid.setBounds(50, 110, 150, 30);
        ltrnm.setBounds(50, 160, 150, 30);
        ltrmsg.setBounds(100,210,300,30);

        jcb.setBounds(250, 110, 150, 30);
        ttrnm.setBounds(250, 160, 150, 30);

        del.setBounds(100, 250, 100, 30);
        back.setBounds(300, 250, 100, 30);

        add(head);
        add(ltrid);
        add(jcb);
        add(ltrnm);
        add(ttrnm);
        add(ltrmsg);
        add(del);
        add(back);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setSize(500, 400);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource(); // trid,trnm
        int id;
        String name;
        if (b == del) 
        {
            try
            {
                String nm=ttrnm.getText();
                int trid=Integer.parseInt(""+jcb.getSelectedItem());

                ref.pstsertr.setString(1,nm);
                rs=ref.pstsertr.executeQuery();

                if(!rs.next())
                {
                    ref.ptrdel.setInt(1,trid);
                    ref.ptrdel.executeUpdate();
                    
                    setVisible(false);
                }   
                else
                {
                    ltrmsg.setText("Trade "+nm+" can't be deleted"); 
                    ltrmsg.setForeground(Color.RED); 
                    ltrmsg.setVisible(true);
                }
                
            }
            catch(Exception e1){}
        }
        if (b == back)
            setVisible(false);
    }
}