/*
package application;

import utility.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LandingPage extends JFrame
{   
    JLabel heading, username, password;

    JTextField usernm;
    JPasswordField pass;

    JButton close, login;

    Font fhead, flabel;

    MainPage mainpage;
    DB ref;

    public LandingPage()
    {
        try
        {
            ref=new DB();
        }
        catch(Exception e){}

        heading = new JLabel("RealSoft Computers");
        username=new JLabel("Username");
        password = new JLabel("Password");

        usernm=new JTextField(30);
        pass=new JPasswordField(20);

        close=new JButton("Close");
        login=new JButton("Login");

        
        fhead = new Font("Times New Roman", Font.BOLD, 35);
        flabel = new Font("Seriff", Font.PLAIN,15);
        
        heading.setFont(fhead);
        
        username.setFont(flabel);
        password.setFont(flabel);
        
        usernm.setFont(flabel);
        pass.setFont(flabel);
        
        close.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                System.exit(0);
            }
        });

        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(usernm.getText().equals("admin") || pass.getPassword().toString().equals("admin"))
                {
                    mainpage=new MainPage(null, "Realsoft Computers", true, ref);
                }
            }
        }); 

        setLayout(null);

        heading.setBounds(130,50,400,50);

        username.setBounds(100,250,100,30);
        usernm.setBounds(230,250,200,30);

        password.setBounds(100,300,100,30);
        pass.setBounds(230,300,200,30);

        close.setBounds(100,350,150,30);
        login.setBounds(350,350,150,30);

        add(heading);

        add(username);
        add(usernm);
        add(password);
        add(pass);
        add(close);
        add(login);

        setSize(600,600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String []args)
    {
        LandingPage a=new LandingPage();

    }
}
*/

package application;

import utility.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LandingPage extends JFrame {   
    JLabel heading, username, password;
    JTextField usernm;
    JPasswordField pass;
    JButton close, login;
    Font fhead, flabel;
    MainPage mainpage;
    DB ref;

    public LandingPage() {
        try {
            ref = new DB();
        } catch (Exception e) {}

        heading = new JLabel("RealSoft Computers");
        username = new JLabel("Username");
        password = new JLabel("Password");

        usernm = new JTextField(30);
        pass = new JPasswordField(20);

        close = new JButton("Close");
        login = new JButton("Login");

        fhead = new Font("Times New Roman", Font.BOLD, 35);
        flabel = new Font("Serif", Font.PLAIN, 15);

        heading.setFont(fhead);
        username.setFont(flabel);
        password.setFont(flabel);
        usernm.setFont(flabel);
        pass.setFont(flabel);

        heading.setForeground(Color.WHITE);
        username.setForeground(Color.WHITE);
        password.setForeground(Color.WHITE);
        usernm.setForeground(Color.BLACK);
        pass.setForeground(Color.BLACK);
        
        close.setBackground(Color.RED);
        close.setForeground(Color.BLACK);
        login.setBackground(Color.GREEN);
        login.setForeground(Color.BLACK);

        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (usernm.getText().equals("admin") || new String(pass.getPassword()).equals("admin")) {
                    mainpage = new MainPage(null, "Realsoft Computers", true, ref);
                }
            }
        });

        setLayout(null);

        heading.setBounds(130, 50, 400, 50);
        username.setBounds(150, 150, 100, 30);
        usernm.setBounds(230, 150, 200, 30);
        password.setBounds(150, 220, 100, 30);
        pass.setBounds(230, 220, 200, 30);
        login.setBounds(100, 290, 150, 30);
        close.setBounds(350, 290, 150, 30);

        add(heading);
        add(username);
        add(usernm);
        add(password);
        add(pass);
        add(close);
        add(login);

        getContentPane().setBackground(Color.DARK_GRAY);

        setTitle("Landing Page");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LandingPage();
    }
}