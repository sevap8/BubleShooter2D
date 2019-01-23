import java.awt.*;

public class Player {

    private double x;
    private double y;
    private int r;
    private double dx;
    private double dy;
    private  int speed;
    private double health;

    private Color color1;
    private Color color2;

    public static boolean up;
    public static boolean down;
    public static boolean left;
    public static boolean right;
    public static boolean isFiring;

    public Player(){

        x = GamePanal.WIDTH / 2;
        y = GamePanal.WIDTH / 2;
        r = 5;
        speed = 10;
        health = 3;
        dx = 0;
        dy = 0;
        color1 = Color.WHITE;

        up = false;
        down = false;
        left = false;
        right = false;
        isFiring = false;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }


    public int getR(){
        return r;
    }

    public void hit(){
        health--;
    }

    public void update(){

        if (up && y > r){
            dy = -speed;
        }
        if (down && y < GamePanal.HEIGHT - r){
            dy = speed;
        }
        if (left && x > r){
            dx = -speed;
        }
        if (right && x < GamePanal.WIDTH - r){
            dx = speed;
        }
        if (up && left || up && right || down && left || down && right ){
            dy = (int) (dy * Math.sin(45));
            dx = (int) (dx * Math.cos(45));
        }

        y += dy;
        x += dx;

        dy = 0;
        dx = 0;

        if (isFiring){
            GamePanal.bullets.add(new Bullet());
        }
    }

    public  void  deaw (Graphics2D g){

        g.setColor(color1);
        g.fillOval((int) (x-r), (int) (y-r), 2*r,2*r );
        g.setStroke(new BasicStroke(3 ));
        g.setColor(color1.darker());
        g.drawOval((int) (x-r), (int) (y-r), 2*r,2*r );
        g.setStroke(new BasicStroke(1));

    }

}
