package student;
import javax.swing.*;

import utility.*;

import java.awt.event.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.awt.*;
public class SearchStudent extends JDialog implements ActionListener
{
    JLabel lid;
    JComboBox<String> cmstid;

    JButton print,bck;

    //font for labels
    Font lfont,tfont;
    JPanel north = new JPanel();
    JPanel center = new JPanel();
    JScrollPane scrollPane; //JScrollPane for the center panel
    JPanel south = new JPanel();
    DB ref;
    int stid;
    String stnm;
    ResultSet rs;

    JLabel head,realsoft, logo,sdetails, edetails, cdetails, cfdetails;

    JLabel lmno,lmadate, lmname, lmgender, lmsmbno, lmpmbno, lmemail, lmpad, lmlad, lmdob, lmcity, lmsame;  // student details
    JLabel lmclnm, lmtrnm, lmacdyr; // educational details
    JLabel lmconm,lmbtnm,lmattmode,ltime; // course details
    JLabel lmcofee,lmadmfee,lmbalfee,lmtrntype,lmtrdetail,lmaccopt; // transaction details */

    // student details
    JTextField tmno,tmname1,tmname2,tmname3, tmemail, tmpad, tmlad;
    NTextField  ntmsmbno, ntmpmbno;
    JTextField cmcity;
    JRadioButton m,f;
    ButtonGroup genderGroup;
    //JCheckBox same; // for address
    JTextField dob,adate;

    SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");
    Date sdob,sdate;
    int sid,cofee,admfee,balfee,acyear;
    String fname,mname,lname,gender,smobile,pmobile,semail,speradd,sloadd,scity,sclname,strname,sconame,sbatname,sbattime,smode,strtype,strdetail,stranto,formattedString;

    // educational details
    JRadioButton rb1,rb2,rb3,rb4;
    ButtonGroup bgacadyr;
    JTextField tmclnm, tmtrnm;
    Font font1,font2,font3;

    // course details
    JTextField ttime, cmconm,cmbtnm;
    ButtonGroup bmattmode;
    JRadioButton online,offline;

    // transaction details
    NTextField ntmcofee,ntmadmfee,ntmbalfee;
    JRadioButton cash,cheque,on,account1,account2;
    ButtonGroup btrntype,bmaccopt;
    JTextField cmtrdetail;

