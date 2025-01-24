package application;

import javax.swing.*;
import utility.*;
import java.awt.event.*;

import batch.*;
import city.*;
import college.*;
import course.*;
import receipt.*;
import student.*;
import trade.*;

public class UtilitiesOptions extends JDialog {
    JButton add, mod, del;

    JLabel sel;

    AddCollege addCollege;
    ModCollege modCollege;
    DelCollege delCollege;

    AddBatch addBatch;
    ModBatch modBatch;
    DelBatch delBatch;

    AddCity addCity;
    ModCity modCity;
    DelCity delCity;

    AddCourse addCourse;
    ModCourse modCourse;
    DelCourse delCourse;

    AddReceipt addReceipt;
    DelReceipt delReceipt;

    SearchNameForReceipt searchNameForReceipt;

    AddStudent addStudent;
    ModStudent modStudent;
    DelStudent delStudent;

    AddTrade addTrade;
    ModTrade modTrade;
    DelTrade delTrade;

    public UtilitiesOptions(JFrame parent, String title, boolean state, DB ref,String option) {
        super(parent, title, state);

        add = new JButton("ADD");
        mod = new JButton("MODIFY");
        del = new JButton("DELETE");

        sel = new JLabel(option + " Utility");

        switch (option) {
            case "College":
                add.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        addCollege = new AddCollege(parent, "College", state, ref);
                    }
                });
                mod.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        modCollege = new ModCollege(parent, "College", state, ref);
                    }
                });
                del.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        delCollege = new DelCollege(parent, "College", state, ref);
                    }
                });
                break;

            case "City":
                add.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        addCity = new AddCity(parent, "City", state, ref);
                    }
                });
                mod.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        modCity = new ModCity(parent, "City", state, ref);
                    }
                });
                del.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        delCity = new DelCity(parent, "City", state, ref);
                    }
                });
                break;
            
            case "Batch":
                add.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        addBatch = new AddBatch(parent, "Batch", state, ref);
                    }
                });
                mod.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        modBatch = new ModBatch(parent, "Batch", state, ref);
                    }
                });
                del.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        delBatch = new DelBatch(parent, "Batch", state, ref);
                    }
                });
                break;

            case "Trade":
                add.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        addTrade = new AddTrade(parent, "Trade", state, ref);
                    }
                });
                mod.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        modTrade = new ModTrade(parent, "Trade", state, ref);
                    }
                });
                del.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        delTrade = new DelTrade(parent, "Trade", state, ref);
                    }
                });
                break;
            

            case "Course":
                add.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        addCourse = new AddCourse(parent, "Course", state, ref);
                    }
                });
                mod.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try
                        {
                            modCourse = new ModCourse(parent, "Course", state, ref);
                        }
                        catch(Exception e1){}
                    }
                });
                del.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try
                        {
                            delCourse = new DelCourse(parent, "Course", state, ref);
                        }
                        catch(Exception e1){}
                       
                    }
                });
                break;
            
            case "Receipt":
                add.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        searchNameForReceipt = new SearchNameForReceipt(parent, "Receipt", state, ref, "Fee Deposite");
                    }
                });
                del.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        delReceipt = new DelReceipt(parent, "Receipt", state, ref);
                    }
                });
                break;
            
            case "Student":
                add.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        addStudent = new AddStudent(parent, "Student", state, ref);
                    }
                });
                mod.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try
                        {
                            modStudent = new ModStudent(parent, "Student", state, ref);
                        }
                        catch(Exception e1){}
                    }
                });
                del.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try
                        {
                            delStudent = new DelStudent(parent, "Student", state, ref);
                        }
                        catch(Exception e1){}
                       
                    }
                });
                break;

            default:
                break;
        }

        setLayout(null);


        sel.setBounds(100,50,200,30);
        add.setBounds(100,150,100,30);
        mod.setBounds(230,150,100,30);
        del.setBounds(215,200,100,30);

        add(sel);
        add(add);
        add(mod);
        add(del);
        

        setSize(500,500);
        setVisible(true);
    }
}
