import javax.swing.*;

public class GameStart {

    public static void main(String[] args) {

        GamePanal panal = new GamePanal();
        JFrame startJframe = new JFrame("BobleShoot");


        startJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startJframe.setContentPane(panal);
        startJframe.setLocationRelativeTo(null);
        startJframe.pack();
        startJframe.setVisible(true);
        panal.start();

    }
}
