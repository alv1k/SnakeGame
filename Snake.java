import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Snake {
    //класс для змеи
    ArrayList<Point> body = new ArrayList<Point>();
    int bodySize;
    static final int DIR_POUSE = 0;
    static final int DIR_UP = 1;
    static final int DIR_RIGHT = 2;
    static final int DIR_DOWN = 3;
    static final int DIR_LEFT = 4;
    int direction = DIR_POUSE;

    Snake(int x0, int y0, int sz){
        bodySize = sz;

        int x = x0 * sz ;
        int y = y0 * sz ;

        for(int i = 0; i < 3; i++){
            //
            body.add(new Point(x+(i*bodySize), y));

            System.out.println(body);
        }
    }
    public ArrayList<Point> getBody(){
        return body;
    }
    private Image loadSnakeImage(){
        ImageIcon snakeHead = new ImageIcon("src/resources/snakeHead.png");
        Image image = snakeHead.getImage();

        Image imageScaled = getScaledImage(image, 45,45);
        return imageScaled;
    }
    public Image loadAppleImage(){
        ImageIcon appleImage = new ImageIcon("src/resources/apple.png");
        Image image = appleImage.getImage();
        Image appleScaled = getScaledImage(image, 45,45);
        return appleScaled;
    }
    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
    void paint(Graphics2D g){
        Point[] counts = new Point[body.size()];
        counts[body.size()-1] = body.get(body.size()-1);
        g.drawImage(loadSnakeImage(),counts[body.size()-1].x+3,counts[body.size()-1].y+3,null);
        for(int i = body.size()-2; i >= 0; i--){
            counts[i] = body.get(i);
            g.setColor(Color.GREEN);
            g.fillArc(counts[i].x, counts[i].y, bodySize, bodySize, 0, 360);
            g.setColor(Color.magenta);
            g.drawArc(counts[i].x, counts[i].y, bodySize, bodySize, 0, 360);
        }
    }
    public Point move(){
        Point last = body.get(body.size() - 1);
        Point pp = last;
        switch (direction){
            case DIR_POUSE:
                break;
            case DIR_UP:
                body.remove(0);
                pp = new Point(last.x, last.y - bodySize);
                body.add(pp);
                delete();
                System.out.println(body);
                break;
            case DIR_RIGHT:
                body.remove(0);
                pp = new Point(last.x + bodySize, last.y);
                body.add(pp);
                delete();
                System.out.println(body);
                break;
            case DIR_DOWN:
                body.remove(0);
                pp = new Point(last.x, last.y + bodySize);
                body.add(pp);
                delete();
                System.out.println(body);
                break;
            case DIR_LEFT:
                body.remove(0);
                pp = new Point(last.x - bodySize, last.y);
                body.add(pp);
                delete();
                System.out.println(body);
                break;
        }
        return pp;
    }
    public int getDirection() {
        return direction;
    }
    public int getSpeed() {
        return 20;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
    public void expand() {
        body.add(0, new Point(body.get(0).x, body.get(0).y));
    }
    public void delete() {
        for (int i = 1; i < body.size(); i++) {
            if (body.get(i).equals(body.get(0))) {
                body.removeAll(body.subList(i, body.size()));
            }
        }
    }
}
