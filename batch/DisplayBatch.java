package batch;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.sql.*;
import java.text.SimpleDateFormat;

import utility.*;

public class DisplayBatch extends JDialog implements ActionListener
{
    //heading
    JLabel lhead;

    JTable jt;

    DefaultTableModel dtm;

    //to provide scrolling option to JTable
    JScrollPane jsp;

    TableModel tableModel;

    JButton print,back;

    DB ref;

    ResultSet rs;

    String tmpstdate,tmpeddate,btnm,bttime;
    int btid;
    Date stdate,eddate;

    SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");

    Font tfont,lfont;

    public DisplayBatch(JFrame frm,String title,boolean state,DB ref)
    {
        super(frm,title,state);

        this.ref=ref;

        lhead=new JLabel("Batch Data");
        lhead.setFont(new Font("Times New Roman", Font.BOLD, 40));

        tfont = new Font("Seriff", Font.PLAIN, 15);
        lfont = new Font("Seriff", Font.BOLD, 15);

        dtm=new DefaultTableModel(new Object [][]{},new String[]{"Batch ID","Batch Name","Start date","End date","Timing"});

        try
        {
            rs=ref.pbtdis.executeQuery();

            int i=0;
            while(rs.next())
            {
                btid=rs.getInt(1);
                btnm=rs.getString(2);
                stdate=(Date)rs.getDate(3);
                eddate=(Date)rs.getDate(4);
                bttime=rs.getString(5);

                tmpstdate=dateFormat.format(stdate);
                tmpeddate=dateFormat.format(eddate);

                dtm.insertRow(i,new String[]{""+btid,btnm,tmpstdate,tmpeddate,bttime});

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

        jt.getTableHeader().setFont(lfont);

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

        lhead.setBounds(200, 30, 300, 40);
        jsp.setBounds(50,90,500,400);
        print.setBounds(100,510,150,30);
        back.setBounds(350, 510, 150, 30);

        add(lhead);
        add(jsp);
        add(print);
        add(back);

        setSize(600,600);
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