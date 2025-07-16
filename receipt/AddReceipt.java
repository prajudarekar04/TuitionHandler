package receipt;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;

import javax.swing.*;

import utility.*;

public class AddReceipt extends JDialog
{
   
    JLabel lrs, lhead, lrollno, lname, lcourse, lfee, linstallfee, ltotfee, lpaid, lrem,lredate,lamount,lmode,ldetails,lacc,lrecno;
    JLabel ltime;
    JLabel limg;
    Image img;
    NJDatePicker receiptdp;
    NTextField tamount;
    JComboBox<String> jcbdetails;

    JRadioButton rbcash,rbon,rbchq;
    ButtonGroup bgmode;

    JRadioButton rbacc1,rbacc2;
    ButtonGroup bgacc;

    JButton print, back,badd;
    DB ref;
    //ResultSet rs;
    Font lfont, tfont;

    // temp variables
    public int rollno,stadmfee,stcofee,stbalfee,stpaidfee;
    String stname,stbtnm,stconm;

    int reamt,reno;
    String remode,redetails,reacc;
    Date redate;

    ResultSet rs;

    AddReceipt addre;

    
    SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat dateFormat2= new SimpleDateFormat("dd/MM/yyyy      hh:mm a");

    public AddReceipt(JFrame parent,String title,boolean state,DB ref,int rollno)
    {
        super(parent,title,state);

        this.ref=ref;

        addre=this;

        try
        {
            limg=new JLabel();
            ImageIcon imageIcon = new ImageIcon(new ImageIcon("D:\\realsoft\\display\\logo.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            limg.setIcon(imageIcon);  //D:\\realsoft\\display\\logo.jpg
        }
        catch(Exception e){System.out.println(""+e);}

        lrs=new JLabel("Realsoft Computers");
        lrs.setFont(new Font("Times New Roman",Font.BOLD,40));

        lhead=new JLabel("Deposite Fee");
        lhead.setFont(new Font("Times New Roman", Font.BOLD, 25));

        java.util.Date dt=new java.util.Date();
        String time=dateFormat2.format(dt);
        ltime=new JLabel(time);

        lrecno=new JLabel("Receipt Id :");
        lrollno = new JLabel("Roll No :");
        lname = new JLabel("Name :");
        lcourse = new JLabel("Course :");
        lfee = new JLabel("Fee :");
        linstallfee = new JLabel("Admission Fee :");
        lpaid = new JLabel("Paid Fee :");
        lrem = new JLabel("Remaining Fee :");
        lredate=new JLabel("Date :");
        lamount=new JLabel("Amount :");
        lmode=new JLabel("Trn Mode :");
        ldetails=new JLabel("Trn Details :");
        lacc=new JLabel("Trn A/c :");

        lfont = new Font("Seriff", Font.BOLD, 15);
        tfont = new Font("Seriff", Font.PLAIN, 15);

        lrollno.setFont(lfont);
        lname.setFont(lfont);
        lcourse.setFont(lfont);
        lfee.setFont(lfont);
        linstallfee.setFont(lfont);
        lpaid.setFont(lfont);
        lrem.setFont(lfont);
        lredate.setFont(lfont);
        lamount.setFont(lfont);
        lmode.setFont(lfont);
        ldetails.setFont(lfont);
        lacc.setFont(lfont);

        //Input Fields
        receiptdp=new NJDatePicker();
        receiptdp.dateField.setText("" + receiptdp.getDate());

        tamount=new NTextField(20);

        rbcash=new JRadioButton("Cash");
        rbon=new JRadioButton("Online");
        rbchq=new JRadioButton("Cheque");

        bgmode=new ButtonGroup();

        bgmode.add(rbcash);
        bgmode.add(rbon);
        bgmode.add(rbchq);
    
        jcbdetails=new JComboBox<String>();
        jcbdetails.addItem("Googlepay");
        jcbdetails.addItem("Phonepay");
        jcbdetails.addItem("Paytm");
        jcbdetails.addItem("NEFT");
        jcbdetails.addItem("Cheque");
        jcbdetails.addItem("Cash");

        rbacc1=new JRadioButton("Account1");
        rbacc2=new JRadioButton("Account2");

        bgacc=new ButtonGroup();

        bgacc.add(rbacc1);
        bgacc.add(rbacc2);

        print=new JButton("PRINT");
        back=new JButton("BACK");
        badd=new JButton("ADD");

        //Window Opened
        lrollno.setText("Roll No : "+rollno);

        try
        {
            rs=ref.precnt.executeQuery();
            
            if(rs.next())
            {
                int cnt=rs.getInt(1);
                cnt++;
                reno=cnt;
                lrecno.setText("Receipt Id : "+cnt);
            }
        }
        catch(Exception e){}

        try
        {
            ref.preserst.setInt(1, rollno);
            rs=ref.preserst.executeQuery();

            if(rs.next())
            {
                stname = rs.getString(1);
                stbtnm = rs.getString(2);
                stconm = rs.getString(3);
                stcofee = rs.getInt(4);
                stadmfee = rs.getInt(5);
                stpaidfee=rs.getInt(6);
                stbalfee=rs.getInt(7);

                System.out.println(""+stname);
            }

            lname.setText("Name : "+stname);
            lcourse.setText("Course : "+stconm);
            lfee.setText("Total Fees : "+stcofee);
            linstallfee.setText("Admission Fee : "+stadmfee);
            lpaid.setText("Fees Paid : "+stpaidfee);
            lrem.setText("Balance Fees : "+stbalfee);
        }   
        catch(Exception e){
            System.out.println(""+e);
        }

        //Actionlisteners
        badd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                redate=(Date)receiptdp.getSqlDate();
                System.out.println("redate :"+redate);

                reamt=Integer.parseInt(""+tamount.getText());
                
                if(rbcash.isSelected())
                {
                    remode=rbcash.getText();
                }
                else
                {
                    if(rbchq.isSelected())
                        remode=rbchq.getText();
                    else
                        remode=rbon.getText();
                }

                redetails=""+jcbdetails.getSelectedItem();

                if(rbacc1.isSelected())
                    reacc=rbacc1.getText();
                else   
                    reacc=rbacc2.getText();
                

                try
                {
                    ref.preadd.setInt(1, reno);
                    ref.preadd.setDate(2,redate);
                    ref.preadd.setInt(3,rollno);
                    ref.preadd.setInt(4,reamt);
                    ref.preadd.setString(5,remode);
                    ref.preadd.setString(6, redetails);
                    ref.preadd.setString(7,reacc);


                    ref.preadd.executeUpdate();

                    ref.pstupdfee.setInt(1,stbalfee-reamt);
                    ref.pstupdfee.setInt(2,stpaidfee+reamt);
                    ref.pstupdfee.setInt(3,rollno);

                    ref.pstupdfee.executeUpdate();

                    addre.dispose();
                    
                }
                catch(Exception e1){}
            }
        });

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                addre.dispose();
            }
        });

        //Insert fee record into table 

        setLayout(null);

        limg.setBounds(20,20,100,100);
        lrs.setBounds(150,10,400,40);
        lhead.setBounds(185,60,400,30);
        ltime.setBounds(200,100,200,30);
        lrecno.setBounds(420,100,130,30);
        lrollno.setBounds(10,140,100,30);
        lname.setBounds(10,180,400,30);
        lcourse.setBounds(10,220,400,30);
        lfee.setBounds(10,260,275,30);
        linstallfee.setBounds(300,260,275,30);
        lpaid.setBounds(10,310,275,30);
        lrem.setBounds(300,310,275,30);
        lredate.setBounds(10,360,100,30);
        receiptdp.setBounds(120,360,200,30);
        lamount.setBounds(10,410,100,30);
        tamount.setBounds(120,410,150,30);
        lmode.setBounds(10,460,100,30);
        rbcash.setBounds(120,460,100,30);
        rbon.setBounds(230,460,100,30);
        rbchq.setBounds(340,460,100,30);
        ldetails.setBounds(10,510,100,30);
        jcbdetails.setBounds(120,510,150,30);
        lacc.setBounds(10,560,100,30);
        rbacc1.setBounds(120,560,120,30);
        rbacc2.setBounds(250,560,120,30);
        badd.setBounds(60,610,120,30);
        print.setBounds(240,610,120,30);
        back.setBounds(420,610,120,30);
        
        // ADD
        add(limg);
        add(lrs);
        add(lhead);
        add(ltime);
        add(lrecno);
        add(lrollno);
        add(lname);
        add(lcourse);
        add(lfee);
        add(linstallfee);
        add(lpaid);
        add(lrem);
        add(lredate);
        add(receiptdp);
        add(lamount);
        add(tamount);
        add(lmode);
        add(rbcash);
        add(rbchq);
        add(rbon);
        add(ldetails);
        add(jcbdetails);
        add(lacc);
        add(rbacc1);
        add(rbacc2);
        add(badd);
        add(print);
        add(back);
        

        setSize(700,740);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

}
