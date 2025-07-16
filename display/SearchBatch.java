package display;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.EventObject;
import java.sql.*;

import utility.*;


public class SearchBatch extends JDialog implements ActionListener
{

    JLabel heading,lnm;

    //JTextField tnm;

    DefaultTableModel dtm;
    TableModel tableModel;
    JComboBox<String> cmbt;
    JTable jt;
    String stnm;
    JScrollPane jsp;

    JButton back;

    ResultSet rs;

    DB ref;

    Font tfont;

    SearchStudentRollNo serstrno;

    SearchBatch serbt;

    public SearchBatch(JFrame parent,String title,boolean state,DB ref)
    {
        super(parent,title,state);

        this.ref=ref;

        serbt=this;

        heading=new JLabel("Search Student");
        heading.setFont(new Font("Times New Roman", Font.BOLD, 40));

        tfont = new Font("Seriff", Font.PLAIN, 15);

        cmbt=new JComboBox<String>();
        lnm=new JLabel("Batch");

        back=new JButton("BACK");

        cmbt.setFont(tfont);
        
        lnm.setFont(tfont);
        
        
        dtm=new DefaultTableModel(new Object[][]{},new String[]{"Roll No","Full Name"});
        
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
        
        try
        {
            rs=ref.pbtdisnm.executeQuery();
            
            while(rs.next())
            {
                String bt=rs.getString(1);
                
                cmbt.addItem(bt);
            }
        }
        catch(Exception e){}
        
        cmbt.addActionListener(this);
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
                serbt.dispose();
            }
        }); 
        
        setLayout(null);
        
        heading.setBounds(100,50,400,40);
        lnm.setBounds(50,120,100,30);
        cmbt.setBounds(160, 120, 130, 30);
        jsp.setBounds(50,170,400,400);
        back.setBounds(200,590,100,30);
        
        add(heading);
        add(lnm);
        add(cmbt);
        add(jsp);
        add(back);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500,700);
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent e) 
    {       
        stnm=""+cmbt.getSelectedItem();
        try
        {
            int count=dtm.getRowCount();
            count--;
            while(count>=0)
            {
                dtm.removeRow(count);
                count--;
            }

            ref.pstserbt.setString(1, stnm);
            rs=ref.pstserbt.executeQuery();
        
            int i=0;
            while(rs.next())
            {
                dtm.insertRow(i,new String[]{""+rs.getInt(1),rs.getString(6)});
                i++;
            }
        }
        
        catch(Exception e1){}
    }
}