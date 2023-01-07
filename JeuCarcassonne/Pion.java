package JeuCarcassonne;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class Pion extends JPanel {

    Color color ;

    public Pion(Color c){
        setColor(c);
    }

    // rend le pion un peu transparent
    public void setColor(Color c ){
        color = new Color(c.getRed(), c.getGreen(), c.getBlue(), 150);
    }

    protected void paintComponent(Graphics g){
        int rayon = Math.min(this.getWidth(),this.getHeight());
        g.setColor(color);
        // Cercle coloré en plein milieu du JPanel
        g.fillOval((this.getWidth() -rayon)/2 , (this.getHeight()-rayon)/2, rayon, rayon);
    }
}
