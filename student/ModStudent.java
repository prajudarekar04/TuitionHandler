package student;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;

import utility.*;
import batch.*;
import city.*;
import course.*;
import trade.*;
import college.*;

public class ModStudent extends JDialog implements FocusListener,ActionListener
{
    JLabel title,realsoft, logo,regdate,sdetails, edetails, cdetails, cfdetails;

    JLabel lmno,lmadate, lmname, lmgender, lmsmbno, lmpmbno, lmemail, lmpad, lmlad, lmdob, lmcity, lmsame;  // student details
    JLabel lmclnm, lmtrnm, lmacdyr; // educational details
    JLabel lmconm,lmbtnm,lmattmode,ltime; // course details
    JLabel lmcofee,lmadmfee,lmbalfee,lmtrntype,lmtrdetail,lmaccopt; // transaction details */
    NJDatePicker date,adate;

    SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");

    JPanel north = new JPanel();
    JPanel center = new JPanel();
    JScrollPane scrollPane; //JScrollPane for the center panel
    JPanel south = new JPanel();
    Font font1,font2,font3;

    JButton bct,bcl,btr,bco,bbt;

    // student details
    JTextField tmname1,tmname2,tmname3, tmemail, tmpad, tmlad;
    NTextField  ntmsmbno, ntmpmbno;
    JComboBox cmcity,tmno;
    JRadioButton m,f;
    ButtonGroup genderGroup;
    JCheckBox same; // for address
    NJDatePicker dob;


    // educational details
    JRadioButton rb1,rb2,rb3,rb4;
    ButtonGroup bgacadyr;
    JComboBox tmclnm, tmtrnm;

    // course details
    JTextField ttime;
    JComboBox cmconm,cmbtnm;
    ButtonGroup bmattmode;
    JRadioButton online,offline;

    // transaction details
    NTextField ntmcofee,ntmadmfee,ntmbalfee;
    JRadioButton cash,cheque,on,account1,account2;
    ButtonGroup btrntype,bmaccopt;
    JComboBox cmtrdetail;

    DB ref;
    ResultSet rs;
    int cnt=0;

    AddCity act;
    AddCollege acl;
    AddTrade atr;
    AddCourse aco;
    AddBatch abt;
    ModStudent mst;

    JButton save,clear,back;

    int sid,cofee,admfee,balfee,acyear;
    String fname,mname,lname,fullname,gender,smobile,pmobile,semail,speradd,sloadd,scity,sclname,strname,sconame,sbatname,sbattime,smode,strtype,strdetail,stranto,formattedString;
    Date sdob,sdate;

     //Email sending
     String to,from,subject,message;

    java.util.Date redate;

    String tempdate;

