package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GamePanel extends JPanel implements KeyListener , ActionListener {
    private boolean playing=false;
    private int score=0;

    private int numOfBricks=30;

    private Timer timer;
    private int delay =1;

    private int Player=300;

    private int ballposX=120;
    private int ballposY=350;
    private int balldirX=-1;
    private int balldirY=-2;

    private BrickGenerator brickGenerator;

    GamePanel(){
       addKeyListener(this);
       setFocusable(true);
       setFocusTraversalKeysEnabled(false);
       timer = new Timer(delay,this);
        timer.start();
        brickGenerator=new BrickGenerator(3,10);
    }
    public void paint(Graphics g){
        //background
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(1,1,679,592);

        //border
        g.setColor(Color.WHITE);
        g.fillRect(0,0,5,592);
        g.fillRect(0,0,692,3);
        g.fillRect(679,0,5,592);

        //Bricks
        brickGenerator.draw((Graphics2D) g);
        //Paddle
        g.setColor(Color.BLACK);
        g.fillRect(Player,530,130,5);

        //Ball
        g.setColor(Color.BLUE);
        g.fillOval(ballposX,ballposY,20,20);

        //score
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif",Font.ITALIC,18));
        g.drawString("Score "+this.score,590,25);

        //Game Over
        if(ballposY > 550){
            playing=false;
            balldirX =balldirY=0;

            g.setColor(Color.RED);
            g.setFont(new Font("serif",Font.ITALIC,25));
            g.drawString("Game Over, Score "+this.score,200,250);

            g.setFont(new Font("serif",Font.ITALIC,20));
            g.drawString("Press Enter to Restart",220,280);
        }

        //Detecting Game over
        if(this.numOfBricks <= 0){
            playing=false;
            balldirX =0;
            balldirY=0;

            g.setColor(Color.BLUE);
            g.setFont(new Font("serif",Font.ITALIC,25));
            g.drawString("You Won, Score "+this.score,200,250);

            g.setFont(new Font("serif",Font.ITALIC,20));
            g.drawString("Press Enter to Restart",220,280);
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(playing){
            if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(Player,530,130,5))){
                balldirY=-balldirY;
            }

//            checking for collisions
            for (int i = 0; i <brickGenerator.Bricks.length ; i++) {
                for (int j = 0; j < brickGenerator.Bricks[0].length; j++) {
                    if(brickGenerator.Bricks[i][j] > 0){
                        int brickX= j * brickGenerator.brickWidth + 80;
                        int brickY= i * brickGenerator.brickHeight + 50;
                        int brickWidth = brickGenerator.brickWidth;
                        int brickHeight = brickGenerator.brickHeight;

                        Rectangle rectangle = new Rectangle(brickX ,brickY,brickWidth ,brickHeight);
                        Rectangle ballRect= new Rectangle(ballposX,ballposY,20,20);
                        Rectangle brickRect = rectangle;

                        if(ballRect.intersects(brickRect)){
                            brickGenerator.setValue(0,i,j);
                            numOfBricks--;
                            score+=5;
                            if(ballposX +19 <= brickRect.x || ballposX +1 >= brickRect.x + brickRect.width)
                                balldirX = -balldirX;
                            else{
                                balldirY = - balldirY;
                            }
                        }
                    }
                }
            }

            ballposY+=balldirY;
            ballposX+=balldirX;

            if(ballposX < 0){
                balldirX = -balldirX;
            }
            if(ballposY < 0){
                balldirY = -balldirY;
            }
            if(ballposX > 653){
                balldirX = -balldirX;
            }

        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_RIGHT:{
                if(Player >= 545){
                    Player=545;
                }else{
                    playing=true;
                    Player+=20;
                }
                break;
            }
            case KeyEvent.VK_LEFT:{
                if(Player < 10){
                    Player=10;
                }else{
                    playing=true;
                    Player-=20;
                }
                break;
            }
            case KeyEvent.VK_ENTER:{
                if(!playing){
                    playing=false;
                    Player=300;
                    score=0;

                    ballposX=120;
                    ballposY=350;
                    balldirX=-1;
                    balldirY=-2;

                    numOfBricks=30;
                    brickGenerator= new BrickGenerator(3,10);
                }
                break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
