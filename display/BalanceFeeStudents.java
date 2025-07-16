package display;

import javax.swing.*;
import javax.swing.table.*;

import threads.SendEmail;

import java.awt.*;
import java.awt.event.*;
import java.util.EventObject;
import java.sql.*;

import utility.*;


public class BalanceFeeStudents extends JDialog
{

    JLabel heading;

    //JTextField tnm;

    DefaultTableModel dtm;
    TableModel tableModel;
    JTable jt;
    String stnm;
    JScrollPane jsp;

    JButton back,sendReminder;

    ResultSet rs,emailRS;

    DB ref;

    Font tfont;

    SearchStudentRollNo serstrno;

    BalanceFeeStudents balst;

    public BalanceFeeStudents(JFrame parent,String title,boolean state,DB ref)
    {
        super(parent,title,state);

        this.ref=ref;

        balst=this;

        heading=new JLabel("Pending Fees Student");
        heading.setFont(new Font("Times New Roman", Font.BOLD, 40));

        tfont = new Font("Seriff", Font.PLAIN, 15);

        back=new JButton("BACK");

        sendReminder=new JButton("SEND REMINDER");
    
        dtm=new DefaultTableModel(new Object[][]{},new String[]{"Roll No","Full Name","Batch","Course","Balance Fees"});
        
        jt=new JTable(dtm)
        {
            public boolean editCellAt(int row,int col,EventObject e)
            {
                return false;
            }
        };
        
        jt.getTableHeader().setFont(tfont);
        jt.setFont(tfont);
        
        jsp=new JScrollPane(jt);
        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment( SwingConstants.CENTER);
        
        tableModel = jt.getModel();
        
        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++)
        {
            jt.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
        
        jt.getColumnModel().getColumn(0).setPreferredWidth(2);
        jt.getColumnModel().getColumn(1).setPreferredWidth(200);
        
        jsp=new JScrollPane(jt);
        
        try
        {
            this.rs=ref.pstserbalfee.executeQuery();
            this.emailRS=rs;
            
            int i=0;
            while(rs.next())
            {
                dtm.insertRow(i,new String[]{""+rs.getInt(1),rs.getString(6),rs.getString(19),rs.getString(18),""+rs.getInt(24)});

                i++;
            }
        }
        catch(Exception e){}
        
        jt.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        
        jt.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e)
            {
                int row=jt.getSelectedRow();
                
                int roll_no = Integer.parseInt(""+jt.getValueAt(row, 0));
                
                serstrno=new SearchStudentRollNo(parent, "Search Student Batch Wise", true, ref, roll_no);
            }
        });


        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                balst.setVisible(false);
            }
        }); 

        sendReminder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try
                {
                    System.out.println("res"+""+emailRS.next());
                    SendEmail b=new SendEmail(ref);
                }
                catch(Exception e3){}
                
                balst.setVisible(false);
            }
        });
        
        setLayout(null);
        
        heading.setBounds(300,50,400,40);
        jsp.setBounds(50,100,900,600);
        back.setBounds(400,730,100,30);
        sendReminder.setBounds(600,730,150,30);
        
        add(heading);
        add(jsp);
        add(back);
        add(sendReminder);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1000,1000);
        setVisible(true);
        
    }
}