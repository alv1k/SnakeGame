package com.snake;

import java.awt.*;
import java.util.ArrayList;

class Snake {
    ArrayList<Point> snake = new ArrayList<Point>();
    int direction;
    final int SNAKE_LEFT = 37;
    final int SNAKE_UP = 38;
    final int SNAKE_RIGHT = 39;
    final int SNAKE_DOWN = 40;
    final int GAME_FIELD_HEIGHT = 20; // не пиксели
    final int GAME_FIELD_WIDTH = 30;
    final String TITLE_OF_GAME = "Змейка";

    boolean gameOver = false;
    //private int length;

    public Snake(int x, int y, int length, int direction) {
        for (int i = 0; i < length; i++) {   //цикл создает объекты
            Point point = new Point(x - i, y);
            snake.add(point);
        }
        this.direction = direction;
    }
    boolean isInsideSnake(int x, int y){
        for(Point point :  snake){
            if((point.getX() == x) && (point.getY() == y)) {
                return true;
            }
        }
        return false;
    }


    void paint(Graphics p) {
        for (Point point : snake) {      //переберание объекта
            point.paint(p);
        }
    }

    boolean isFood(Point food) {
        return ((snake.get(0).getX() == food.getX()) && (snake.get(0).getY() == food.getY()));

    }
    void move() {
        int x = snake.get(0).getX();
        int y = snake.get(0).getY();

        if (direction == SNAKE_LEFT) {
            x--;
        }
        if (direction == SNAKE_RIGHT) {
            x++;
        }
        if (direction == SNAKE_UP) {
            y--;
        }
        if (direction == SNAKE_DOWN) {
            y++;
        }
        if (x > GAME_FIELD_WIDTH - 1) {                 //контроль поля
            x = 0;
        }
        if (x < 0) {
            x = GAME_FIELD_WIDTH - 1;
        }
        if (y > GAME_FIELD_HEIGHT - 1) {
            y = 0;
        }
        if (y < 0) {
            y = GAME_FIELD_HEIGHT - 1;
        }

        gameOver = isInsideSnake(x,y);      // игра окончена

        snake.add(0, new Point(x, y));

        if (isFood(food)) {
            food.eat();
            frame.setTitle(TITLE_OF_GAME + " Счет: " + snake.size());
        } else {
            snake.remove(snake.size() - 1);             //удаление хвоста
        }
    }

    void setDirection(int direction) {
        if ((direction >= SNAKE_LEFT) && (direction <= SNAKE_DOWN)) {
            if(Math.abs(this.direction - direction) != 2){ //исправление проблемы с движением влево и вправо
                this.direction = direction;
            }
        }
    }
}