    public ModStudent(JFrame parent,String frmnm,boolean state,DB ref)
    {
                super(parent,frmnm,state);

                this.ref=ref;
                
                mst=this;

                try
                {
                        java.util.Date dt=new java.util.Date();

                        tempdate = dateFormat.format(dt);

                        System.out.println("Date is " + tempdate);

                        redate=dateFormat.parse(tempdate);

                        System.out.println("Converted date "+ redate);
                }
                catch(Exception e){}

                title = new JLabel("Student Registration");
                realsoft=new JLabel("Realsoft Computers");
                title.setFont(new Font("Times New Roman",Font.BOLD,30));
                realsoft.setFont(new Font("Times New Roman",Font.BOLD,40));

                regdate=new JLabel("Date :" + tempdate);

                regdate.setFont(new Font("Seriff",Font.BOLD,15));

                try
                {
                    logo=new JLabel();
                    ImageIcon imageIcon = new ImageIcon(new ImageIcon("D:\\realsoft\\utility\\logo.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
                    logo.setIcon(imageIcon);  //D:\\realsoft\\display\\logo.jpg
                }
                catch(Exception e){System.out.println(""+e);}

                font1=new Font("Seriff",Font.BOLD,20);
                font2=new Font("Seriff",Font.BOLD,15);
                font3=new Font("Seriff",Font.PLAIN,15);
                //setLayout(null);
                center.setLayout(null);
                center.setPreferredSize(new Dimension(1100,1250));

                north.setLayout(null);
                north.setPreferredSize(new Dimension(1100,140));

                south.setLayout(null);
                south.setPreferredSize(new Dimension(1100,50));

                //--------------------------------------------------------------------------------
                //Student details
                sdetails = new JLabel("Student Details");

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

                adate=new NJDatePicker();
                tmname1=new JTextField(10);
                tmname2=new JTextField(10);
                tmname3=new JTextField(10);
                dob=new NJDatePicker();
                ntmsmbno=new NTextField(10);
                ntmpmbno=new NTextField(10);
                tmemail=new JTextField(10);
                tmpad=new JTextField(10);
                tmlad=new JTextField(10);
                
                cmcity = new JComboBox();

                tmname1.setText("First Name");
                tmname2.setText("Middle Name");
                tmname3.setText("Last Name");

                tmno=new JComboBox();
                m=new JRadioButton("Male",true);
                f=new JRadioButton("Female",false);
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


                //tmno.addFocusListener(this);
                tmname1.addFocusListener(this);
                tmname2.addFocusListener(this);
                tmname3.addFocusListener(this);
                ntmsmbno.addFocusListener(this);
                ntmpmbno.addFocusListener(this);
                tmemail.addFocusListener(this);
                tmpad.addFocusListener(this);
                tmlad.addFocusListener(this);

                try
                {
                    rs=ref.pstdis.executeQuery();
                    while(rs.next())
                    {
                        tmno.addItem(""+rs.getInt(1));
                    }
                }
                catch(Exception e1){}
                System.out.println("No Error");

                // tmname1.setText("First Name");
                //tmno.setEditable(false);
                tmno.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent e)
                        {
                                sid=Integer.parseInt(""+tmno.getSelectedItem());
                                try
                                {
                                    ref.pstser.setInt(1,sid);
                                    rs=ref.pstser.executeQuery();
                                    if(rs.next())
                                    {
                                        sid =rs.getInt(1);
                                        sdate = rs.getDate(2);
                                        String tmpdate1=dateFormat.format(sdate);
                                        fname = rs.getString(3);
                                        mname = rs.getString(4);
                                        lname = rs.getString(5);
                                        fullname = rs.getString(6);
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
                                        cofee = rs.getInt(22);
                                        admfee = rs.getInt(22);
                                        balfee = rs.getInt(24);
                                        strtype = rs.getString(25);
                                        strdetail = rs.getString(26);
                                        stranto = rs.getString(27);
                                        smode = rs.getString(28);


                                        //tmno.setText(sid);
                                        adate.dateField.setText(tmpdate1);
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
                                        dob.dateField.setText(tmpdate2);
                                        ntmsmbno.setText(smobile);
                                        ntmpmbno.setText(pmobile);
                                        tmemail.setText(semail);
                                        tmpad.setText(speradd);
                                        tmlad.setText(sloadd);
                                        cmcity.setSelectedItem(scity);
                                        tmclnm.setSelectedItem(sclname);
                                        tmtrnm.setSelectedItem(strname);
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

                                        cmconm.setSelectedItem(""+sconame);
                                        cmbtnm.setSelectedItem(""+sbatname);
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

                                        cmtrdetail.setSelectedItem(""+strdetail);

                                        if(stranto.equals("Account1"))
                                        {
                                            account1.setSelected(true);
                                            account2.setSelected(false);
                                        }

                                        System.out.println("Account :"+bmaccopt);

                                        if(stranto.equals("Account2"))
                                        {
                                            account2.setSelected(true);
                                            account1.setSelected(false);
                                        }
                                        //setVisible(false);
                                    }
                            }
                            catch(Exception e1){System.out.println(""+e1);}
                    }
                });


