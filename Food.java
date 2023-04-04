package com.snake;

import java.awt.*;
import java.util.Random;

class Food extends Point {

    final Color FOOD_COLOR = Color.red;
    Random random = new Random();
    final int GAME_FIELD_HEIGHT = 20; // не пиксели
    final int GAME_FIELD_WIDTH = 30;

    public Food() {         // конструктор еды         // один объект
        super(-1, -1);
        this.color = FOOD_COLOR;
    }

    void eat() {
        this.setXY(-1, -1);
    }

    boolean isEaten() {
        return this.getX() == -1;
    }

    void next(){
        int x,y;
        do{
            x =  random.nextInt(GAME_FIELD_WIDTH);
            y =  random.nextInt(GAME_FIELD_HEIGHT);
        } while(snake.isInsideSnake(x,y));           //находится лм еда в змейке
        this.setXY(x,y);
    }
}