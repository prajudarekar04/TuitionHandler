/*package application;

import student.*;
import receipt.*;
import utility.*;

import javax.print.attribute.standard.MediaSize.Other;
import javax.swing.*;

import display.BalanceFeeStudents;

import java.awt.*;
import java.awt.event.*;

public class MainPage extends JDialog 
{
    JLabel heading;
    JButton addSt,addRe,pendingfees,printRe,utility;
    Font flabel,fhead;

    AddStudent addStudent;
    SearchNameForReceipt  searchNameForReceipt;
    BalanceFeeStudents balanceFeeStudents;
    OtherUtilities otherUtilities;

    public MainPage(JFrame parent,String title,boolean state,DB ref)
    {
        addSt=new JButton("New Registration");
        addRe=new JButton("Deposite Fees");
        pendingfees=new JButton("Pending Fees"); 
        printRe=new JButton("Print Receipt");
        utility=new JButton("Other Utility");

        heading = new JLabel("Home Page");

        fhead = new Font("Times New Roman", Font.BOLD, 35);
        flabel = new Font("Seriff", Font.PLAIN, 15);

        heading.setFont(fhead);

        addSt.setFont(flabel);
        addRe.setFont(flabel);
        pendingfees.setFont(flabel);
        printRe.setFont(flabel);
        utility.setFont(flabel);

        addSt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent=new AddStudent(parent, "RealSoft Computers", true, ref);
            }
        });

        addRe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchNameForReceipt=new SearchNameForReceipt(parent, "RealSoft Computers", true, ref,"Fee Deposite");
            }
        });

        pendingfees.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                balanceFeeStudents=new BalanceFeeStudents(parent, "RealSoft Computers", true, ref);
            }
        });

        printRe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchNameForReceipt=new SearchNameForReceipt(parent, "RealSoft Computers", true, ref,"Student Fee Record");
            }
        });


        utility.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                otherUtilities=new OtherUtilities(parent, "RealSoft Computers", true, ref);
            }
        });

        setLayout(null);

        heading.setBounds(200,50,400,50);

        addSt.setBounds(50,150,200,30);
        addRe.setBounds(350,150,200,30);
        pendingfees.setBounds(50,210,200,30);
        printRe.setBounds(350,210,200,30);
        utility.setBounds(200,270,200,30);

        add(heading);
        add(addSt);
        add(addRe);
        add(pendingfees);
        add(printRe);
        add(utility);

        setSize(600,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
*/
package application;

import student.*;
import receipt.*;
import utility.*;

import javax.print.attribute.standard.MediaSize.Other;
import javax.swing.*;

import display.BalanceFeeStudents;

import java.awt.*;
import java.awt.event.*;

public class MainPage extends JDialog {
    JButton addSt, addRe, pendingfees, printRe, utility;
    DB ref;
    
    JLabel heading;
    Font flabel,fhead;

    AddStudent addStudent;
    SearchNameForReceipt  searchNameForReceipt;
    BalanceFeeStudents balanceFeeStudents;
    OtherUtilities otherUtilities;

    public MainPage(JFrame parent, String title, boolean state, DB ref) {
        super(parent, title, state);
        this.ref = ref;

        heading = new JLabel("RealSoft Computers");
        addSt = new JButton("Add Student");
        addRe = new JButton("Add Receipt");
        pendingfees = new JButton("Pending Fees");
        printRe = new JButton("Print Receipt");
        utility = new JButton("Other Utilities");

        setLayout(null);
        heading.setBounds(130, 50, 400, 50);
        addSt.setBounds(100, 150, 150, 30);
        addRe.setBounds(300, 150, 150, 30);
        pendingfees.setBounds(100, 220, 150, 30);
        printRe.setBounds(300, 220, 150, 30);
        utility.setBounds(200, 290, 150, 30);

        heading.setFont(new Font("Times New Roman", Font.BOLD, 35));
        addSt.setFont(new Font("Serif", Font.BOLD, 14));
        addRe.setFont(new Font("Serif", Font.BOLD, 14));
        pendingfees.setFont(new Font("Serif", Font.BOLD, 14));
        printRe.setFont(new Font("Serif", Font.BOLD, 14));
        utility.setFont(new Font("Serif", Font.BOLD, 14));


        heading.setForeground(Color.WHITE);
        addSt.setBackground(Color.BLUE);
        addSt.setForeground(Color.WHITE);
        addRe.setBackground(Color.BLUE);
        addRe.setForeground(Color.WHITE);
        pendingfees.setBackground(Color.BLUE);
        pendingfees.setForeground(Color.WHITE);
        printRe.setBackground(Color.BLUE);
        printRe.setForeground(Color.WHITE);
        utility.setBackground(Color.BLUE);
        utility.setForeground(Color.WHITE);

        add(heading);
        add(addSt);
        add(addRe);
        add(pendingfees);
        add(printRe);
        add(utility);

        addSt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add action logic for addSt button
                addStudent=new AddStudent(parent, "RealSoft Computers", true, ref);
            }
        });

        addRe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add action logic for addRe button
                searchNameForReceipt=new SearchNameForReceipt(parent, "RealSoft Computers", true, ref,"Fee Deposite");
            }
        });

        pendingfees.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add action logic for pendingfees button
                balanceFeeStudents=new BalanceFeeStudents(parent, "RealSoft Computers", true, ref);
            }
        });

        printRe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add action logic for printRe button
                searchNameForReceipt=new SearchNameForReceipt(parent, "RealSoft Computers", true, ref,"Student Fee Record");
            }
        });

        utility.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                otherUtilities=new OtherUtilities(parent, "RealSoft Computers", true, ref);
            }
        });

        getContentPane().setBackground(Color.DARK_GRAY);

        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}