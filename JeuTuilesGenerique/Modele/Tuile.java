package JeuTuilesGenerique.Modele;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import JeuTuilesGenerique.Vue.GameView;

public class Tuile extends JPanel implements MouseInputListener {

    GameView environnement;
    int x, y;
    public Bord nord;
    public Bord est;
    public Bord sud;
    public Bord ouest;
    boolean moving;

    public Tuile() {
        addMouseListener(this);
        addMouseMotionListener(this);
        setBackground(Color.GRAY);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public Tuile(Bord nord, Bord est, Bord sud, Bord ouest) {
        this();
        this.nord = nord;
        this.est = est;
        this.sud = sud;
        this.ouest = ouest;
    }

    // true --> tourner dans le sens Horaire
    public void Rotate(boolean sensHoraire){
        Bord tmp = nord;
        if ( sensHoraire) {
            nord = ouest;
            ouest  = sud;
            sud =  est;
            est = tmp;
        } else {
            nord = est;
            est = sud;
            sud = ouest; 
            ouest = tmp;
        }
    }

    public void setEnvironnement(GameView environnement) {
        this.environnement = environnement;
    }

    public void setCoordonnées(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Mouse clicked = mouse pressed and released
    public void mouseClicked(MouseEvent e) {
        if (environnement.partie.aJouer != this && environnement.partie.check(x, y)) {
            environnement.partie.jouer(x, y);
            environnement.updateGrille(environnement.partie.aJouer, x, y);
            environnement.updateTuileAJouer();
        }  
    }

    public void mousePressed(MouseEvent e) {
        if (environnement.partie.aJouer == this) moving = true;
    }

    public void mouseReleased(MouseEvent e) {
        if (environnement.partie.aJouer == this) moving = false;
    }

    // TODO bon comportement quand tuile est dragged
    public void mouseDragged(MouseEvent e) {
        // if (moving) setLocation(e.getXOnScreen() - environnement.getX(),
        // e.getYOnScreen() - environnement.getY() - environnement.getInsets().top);
    }

    public void mouseMoved(MouseEvent e) {}
    // Lorsque la souris passe sur une tuile de la grille, sa bordure devient verte si la tuile à jouer est
    // plaçable à cet endroit, sinon sa bordure devient rouge.
    public void mouseEntered(MouseEvent e) {
        if (environnement.partie.aJouer != this) {
            if (environnement.partie.check(x, y)) setBorder(BorderFactory.createLineBorder(Color.GREEN));
            else setBorder(BorderFactory.createLineBorder(Color.RED));
        }
    }

    public void mouseExited(MouseEvent e) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

}
