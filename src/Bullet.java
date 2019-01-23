import java.awt.*;

public class Bullet {

    private double x;
    private double y;
    private double bulletDX;
    private double bulletDY;

    private double distX;
    private double distY;
    private double dist;

    private int r;

    private double speed;

    private Color color;

    public Bullet(){

        x = GamePanal.player.getX();
        y = GamePanal.player.getY();
        r = 2;
        speed = 10;

        distX = GamePanal.mouseX - x;
        distY = GamePanal.mouseY - y;
        dist = Math.sqrt(distX * distX + distY * distY);

        bulletDX = distX/dist * speed;
        bulletDY = distY/dist * speed;

        color = Color.WHITE;
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


    public boolean remove (){
        if (y < 0 && y > GamePanal.HEIGHT && x < 0 && x > GamePanal.WIDTH){
            return true;
        }
        return false;
    }

    public void update(){
        y += bulletDY;
        x += bulletDX;

    }

    public void drow(Graphics2D g){

        g.setColor(color);
        g.fillOval((int) x, (int) y, r, 2 * r);
    }
}
