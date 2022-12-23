import java.awt.*;

public class Flappy extends Sprite {
    private int x;
    private int y;
    private float vitesseFlappy;

    public Flappy() {
        this.x = 25;
        this.y = 300;
        this.largeur = 25;
        this.hauteur = 25;
        this.vitesseFlappy = 2;
        couleur = Color.YELLOW;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public void mouvement() {
        this.vitesseFlappy -= 0.1;
        this.y -= vitesseFlappy;
        if(this.y <= 5){
            this.y = 0;
        }
    }

    public void vol(){
        this.vitesseFlappy += 5;
    }

    public boolean collision(Tuyau tuyau){
        if(getY() <= 0 ){
            Rectangle flappyHit = new Rectangle(getX(), getY(), getLargeur(), getHauteur());
            Rectangle tuyauHit = new Rectangle(tuyau.x, tuyau.y, tuyau.largeur, tuyau.hauteur);
            System.out.println("*** FLAPPY ***");
            System.out.println(flappyHit);
            System.out.println("*** TUYAU ***");
            System.out.println(tuyauHit);
            if(flappyHit.intersects(tuyauHit)) {
                System.out.println("Tu as touché un tuyau !!");
                return true;
            }
            return false;
        }
        return false;
    }
    @Override
    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillRect(x, y, largeur, hauteur);
    }
}
