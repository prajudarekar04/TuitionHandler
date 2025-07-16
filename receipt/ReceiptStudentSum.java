package receipt;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import java.awt.print.*;
import java.sql.*;
import java.text.*;

import javax.swing.*;
import javax.swing.table.*;

import utility.*;

public class ReceiptStudentSum extends JDialog
{
   
    JLabel lrs, lhead, lrollno, lname, lbatch, lcourse, lfee, linstallfee, ltotfee, lpaid, lrem;
    JLabel ltime;
    JLabel limg;
    Image img;
    JTable jt;
    DefaultTableModel dtm;
    TableModel tableModel;
    JScrollPane jsp;
    JButton print, back;
    DB ref;
    //ResultSet rs;
    Font lfont, tfont;

    // temp variables
    public int rollno,stadmfee,stcofee,stbalfee,stpaidfee;
    String stname,stbtnm,stconm;

    int reamt;
    String remode,redetails;
    Date redate;

    ResultSet rs;

    AddReceipt addre;

    ReceiptStudentSum recstusum;

    
    SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat dateFormat2= new SimpleDateFormat("dd/MM/yyyy      hh:mm a");

    public ReceiptStudentSum(JFrame parent,String title,boolean state,DB ref,int rollno)
    {
        super(parent,title,state);

        this.ref=ref;

        recstusum=this;

        try
        {
            limg=new JLabel();
            ImageIcon imageIcon = new ImageIcon(new ImageIcon("D:\\realsoft\\display\\logo.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            limg.setIcon(imageIcon);  //D:\\realsoft\\display\\logo.jpg
        }
        catch(Exception e){System.out.println(""+e);}

        lrs=new JLabel("Realsoft Computers");
        lrs.setFont(new Font("Times New Roman",Font.BOLD,40));

        lhead=new JLabel("Student Receipt Record");
        lhead.setFont(new Font("Times New Roman", Font.BOLD, 25));

        java.util.Date dt=new java.util.Date();
        String time=dateFormat2.format(dt);
        ltime=new JLabel(time);

        lrollno = new JLabel("Roll No :");
        lname = new JLabel("Name :");
        lbatch = new JLabel("Batch :");
        lcourse = new JLabel("Course :");
        lfee = new JLabel("Fee :");
        linstallfee = new JLabel("Admission Fee :");
        //ltotfee = new JLabel("Total Fee");
        lpaid = new JLabel("Paid Fee :");
        lrem = new JLabel("Remaining Fee :");

        //crollno = new ComboBox();

        lfont = new Font("Seriff", Font.BOLD, 15);
        tfont = new Font("Seriff", Font.PLAIN, 15);

        lrollno.setFont(lfont);
        lname.setFont(lfont);
        lbatch.setFont(lfont);
        lcourse.setFont(lfont);
        lfee.setFont(lfont);
        linstallfee.setFont(lfont);
        //ltotfee.setFont(lfont);
        lpaid.setFont(lfont);
        lrem.setFont(lfont);

        dtm=new DefaultTableModel(new Object [][]{},new String[]{"No","Date","Amount","Mode","Details"});
        jt=new JTable(dtm)
        {
			public boolean editCellAt(int row,int col,java.util.EventObject e)
			{
				return false;
			}
		};

        jt.setFont(tfont);
        jt.getTableHeader().setFont(lfont);
        jt.getColumnModel().getColumn(0).setPreferredWidth(10);
        jt.getColumnModel().getColumn(3).setPreferredWidth(15);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment( SwingConstants.CENTER);

        tableModel = jt.getModel();
        
        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++)
        {
            jt.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }

        jsp=new JScrollPane(jt);
        
        print=new JButton("PRINT");
        back=new JButton("BACK");

        //Window Opened
        lrollno.setText("Roll No : "+rollno);

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

                System.out.println(""+stname);
            }

            lname.setText("Name : "+stname);
            lbatch.setText("Batch : "+stbtnm);
            lcourse.setText("Course : "+stconm);
            lfee.setText("Total Fees : "+stcofee);
            linstallfee.setText("Admission Fee : "+stadmfee);
        }   
        catch(Exception e){
            System.out.println(""+e);
        }

        //Insert fee record into table 
        System.out.println("Rollll :"+rollno);
        fillTable(rollno);

        //action listeners
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                recstusum.dispose();
            }
        });

        setLayout(null);

        limg.setBounds(20,20,100,100);
        lrs.setBounds(150,10,400,40);
        lhead.setBounds(185,60,400,30);
        ltime.setBounds(250,100,200,30);
        lrollno.setBounds(10,140,100,30);
        lname.setBounds(10,180,400,30);
        lbatch.setBounds(10,220,500,30);
        lcourse.setBounds(10,260,400,30);
        lfee.setBounds(10,300,275,30);
        linstallfee.setBounds(300,300,275,30);
        jsp.setBounds(8,340,572,250);
        //ltotfee.setBounds(10,690,400,30);
        lpaid.setBounds(10,600,275,30);
        lrem.setBounds(300,600,275,30);

        print.setBounds(100,650,150,30);
        back.setBounds(350,650,150,30);
        
        // ADD
        add(limg);
        add(lrs);
        add(lhead);
        add(ltime);
        add(lrollno);
        add(lname);
        add(lbatch);
        add(lcourse);
        add(lfee);
        add(linstallfee);
        //add(ltotfee);
        add(lpaid);
        add(lrem);
        add(jsp);
        add(print);
        add(back);
        

        setSize(600,740);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void fillTable(int rollno)
    {
        try
        {
            int count=dtm.getRowCount();
            count--;
            while(count>=0)
            {
                dtm.removeRow(count);
                count--;
            }

            ref.predis.setInt(1, rollno);

            rs=ref.predis.executeQuery();

            int i=0;

            System.out.println("Roll No :"+rollno);
            while(rs.next())
            {
                int reno=rs.getInt(1);
                redate=rs.getDate(2);
                reamt=rs.getInt(4);
                remode=rs.getString(5);
                redetails=rs.getString(6);

                dtm.insertRow(i,new String[]{""+reno,""+redate,""+reamt,""+remode,""+redetails});
                i++;
            }


            ref.pstfees.setInt(1, rollno);

            rs=ref.pstfees.executeQuery();

            if(rs.next())
            {
                System.out.println("hiiii");
                stpaidfee=rs.getInt(1);
                stbalfee=rs.getInt(2);

                lpaid.setText("Paid Fees : "+stpaidfee);
                lrem.setText("Balance Fees : "+stbalfee);
            }

        } 
        catch(Exception e){
            System.out.println(""+e);
        }
    }
}