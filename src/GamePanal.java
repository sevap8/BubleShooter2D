import org.omg.CORBA.PUBLIC_MEMBER;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanal extends JPanel implements Runnable{

    public static int WIDTH = 600;
    public static int HEIGHT = 600;

    public static int mouseX;
    public static int mouseY;

    public static boolean leftMouse;
    private Thread thread;

    private BufferedImage image;
    private Graphics2D g;

    private int FPS;
    private double millisToFPS;
    private long timerFPS;
    private int sleepTime;

    public static enum STATES{
        MENUE,
        PLAY
    }

    public static STATES states = STATES.MENUE;

    public static GameBack background;
    public static Player player;
    public static ArrayList<Bullet> bullets;
    public static ArrayList<Enemy> enemies;
    public static Wave wave;
    public static Menue menue;

    public GamePanal (){
        super();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
        addKeyListener(new Listeners());
        addMouseMotionListener(new Listeners());
        addMouseListener(new Listeners());
    }

    public void start(){

        thread = new Thread(this);
        thread.start();
    }

    public void run() {

        FPS = 30;
        millisToFPS = 1000/FPS;
        sleepTime = 0;


        leftMouse = false;
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
        g = (Graphics2D) image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);



        background = new GameBack();
        player = new Player();
        bullets = new ArrayList<Bullet>();
        enemies = new ArrayList<Enemy>();
        wave = new Wave();
        menue = new Menue();

        Toolkit kit = Toolkit.getDefaultToolkit();
        BufferedImage buffered = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g3 = (Graphics2D) buffered.getGraphics();
        g3.setColor(new Color(255,255,255));
        g3.drawOval(0,0, 4,4);
        g3.drawLine(2, 0, 2, 4);
        g3.drawLine(0,2,4,2);
        Cursor myCursor = kit.createCustomCursor(buffered, new Point(3,3), "myCursor");
        g3.dispose();



        while (true){

            this.setCursor(myCursor);
            timerFPS = System.nanoTime();


            if (states.equals(STATES.MENUE)){
                background.update();
                background.draw(g);
                menue.drow(g);
                menue.update();

                gameDraw();

            }
            if (states.equals(STATES.PLAY)){
                gameUpdate();
                gameRender();
                gameDraw();
            }





            timerFPS = (System.nanoTime()-timerFPS)/1000000;

            if (millisToFPS > timerFPS){
                sleepTime = (int)(millisToFPS - timerFPS);
            }else sleepTime = 1;

            try {
                thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timerFPS = 0;
            sleepTime = 1;
        }

    }

    public void gameUpdate(){

        background.update();

        player.update();

        for (int i = 0; i <bullets.size(); i++){
            bullets.get(i).update();
            boolean remove = bullets.get(i).remove();
            if (remove){
                bullets.remove(i);
                i--;
            }
        }

        for (int i = 0; i < enemies.size(); i ++ ){
            enemies.get(i).update();
        }

        for (int i = 0; i < enemies.size(); i ++ ){
            Enemy e = enemies.get(i);
            double ex = e.getX();
            double ey = e.getY();

            for (int j = 0; j < bullets.size(); j++){

                Bullet b = bullets.get(j);
                double bx = b.getX();
                double by = b.getY();

                double dx = ex - bx;
                double dy = ey - by;

                double dist = Math.sqrt(dx * dx + dy * dy);

                if ((int) dist <= e.getR() + b.getR()){
                    e.hit();
                    bullets.remove(j);

                    j--;

                    boolean revive = e.remove();
                    if (revive){
                        enemies.remove(i);
                        i--;
                        break;
                    }

                }

            }

        }

        for (int i = 0; i < enemies.size(); i++){

            Enemy e = enemies.get(i);
             double ex = e.getX();
             double ey = e.getY();

             double px = player.getX();
             double py = player.getY();

             double dx = ex - px;
             double dy = ey - py;

             double dist = Math.sqrt(dx * dx + dy * dy);

             if ((int)dist <= e.getR()+ player.getR()){
                 e.hit();
                 player.hit();

                 boolean revive = e.remove();
                 if (revive){
                     enemies.remove(i);
                     i--;

                 }
             }
        }

        wave.update();
    }



    public  void gameRender() {

        background.draw(g);

        player.deaw(g);

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).drow(g);
        }


        for (int i = 0; i < enemies.size(); i ++ ){
            enemies.get(i).draw(g);
        }

        if (wave.showWave()){
        wave.drow(g);
        }

    }

    private void gameDraw(){
        Graphics g2 = this.getGraphics();
        g2.drawImage(image,0,0,null);
        g2.dispose();
    }
}
