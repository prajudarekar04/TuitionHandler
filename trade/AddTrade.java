package trade;

import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.table.*;

import utility.*;

import java.sql.*;

public class AddTrade extends JDialog implements ActionListener, FocusListener {
    // heading label
    JLabel head;

    JLabel ltrid, ltrnm;

    JTextField ttrid, ttrnm;

    JButton add, back,clr;
    int id;String name;
    // font for labels
    Font h = new Font("Times New Roman", Font.BOLD, 40);
    Font t;
    Font l;

    int cnt;
    // to store rows fetched from table
    ResultSet rs;

    // to execute queries
    DB ref;

    public AddTrade(JFrame frm, String title, boolean state, DB ref) {
        super(frm, "ADD Trade", state);

        this.ref = ref;

        // heading
        head = new JLabel("ADD TRADE");
        head.setFont(h);

        t = new Font("Seriff", Font.PLAIN, 15);
        l = new Font("Seriff", Font.BOLD, 15);

        // creating labels
        ltrid = new JLabel("Trade ID");
        ltrnm = new JLabel("Trade Name");

        // setting font to all labels
        ltrid.setFont(l);
        ltrnm.setFont(l);

        // id text field
        ttrid = new JTextField(10);
        ttrid.setFont(t);
        ttrid.setEditable(false); // cant edit tbtrid

        // id will be automatically calculated based on count of ids in the table
        try 
        {
            rs = ref.ptrcnt.executeQuery();

            if (rs.next())
                cnt = rs.getInt(1);

            cnt += 1;

            ttrid.setText("" + cnt);
        } catch (Exception e) {}

        // textfield object creation
        ttrnm = new JTextField(10);
        ttrnm.setFont(t);
        ttrnm.addFocusListener(this);

        // Jbutton object creation
        add = new JButton("ADD");
        add.setEnabled(false);
        back = new JButton("BACK");
        clr=new JButton("CLEAR");
        // registering action listener
        add.addActionListener(this);
        back.addActionListener(this);
        clr.addActionListener(this);

        // default layout set to null
        setLayout(null);

        // setting bounds
        head.setBounds(100, 30, 300, 40);
        ltrid.setBounds(50, 110, 150, 30);
        ltrnm.setBounds(50, 160, 150, 30);

        ttrid.setBounds(250, 110, 150, 30);
        ttrnm.setBounds(250, 160, 150, 30);

        add.setBounds(40, 230, 100, 30);
        clr.setBounds(190,230,100,30);
        back.setBounds(340, 230, 100, 30);
        
        add(head);
        add(ltrid);
        add(ttrid);
        add(ltrnm);
        add(ttrnm);
        add(add);
        add(back);
        add(clr);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setSize(500, 400);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource(); // trid,trnm
        
        if (b == add) {
            try {
                // fetching data from all textfield
                id = Integer.parseInt("" + ttrid.getText());
               // name = ttrnm.getText();

                // giving data to sql query
                ref.ptradd.setInt(1, id);
                ref.ptradd.setString(2, name);
                ref.ptradd.setInt(3, 1);

                ref.ptradd.executeUpdate();

                setVisible(false);

            } catch (Exception e3) {}
        }
        if (b == back)
            setVisible(false);
        if(b==clr)
        {
            ttrnm.setText("");
            ttrnm.requestFocus();
            add.setEnabled(false);
        }
    }

    public void focusLost(FocusEvent e) {

        try{
            name=ttrnm.getText();
             if(ttrnm.getText().equals(""))
            {
                System.out.println("holl");
                ttrnm.requestFocus();
                return;
            }
            else
            {
                add.setEnabled(true);
                add.requestFocus();
            }

        }
        catch(Exception e1){}
    }

    public void focusGained(FocusEvent e) {
    }

}