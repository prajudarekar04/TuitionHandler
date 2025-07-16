package course;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import utility.DB;

import java.sql.*;
import java.util.*;

public class DisplayCourse extends JDialog implements ActionListener
{
    JLabel lhead;

    JTable jt;

    DefaultTableModel dtm;

    JScrollPane jsp;

    JButton print,back;

    DB ref;

    ResultSet rs;

    Font tfont;

    TableModel tableModel;

    public DisplayCourse(JFrame frm,String title,boolean state,DB ref)
    {
        super(frm,title,state);

        this.ref=ref;
        
        lhead=new JLabel("Course Data");
        lhead.setFont(new Font("Times New Roman", Font.BOLD, 40));

        tfont = new Font("Seriff", Font.PLAIN, 15);

        dtm=new DefaultTableModel(new Object [][]{},new String[]{"Course Id","Course Name","Reguler Fee","Discounted Fee"});
        
        try
        {
            rs=ref.pcodis.executeQuery();
            int i=0;
            while(rs.next())
            {
                dtm.insertRow(i,new String[]{""+rs.getInt(1),rs.getString(2),""+rs.getFloat(4),""+rs.getFloat(3)});
                i++;
            }
        }
        catch(Exception e){System.out.println(e);}

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

        print=new JButton("PRINT");
        back=new JButton("BACK");

        print.addActionListener(this);
        back.addActionListener(this);

        setLayout(null);

        lhead.setBounds(200, 30, 300, 40);
        jsp.setBounds(50,90,500,300);
        print.setBounds(100,410,150,30);
        back.setBounds(350, 410, 150, 30);

        add(lhead);
        add(jsp);
        add(print);
        add(back);

        setSize(600,500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        JButton b=(JButton)e.getSource();

        if(b==print)
        {
            try
            {
                jt.print();
            }
            catch(Exception e1){}
        }
        else
            setVisible(false);
    }
}
