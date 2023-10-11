package com.company;

import javax.swing.*;

public class GameFrame extends JFrame {
    private GamePanel gamePanel;
     GameFrame(){
         gamePanel=new GamePanel();


//       this.setBounds(20,20,700,700);
         this.setTitle("Brick Game");
         this.setSize(700,600);
         this.setLocationRelativeTo(null);
         this.setDefaultCloseOperation(3);
         this.setResizable(false);
         this.add(gamePanel);
         this.setVisible(true);
     }
}
