package com.company;

import java.awt.*;

public class BrickGenerator {
    public int Bricks[][];
    public int brickWidth;
    public int brickHeight;

    BrickGenerator(int row ,int col){
        Bricks=new int[row][col];
        for (int i = 0; i <Bricks.length ; i++) {
            for (int j = 0; j < Bricks[0].length; j++) {
                Bricks[i][j]=1;
            }
        }
        brickWidth=540/col;
        brickHeight = 150 /row;
    }

    public void draw(Graphics2D g){
        for (int i = 0; i <Bricks.length ; i++) {
            for (int j = 0; j < Bricks[0].length; j++) {
                if(Bricks[i][j] > 0){
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(j * brickWidth +80,i * brickHeight + 50,brickWidth,brickHeight);

                    g.setStroke(new BasicStroke(2));
                    g.setColor(Color.LIGHT_GRAY);
                    g.drawRect(j * brickWidth +80,i * brickHeight + 50,brickWidth,brickHeight);
                }
            }
        }
    }

    public void setValue(int value, int row,int col){
       Bricks[row][col]=value;
    }
}
