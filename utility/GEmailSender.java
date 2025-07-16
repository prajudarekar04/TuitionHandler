package utility;

import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;

public class GEmailSender 
{
    public boolean sendEmail(String to,String from,String subject,String text)
    {
        boolean flag=false;

        //SMTP propeertied set up
        Properties prop=new Properties();
        prop.put("mail.smtp.auth",true);
        prop.put("mail.smtp.starttls.enable",true);
        prop.put("mail.smtp.port","587");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        prop.put("mail.smtp.host","smtp.gmail.com");

        //session
        String username="prathameshb343";
        String pass="nvxn ucnc ucqb wzjx";

        Session ses=Session.getInstance(prop, new Authenticator() 
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(username,pass);
            }
        });

        try
        { 
            MimeMessage m=new MimeMessage(ses);

            m.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
            m.setFrom(new InternetAddress(from));
            m.setSubject(subject);

            //meassage with attachment
            MimeMultipart mimeMultipart=new MimeMultipart();
            
            MimeBodyPart textPart=new MimeBodyPart();
            //MimeBodyPart filePart=new MimeBodyPart();

            try
            {
                textPart.setText(text);

                // File f=new File(path);
                // filePart.attachFile(f);

                mimeMultipart.addBodyPart(textPart);
                //mimeMultipart.addBodyPart(filePart);

                m.setContent(mimeMultipart);
            }
            catch(Exception e)
            {
                System.out.println(""+e);
            }

            Transport.send(m);

            flag=true;
        }
        catch(Exception e)
        {
            System.out.println("Exception :"+e);
        }

        return flag;
    }
}