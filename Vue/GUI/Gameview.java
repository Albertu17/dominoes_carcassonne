package GUI;

import javax.swing.* ;
import Modele.Commun.Modele ;

public class Gameview extends JFrame{
    Modele modele ;
    Menu menu ;

    public Gameview(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // setExtendedState(JFrame.MAXIMIZED_BOTH); 
        // setUndecorated(true);
        setSize(400, 400);
        setVisible(true);
        this.setLayout(null);

        JPanel panel = new JPanel() ;
        panel.setVisible(true);

        JLabel texte = new JLabel("dams");
        texte.setLocation(150, 100);
        // setLocationRelativeTo(null);
        // panel.add(texte) ;
        this. add(texte) ;
        System.out.println( texte.getLocation() ) ;
        // texte.setVisible(true);


        menu = new Menu(this);
        
    }

    public void setModele(Modele mod){this.modele = mod ;}



    public static void main(String[] args) {
        new Gameview() ;
    }

}
