import java.awt.*;

public class Menue {

    private int buttonWidth;
    private int buttonHaight;
    private Color color1;
    private String s;
    private int transp = 0;


    //Constructor

    public Menue(){

        buttonHaight = 60;
        buttonWidth = 120;

        color1 = Color.white;
        s = "Play!";
    }

    //Metods

    public  void update(){

        if (GamePanal.mouseX > GamePanal.WIDTH/2 -buttonWidth/2 &&
                GamePanal.mouseX < GamePanal.WIDTH/2 + buttonWidth/2 &&
                GamePanal.mouseY > GamePanal.HEIGHT/2 - buttonHaight/2 &&
                GamePanal.mouseY < GamePanal.HEIGHT/2 + buttonHaight/2){

            transp = 60;
            if (GamePanal.leftMouse){
                GamePanal.states = GamePanal.STATES.PLAY;
            }
        }
        else {
            transp = 0;
        }
    }

    public void drow (Graphics2D g){

        g.setColor(color1);
        g.setStroke(new BasicStroke(3));
        g.drawRect(GamePanal.WIDTH/2 -buttonWidth/2, GamePanal.HEIGHT/2 - buttonHaight/2, buttonWidth, buttonHaight);
        g.setColor(new Color(255,255,255, transp));
        g.fillRect(GamePanal.WIDTH/2 -buttonWidth/2, GamePanal.HEIGHT/2 - buttonHaight/2, buttonWidth, buttonHaight);
        g.setStroke(new BasicStroke(1));
        g.setColor(color1);
        g.setFont(new Font("Consolas",Font.BOLD, 40));
        long length = (int) g.getFontMetrics().getStringBounds(s,g).getWidth();
        g.drawString(s, (int)(GamePanal.WIDTH/2 - length/2), (int) (GamePanal.HEIGHT/2 +buttonHaight/4));


    }


}
