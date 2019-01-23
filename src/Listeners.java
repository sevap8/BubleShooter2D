import java.awt.event.*;

public class Listeners implements KeyListener, MouseListener, MouseMotionListener {

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W){
            Player.up = true;
        }
        if(key == KeyEvent.VK_S){
            Player.down = true;
        }
        if(key == KeyEvent.VK_A){
            Player.left = true;
        }
        if(key == KeyEvent.VK_D){
            Player.right = true;
        }
        if(key == KeyEvent.VK_SPACE){
            Player.isFiring = true;
        }
        if(key == KeyEvent.VK_ESCAPE){
            GamePanal.states = GamePanal.STATES.MENUE;
        }

    }


    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W){
            Player.up = false;
        }
        if(key == KeyEvent.VK_S){
            Player.down = false;
        }
        if(key == KeyEvent.VK_A){
            Player.left = false ;
        }
        if(key == KeyEvent.VK_D){
            Player.right = false;
        }
        if(key == KeyEvent.VK_SPACE){
            Player.isFiring = false;
        }

    }


    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }


    public void mousePressed(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON1){
            GamePanal.player.isFiring = true;
            GamePanal.leftMouse = true;
        }
    }

    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            GamePanal.player.isFiring = false;
            GamePanal.leftMouse = false;
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    public void mouseDragged(MouseEvent e) {

        GamePanal.mouseX = e.getX();
        GamePanal.mouseY = e.getY();

    }


    public void mouseMoved(MouseEvent e) {

        GamePanal.mouseX = e.getX();
        GamePanal.mouseY = e.getY();
    }
}
