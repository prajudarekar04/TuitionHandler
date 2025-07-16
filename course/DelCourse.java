package course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import utility.*;

public class DelCourse extends JDialog implements ActionListener,FocusListener
{
    JLabel head;

    JLabel lcoid, lconm,lcodfee,lcorfee,lcomesg;

    JTextField  tconm,tcodfee,tcorfee;

    String sconm;
    int scodfee,scorfee;

    JComboBox<String> tcoid;

    JButton del, clr, back;

    DB ref;

    //Temparary storage
    int coid,codfee,corfee;
    String conm,nm;

    public ResultSet rs;

    int co_id;  //for searching in database for course id
    //Fonts
    Font lfont,tfont;

    public DelCourse(JFrame frm,String title,boolean state,DB ref) throws SQLException 
    {
        super(frm,"Delete Course",state);

        this.ref=ref;

        head = new JLabel("DELETE COURSE");
        head.setFont(new Font("Times New Roman", Font.BOLD, 40));

        lcoid = new JLabel("ID");
        lconm = new JLabel("Name");
        lcodfee = new JLabel("Discounted Fee");
        lcorfee = new JLabel("Regular Fee");
        lcomesg=new JLabel("");
        lcomesg.setVisible(false);
        
        tcoid = new JComboBox<String>();
        tconm = new JTextField(10);
        tcodfee = new JTextField(10);
        tcorfee = new JTextField(10);
        tcodfee.addFocusListener(this);
        tconm.setEditable(false);
        tcodfee.setEditable(false);
        tcorfee.setEditable(false);
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

        lcomesg.setFont(lfont);

        del = new JButton("DELETE");
        back = new JButton("BACK");

        del.addActionListener(this);
        back.addActionListener(this);

        del.setEnabled(false);


        try 
        {
            rs=ref.pcodis.executeQuery();
        } catch (Exception e) 
        {
            System.out.println("82"+e);
        }

        try 
		{
			while(rs.next())
			{

				int s=rs.getInt(1);
                if(rs.getInt(5)==1)
				    tcoid.addItem(""+s);
			}	//adds each course id to combo box
		} catch (Exception e) {
            System.out.println(""+e);
        }


        tcoid.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                try 
                {
                    co_id=Integer.parseInt((String) tcoid.getSelectedItem());
                    ref.pcoser.setInt(1, co_id);
                    rs=ref.pcoser.executeQuery();
                    if(rs.next())
                    {
                        sconm=rs.getString(2);
                        scodfee=rs.getInt(3);
                        scorfee=rs.getInt(4);
                    }
                    tconm.setText(sconm);
                    tcodfee.setText(""+scodfee);
                    tcorfee.setText(""+scorfee);

                    del.setEnabled(true);
                } catch (Exception e1) {
                    System.out.println(""+e1);
                }
                
            }
        });

        setLayout(null);

        head.setBounds(100, 30, 400, 45);

        lcoid.setBounds(50, 110, 100, 30);
        lconm.setBounds(50, 160, 100, 30);
        lcorfee.setBounds(50, 210, 100, 30);
        lcodfee.setBounds(50, 260, 130, 30);
        lcomesg.setBounds(140,310,300,30);

        tcoid.setBounds(250, 110, 150, 30);
        tconm.setBounds(250, 160, 150, 30);
        tcorfee.setBounds(250, 210, 150, 30);
        tcodfee.setBounds(250, 260,150, 30);

        del.setBounds(120, 360, 100, 30);
        back.setBounds(280, 360, 100, 30);

        add(head);
        add(lcoid);
        add(tcoid);
        add(lconm);
        add(tconm);
        add(lcorfee);
        add(tcorfee);
        add(lcodfee);
        add(tcodfee);
        add(lcomesg);

        add(del);
        add(back);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setSize(520, 480);

        setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        JButton b=(JButton)e.getSource();

        if(b==del)
        {
            nm=tconm.getText();
            try 
            {   ref.pstserco.setString(1, nm);
                rs=ref.pstserco.executeQuery();
                if(rs.next())
                {
                    lcomesg.setText("Trade "+nm+" can't be deleted"); 
                    lcomesg.setForeground(Color.RED); 
                    lcomesg.setVisible(true);
                }
                else
                {
                    ref.pcodel.setInt(1, co_id);
                    ref.pcodel.executeUpdate();
                    setVisible(false);
                }
            } catch (Exception e1) 
            {
                System.out.println("173"+e1);
            }
           //JOptionPane.showMessageDialog(null, "Record Deleted Succesfully");
        }
        else
            setVisible(false);
    }

    public void focusLost(FocusEvent e)
    {
    }

    public void focusGained(FocusEvent e){}
    public static void main(String[] args) throws Exception
    {
        DB d=new DB();
        DelCourse a=new DelCourse(null, "Mod", true, d);
    }
}