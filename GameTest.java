package com.snake;
/*Start: 21.03.2021 20:44
  End:  25.03.2021 22:11
  Created by gg35y  */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GameTest {
    final String TITLE_OF_GAME = "Змейка";
    final String GAME_OVER_TITLE = "Игра окончена";
    final int POINT_RADIUS = 20; //пиксели
    final int GAME_FIELD_HEIGHT = 20; // не пиксели
    final int GAME_FIELD_WIDTH = 30;
    final int GAME_FIELD_DX = 6;
    final int GAME_FIELD_DY = 28;
    final int START_LOCATION = 200;
    final int START_SIZE_SNAKE = 6;
    final int START_LOCATION_SNAKE_X = 10;
    final int START_LOCATION_SNAKE_Y = 10;
    final int SHOW_DELAY = 150;
    //Движение (коды клавиш)
    final int SNAKE_LEFT = 37;
    final int SNAKE_UP = 38;
    final int SNAKE_RIGHT = 39;
    final int SNAKE_DOWN = 40;
    final int START_DIRECTION_SNAKE = SNAKE_RIGHT;
    //цвет
    final Color SNAKY_COLOR = Color.green;
    final Color FOOD_COLOR = Color.red;

    Snake snake;
    Food food;
    JFrame frame;
    Canvas canvasPanel;
    Random random = new Random();
    boolean gameOver = false;

    public static void main(String[] args) {
        new GameTest().Main();
    }

    void Main() {
        frame = new JFrame(TITLE_OF_GAME + " Счет: " + START_SIZE_SNAKE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(GAME_FIELD_WIDTH * POINT_RADIUS + GAME_FIELD_DX, GAME_FIELD_HEIGHT * POINT_RADIUS + GAME_FIELD_DY);
        frame.setLocation(START_LOCATION, START_LOCATION);
        frame.setResizable(false);

        canvasPanel = new Canvas();
        canvasPanel.setBackground(Color.WHITE);

        frame.getContentPane().add(BorderLayout.CENTER, canvasPanel);
        frame.addKeyListener(new KeyAdapter() {
            //@Override
            public void keyPressed(KeyEvent e) {
                snake.setDirection(e.getKeyCode());
                //System.out.println(e.getKeyCode());
            }
        });

        frame.setVisible(true);



        while (!gameOver) {     // !gameOver == true
            snake.move();       //задержка в движении
            if(food.isEaten()){
                food.next();
            }
            canvasPanel.repaint();
            try {
                Thread.sleep(SHOW_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }













}