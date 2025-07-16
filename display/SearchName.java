package display;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.EventObject;
import java.util.Vector;
import java.sql.*;

import utility.*;
import display.*;


public class SearchName extends JDialog
{

    JLabel heading,lnm;

    JTextField tnm;

    DefaultTableModel dtm;
    TableModel tableModel;

    JTable jt;

    JScrollPane jsp;

    JButton back;

    ResultSet rs;

    DB ref;

    Font tfont;

    SearchStudentRollNo serstrno;

    SearchName sernm; ///to store the reference of this object

    public SearchName(JFrame parent,String title,boolean state,DB ref)
    {
        super(parent,title,state);

        this.ref=ref;

        sernm=this;

        heading=new JLabel("Search Student Name");
        heading.setFont(new Font("Times New Roman", Font.BOLD, 40));

        tfont = new Font("Seriff", Font.PLAIN, 15);

        lnm=new JLabel("Enter Name");

        tnm=new JTextField(50);

        back=new JButton("BACK");

        lnm.setFont(tfont);
        tnm.setFont(tfont);

        dtm=new DefaultTableModel(new Object[][]{},new String[]{"Roll No","Full Name","Course"});

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

        jt.getColumnModel().getColumn(0).setPreferredWidth(7);

        jsp=new JScrollPane(jt);

        tnm.addKeyListener(new KeyAdapter() 
        {
            public void keyReleased(KeyEvent e)
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
                    
                    if(!tnm.getText().equals(""))
                    {
                        String parameter="%"+tnm.getText()+"%";
                        ref.pstdisnm.setString(1,parameter);

                        rs=ref.pstdisnm.executeQuery();

                        int i=0;
                        while(rs.next())
                        {
                            dtm.insertRow(i,new String[]{""+rs.getInt(1),rs.getString(2),rs.getString(3)});
                            i++;
                        }
                    }  
                }
                catch(Exception e1){
                    System.out.println(""+e1);
                }
            }
        });

        jt.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jt.addMouseListener(new MouseAdapter()  
        {
            public void mouseClicked(MouseEvent e)
            {
                int row=jt.getSelectedRow();

                int rollno=Integer.parseInt(""+jt.getValueAt(row, 0));

                serstrno=new SearchStudentRollNo(parent, "Search Student Name Wise", true, ref, rollno);
            }
        });

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                sernm.dispose();
            }
        });

        setLayout(null);

        heading.setBounds(50,50,400,40);
        lnm.setBounds(50,120,100,30);
        tnm.setBounds(160,120,260,30);
        jsp.setBounds(50,180,600,400);
        back.setBounds(200,600,100,30);

        add(heading);
        add(lnm);
        add(tnm);
        add(jsp);
        add(back);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setSize(700,700);
        setVisible(true);


    }
}