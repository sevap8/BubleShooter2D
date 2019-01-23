import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.awt.*;

public class Wave {


    private int waveMultiplier;

    private int weaeNamber;

    private long waveTimer;

    private long waveDelay;

    private long waveTimeDiff;

    private String waveText;

    public Wave(){


        weaeNamber=1;
        waveMultiplier = 5;
        waveTimer = 0;
        waveDelay = 5000;
        waveTimeDiff = 0;
        waveText = " W A F E !!!";


    }

    public void createEnemies(){

        int enemyCount  = weaeNamber * waveMultiplier;

        if (weaeNamber < 10){
            while (enemyCount > 0){

                int taye = 1;
                int rang = 1;


                GamePanal.enemies.add(new Enemy(taye,rang));

                enemyCount -= taye * rang;



            }
        }

        weaeNamber++;
    }

    public  void update() {

        if (GamePanal.enemies.size() == 0 && waveTimer == 0){

            waveTimer = System.nanoTime();

        }

        if (waveTimer > 0 ){
            waveTimeDiff += (System.nanoTime() - waveTimer) / 1000000;
            waveTimer = System.nanoTime();
        }

        if (waveTimeDiff > waveDelay){
            createEnemies();
            waveTimer = 0;
            waveTimeDiff = 0;
        }

    }

    public boolean showWave(){
        if (waveTimer != 0 ){
            return true;
        }
        else {
            return false;
        }
    }

    public void drow(Graphics2D g){

        double divider = waveDelay / 180;
        double alpha = waveTimeDiff/divider;
        alpha = 255 * Math.sin(Math.toRadians(alpha));

      if (alpha < 0) alpha = 0;

      if (alpha > 255) alpha = 255;

      g.setFont(new Font("consolas", Font.PLAIN, 20 ));

        g.setColor(new Color (255,255,255, (int)alpha));

        String s = "-" + weaeNamber + " " +"ая" + " " + waveText;

        long lenght = (int)g.getFontMetrics().getStringBounds(s,g).getWidth();

        g.drawString(s, GamePanal.WIDTH /2 - (int)(lenght/2), GamePanal.HEIGHT /2);

    }
}
