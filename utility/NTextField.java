package utility;

import javax.swing.*;
import java.awt.event.*;

public class NTextField extends JTextField
{
    public int dotcnt=0;

    NTextField obj;
    public NTextField(int n)
    {
        super(n);

        obj=this;

        addKeyListener(new KeyAdapter() 
        {
            public void keyPressed(KeyEvent e)
            {
                String a=getText();
                int i=a.length();

                if(e.getKeyChar()>='0' && e.getKeyChar()<='9' || (e.getKeyChar()=='.'  && dotcnt<1))
                {
                    if(e.getKeyChar()=='.')
                    {
                        dotcnt++;
                    }
                    setEditable(true);
                }
                else
                {
                    if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE)
                    {
                        if(!obj.getText().equals(""))
                        {
                            if(a.charAt(a.length()-1)=='.')
                            {
                                dotcnt--;
                            }
                            StringBuffer b=new StringBuffer(a);
                            a=b.substring(0, i);
                            setText(a);

                        }
                        
                    }
                    else
                    {
                        //setText(a);
                        setEditable(false);
                    }
                }
            }
        });
    }
}
