package HannahRuthMichaelson__ST10158643_PROG_6112_Assignment_Part_2;

import javax.swing.JFrame;

public class MyFrame extends JFrame
{
  //Class constructor
    MyFrame() 
    {//Declaring Panel Object
        Panel panel = new Panel();
    //adding Panel Class Object to Frame
        this.add(panel);
    //Setting MyFrame Attributes 
        this.setTitle("The Snake Game");
        this.setDefaultCloseOperation(MyFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null);
        
        
    }
}//end class
