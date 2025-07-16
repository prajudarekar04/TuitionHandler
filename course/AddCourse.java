package course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import utility.*;

public class AddCourse extends JDialog implements ActionListener
{
    JLabel head;

    JLabel lcoid, lconm,lcodfee,lcorfee;

    JTextField tcoid, tconm;
    NTextField tcodfee,tcorfee;

    JButton add, clr, back;

    DB ref;

    //Temparary storage
    int coid;
    Float codfee,corfee;
    String conm;

    ResultSet rs;

    //Fonts
    Font lfont,tfont;

    public AddCourse(JFrame frm,String title,boolean state,DB ref) 
    {
        super(frm,"ADD Course",state);

        this.ref=ref;

        head = new JLabel("ADD COURSE");
        head.setFont(new Font("Times New Roman", Font.BOLD, 40));

        lcoid = new JLabel("ID");
        lconm = new JLabel("Name");
        lcodfee = new JLabel("Discounted Fee");
        lcorfee = new JLabel("Regular Fee");
        
        tcoid = new JTextField(10);
        tcoid.setEditable(false);
        tconm = new JTextField(10);
        tcodfee = new NTextField(10);
        tcorfee = new NTextField(10);


        //setting fonts
        lfont = new Font("Seriff", Font.BOLD, 15);
        tfont = new Font("Seriff", Font.PLAIN, 15);

        lcoid.setFont(lfont);
        lconm.setFont(lfont);
        lcodfee.setFont(lfont);
        lcorfee.setFont(lfont);

        tcoid.setFont(tfont);
        tconm.setFont(tfont);
        tcodfee.setFont(tfont);
        tcorfee.setFont(tfont);


        add = new JButton("ADD");
        add.setEnabled(false);
        clr = new JButton("CLR");
        back = new JButton("BACK");

        add.addActionListener(this);
        clr.addActionListener(this);
        back.addActionListener(this);

        //Automatically calculating next Course id
        try
        {
            rs=ref.pcocnt.executeQuery();

            while(rs.next())
            {
                int cnt;
                cnt=rs.getInt(1);
                cnt++;

                tcoid.setText(""+cnt);
            }
        }
        catch(Exception e){}



        tcorfee.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e)
            {
                 try
                {
                    conm=tconm.getText();
                    System.out.println(conm);
                    if(tconm.getText().equals(""))
                    {
                        tconm.requestFocus();
                        return;
                    }   
                }
                catch(Exception e1){
                    System.out.println(""+e1);
                    tcorfee.requestFocus();
                }
            }
        });
        tcodfee.addFocusListener(new FocusAdapter() 
        {
            public void focusGained(FocusEvent e)
            {
                try
                {
                    corfee=Float.parseFloat(""+tcorfee.getText());
                }
                catch(Exception e1)
                {
                     tcorfee.requestFocus();
                }
            }
            
        });


        clr.addFocusListener(new FocusAdapter() 
        {
            public void focusGained(FocusEvent e)
            {
                try
                {
                    codfee=Float.parseFloat(""+tcodfee.getText());
                    if(!add.isEnabled())
                    {
                        add.setEnabled(true);
                        add.requestFocus();
                    }
                }
                catch(Exception e1){tcodfee.requestFocus();}
                add.requestFocus();
            }
        });
       
        setLayout(null);

        head.setBounds(100, 30, 300, 45);

        lcoid.setBounds(50, 110, 100, 30);
        lconm.setBounds(50, 160, 100, 30);
        lcorfee.setBounds(50, 210, 100, 30);
        lcodfee.setBounds(50, 260, 130, 30);

        tcoid.setBounds(250, 110, 150, 30);
        tconm.setBounds(250, 160, 150, 30);
        tcorfee.setBounds(250, 210, 150, 30);
        tcodfee.setBounds(250, 260,     150, 30);

        add.setBounds(50, 340, 100, 30);
        clr.setBounds(200, 340, 100, 30);
        back.setBounds(350, 340, 100, 30);

        add(head);
        add(lcoid);
        add(tcoid);
        add(lconm);
        add(tconm);
        add(lcorfee);
        add(tcorfee);
        add(lcodfee);
        add(tcodfee);

        add(add);
        add(clr);
        add(back);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setSize(500, 500);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e)
    {
        JButton b=(JButton)e.getSource();

        if(b==add)
        {
            coid=Integer.parseInt(tcoid.getText());
            conm=tconm.getText();
            codfee=Float.parseFloat(tcodfee.getText());
            corfee=Float.parseFloat(tcorfee.getText());

            try
            {
                ref.pcoadd.setInt(1,coid);
                ref.pcoadd.setString(2,conm);
                ref.pcoadd.setFloat(3,codfee);
                ref.pcoadd.setFloat(4,corfee);
                ref.pcoadd.setInt(5,1);

                ref.pcoadd.executeUpdate();
            }
            catch(Exception e1){}

            setVisible(false);
        }

        if(b==clr)
        {
            tconm.setText("");
            tcodfee.setText("");
            tcorfee.setText("");
            add.setEnabled(false);

            tcodfee.dotcnt=0;
            tcorfee.dotcnt=0;
        }

        if(b==back)
        {
            setVisible(false);
        }
    }
}