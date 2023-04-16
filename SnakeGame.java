import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class SnakeGame extends JPanel {
    int frameHeight = 500;
    int frameWidth = 500;
    private static final int wallStep = 500/10;
    Snake snake = new Snake(5,5, wallStep);
    Shape walls1 = new Polygon();
    Shape walls2 = new Polygon();
    Shape walls3 = new Polygon();
    private Point orange = new Point();
    private Point cherry = new Point();
    private int points = 0;
    boolean GameOver = true;
    int lastPressedKey = 0;
    private final Random rand = new Random();
    private String message = null;
    Point apple = new Point();

    public SnakeGame(){
        super(true);
        //Dimension d = getLevel("sn1.dat");
        putApple();
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    gameCycle();
                    try{
                        Thread.sleep(500 - snake.getSpeed());
                    }catch (InterruptedException e){

                    }
                }
            }
        });
        th.start();

    }
    //класс для игрового поля
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g.setColor(Color.BLUE);
        for(int i = 0; i < 11; i++){
            g.drawLine(0,frameHeight/10*i,frameWidth,frameHeight/10*i);
            g.drawLine(frameWidth/10*i,0,frameWidth/10*i,frameHeight);
        }

        snake.paint(g2);
        g.setColor(Color.red);
        g.drawImage(snake.loadAppleImage(), apple.x, apple.y, null);
    }
    private Snake getSnake(){
        return snake;
    }
    void processKey(KeyEvent ev){
        Snake snake = getSnake();

        switch (ev.getKeyCode()){
            case KeyEvent.VK_RIGHT:
                //System.out.println(KeyEvent.VK_RIGHT);
                if(lastPressedKey != KeyEvent.VK_LEFT && !GameOver){
                    snake.setDirection(Snake.DIR_RIGHT);
                    lastPressedKey = KeyEvent.VK_RIGHT;
                }
                break;
            case KeyEvent.VK_LEFT:
                //System.out.println(KeyEvent.VK_LEFT);
                if(lastPressedKey != KeyEvent.VK_RIGHT && !GameOver){
                    snake.setDirection(Snake.DIR_LEFT);
                    lastPressedKey = KeyEvent.VK_LEFT;
                }
                break;
            case KeyEvent.VK_UP:
                //System.out.println(KeyEvent.VK_UP);
                if(lastPressedKey != KeyEvent.VK_DOWN && !GameOver){
                    snake.setDirection(Snake.DIR_UP);
                    lastPressedKey = KeyEvent.VK_UP;
                }
                break;
            case KeyEvent.VK_DOWN:
                //System.out.println(KeyEvent.VK_DOWN);
                if(lastPressedKey != KeyEvent.VK_UP && !GameOver){
                    snake.setDirection(Snake.DIR_DOWN);
                    lastPressedKey = KeyEvent.VK_DOWN;
                }
                break;
        }
    }

    public void gameCycle() {
        if (snake.getDirection() != Snake.DIR_POUSE) {
            setMessage(null);
        }
        Point p = snake.move();
        if(p.x == -50 || p.y == -50 || p.x == 500 || p.y == 500){
            snake.setDirection(Snake.DIR_POUSE);
            Object[] options = {"OK"};
            int exit = JOptionPane.showOptionDialog(null, "bye", "Exit", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if(exit == 0){
                System.exit(0);
            }

        }
        if(p.y == apple.y && p.x == apple.x){
            snake.expand();
            putApple();
        }

        this.repaint();
    }
    void putApple(){
        int x = 5;
        int y = 5;
        x = 50 * (rand.nextInt(9));
        y = 50 * (rand.nextInt(9));
        apple.setLocation(x,y);

    }
    private void setMessage(String msg) {
        message = msg;
    }
    private void putOrange() {
        int x = 5;
        int y = 5;
        while (walls2.contains(x, y)) {
            x = wallStep * rand.nextInt(40) + 2;
            y = wallStep * rand.nextInt(40) + 2;
        }
        orange.setLocation(x, y);
    }

    /**
     * Put fruit on map
     */
    private void putCherry() {
        int x = 5;
        int y = 5;
        while (walls2.contains(x, y)) {
            x = wallStep * rand.nextInt(40) + 2;
            y = wallStep * rand.nextInt(40) + 2;
        }
        cherry.setLocation(x, y);
    }
    void newGame() {
        GameOver = false;
        points = 0;
        lastPressedKey = 0;
    }
}
