package threads;

import java.sql.*;
import utility.*;
import utility.GEmailSender;

public class SendEmail extends Thread {
    
    ResultSet rs;
    String to,name,from,subject,message;

    DB ref;
    public SendEmail(DB ref)
    {
        super();

        this.ref=ref;
       

        start();
    }

    public void run()
    {
        try
        {
            rs=ref.pstserbalfee.executeQuery();
            System.out.println("Hellooooo");
            while(rs.next())
            {
                System.out.println(""+rs.getString(17));
                GEmailSender gEmailSender = new GEmailSender();
                to = rs.getString(17);
                from = "prathameshb343@gmail.com";
                subject = "Pending Fee payments of Realsoft Computers";
                // message="This is a example email";
                message = "Hello " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) 
                                + " ,your balance fees are : Rs " 
                                +  ""+rs.getInt(24) + "\nPlease fill your balance fees as early as possible. \nThank you";
                boolean b1 = gEmailSender.sendEmail(to, from, subject, message);
            }
        }
        catch(Exception e){}
    }

}
