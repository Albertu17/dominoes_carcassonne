package JeuCarcassonne;

import java.awt.BorderLayout;

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

    public void updatePanelJoueurs() {
        for (int i = 0; i < partie.joueurs.nbJoueurs(); i++) {
            panelJoueurs.get(i).updatePanel();
            panelJoueurs.get(i).affichePionsRestants();
        }
        conteneurInfos.repaint(); // Repeint GUI.
        conteneurInfos.revalidate(); // Revalide GUI.
    }

    public void demanderSiPosePion(){
        ((TuileCarcassonne)partie.aJouer).checkBoxesAjouterPion();
        conteneurTuileAJouer.removeAll() ;
        // ajout du bouton ne pas poser pion
        nePasPoserPion = new JButton("Finir mon tour") ;
        nePasPoserPion.setVisible(true);
        nePasPoserPion.addActionListener(event ->{
            ((TuileCarcassonne)partie.aJouer).removeBoutonPlacagePion();
            // au joueur d'apres
            partie.tourSuivant();
        });
        conteneurTuileAJouer.add(nePasPoserPion, BorderLayout.CENTER); 
        conteneurTuileAJouer.revalidate();
        conteneurTuileAJouer.repaint();
    }

}
