import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class SnakeApp {


    //класс для окна запуска
    public static void main(String[] args) {
        new SnakeApp().startGraphicInterface();
    }
    public void startGraphicInterface(){
        //метод для запуска окна

        //создать JFrame
        //установить для него закрытие по крестику, размер, видимость
        JFrame frame = new JFrame("Snake game");
        frame.setSize(550,550);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.setResizable(false);

        SnakeGame field = new SnakeGame();
        frame.add(field);
        field.newGame();
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                field.processKey(e);
            }
        });
        frame.setVisible(true);
        //осталось сделать поле, змейку
    }

}
