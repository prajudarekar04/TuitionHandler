package college;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

import utility.DB;

public class AddCollege extends JDialog implements ActionListener, FocusListener 
{
    JLabel head;

    JLabel lcid, lcnm;

    JTextField tcid, tcnm;

    JButton add, clr, back;

    DB ref;

    // Temparary storage
    int cid;

    String conm;

    ResultSet rs;

    // Fonts
    Font lfont, tfont;

    public AddCollege(JFrame frm, String title, boolean state, DB ref) 
    {
        super(frm, "ADD College", state);

        this.ref = ref;

        head = new JLabel("ADD COLLEGE");
        head.setFont(new Font("Times New Roman", Font.BOLD, 40));

        lcid = new JLabel("ID");
        lcnm = new JLabel("College Name");

        tcid = new JTextField(10);
        tcid.setEditable(false);
        tcnm = new JTextField(10);
        tcnm.addFocusListener(this);

        // setting fonts
        lfont = new Font("Seriff", Font.BOLD, 15);
        tfont = new Font("Seriff", Font.PLAIN, 15);

        lcid.setFont(lfont);
        lcnm.setFont(lfont);

        tcid.setFont(tfont);
        tcnm.setFont(tfont);

        add = new JButton("ADD");
        add.setEnabled(false);
        clr = new JButton("CLEAR");
        back = new JButton("BACK");

        add.addActionListener(this);
        clr.addActionListener(this);
        back.addActionListener(this);

        // Automatically calculating next College id
        try {
            rs = ref.pclcnt.executeQuery();

            while (rs.next()) {
                int cnt;
                cnt = rs.getInt(1);
                cnt++;

                tcid.setText("" + cnt);
            }
        } catch (Exception e) {
        }

        setLayout(null);

        head.setBounds(100, 30, 300, 45);

        lcid.setBounds(50, 110, 100, 30);
        lcnm.setBounds(50, 160, 150, 30);

        tcid.setBounds(250, 110, 150, 30);
        tcnm.setBounds(250, 160, 150, 30);

        add.setBounds(50, 240, 100, 30);
        clr.setBounds(200, 240, 100, 30);
        back.setBounds(350, 240, 100, 30);

        add(head);
        add(lcid);
        add(tcid);
        add(lcnm);
        add(tcnm);

        add(add);
        add(clr);
        add(back);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setSize(500, 400);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) 
    {
        JButton b = (JButton) e.getSource();

        if (b == add) 
        {
            cid = Integer.parseInt(tcid.getText());
           // conm = tcnm.getText();
            try 
            {
                ref.pcladd.setInt(1, cid);
                ref.pcladd.setString(2, conm);
                ref.pcladd.setInt(3,1);
                ref.pcladd.executeUpdate();
            } catch (Exception e1) {}

            setVisible(false);
        }

        if (b == clr) 
        {
            tcnm.setText("");
            add.setEnabled(false);
        }
        if (b == back) 
        {
            setVisible(false);
        }
    }

    public void focusLost(FocusEvent e) 
    {
        try{
            conm=tcnm.getText();
            if(tcnm.getText().equals(""))
            {
                tcnm.requestFocus();
                return;
            }
        }
        catch(Exception e1){}
        add.setEnabled(true);
        add.requestFocus();
    }

    public void focusGained(FocusEvent e) {}
}