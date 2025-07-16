package output;

import utility.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import java.sql.*;

public class AddOutput extends JDialog implements ActionListener
{
    JLabel lhead;
    JLabel lotdate,lotamt,lotdetail,lottype;

    NTextField totamt;
    
    JRadioButton rbrec,rbexp;
    ButtonGroup bg;

    NJDatePicker dpotdate;

    JTextField totdetail;

    JButton add,clr,back;

    Font lfont,tfont;

    DB ref;

    int amt;
    String detail,type;
    Date date;


    public AddOutput(JFrame frm,String title,boolean state,DB ref)
    {
        super(frm,title,state);

        this.ref=ref;

        lhead=new JLabel("Add Output");
        lhead.setFont(new Font("Times New Roman", Font.BOLD, 40));

        lotdate =new JLabel("Date");
        lotamt=new JLabel("Amount");
        lotdetail=new JLabel("Detail");
        lottype=new JLabel("Type");

        totamt=new NTextField(10);
        totdetail=new JTextField(20);

        dpotdate=new NJDatePicker();

        rbrec=new JRadioButton("R",true);
        rbexp=new JRadioButton("E",false);

        bg=new ButtonGroup();
        bg.add(rbrec);
        bg.add(rbexp);

        add=new JButton("ADD");
        clr=new JButton("CLEAR");
        back=new JButton("BACK");

        add.addActionListener(this);
        clr.addActionListener(this);
        back.addActionListener(this);
        
        //Setting fonts
        lfont = new Font("Seriff", Font.BOLD, 15);
        tfont = new Font("Seriff", Font.PLAIN, 15);

        lotdate.setFont(lfont);
        lotamt.setFont(lfont);
        lotdetail.setFont(lfont);
        lottype.setFont(lfont);

        dpotdate.dateField.setFont(tfont);
        totamt.setFont(tfont);
        totdetail.setFont(tfont);
        rbrec.setFont(tfont);
        rbexp.setFont(tfont);

        setLayout(null);

        lhead.setBounds(100, 30, 300, 45);
        
        lotdate.setBounds(50,110,100,30);
        lotamt.setBounds(50,160,100,30);;
        lotdetail.setBounds(50,210,100,30);
        lottype.setBounds(50,260,100,30);

        dpotdate.setBounds(200,110,200,30);
        totamt.setBounds(200,160,200,30);
        totdetail.setBounds(200,210,150,30);
        rbrec.setBounds(200,260,50,30);
        rbexp.setBounds(260,260,50,30);

        
        add.setBounds(50, 340, 100, 30);
        clr.setBounds(200, 340, 100, 30);
        back.setBounds(350, 340, 100, 30);

        add(lhead);
        add(lotdate);
        add(dpotdate);
        add(lotamt);
        add(totamt);
        add(lotdetail);
        add(totdetail);
        add(lottype);
        add(rbrec);
        add(rbexp);

        add(add);
        add(clr);
        add(back);

        setSize(500,450);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        JButton b=(JButton)e.getSource();

        if(b==add)
        {
            date=(Date)dpotdate.getSqlDate();
            amt=Integer.parseInt(totamt.getText());
            detail=totdetail.getText();
            if(rbexp.isSelected())
            {
                type="E";
            }
            else
            {
                type="R";
            }

            try
            {
                ref.potadd.setDate(1, date);
                ref.potadd.setInt(2,amt);
                ref.potadd.setString(3, detail);
                ref.potadd.setString(4, type);
                ref.potadd.setInt(5,1);

                ref.potadd.executeUpdate();

                setVisible(false);
            }
            catch(Exception e1)
            {
                System.out.println(""+e1);
            }
        }

        if(b==clr)
        {
            dpotdate.dateField.setText("");
            totamt.setText("");
            totdetail.setText("");
        }

        if(b==back)
        {
            setVisible(false);
        }
    }
}