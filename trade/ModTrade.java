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

public class ModTrade extends JDialog implements ActionListener, FocusListener {
    // heading label
    JLabel head;

    JLabel ltrid, ltrnm;

    JTextField ttrnm;

    JButton mod, back,clr;

    JComboBox jcb;

    // font for labels
    Font h = new Font("Times New Roman", Font.BOLD, 40);
    Font t = new Font("Seriff", Font.PLAIN, 15);
    Font l = new Font("Seriff", Font.BOLD, 15);
 int id;
        String name;
    int cnt;
    // to store rows fetched from table
    ResultSet rs;

    // to execute queries
    DB ref;

    int trid;
    String trnm;

    public ModTrade(JFrame frm, String title, boolean state, DB ref) {
        super(frm, "Mod Trade", state);

        this.ref = ref;

        // heading
        head = new JLabel("MOD TRADE");
        head.setFont(h);

        // creating labels
        ltrid = new JLabel("Trade ID");
        ltrnm = new JLabel("Trade Name");

        // setting font to all labels
        ltrid.setFont(l);
        ltrnm.setFont(l);

        ttrnm = new JTextField(10);
        ttrnm.setFont(t);
        ttrnm.addFocusListener(this);
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

        // open combo box on clicking on id
        jcb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {

                    mod.setEnabled(false);

                    ttrnm.setEditable(true);
                    
                    trid = Integer.parseInt("" + jcb.getSelectedItem());
                    ref.ptrser.setInt(1, trid);
                    rs = ref.ptrser.executeQuery();

                    System.out.println("hii");
                    if (rs.next()) {
                        System.out.println("bye");
                        ttrnm.setText(rs.getString(2));
                    }

                    System.out.println("hello");
                    
                } catch (Exception e1) {
                    System.out.println("" + e1);
                }

            }
        });

        // Jbutton object creation
        mod = new JButton("MODIFY");
        mod.setEnabled(false);
        back = new JButton("BACK");
        clr=new JButton("CLEAR");
        // registering action listener
        mod.addActionListener(this);
        back.addActionListener(this);
        clr.addActionListener(this);
        // default layout set to null
        setLayout(null);

        // setting bounds
        head.setBounds(100, 30, 300, 40);
        ltrid.setBounds(50, 110, 150, 30);
        ltrnm.setBounds(50, 160, 150, 30);

        jcb.setBounds(250, 110, 150, 30);
        ttrnm.setBounds(250, 160, 150, 30);

        mod.setBounds(40, 230, 100, 30);
        clr.setBounds(190,230,100,30);
        back.setBounds(340, 230, 100, 30);
        add(head);
        add(ltrid);
        add(jcb);
        add(ltrnm);
        add(ttrnm);
        add(mod);
        add(back);
        add(clr);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setSize(500, 400);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource(); // trid,trnm
       
        if (b == mod) {
            try {
                // fetching data from all textfield
                id = Integer.parseInt("" + trid);
                //name = ttrnm.getText();

                // giving data to sql query
                ref.ptrmod.setInt(2, id);
                ref.ptrmod.setString(1, name);

                ref.ptrmod.executeUpdate();

                setVisible(false);

            } catch (Exception e3) {
            }
        }
        if (b == back)
            setVisible(false);
        if(b==clr)
        {
            ttrnm.setText("");
            mod.setEnabled(false);
            ttrnm.setEditable(false);
            jcb.requestFocus();
        }
    }

    public void focusLost(FocusEvent e) {
        try{
            name = ttrnm.getText();
            if(ttrnm.getText().equals(""))
            {
                ttrnm.requestFocus();
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

    public void focusGained(FocusEvent e) {
    }
}