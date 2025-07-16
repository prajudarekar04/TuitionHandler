package college;

import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

import java.awt.*;

import utility.*;

public class ModCollege extends JDialog implements ActionListener, FocusListener {
    // heading label
    JLabel head;

    JLabel lcid, lcnm;

    JTextField tcnm;

    JButton mod, back,clr;

    JComboBox jcb;
    String name;
    // font for labels
    Font s = new Font("Seriff", Font.BOLD, 15);
    Font h = new Font("Times New Roman", Font.BOLD, 40);
    Font r = new Font("Seriff", Font.PLAIN, 15);

    int cnt;
    // to store rows fetched from table
    ResultSet rs;

    // to execute queries
    DB ref;

    int cid;
    String cnm;

    public ModCollege(JFrame frm, String title, boolean state, DB ref) {
        super(frm, "Mod College", state);

        this.ref = ref;

        // heading
        head = new JLabel("Modify College");
        head.setFont(h);

        // creating labels
        lcid = new JLabel("College ID");
        lcnm = new JLabel("College Name");

        // setting font to all labels
        lcid.setFont(s);
        lcnm.setFont(s);

        tcnm = new JTextField(10);
        tcnm.addFocusListener(this);
        tcnm.setFont(r);

        jcb = new JComboBox();
        jcb.setFont(r);

        try {

            rs = ref.pcldis.executeQuery();
            while (rs.next()) {

                jcb.addItem(""+ rs.getInt(1));
            }
        } catch (Exception e) {
        }

        jcb.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {

                try 
                {
                    mod.setEnabled(false);

                    cid = Integer.parseInt("" + jcb.getSelectedItem());
                    ref.pclser.setInt(1, cid);
                    rs = ref.pclser.executeQuery();
                    if (rs.next()) {
                        tcnm.setText(rs.getString(2));
                    }

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
        clr.addActionListener(this);;
        // default layout set to null
        setLayout(null);

        // setting bounds
        head.setBounds(100, 30, 300, 40);
        lcid.setBounds(50, 110, 150, 30);
        lcnm.setBounds(50, 160, 150, 30);

        jcb.setBounds(250, 110, 150, 30);
        tcnm.setBounds(250, 160, 150, 30);

        mod.setBounds(50, 240, 100, 30);
        clr.setBounds(200, 240, 100, 30);
        back.setBounds(350, 240, 100, 30);
        add(head);
        add(lcid);
        add(jcb);
        add(lcnm);
        add(tcnm);
        add(mod);
        add(back);
        add(clr);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setSize(500, 400);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource(); // cid,cnm
        
        if (b == mod) 
        {
            try {
                // fetching data from all textfield
              //  name = tcnm.getText();

                // giving data to sql query
                
                ref.pclmod.setString(1, name);
                ref.pclmod.setInt(2, cid);

                ref.pclmod.executeUpdate();

                setVisible(false);

            } catch (Exception e3) {}
        }
        if (b == back)
            setVisible(false);
        if(b==clr)
        {
            tcnm.setText("");
            mod.setEnabled(false);
        }
    }

    public void focusLost(FocusEvent e) {
        try{
           name=tcnm.getText();
            if(tcnm.getText().equals(""))
            {
                tcnm.requestFocus();
                return;
            }
        }
        catch(Exception e1){}
        mod.setEnabled(true);
    }

    public void focusGained(FocusEvent e) {
    }


}