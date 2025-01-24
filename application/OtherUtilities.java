package application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import utility.*;

public class OtherUtilities extends JDialog {
    JButton batch, city, college, course, trade, receipt, student;
    JLabel heading;

    UtilitiesOptions utilitiesOptions;

    public OtherUtilities(JFrame parent, String title, boolean state, DB ref) {
        super(parent, title, state);

        heading = new JLabel("RealSoft Computers");
        batch = new JButton("BATCH");
        city = new JButton("CITY");
        college = new JButton("COLLEGE");
        course = new JButton("COURSE");
        trade = new JButton("TRADE");
        receipt = new JButton("RECEIPT");
        student = new JButton("STUDENT");

        heading.setFont(new Font("Times New Roman", Font.BOLD, 35));


        setLayout(null);

        heading.setBounds(100, 50, 400, 50);
        batch.setBounds(100, 150, 120, 30);
        city.setBounds(250, 150, 120, 30);
        college.setBounds(100, 220, 120, 30);
        course.setBounds(250, 220, 120, 30);
        trade.setBounds(100, 290, 120, 30);
        receipt.setBounds(250, 290, 120, 30);
        student.setBounds(175, 360, 120, 30);

        heading.setForeground(Color.WHITE);
        batch.setBackground(Color.BLUE);
        batch.setForeground(Color.WHITE);
        city.setBackground(Color.BLUE);
        city.setForeground(Color.WHITE);
        college.setBackground(Color.BLUE);
        college.setForeground(Color.WHITE);
        course.setBackground(Color.BLUE);
        course.setForeground(Color.WHITE);
        trade.setBackground(Color.BLUE);
        trade.setForeground(Color.WHITE);
        receipt.setBackground(Color.BLUE);
        receipt.setForeground(Color.WHITE);
        student.setBackground(Color.BLUE);
        student.setForeground(Color.WHITE);

        add(heading);
        add(batch);
        add(city);
        add(college);
        add(course);
        add(trade);
        add(receipt);
        add(student);

        getContentPane().setBackground(Color.DARK_GRAY);

        batch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                utilitiesOptions=new UtilitiesOptions(parent,"Utilities",true,ref,"Batch");
            }
        });

        city.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                utilitiesOptions=new UtilitiesOptions(parent,"Utilities",true,ref,"City");
            }
        });
        college.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                utilitiesOptions=new UtilitiesOptions(parent,"Utilities",true,ref,"College");
            }
        });
        course.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                utilitiesOptions=new UtilitiesOptions(parent,"Utilities",true,ref,"Course");
            }
        });
        trade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                utilitiesOptions=new UtilitiesOptions(parent,"Utilities",true,ref,"Trade");
            }
        });
        receipt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                utilitiesOptions=new UtilitiesOptions(parent,"Utilities",true,ref,"Receipt");
            }
        });
        student.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                utilitiesOptions=new UtilitiesOptions(parent,"Utilities",true,ref,"Student");
            }
        });


        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}