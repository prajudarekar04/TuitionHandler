package college;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.sql.*;

import utility.*;

public class DisplayCollege extends JDialog implements ActionListener
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

    public DisplayCollege(JFrame frm,String title,boolean state,DB ref)
    {
        super(frm,title,state);

        this.ref=ref;

        lhead=new JLabel("College Data");
        lhead.setFont(new Font("Times New Roman", Font.BOLD, 40));

        tfont = new Font("Seriff", Font.PLAIN, 15);

        dtm=new DefaultTableModel(new Object [][]{},new String[]{"College Id","College Name"});

        try
        {
            rs=ref.pcldis.executeQuery();
            int i=0;
            while(rs.next())
            {
                dtm.insertRow(i,new String[]{""+rs.getInt(1),rs.getString(2)});
                i++;
            }

        }
        catch(Exception e){}

        jt=new JTable(dtm)
        {
			public boolean editCellAt(int row,int col,java.util.EventObject e)
			{
				return false;
			}
		};

        jt.getTableHeader().setFont(new Font("Seriff", Font.BOLD, 15));

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment( SwingConstants.CENTER);

        tableModel = jt.getModel();

        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++)
        {
            jt.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }


        jt.setFont(tfont);

        jsp=new JScrollPane(jt);

        print=new JButton("PRINT");
        back=new JButton("BACK");

        print.addActionListener(this);
        back.addActionListener(this);

        setLayout(null);

        lhead.setBounds(150, 30, 300, 40);
        jsp.setBounds(50,90,400,300);
        print.setBounds(100,410,100,30);
        back.setBounds(300, 410, 100, 30);

        add(lhead);
        add(jsp);
        add(print);
        add(back);

        setSize(500,500);
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
