package course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import utility.*;

public class ModCourse extends JDialog implements ActionListener
{
    JLabel head;

    JLabel lcoid, lconm,lcodfee,lcorfee;

    JTextField  tconm;
    NTextField tcodfee,tcorfee;

    String sconm;

    JComboBox<String> tcoid;

    JButton mod, clr, back;

    DB ref;

    //Temparary storage
    int coid;
    Float codfee,corfee;
    String conm;

    public ResultSet rs;

    int co_id;  //for searching in database for course id
    //Fonts
    Font lfont,tfont;

    public ModCourse(JFrame frm,String title,boolean state,DB ref) throws SQLException 
    {
        super(frm,"Mod Course",state);

        this.ref=ref;

        head = new JLabel("MODIFY COURSE");
        head.setFont(new Font("Times New Roman", Font.BOLD, 40));

        lcoid = new JLabel("ID");
        lconm = new JLabel("Name");
        lcodfee = new JLabel("Discounted Fee");
        lcorfee = new JLabel("Regular Fee");
        
        tcoid = new JComboBox<String>();
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

        tconm.setEditable(false);
        tcodfee.setEditable(false);
        tcorfee.setEditable(false);


        mod = new JButton("MODIFY");
        mod.setEnabled(false);
        clr = new JButton("CLR");
        back = new JButton("BACK");

        mod.addActionListener(this);
        clr.addActionListener(this);
        back.addActionListener(this);
        try 
        {
            rs=ref.pcodis.executeQuery();
        } catch (Exception e) {}

        try 
		{
			while(rs.next())
			{
				int s=rs.getInt(1);
				tcoid.addItem(""+s);
			}	//adds each course id to combo box
		} catch (Exception e) {}


        tcoid.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                try 
                {
                    mod.setEnabled(false);

                    tconm.setEditable(true);
                    tcodfee.setEditable(true);
                    tcorfee.setEditable(true);
                    
                    co_id=Integer.parseInt((String) tcoid.getSelectedItem());

                    ref.pcoser.setInt(1, co_id);

                    rs=ref.pcoser.executeQuery();

                    if(rs.next())
                    {
                        sconm=rs.getString(2);

                        codfee=rs.getFloat(3);
                        corfee=rs.getFloat(4);
                    }

                    tconm.setText(sconm);

                    tcodfee.setText(""+codfee);
                    
                    tcorfee.setText(""+corfee);
                } catch (Exception e1) {
                    System.out.println(""+e1);
                }
                
            }
        });

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
                    if(!mod.isEnabled())
                    {
                        mod.setEnabled(true);
                        mod.requestFocus();
                    }
                }
                catch(Exception e1){tcodfee.requestFocus();}
            }
        });

        setLayout(null);

        head.setBounds(100, 30, 400, 45);

        lcoid.setBounds(50, 110, 100, 30);
        lconm.setBounds(50, 160, 100, 30);
        lcorfee.setBounds(50, 210, 100, 30);
        lcodfee.setBounds(50, 260, 130, 30);

        tcoid.setBounds(250, 110, 150, 30);
        tconm.setBounds(250, 160, 150, 30);
        tcorfee.setBounds(250, 210, 150, 30);
        tcodfee.setBounds(250, 260,     150, 30);

        mod.setBounds(50, 340, 100, 30);
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

        add(mod);
        add(clr);
        add(back);
        

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setSize(520, 500);
        

        setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        JButton b=(JButton)e.getSource();

        if(b==mod)
        {
            coid=Integer.parseInt(""+tcoid.getSelectedItem());
           // conm=tconm.getText();
           // codfee=Integer.parseInt(tcodfee.getText());
           // corfee=Integer.parseInt(tcorfee.getText());

            try
            {
                ref.pcomod.setString(1,conm);
                ref.pcomod.setFloat(2,codfee);
                ref.pcomod.setFloat(3,corfee);
                ref.pcomod.setInt(4, coid);
                ref.pcomod.executeUpdate();
            }
            catch(Exception e1)
            {
                System.out.println("184"+e1);
            }

            setVisible(false);
        }

        if(b==clr)
        {
            
            tconm.setText("");
            tcodfee.setText("");
            tcorfee.setText("");
            tconm.setEditable(false);
            tcodfee.setEditable(false);
            tcorfee.setEditable(false);
            mod.setEnabled(false);
            tconm.requestFocus();
        }

        if(b==back)
        {
            setVisible(false);
        }
    }
}