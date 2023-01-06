import java.awt.*;
import java.util.Random;

public class Tuyau extends Sprite {
    private int vitesseTuyau;
    public static final int LARGEUR_TUYAU = 50;
    public static final int HAUTEUR_TUYAU = 200;

    public Tuyau(int x, int y) {
        this.x = x;
        this.y = y;
        this.largeur = LARGEUR_TUYAU;
        this.hauteur = HAUTEUR_TUYAU;
        this.couleur = new Color(60, 134, 5);
        setVitesseTuyau(-2);
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

    public int getVitesseTuyau() {
        return vitesseTuyau;
    }

    public void mouvement() {
        x += vitesseTuyau;
        if(x <= -LARGEUR_TUYAU) {
            this.x = Main.LARGEUR;
            Random random = new Random();
            this.hauteur = random.nextInt(100, 300);
            if(this.y >= 400){
                this.y = random.nextInt(400, 550);
                this.hauteur = 600;
            }
        }
    }

    public void setVitesseTuyau(int vitesseTuyau) {
        if(vitesseTuyau == 0){
            vitesseTuyau = 1;
        }
        this.vitesseTuyau = vitesseTuyau;
    }

    @Override
    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillRect(x, y, largeur, hauteur);
    }
}