                cmcity.addFocusListener(new FocusAdapter() {
                        public void focusGained(FocusEvent e)
                        {
                                if(tmpad.getText().equals(""))
                                {
                                        tmpad.requestFocus();
                                        return;
                                }
                        }
                });

                bct=new JButton("...");

                same = new JCheckBox();

                m.addFocusListener(new FocusAdapter() {
                        public void focusGained(FocusEvent e)
                        {
                                if(tmname3.getText().equals(""))
                                {
                                        tmname3.requestFocus();
                                        return;
                                }
                        }
                });

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
                bct.setBounds(530,370,30,30);
                lmsame.setBounds(600, 370, 130, 30);
                same.setBounds(740, 370, 200, 30);
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
                center.add(lmcity);center.add(cmcity);center.add(bct);
                center.add(lmsame);center.add(same);
                center.add(lmlad);center.add(tmlad);

                try
                {
                        rs=ref.pctdis.executeQuery();

                        while(rs.next())
                        {
                                String ct=rs.getString(2);
                                cmcity.addItem(ct);
                        }
                }
                catch(Exception e){}



                try
                {
                        rs = ref.pstcnt.executeQuery();
                        if (rs.next())
                        cnt = rs.getInt(1);
                        cnt+=1;
                        tmno.setSelectedItem(""+cnt);
                }
                catch(Exception e){}

                //action listeners

