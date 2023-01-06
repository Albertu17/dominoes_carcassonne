package JeuCarcassonne;

import javax.swing.JButton;

import JeuTuilesGenerique.Modele.Partie;
import JeuTuilesGenerique.Vue.GameView;

public class VueCarcassonne extends GameView{

    
    JButton nePasPoserPion ;

    public VueCarcassonne(Partie partie) {
        super(partie);
        super.titre.setText("Jeu de Carcassonne");
        // TODO Ajouter spécificités conteneurInfos

         
    }

    public void demanderSiPosePion(){
        ((TuileCarcassonne)partie.aJouer).BoutonsAjouterPion();
        conteneurTuileAJouer.removeAll() ;
        // ajout du bouton ne pas poser pion
        nePasPoserPion = new JButton("Finir mon tour") ;
        
        nePasPoserPion.setVisible(true);

        conteneurTuileAJouer.add(nePasPoserPion); 

        nePasPoserPion.addActionListener(event ->{
            ((TuileCarcassonne)partie.aJouer).removeBoutonPlacagePion();

            // au joueur d'apres
            partie.tourSuivant();
        });

    }

}
