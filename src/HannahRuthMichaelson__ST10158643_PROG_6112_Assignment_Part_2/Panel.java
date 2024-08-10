package HannahRuthMichaelson__ST10158643_PROG_6112_Assignment_Part_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

//Panel Class Inherits from JPanel Class and implements ActionListner Interface
public class Panel extends JPanel implements ActionListener
{//Intialsing int array's to hold x and y coordinates 
   final int[] xMoves = new int[PIXELS];
   final int[] yMoves = new int[PIXELS];
//Declaring variables 
   int totalScore,appleX, appleY;   
//Declaring and assigning values to variables 
   private static final int PANEL_WIDTH = 300;
   private static final int PANEL_HEIGHT = 300;
   private static final int PIXEL_SIZE = 10;
   private static final int PIXELS = (PANEL_HEIGHT*PANEL_WIDTH)/PIXEL_SIZE;
   private static final int GAME_LAG = 75;
   int snakeLength = 6;   
   char direction = 'R';
   boolean inPlay = true;
   boolean welcome = true;
//Declaring Class Objects  
    Timer timer;
    Random random;
           
 //Panel Class Constutor 
    Panel(){
//instancing Random Class Object
      random = new Random();
    //Declaring Dimension Object
      Dimension size = new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
   //Declaring MyKeyAdapter Object
      MyKeyAdapter k = new MyKeyAdapter();
    //Setting Default Panel Details
      this.setPreferredSize(size);
      this.setBackground(Color.BLUE);
      this.setFocusable(true);
      this.addKeyListener(k); 
    }
    public void beginGame()
    {//Method Call to generate new apple 
        generateApple();
    //inPlay is true, thus game is running
        inPlay = true;
    //instancing Timer Class Object
        timer = new Timer(GAME_LAG,this);
    //being timer count 
        timer.start();
    }
   /*Method to end game run___________________________________________________________________________________________________________________________*/
    public void endGame(Graphics o)
    { //Instantiation Font Class object to print user score 
        Font scoreText = new Font("Monospaced",Font.BOLD,25);
      //setting attributes of Font Class Object
        o.setColor(Color.red);
        o.setFont(scoreText);
        
        FontMetrics metrics1 = getFontMetrics(o.getFont());       
        o.drawString("Score: "+totalScore, (PANEL_WIDTH - metrics1.stringWidth("Score: "+totalScore))/2, o.getFont().getSize());
       //Instantiation Font Class object to print end game message 
        Font endGameText = new Font("Monospaced",Font.BOLD, 35);
       //setting attributes of Font Class Object
        o.setColor(Color.red);
        o.setFont( endGameText);
        
        FontMetrics metrics2 = getFontMetrics(o.getFont());        
        o.drawString("Game Over", (PANEL_WIDTH - metrics2.stringWidth("Game Over"))/2, PANEL_HEIGHT/2);     
    }
/*Overrided Method draw panel components_______________________________________________________________________________________________________________*/
   @Override
    public void paintComponent(Graphics icons)
    {
        super.paintComponent(icons);
         createFigures(icons);
    }
/*Method to draw game componets__________________________________________________________________________________________________________________________*/
    public void createFigures(Graphics f)
    {
      if(welcome)
      {//Instantiation Font Class object to print end game message 
       Font beginText = new Font("Monospaced",Font.BOLD, 18);
       //setting attributes of Font Class Object          
     	f.setFont(beginText);
        f.setColor(Color.WHITE);  

        f.drawString("Welcome to Snake Game",30, 70);
        f.drawString("Enter SpaceBar to Begin",30, 90);
        
       }
     //if game is running
      if (inPlay && !welcome)
      {//Setting Graphics Object values
        f.setColor(Color.red);
        f.fillOval(appleX, appleY, PIXEL_SIZE, PIXEL_SIZE);
        //For loop, iterates through lenght of snakes body
          for(int i = 0; i < snakeLength; i++)
            { //Setting object values           
              f.setColor(Color.green);
              f.fillRect(xMoves[i], yMoves[i], PIXEL_SIZE, PIXEL_SIZE);               
            }  
        //Instantiation Font Class object to print end game message 
       Font scoreText = new Font("Monospaced",Font.BOLD, 25);
       //setting attributes of Font Class Object          
     	f.setFont(scoreText);
        f.setColor(Color.WHITE);
	FontMetrics metrics = getFontMetrics(f.getFont());
        f.drawString("Score: "+totalScore, (PANEL_WIDTH - metrics.stringWidth("Score: "+totalScore))/2, f.getFont().getSize());
       }//else, if game is no longer running 
      else if (!inPlay && !timer.isRunning()){
       //Method call to end game
        endGame(f);
       }
     }
/*Method to generate new apple___________________________________________________________________________________________________________________________*/
    public void generateApple()
    {//Declaring and assigning variables 
       int randomX = PANEL_WIDTH/PIXEL_SIZE;
       int randomY = PANEL_HEIGHT/PIXEL_SIZE;
    //assigning radome values to apple  x and y coordinates in frame
       appleX = random.nextInt(randomX)*PIXEL_SIZE;
       appleY = random.nextInt(randomY)*PIXEL_SIZE;
    }
/*Method to enable snake movement___________________________________________________________________________________________________________________________*/
    public void snakeAction()
    {//For loop, iterates to length of snake body 
        for(int i = snakeLength; i > 0; i--)
        {//Currrent x and y coordinates of snake minus 1 
            xMoves[i] = xMoves[i-1];
            yMoves[i] = yMoves[i-1];
	}
        //switch statement, to evaluate selection
        switch(direction) 
           {
               case 'U':
               yMoves[0] = yMoves[0] - PIXEL_SIZE;
                        break;
               case 'D':
               yMoves[0] = yMoves[0] + PIXEL_SIZE;
                        break;
               case 'L':
               xMoves[0] = xMoves[0] - PIXEL_SIZE;
                        break;
               case 'R':
               xMoves[0] = xMoves[0] + PIXEL_SIZE;
                        break;
           }
    }           
/*Method to add new point___________________________________________________________________________________________________________________________*/
    public void scorePoint()
    {/*if the snakes head x and y coordinates are equal to the of x and y
        coordinates of apple */
       if((xMoves[0] == appleX) && (yMoves[0] == appleY))
       {//Increment snakes length by 1
           snakeLength++;
        //Increment total score by 1
           totalScore++;
        //Method call to generate new apple
           generateApple();
       }
    }
    /*Method to detect collisions___________________________________________________________________________________________________________________________*/
    public void detectCollision()
    {
      //for loop to iterate to snake body lenghth
       for(int i = snakeLength; i > 0; i--)
        {//if snakes head collides with snakes body 
          if((xMoves[0] == xMoves[i])&& (yMoves[0] == yMoves[i])) 
          {//inPlay is false, game is nolonger running
            inPlay = false;
          }
        }
       //if head touches left border
        if(xMoves[0] < 0) 
        {//inPlay is false, game is nolonger running
            inPlay = false;
        }
        //if head touches right border
        if(xMoves[0] > PANEL_WIDTH) 
        {//inPlay is false, game is nolonger running
           inPlay = false;
        }
        //if head touches top border
        if(yMoves[0] < 0)
        {//inPlay is false, game is nolonger running
            inPlay = false;
        }
       //if head touches bottom border
        if(yMoves[0] > PANEL_HEIGHT) 
        {//inPlay is false, game is nolonger running
            inPlay = false;
        }
        //if game is not running 
        if(!inPlay) 
        {//stop timer 
            timer.stop();
        }
    }
/*Overrided Method, enables actions___________________________________________________________________________________________________________________________*/ 
    @Override
    public void actionPerformed(ActionEvent e) 
    {//if the game in running
        if(inPlay)
        {//Method calls
            snakeAction();
            scorePoint();
            detectCollision();
        }
      //method calls to restart  
        repaint();
    }
    /*___________________________________Sub Class the inherits from KeyAdapter Class___________________________________________________________________________________________________________________________*/  
    public class MyKeyAdapter extends KeyAdapter
    {
//Method to determine user inputter directions____________________________________________________________________________________________________________
        @Override
        public void keyPressed(KeyEvent e)
        { //switch statement, to evaluate keyboard entry
           switch(e.getKeyCode())
           {
               case KeyEvent.VK_LEFT:
                    if(direction != 'R') 
                    {
                      direction = 'L';
                    }break;
               case KeyEvent.VK_RIGHT:
                    if(direction != 'L') 
                    {
                      direction = 'R';
                    }break;
                case KeyEvent.VK_UP:
                    if(direction != 'D') 
                    {
                      direction = 'U';
                    }break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U') 
                    {
                      direction = 'D';
                    }break;
                case KeyEvent.VK_SPACE:
                    {//Method Call to Begin Game
                    beginGame();
                    welcome = false;
                    }break;
           }
        }
    }
}//end class