                bct.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e)
                        {
                                act=new AddCity(null, "Add City", true, ref);
                                try
                                {
                                        cmcity.removeAllItems();

                                        ResultSet rs=ref.pctdis.executeQuery();
                                        while(rs.next())
                                        {
                                                String ct=rs.getString(2);
                                                System.out.println(ct);
                                                cmcity.addItem(ct);
                                        }
                                }
                                catch(Exception e1){}


                        }
                });

                same.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e)
                        {
                                if(same.isSelected())
                                {
                                        String newadd=tmpad.getText();
                                        tmlad.setText(newadd);
                                        tmlad.setEditable(false);
                                }
                                else
                                {
                                        tmlad.setEditable(true);
                                        tmlad.setText("");
                                }


                        }
                });

                //-------------------------------------------------------------------------------
                //educational details
                edetails = new JLabel("Educational Details");
                lmclnm = new JLabel("College Name");
                lmtrnm = new JLabel("Trade");
                lmacdyr = new JLabel("Academic Year");

                edetails.setForeground(Color.RED);

                tmclnm = new JComboBox();
                tmclnm.addFocusListener(new FocusAdapter() {
                        public void focusGained(FocusEvent e)
                        {
                                if(tmlad.getText().equals(""))
                                {
                                        tmlad.requestFocus();
                                        return;
                                }
                        }
                });


                tmtrnm = new JComboBox();

                bcl=new JButton("...");
                btr=new JButton("...");

                rb1=new JRadioButton("1",true);
                rb2=new JRadioButton("2",false);
                rb3=new JRadioButton("3",false);
                rb4=new JRadioButton("4",false);

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
                bcl.setBounds(530,530,30,30);
                lmtrnm.setBounds(150, 580, 150, 30);
                tmtrnm.setBounds(320, 580, 200, 30);
                btr.setBounds(530,580,30,30);
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
                center.add(lmclnm);center.add(tmclnm);center.add(bcl);
                center.add(lmtrnm);center.add(tmtrnm);center.add(btr);
                center.add(lmacdyr);center.add(rb1);center.add(rb2);center.add(rb3);center.add(rb4);

                try
                {
                        rs=ref.pcldis.executeQuery();
                        while(rs.next())
                        {
                                String cl=rs.getString(2);
                                tmclnm.addItem(cl);
                        }

                        rs=ref.ptrdis.executeQuery();
                        while(rs.next())
                        {
                                String tr=rs.getString(2);
                                tmtrnm.addItem(tr);
                        }
                }
                catch(Exception e){}

                //action listeners
                bcl.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e)
                        {
                                acl=new AddCollege(null, "Add College", true, ref);
                                System.out.println("Here college");
                                try
                                {
                                        tmclnm.removeAllItems();

                                        ResultSet rs=ref.pcldis.executeQuery();
                                        while(rs.next())
                                        {
                                                String cl=rs.getString(2);
                                                tmclnm.addItem(cl);
                                        }
                                }
                                catch(Exception e1){}
                        }
                });

                btr.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e)
                        {
                                atr=new AddTrade(null, "Add Trade", true, ref);
                                System.out.println("Here Trade");
                                try
                                {
                                        tmtrnm.removeAllItems();

                                        ResultSet rs=ref.ptrdis.executeQuery();
                                        while(rs.next())
                                        {
                                                String tr=rs.getString(2);

                                                tmtrnm.addItem(tr);
                                        }
                                }
                                catch(Exception e1){
                                        System.out.println("Exception "+e1);
                                }
                        }
                });

                //------------------------------------------------------------------------------------------
                //course details
                cdetails = new JLabel("Course Details");
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
                cmconm=new JComboBox();
                cmbtnm=new JComboBox();
                online=new JRadioButton("Online",true);
                offline=new JRadioButton("Offline",true);
                online.setFont(font2);
                offline.setFont(font2);
                bmattmode=new ButtonGroup();
                bmattmode.add(online);
                bmattmode.add(offline);

                ttime.setFont(font3);
                online.setFont(font3);
                offline.setFont(font3);
                cmconm.setFont(font3);
                cmbtnm.setFont(font3);

                bco=new JButton("...");
                bbt=new JButton("...");

                bco=new JButton("...");
                bbt=new JButton("...");



                cdetails.setBounds(150,690,200,40);
                lmconm.setBounds(150,740,150,30);
                cmconm.setBounds(320,740,200,30);
                bco.setBounds(530,740,30,30);
                lmbtnm.setBounds(150,790,150,30);
                cmbtnm.setBounds(320,790,200,30);
                bbt.setBounds(530,790,30,30);
                ltime.setBounds(600,790,130,30);
                ttime.setBounds(750,790,200,30);
                lmattmode.setBounds(150,840,150,30);
                online.setBounds(320,840,100,30);
                offline.setBounds(440,840,100,30);

                center.add(cdetails);
                center.add(lmconm);center.add(cmconm);center.add(bco);
                center.add(lmbtnm);center.add(cmbtnm);center.add(bbt);
                center.add(ltime);center.add(ttime);
                center.add(lmattmode);
                center.add(online);center.add(offline);


                try
                {
                        rs=ref.pcodis.executeQuery();
                        while(rs.next())
                        {
                                String co=rs.getString(2);
                                cmconm.addItem(co);
                        }

                        rs=ref.pbtdis.executeQuery();
                        while(rs.next())
                        {
                                String bt=rs.getString(2);
                                System.out.println("Batch :"+bt);
                                cmbtnm.addItem(bt);
                        }
                }
                catch(Exception e){}


                //action listeners
                bco.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e)
                        {
                                aco=new AddCourse(null, "Add Course", true, ref);
                                System.out.println("Here Course");
                                try
                                {
                                        cmconm.removeAllItems();

                                        ResultSet rs=ref.pcodis.executeQuery();

                                        while(rs.next())
                                        {
                                                String co=rs.getString(2);
                                                cmconm.addItem(co);
                                        }
                                }
                                catch(Exception e1)
                                {
                                        System.out.println("Exception Course" + e1);
                                }
                        }
                });
