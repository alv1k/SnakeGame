package com.snake;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {

    final String GAME_OVER_TITLE = "Игра окончена";
    final int GAME_FIELD_HEIGHT = 20; // не пиксели
    final int GAME_FIELD_WIDTH = 30;
    final int GAME_FIELD_DX = 6;
    final int GAME_FIELD_DY = 28;
    final int POINT_RADIUS = 20; //пиксели
    boolean gameOver = false;
    final int START_LOCATION_SNAKE_X = 10;
    final int START_LOCATION_SNAKE_Y = 10;
    final int SNAKE_RIGHT = 39;
    final int START_SIZE_SNAKE = 6;
    final int START_DIRECTION_SNAKE = SNAKE_RIGHT;
    Snake snake = new Snake(START_LOCATION_SNAKE_X, START_LOCATION_SNAKE_Y, START_SIZE_SNAKE, START_DIRECTION_SNAKE);
    Food food = new Food();
    @Override
    public void paint(Graphics g) {              //конструктор
        super.paint(g);
        snake.paint(g);                         //рисование змейки
        food.paint(g);                          //рисование еды
        if(gameOver){
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            FontMetrics q1 = g.getFontMetrics();
            g.drawString(GAME_OVER_TITLE, (GAME_FIELD_WIDTH * GAME_FIELD_HEIGHT + GAME_FIELD_DX - q1.stringWidth(GAME_OVER_TITLE))/2, (GAME_FIELD_HEIGHT * POINT_RADIUS + GAME_FIELD_DY)/2);
        }
    }
}