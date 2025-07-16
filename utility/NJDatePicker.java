package utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;

import org.jdatepicker.impl.*;

public class NJDatePicker extends Panel
{
    public JDatePickerImpl dp;   
    
    public JTextField dateField;

    JDatePanelImpl datePanel;  

    Properties properties;  
    
    public NJDatePicker()
    {
        super();

        //System.out.println("AAA");

        try
        {
            SqlDateModel model;  
            model = new SqlDateModel();

            properties = new Properties();

            Font font1 = new Font("SansSerif", Font.PLAIN, 15);

            properties.put("text.today", "Today");
            properties.put("text.month", "Month");
            properties.put("text.year", "Year");
            
            datePanel = new JDatePanelImpl(model, properties);

            dp=new JDatePickerImpl(datePanel,null);     //null for abstract formatter

            dateField = ((JTextField)dp.getComponent(0));
            dateField.setFont(font1);

            add(dp);

            dp.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent e)
                {
                    dateField.setText(getDate());
                }
            });

            model.setSelected(true);

        }
        catch(Exception e){System.out.println(""+e);}
    }

    public String getDate()
    {
        Date selDate = (Date) dp.getModel().getValue();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(selDate);

        return formattedDate;
    }


    public String toString()
    {
        return dateField.getText();
    }

    public Date getSqlDate()
    {
        return  (Date) dp.getModel().getValue();
    }
}