/*
                bbt.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e)
                        {
                                abt=new AddBatch(null, "Add Batch", true, ref);
                                System.out.println("Here Batch");
                                try
                                {
                                        cmbtnm.removeAllItems();
                                        System.out.println("Inside Try batch");

                                        ResultSet rs=ref.pbtdisnm.executeQuery();

                                        while(rs.next())
                                        {
                                                String batch=rs.getString(1);
                                                System.out.println("Batche :"+batch);
                                                cmbtnm.addItem(batch);
                                        }
                                }
                                catch(Exception e1)
                                {
                                        System.out.println("Exception Batch "+e1);
                                }

                                System.out.println("Outside try batch");
                        }
                });
 */
                cmconm.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e)
                        {
                                String conm=""+cmconm.getSelectedItem();

                                try
                                {
                                        ref.pcosernm.setString(1,conm);
                                        rs=ref.pcosernm.executeQuery();

                                        if(rs.next())
                                        {
                                                int corfee=rs.getInt(4);

                                                ntmcofee.setText(""+corfee);
                                                ntmadmfee.setText("");
                                                ntmbalfee.setText("");
                                        }

                                }
                                catch(Exception e1){}

                        }
                });

                cmbtnm.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e)
                        {
                                String btnm=""+cmbtnm.getSelectedItem();

                                try
                                {
                                        ref.pbtsernm.setString(1,btnm);
                                        rs=ref.pbtsernm.executeQuery();

                                        if(rs.next())
                                        {
                                                String time=rs.getString(5);

                                                ttime.setText(time);
                                        }

                                }
                                catch(Exception e1){}

                        }
                });

                //--------------------------------------------------------------------------------------
                //transaction details
                cfdetails = new JLabel("Transaction Details");
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

                ntmbalfee.addFocusListener(this);
                ntmadmfee.addFocusListener(this);

                ntmadmfee.addFocusListener(new FocusAdapter() {
                        public void focusLost(FocusEvent e)
                        {
                                if(!ntmadmfee.getText().equals("") && !ntmadmfee.getText().equals("0")  )
                                {
                                        int cofee=Integer.parseInt(""+ntmcofee.getText());
                                        int adfee=Integer.parseInt(""+ntmadmfee.getText());
                                        int res=cofee-adfee;
                                        ntmbalfee.setText(""+res);
                                }
                        }
                });
                cmtrdetail=new JComboBox();

                cash=new JRadioButton("Cash",true);
                cheque=new JRadioButton("Cheque",false);
                on=new JRadioButton("Online",false);



                cash.addFocusListener(new FocusAdapter() {
                        public void focusGained(FocusEvent e)
                        {
                                if(ntmbalfee.getText().equals(""))
                                {
                                        ntmbalfee.requestFocus();
                                        return;
                                }
                        }
                });

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

                bmaccopt = new ButtonGroup();
                bmaccopt.add(account1);
                bmaccopt.add(account2);

                cmtrdetail.addItem("GooglePay");
                cmtrdetail.addItem("PhonePay");
                cmtrdetail.addItem("NEFT");
                cmtrdetail.addItem("Cheque");
                cmtrdetail.addItem("Cash");


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



                //Buttons
                save=new JButton("MODIFY");
                clear=new JButton("CLEAR");
                back=new JButton("BACK");

                save.addActionListener(this);
                clear.addActionListener(this);
                back.addActionListener(this);

                save.setFont(font2);
                clear.setFont(font2);
                back.setFont(font2);

                south.setLayout(null);

                save.setBounds(200,10,100,30);
                clear.setBounds(500,10,100,30);
                back.setBounds(800,10,100,30);

                south.add(clear);
                south.add(save);
                south.add(back);

                north.setLayout(null);

                logo.setBounds(50,20,100,100);
                realsoft.setBounds(400,20,500,50);
                title.setBounds(430,90,400,40);
                regdate.setBounds(875,100,200,30);

                north.add(title);
                north.add(logo);
                north.add(regdate);
                north.add(realsoft);

                scrollPane = new JScrollPane(center);
                scrollPane.getVerticalScrollBar().setUnitIncrement(10);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                //south.setLayout(new FlowLayout(FlowLayout.CENTER));
                



                setLayout(new BorderLayout());
                add(north, BorderLayout.NORTH);
                add(scrollPane, BorderLayout.CENTER);
                add(south, BorderLayout.SOUTH);



                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                setSize(1100,800);
                setVisible(true);
        }

        public void actionPerformed(ActionEvent e)
        {
            JButton b=(JButton)e.getSource();
            if(b==save)
            {
                sid=Integer.parseInt(""+tmno.getSelectedItem());

                //sdate=(Date)adate.getSqlDate();
                fname=tmname1.getText();
                mname=tmname2.getText();
                lname=tmname3.getText();
                fullname = fname + " " + mname + " " + lname;

                if(m.isSelected())
                {
                    gender=m.getText();
                }
                if(f.isSelected())
                {
                        gender=f.getText();
                }
                //sdob=(Date)dob.getSqlDate();
                smobile=ntmsmbno.getText();
                pmobile=ntmpmbno.getText();
                semail=tmemail.getText();
                speradd=tmpad.getText();
                sloadd=tmlad.getText();
                scity=""+cmcity.getSelectedItem();
                sclname=""+tmclnm.getSelectedItem();
                strname=""+tmtrnm.getSelectedItem();

                
                if(rb1.isSelected())
                {
                    acyear=Integer.parseInt(""+rb1.getText());
                }
                if(rb2.isSelected())
                {
                    acyear=Integer.parseInt(""+rb2.getText());
                }
                if(rb3.isSelected())
                {
                    acyear=Integer.parseInt(""+rb3.getText());
                }
                if(rb4.isSelected())
                {
                    acyear=Integer.parseInt(""+rb4.getText());
                }
                sconame=""+cmconm.getSelectedItem();
                sbatname=""+cmbtnm.getSelectedItem();
                String btnm=""+cmbtnm.getSelectedItem();
                try
                {
                        ref.pbtsernm.setString(1,btnm);
                        rs=ref.pbtsernm.executeQuery();
                        if(rs.next())
                        {
                                sbattime=rs.getString(5);

                        }
                }
                catch(Exception e1){}
                if(online.isSelected())
                {
                    smode=online.getText();
                }
                if(offline.isSelected())
                {
                    smode=offline.getText();
                }
                cofee=Integer.parseInt(""+ntmcofee.getText());
                admfee=Integer.parseInt(""+ntmadmfee.getText());
                balfee=Integer.parseInt(""+ntmbalfee.getText());
                if(cash.isSelected())
                {
                    strtype=cash.getText();
                }
                if(cheque.isSelected())
                {
                    strtype=cheque.getText();
                }
                if(on.isSelected())
                {
                    strtype=on.getText();
                }
                strdetail=""+cmtrdetail.getSelectedItem();
                if(account1.isSelected())
                {
                    stranto=account1.getText();
                }
                if(account2.isSelected())
                {
                    stranto=account2.getText();
                }

                try
                {
                        ref.pstmod.setDate(1, sdate);
                        ref.pstmod.setString(2, fname);
                        ref.pstmod.setString(3, mname);
                        ref.pstmod.setString(4, lname);
                        ref.pstmod.setString(5, fullname);
                        ref.pstmod.setString(6, gender);
                        ref.pstmod.setDate(7, sdob);
                        ref.pstmod.setString(8, sclname);
                        ref.pstmod.setString(9, strname);
                        ref.pstmod.setInt(10, acyear);
                        ref.pstmod.setString(11, speradd);
                        ref.pstmod.setString(12, sloadd);
                        ref.pstmod.setString(13, scity);
                        ref.pstmod.setString(14, smobile);
                        ref.pstmod.setString(15, pmobile);
                        ref.pstmod.setString(16, semail);
                        ref.pstmod.setString(17, sconame);
                        ref.pstmod.setString(18, sbatname);
                        ref.pstmod.setString(19, sbattime);
                        ref.pstmod.setInt(20, cofee);
                        ref.pstmod.setInt(21, admfee);
                        ref.pstmod.setInt(22, admfee);
                        ref.pstmod.setInt(23, balfee);
                        ref.pstmod.setString(24, strtype);
                        ref.pstmod.setString(25, strdetail);
                        ref.pstmod.setString(26, stranto);
                        ref.pstmod.setString(27, smode);
                        ref.pstmod.setInt(28, sid);
                        ref.pstmod.executeUpdate();
                        setVisible(false);


                        GEmailSender gEmailSender =new GEmailSender();
                        to=semail;
                        from="prathameshb343@gmail.com"; 
                        subject="Admission in Realsoft Computers";
                        //message="This is a example email";
                        message="Hello "+ fname +" "+mname+" "+lname+ ",\nyour data is successfully updated." +",\n"+"Admission Date : "+adate.dateField.getText()+",\n"+"Batch Name: "+sbatname+"    Timing : " + sbattime +",\n"+"Course Fees : "+cofee+"\nFee Paid : "+admfee+"\nBalance Fees : "+balfee+"\nThank you!!!";
                        
                        boolean b1 = gEmailSender.sendEmail(to, from, subject, message);
                        
                        if(b1)
                        {
                            System.out.println("Success");
                        }
                        else
                        {
                            System.out.println("Error");
                        }
        
                        System.exit(0);
                }
                catch(Exception e1){System.out.println("Here"+e1);}
            }
            if(b==clear)
            {

                tmname1.setText("First Name");
                tmname2.setText("Middle Name");
                tmname3.setText("Last Name");
                tmemail.setText("");
                tmpad.setText("");
                tmlad.setText("");
                ntmsmbno.setText("");
                ntmpmbno.setText("");
                ntmadmfee.setText("");
                ntmbalfee.setText("");
                ntmcofee.setText("");
                adate.dateField.setText("");
                dob.dateField.setText("");

                tmname1.requestFocus();
                return;
            }
            if(b==back)
            {
                mst.dispose();
            }
        }
        //Focus Listeners
        public void focusGained(FocusEvent e)
        {
                JTextField t=(JTextField)e.getSource();

                if(t==tmname1)
                {
                      tmname1.setText("");
                }


                if(t==tmname2)
                {
                        tmname2.setText("");
                        if(tmname1.getText().equals(""))
                        {
                                tmname1.requestFocus();
                                return;
                        }

                }
                if(t==tmname3)
                {
                        tmname3.setText("");
                        if(tmname2.getText().equals(""))
                        {
                                tmname2.requestFocus();
                                return;
                        }
                }

                if(t==ntmsmbno)
                {
                        if(dob.dateField.getText().equals(""))
                        {
                                dob.dateField.requestFocus();
                                return;
                        }
                }

                if(t==ntmpmbno)
                {
                        if(ntmsmbno.getText().equals(""))
                        {
                                ntmsmbno.requestFocus();
                                return;
                        }
                }

                if(t==tmemail)
                {
                        if(ntmpmbno.getText().equals(""))
                        {
                                ntmpmbno.requestFocus();
                                return;
                        }
                }

                if(t==tmpad)
                {
                        if(tmemail.getText().equals(""))
                        {
                                tmemail.requestFocus();
                                return;
                        }
                }

                if(t==tmpad)
                {
                        if(tmemail.getText().equals(""))
                        {
                                tmemail.requestFocus();
                                return;
                        }
                }

                if(t==ntmadmfee)
                {
                        if(ntmcofee.getText().equals(""))
                        {
                                ntmcofee.requestFocus();
                                return;
                        }
                }

                if(t==ntmbalfee)
                {

                        if(ntmadmfee.getText().equals(""))
                        {
                                ntmadmfee.requestFocus();
                                return;
                        }
                        else
                        {
                                int feepaid=Integer.parseInt(""+ntmadmfee.getText());
                                int cofee=Integer.parseInt(""+ntmcofee.getText());
                                if(feepaid <= 0 || feepaid>cofee)
                                {
                                        ntmadmfee.requestFocus();
                                        ntmbalfee.setText("");
                                        return;
                                }
                        }

                }
    }

    public void focusLost(FocusEvent e){}
}