package com.snake;

import java.awt.*;

class Point {
    final Color SNAKY_COLOR = Color.green;
    final int POINT_RADIUS = 20; //пиксели
    int x, y;
    Color color = SNAKY_COLOR;

    int getX() {

        return x;
    }

    int getY() {

        return y;
    }

    public Point(int x, int y) {
        this.setXY(x, y);
    }

    void paint(Graphics g) {
        g.setColor(color);
        g.fillOval(x * POINT_RADIUS, y * POINT_RADIUS, POINT_RADIUS, POINT_RADIUS);
    }

    void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

}