    public SearchStudent(JFrame parent,String title,boolean state,DB ref)
    {
        super(parent,"Search Student",state);
        this.ref=ref;
       
        head = new JLabel("Student Information");
        realsoft=new JLabel("Realsoft Computers");
        head.setFont(new Font("Times New Roman",Font.BOLD,30));
        realsoft.setFont(new Font("Times New Roman",Font.BOLD,40));

        try
        {
            logo=new JLabel();
            ImageIcon imageIcon = new ImageIcon(new ImageIcon("D:\\realsoft\\utility\\logo.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            logo.setIcon(imageIcon);  //D:\\realsoft\\display\\logo.jpg
        }
        catch(Exception e){System.out.println(""+e);}


        lfont = new Font("Seriff", Font.BOLD, 15);
        tfont = new Font("Seriff", Font.PLAIN, 15);

        lid=new JLabel("Enter ID");


        cmstid=new JComboBox<String>();

        
        print=new JButton("PRINT");
        bck=new JButton("BACK");

        print.addActionListener(this);
        bck.addActionListener(this);

        lid.setFont(lfont);
        cmstid.setFont(tfont);

        font1=new Font("Seriff",Font.BOLD,20);
        font2=new Font("Seriff",Font.BOLD,15);
        font3=new Font("Seriff",Font.PLAIN,15);

        center.setLayout(null);
        center.setPreferredSize(new Dimension(1100,1250));

        north.setLayout(null);
        north.setPreferredSize(new Dimension(1100,140));

        south.setLayout(null);
        south.setPreferredSize(new Dimension(1100,50));

        logo.setBounds(50,20,100,100);
        realsoft.setBounds(400,20,500,50);
        head.setBounds(430,90,400,40);
        lid.setBounds(170, 100, 100, 30);
        cmstid.setBounds(290, 100, 130, 30);


        print.setBounds(420, 720, 100, 30);
        bck.setBounds(540, 720, 100, 30);

        add(head);
        add(logo);
        add(realsoft);
        add(lid);
        add(cmstid);

        
        add(print);
        add(bck);

        try
        {
            rs=ref.pstdis.executeQuery();

            while(rs.next())
            {
                int no=rs.getInt(1);

                cmstid.addItem(""+no);
            }
        }
        catch(Exception e){}

        cmstid.addActionListener(this);

        //Student details
        sdetails = new JLabel("Student Details :");

        lmno = new JLabel("Student ID");
        lmadate=new JLabel("Admission Date");
        lmname = new JLabel("Name");
        lmgender = new JLabel("Gender");
        lmsmbno = new JLabel("Mobile No.");
        lmpmbno = new JLabel("Parent Mobile No.");
        lmemail = new JLabel("E-mail ID");
        lmpad = new JLabel("Permanant Address");
        lmlad = new JLabel("Local Address");
        lmdob = new JLabel("Date of Birth");
        lmcity = new JLabel("City");
        lmsame = new JLabel("Same address");

        sdetails.setForeground(Color.RED);
        sdetails.setFont(font1);
        lmno.setFont(font2);
        lmadate.setFont(font2);
        lmname.setFont(font2);
        lmgender.setFont(font2);
        lmsmbno.setFont(font2);
        lmpmbno.setFont(font2);
        lmemail.setFont(font2);
        lmpad.setFont(font2);
        lmlad.setFont(font2);
        lmcity.setFont(font2);
        lmsame.setFont(font2);   
        
        tmno=new JTextField(10);
        adate=new JTextField();
        tmname1=new JTextField(10);
        tmname2=new JTextField(10);
        tmname3=new JTextField(10);
        dob=new JTextField();
        ntmsmbno=new NTextField(10);
        ntmpmbno=new NTextField(10);
        tmemail=new JTextField(10);
        tmpad=new JTextField(10);
        tmlad=new JTextField(10);

        tmname1.setText("First Name");
        tmname2.setText("Middle Name");
        tmname3.setText("Last Name");   
        m=new JRadioButton("Male",true);
        f=new JRadioButton("Female",false);
        cmcity = new JTextField();
       
        genderGroup = new ButtonGroup();
        genderGroup.add(m);
        genderGroup.add(f);

        tmno.setFont(font3);
        tmname1.setFont(font3);
        tmname2.setFont(font3);
        tmname3.setFont(font3);
        ntmsmbno.setFont(font3);
        ntmpmbno.setFont(font3);
        tmemail.setFont(font3);
        tmpad.setFont(font3);
        tmlad.setFont(font3);
        cmcity.setFont(font3);
        m.setFont(font3);
        f.setFont(font3);
        adate.setFont(font3);
        dob.setFont(font3);

        tmno.setEditable(false);

        sdetails.setBounds(150, 20, 200, 40);
        lmno.setBounds(150, 70, 150, 30);
        tmno.setBounds(320, 70, 200,30);
        lmadate.setBounds(540,70,130,30);
        adate.setBounds(690,70,210,35);
        lmname.setBounds(150, 120, 150, 30);
        tmname1.setBounds(320,120, 200, 30);
        tmname2.setBounds(540,120, 200, 30);
        tmname3.setBounds(760,120, 200, 30);
        lmgender.setBounds(150, 170, 150,30);
        m.setBounds(320, 170, 80, 30);
        f.setBounds(420, 170, 80, 30);
        lmdob.setBounds(600, 170, 100,30);
        dob.setBounds(750,170,210,35);
        lmsmbno.setBounds(150, 220,150, 30);
        ntmsmbno.setBounds(320, 220, 250, 30);
        lmpmbno.setBounds(600, 220, 130, 30);
        ntmpmbno.setBounds(750, 220, 210,30);
        lmemail.setBounds(150, 270, 150, 30);
        tmemail.setBounds(320, 270, 250,30);
        lmpad.setBounds(150, 320, 150, 30);
        tmpad.setBounds(320, 320, 640,30);
        lmcity.setBounds(150, 370, 150, 30);
        cmcity.setBounds(320, 370, 200, 30);
        lmsame.setBounds(600, 370, 130, 30);
        lmlad.setBounds(150, 420, 150, 30);
        tmlad.setBounds(320, 420, 640,30);

        center.add(sdetails);
        center.add(lmno);center.add(tmno);
        center.add(lmadate);center.add(adate);
        center.add(lmname);center.add(tmname1);center.add(tmname2);center.add(tmname3);
        center.add(lmgender);
        center.add(m);center.add(f);
        center.add(lmdob);center.add(dob);
        center.add(lmsmbno);center.add(ntmsmbno);
        center.add(lmpmbno);center.add(ntmpmbno);
        center.add(lmemail);center.add(tmemail);
        center.add(lmpad);center.add(tmpad);
        center.add(lmcity);center.add(cmcity);
        center.add(lmlad);center.add(tmlad);

        //educational details
        edetails = new JLabel("Educational Details :");
        lmclnm = new JLabel("College Name");
        lmtrnm = new JLabel("Trade");
        lmacdyr = new JLabel("Academic Year");

        tmtrnm = new JTextField();
        edetails.setForeground(Color.RED);

        rb1=new JRadioButton("1",true);
        rb2=new JRadioButton("2",false);
        rb3=new JRadioButton("3",false);
        rb4=new JRadioButton("4",false);

        tmclnm = new JTextField();
        bgacadyr=new ButtonGroup();
        bgacadyr.add(rb1);
        bgacadyr.add(rb2);
        bgacadyr.add(rb3);
        bgacadyr.add(rb4);

        edetails.setFont(font1);
        lmclnm.setFont(font2);
        lmtrnm.setFont(font2);
        lmacdyr.setFont(font2);
        lmdob.setFont(font2);

        edetails.setBounds(150, 480, 200, 40);
        lmclnm.setBounds(150, 530, 150, 30);
        tmclnm.setBounds(320, 530, 200, 30);
        lmtrnm.setBounds(150, 580, 150, 30);
        tmtrnm.setBounds(320, 580, 200, 30);
        lmacdyr.setBounds(150, 630, 150, 30);
        rb1.setBounds(320,630,50,30);
        rb2.setBounds(380,630,50,30);
        rb3.setBounds(440,630,50,30);
        rb4.setBounds(500,630,50,30);

        tmclnm.setFont(font3);
        tmtrnm.setFont(font3);
        lmacdyr.setFont(font3);
        rb1.setFont(font3);
        rb2.setFont(font3);
        rb3.setFont(font3);
        rb4.setFont(font3);

        center.add(edetails);
        center.add(lmclnm);center.add(tmclnm);
        center.add(lmtrnm);center.add(tmtrnm);
        center.add(lmacdyr);center.add(rb1);center.add(rb2);center.add(rb3);center.add(rb4);

        //course details
        cdetails = new JLabel("Course Details :");
        lmconm=new JLabel("Course Name");
        lmbtnm=new JLabel("Batch Name");
        ltime=new JLabel("Batch Time");
        lmattmode=new JLabel("Mode");

        cdetails.setForeground(Color.RED);

        cdetails.setFont(font1);
        lmconm.setFont(font2);
        lmbtnm.setFont(font2);
        ltime.setFont(font2);
        lmattmode.setFont(font2);

        ttime=new JTextField(10);
        ttime.setEditable(false);

        cmconm=new JTextField();
        cmbtnm=new JTextField();

        online=new JRadioButton("Online",true);
        offline=new JRadioButton("Offline",true);

        
        bmattmode=new ButtonGroup();
        
        bmattmode.add(online);
        bmattmode.add(offline);
        
        ttime.setFont(font3);
        online.setFont(font3);
        offline.setFont(font3);
        cmconm.setFont(font3);
        cmbtnm.setFont(font3);

        cdetails.setBounds(150,690,200,40);
        lmconm.setBounds(150,740,150,30);
        cmconm.setBounds(320,740,200,30);
        lmbtnm.setBounds(150,790,150,30);
        cmbtnm.setBounds(320,790,200,30);
        ltime.setBounds(600,790,130,30);
        ttime.setBounds(750,790,200,30);
        lmattmode.setBounds(150,840,150,30);
        online.setBounds(320,840,100,30);
        offline.setBounds(440,840,100,30);

        center.add(cdetails);
        center.add(lmconm);center.add(cmconm);
        center.add(lmbtnm);center.add(cmbtnm);
        center.add(ltime);center.add(ttime);
        center.add(lmattmode);
        center.add(online);center.add(offline);

        //transaction details
        cfdetails = new JLabel("Transaction Details :");
        lmcofee=new JLabel("Course Fee");
        lmadmfee=new JLabel("Fees Paid");
        lmbalfee=new JLabel("Balance Fees");
        lmtrntype=new JLabel("Transaction Type");
        lmtrdetail=new JLabel("Transaction Details");
        lmaccopt=new JLabel("Transaction To");

        cfdetails.setForeground(Color.RED);

        cfdetails.setFont(font1);
        lmcofee.setFont(font2);
        lmadmfee.setFont(font2);
        lmbalfee.setFont(font2);
        lmtrntype.setFont(font2);
        lmtrdetail.setFont(font2);
        lmaccopt.setFont(font2);

        ntmcofee=new NTextField(10);
        ntmadmfee=new NTextField(10);
        ntmbalfee=new NTextField(10);
        ntmbalfee.setEditable(false);
        
        cmtrdetail=new JTextField();
        
        cash=new JRadioButton("Cash",true);
        cheque=new JRadioButton("Cheque",false);
        on=new JRadioButton("Online",false);
        
        
        btrntype = new ButtonGroup();
        btrntype.add(cash);
        btrntype.add(cheque);
        btrntype.add(on);
        
        account1=new JRadioButton("Account1",true);
        account2=new JRadioButton("Account2",false);
        
        ntmcofee.setFont(font3);
        ntmadmfee.setFont(font3);
        ntmbalfee.setFont(font3);
        cash.setFont(font3);
        cheque.setFont(font3);
        on.setFont(font3);
        account1.setFont(font3);
        account2.setFont(font3);
        cmtrdetail.setFont(font3);
        cash.setEnabled(false);
        cheque.setEnabled(false);
        on.setEnabled(false);

        account1.setEnabled(false);
        account2.setEnabled(false);

        bmaccopt = new ButtonGroup();
        bmaccopt.add(account1);
        bmaccopt.add(account2);

        cfdetails.setBounds(150,900,200,40);
        lmcofee.setBounds(150,950,150,30);
        ntmcofee.setBounds(320,950,200,30);
        lmadmfee.setBounds(150,1000,150,30);
        ntmadmfee.setBounds(320,1000,200,30);
        lmbalfee.setBounds(150,1050,150,30);
        ntmbalfee.setBounds(320,1050,200,30);
        lmtrntype.setBounds(150,1100,150,30);
        cash.setBounds(320,1100,100,30);
        cheque.setBounds(440,1100,100,30);
        on.setBounds(560,1100,100,30);
        lmtrdetail.setBounds(150,1150,150,30);
        cmtrdetail.setBounds(320,1150,200,30);
        lmaccopt.setBounds(150,1200,150,30);
        account1.setBounds(320,1200,100,30);
        account2.setBounds(440,1200,100,30);

        center.add(cfdetails);
        center.add(lmcofee);center.add(ntmcofee);
        center.add(lmadmfee);center.add(ntmadmfee);
        center.add(lmbalfee);center.add(ntmbalfee);
        center.add(lmtrntype);
        center.add(cash);center.add(cheque);center.add(on);
        center.add(lmtrdetail);center.add(cmtrdetail);
        center.add(lmaccopt);
        center.add(account1);center.add(account2);

        //all fields setEditable(false)------------------------------

        tmname1.setEditable(false);
        tmname2.setEditable(false);
        tmname3.setEditable(false);
        dob.setEditable(false);
        adate.setEditable(false);
        ntmsmbno.setEditable(false);
        ntmpmbno.setEditable(false);
        tmemail.setEditable(false);
        tmpad.setEditable(false);
        tmlad.setEditable(false);
        cmcity.setEditable(false);
        tmclnm.setEditable(false);
        tmtrnm.setEditable(false);
        cmconm.setEditable(false);
        cmbtnm.setEditable(false);
        cmtrdetail.setEditable(false);
        ntmcofee.setEditable(false);
        ntmadmfee.setEditable(false);
        ntmbalfee.setEditable(false);
        m.setEnabled(false);
        f.setEnabled(false);

        rb1.setEnabled(false);
        rb2.setEnabled(false);
        rb3.setEnabled(false);
        rb4.setEnabled(false);

        online.setEnabled(false);
        offline.setEnabled(false);
        cash.setEnabled(false);
        cheque.setEnabled(false);
        on.setEnabled(false);
                                        
        
        scrollPane = new JScrollPane(center);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setBackground(Color.WHITE);

        setLayout(new BorderLayout());
        add(north, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1100, 800);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) 
    {
            stid=Integer.parseInt(""+cmstid.getSelectedItem());
            try
            {
                ref.pstser.setInt(1, stid);
                rs=ref.pstser.executeQuery();
                if(rs.next())
                { 
                    System.out.println("Hello");
                    sid =rs.getInt(1);
                    sdate = rs.getDate(2);
                    String tmpdate1=dateFormat.format(sdate);
                    fname = rs.getString(3);
                    mname = rs.getString(4);
                    lname = rs.getString(5);
                    gender =rs.getString(7);
                    sdob = rs.getDate(8);
                    String tmpdate2=dateFormat.format(sdob);
                    sclname= rs.getString(9);
                    strname= rs.getString(10);
                    acyear = rs.getInt(11);
                    speradd= rs.getString(12);
                    sloadd= rs.getString(13);
                    scity= rs.getString(14);
                    smobile = rs.getString(15);
                    pmobile = rs.getString(16);
                    semail= rs.getString(17);
                    sconame= rs.getString(18);
                    sbatname= rs.getString(19);
                    sbattime = rs.getString(20);
                    cofee = rs.getInt(21);
                    admfee = rs.getInt(22);
                    balfee = rs.getInt(23);
                    strtype = rs.getString(24);
                    strdetail = rs.getString(25);
                    stranto = rs.getString(26);
                    smode = rs.getString(2);
                    
                    cmcity.setText(scity);
                    tmclnm.setText(sclname);
                    tmtrnm.setText(strname);
                    cmconm.setText(sconame);
                    cmbtnm.setText(sbatname);
                    cmtrdetail.setText(strdetail);
                    tmno.setText(""+sid);
                    adate.setText(tmpdate1);
                    tmname1.setText(fname);
                    tmname2.setText(mname);
                    tmname3.setText(lname);
                    if(gender.equals("Male"))
                    {
                        m.setSelected(true);
                        f.setSelected(false);
                    }
                    if(gender.equals("Female"))
                    {
                        m.setSelected(false);
                        f.setSelected(true);
                    }
                    dob.setText(tmpdate2);
                    ntmsmbno.setText(smobile);
                    ntmpmbno.setText(pmobile);
                    tmemail.setText(semail);
                    tmpad.setText(speradd);
                    tmlad.setText(sloadd);
                    if(acyear == 1)
                    {
                        rb1.setSelected(true);
                        rb2.setSelected(false);
                        rb3.setSelected(false);
                        rb4.setSelected(false);
                    }
                    if(acyear == 2)
                    {
                        rb1.setSelected(false);
                        rb2.setSelected(true);
                        rb3.setSelected(false);
                        rb4.setSelected(false);
                    }
                    if(acyear == 3)
                    {
                        rb1.setSelected(false);
                        rb2.setSelected(false);
                        rb3.setSelected(true);
                        rb4.setSelected(false);
                    }
                    if(acyear == 4)
                    {
                        rb1.setSelected(false);
                        rb2.setSelected(false);
                        rb3.setSelected(false);
                        rb4.setSelected(true);
                    }
                    
                    ttime.setText(sbattime);
                    if(smode.equals("Online"))
                    {
                        online.setSelected(true);
                        offline.setSelected(false);
                    }
                    if(smode.equals("Offline"))
                    {
                        online.setSelected(false);
                        offline.setSelected(true);
                    }
                    
                    ntmcofee.setText(""+cofee);
                    ntmadmfee.setText(""+admfee);
                    ntmbalfee.setText(""+balfee);
                    if(strtype.equals("Cash"))
                    {
                        cash.setSelected(true);
                        cheque.setSelected(false);
                        on.setSelected(false);
                    }
                    if(strtype.equals("Cheque"))
                    {
                        cash.setSelected(false);
                        cheque.setSelected(true);
                        on.setSelected(false);
                    }
                    if(strtype.equals("Online"))
                    {
                        cash.setSelected(false);
                        cheque.setSelected(false);
                        on.setSelected(true);
                    }
                    if(stranto.equals("Account1"))
                    {
                        account1.setSelected(true);
                        account2.setSelected(false);
                    }
                    if(stranto.equals("Account2"))
                    {
                        account2.setSelected(true);
                        account1.setSelected(false);
                    }
                    account1.setEnabled(false);
                    account2.setEnabled(false);
                }
            }
            catch(Exception e1)
            {
                System.out.println("104 "+e1);
            }
    }

}