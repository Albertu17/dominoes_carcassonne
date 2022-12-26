package JeuTuilesGenerique.Modele;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class Tuile extends JPanel implements MouseInputListener {

    Bord nord;
    Bord est;
    Bord sud;
    Bord ouest;

    boolean moving;

    // Mouse clicked = mouse pressed and released
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    public void mousePressed(MouseEvent e) {
        moving = true;
    }

    public void mouseReleased(MouseEvent e) {
        moving = false;
    }

    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

}
