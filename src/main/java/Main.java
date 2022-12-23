import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Main extends Canvas {
    public static final int LARGEUR = 500;
    public static final int HAUTEUR = 600;
    private ArrayList<Sprite> listeSprite = new ArrayList<>();
    private ArrayList<Tuyau> listeTuyaux = new ArrayList<>();
    private Flappy flappy = new Flappy();

    Main() throws InterruptedException {
        JFrame fenetre = new JFrame("Flappy Bird");
        JPanel panneau = (JPanel) fenetre.getContentPane();
        panneau.setPreferredSize(new Dimension(LARGEUR, HAUTEUR));
        setBounds(0, 0, LARGEUR, HAUTEUR);
        panneau.add(this);

        fenetre.pack();
        fenetre.setResizable(false);
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        KeyListener key = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_SPACE){
                    flappy.vol();
                    /*System.out.println(e.getKeyCode());*/
                    /*System.out.println(KeyEvent.VK_SPACE);*/
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        };

        //On ajoute l'écoute
        addKeyListener(key);

        createBufferStrategy(2);
        setIgnoreRepaint(true);
        setFocusable(true);

        //On prend le focus de la fenêtre pour l'écoute
        this.requestFocus();
        run();
    }

    public void run() throws InterruptedException {
        Tuyau tuyauHaut = new Tuyau(250, 0);
        Tuyau tuyauBas = new Tuyau(250, 400);

        listeSprite.add(flappy);

        listeTuyaux.add(tuyauHaut);
        listeSprite.add(tuyauHaut);
        listeTuyaux.add(tuyauBas);
        listeSprite.add(tuyauBas);

        boolean startGame = true;
        while(startGame) {

            Graphics2D dessin = (Graphics2D) getBufferStrategy().getDrawGraphics();

            dessin.setColor(new Color(40, 40, 128));
            dessin.fillRect(0,0,LARGEUR,HAUTEUR);

            for(Sprite sprite : listeSprite) {
                sprite.dessiner(dessin);
            }

            tuyauHaut.mouvement();
            tuyauBas.mouvement();
            flappy.mouvement();
            if(flappy.collision(tuyauHaut) || flappy.collision(tuyauBas)) {
                startGame = false;
                int response = JOptionPane.showConfirmDialog(null, "*** Game OVER *** \n Refaire une partie ?", "Flappy Bird", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    dessin.dispose();
                    Main restart = new Main();
                    restart.run();
                } else {
                    System.exit(0);
                }
            }

            dessin.dispose();
            getBufferStrategy().show();
            Thread.sleep(1000 / 60);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Main();
    }

}
