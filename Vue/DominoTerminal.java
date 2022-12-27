import Modele.Commun.*;
public class DominoTerminal {
    Plateau plat ;


    
    DominoTerminal(){
        // juste dans le but de tester  la fonction print 
        plat  = new Plateau() ;
        Pioche sac = new Pioche((int)(Math.random()*10) +1 ) ;
        Tuiles t = sac.pickOne();
        plat.add(t, 0, 0) ;
        while ((t = sac.pickOne()) != null){
            if (Math.random() > .5){
                plat.add(t, -1, 0) ;
            }else{
                plat.add(t, 0, -1) ;
            }
        }
    }




    public void printPlateau(){
        Tuiles[][] platTab = plat.getPlateau() ; 
        Tuiles act  ;

        System.out.println(" " + "_".repeat((9+2) *(platTab[0].length ) +2 ) + " ");
        System.out.println("|"+  " ".repeat((9+2) *(platTab[0].length ) + 2)  +"|");


        for(int i = 0 ; i < platTab.length ; i++ ){
            String[] AffichesTuiles = new String[5] ;

            for (int nbrligne = 0 ; nbrligne < AffichesTuiles.length ; nbrligne++) {
                AffichesTuiles[nbrligne]  = "|  " ;
            }
            

            for(int j = 0 ; j < platTab[0].length ; j++ ){
                act = platTab[i][j] ;
                if (act != null){
                    // affichage haut 1 :
                        AffichesTuiles[0] += "* " ;
                        for (int nombre : act.getHaut().getNumero()) {
                            AffichesTuiles[0] += nombre + " " ;
                        }
                        AffichesTuiles[0] += "*" ;
                    // affichage bas 5 :
                        AffichesTuiles[4] += "* " ;
                        for (int nombre : act.getBas().getNumero()) {
                            AffichesTuiles[4] += nombre + " " ;
                        }
                        AffichesTuiles[4] += "*" ;


                    // affichage ligne 2,3,4 :
                    AffichesTuiles[1] += act.getGauche().getNumero()[0] + " ".repeat(7) + act.getDroite().getNumero()[0] ;
                    AffichesTuiles[2] += act.getGauche().getNumero()[1] + " ".repeat(3) + "#" + " ".repeat(3) + act.getDroite().getNumero()[1] ;
                    AffichesTuiles[3] += act.getGauche().getNumero()[2] + " ".repeat(7) + act.getDroite().getNumero()[2] ;


                }else{
                    for (int nbrligne = 0 ; nbrligne < AffichesTuiles.length ; nbrligne++) {
                        AffichesTuiles[nbrligne] += " ".repeat(9) ; 
                    }
                }

                for (int nbrligne = 0 ; nbrligne < AffichesTuiles.length ; nbrligne++) {
                    AffichesTuiles[nbrligne] += "  " ;
                }
            }

            for (String ligne : AffichesTuiles) {
                System.out.println(ligne + "|");
            }
            
            
        }
        System.out.println("|"+  "_".repeat((9+2) *(platTab[0].length ) + 2)  +"|");
    }


    public static void main(String[] args) {
        DominoTerminal  d1 = new DominoTerminal() ;
        d1.printPlateau();
    }
}