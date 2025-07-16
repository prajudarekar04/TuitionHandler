package college;

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

public class DelCollege extends JDialog implements ActionListener, FocusListener {
    // heading label
    JLabel head;

    JLabel lcid, lcnm,lclmsg;

    JTextField tcnm;

    JButton del, back;

    JComboBox jcb;

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

    public DelCollege(JFrame frm, String title, boolean state, DB ref) {
        super(frm, "Del College", state);

        this.ref = ref;

        // heading
        head = new JLabel("Delete College");
        head.setFont(h);

        // creating labels
        lcid = new JLabel("College ID");
        lcnm = new JLabel("College Name");
        lclmsg=new JLabel("");
        lclmsg.setVisible(false);

        // setting font to all labels
        lcid.setFont(s);
        lcnm.setFont(s);
        lclmsg.setFont(s);

        tcnm = new JTextField(10);
        tcnm.addFocusListener(this);
        tcnm.setFont(r);
        tcnm.setEditable(false);

        jcb = new JComboBox();
        jcb.setFont(r);

        try {

            rs = ref.pcldis.executeQuery();
            while (rs.next()) {

                jcb.addItem(""+ rs.getInt(1));
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

        jcb.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {

                try 
                {
                    cid = Integer.parseInt("" + jcb.getSelectedItem());
                    ref.pclser.setInt(1, cid);
                    rs = ref.pclser.executeQuery();
                    
                    if (rs.next()) 
                    {
                        tcnm.setText(rs.getString(2));
                    }

                    del.setEnabled(true);

                } catch (Exception e1) {
                    System.out.println("" + e1);
                }

            }
        });

        

        // default layout set to null
        setLayout(null);

        // setting bounds
        head.setBounds(100, 30, 300, 40);

        lcid.setBounds(50, 110, 150, 30);
        lcnm.setBounds(50, 160, 150, 30);
        lclmsg.setBounds(100,210,300,30);

        jcb.setBounds(250, 110, 150, 30);
        tcnm.setBounds(250, 160, 150, 30);

        del.setBounds(100, 260, 100, 30);
        back.setBounds(300, 260, 100, 30);

        add(head);
        add(lcid);
        add(jcb);
        add(lcnm);
        add(tcnm);
        add(lclmsg);
        add(del);
        add(back);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setSize(500, 400);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource(); // cid,cnm
        String name;
        if (b == del) 
        {
            try
            {
                String nm=tcnm.getText();

                ref.pstsercl.setString(1,nm);
                rs=ref.pstsercl.executeQuery();

                if(!rs.next())
                {
                    ref.pcldel.setInt(1,cid);
                    ref.pcldel.executeUpdate();
                    
                    setVisible(false);
                }   
                else
                {
                    lclmsg.setText("College "+nm+" can't be deleted"); 
                    lclmsg.setForeground(Color.RED); 
                    lclmsg.setVisible(true);
                }
                
            }
            catch(Exception e1){}
        }
        if (b == back)
            setVisible(false);;
    }

    public void focusLost(FocusEvent e) {
        del.setEnabled(true);
    }

    public void focusGained(FocusEvent e) {
    }


}