package GUI;

import javax.swing.* ;
import Modele.Commun.Modele ;

public class Gameview extends JFrame{
    Modele modele ;
    Menu menu ;

    public Gameview(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setUndecorated(true);
        setVisible(true);


        menu = new Menu(this);
        
    }

    public void setModele(Modele mod){this.modele = mod ;}



    public static void main(String[] args) {
        new Gameview() ;
    }

}
