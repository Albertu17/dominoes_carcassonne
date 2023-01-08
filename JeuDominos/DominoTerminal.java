package JeuDominos;

import java.util.Scanner;

import JeuTuilesGenerique.Modele.Joueurs.Joueur;
import JeuTuilesGenerique.Modele.Tuile;

public class DominoTerminal {

    PartieDominos partie ;
    Scanner sc ;

    // pour test
    void addPrePlayer(){
        partie.getJoueurs().addPlayer("Pierre", true) ;
        partie.getJoueurs().addPlayer("Mathieu", false) ;
    }

    
    DominoTerminal(){
        sc = new Scanner(System.in) ;
        partie = new PartieDominos("", false) ;
    }
    

    public void ajoutJoueur(){
        
        String nom ;
        System.out.println();
        System.out.println("Rentrer un nom de Joueur :");
        boolean allreadyask = false ;

        do{
            if (allreadyask) System.out.println("Ce nom est déjà pris, ou nul. Entrer un nouveau nom !");
            nom = sc.next() ;
            allreadyask = true ; 
        }while ( ! partie.getJoueurs().nomLibre(nom)) ;
        
        System.out.println("Ce joueur est-il un humain ? (oui/non)");
        
        Joueur j = partie.getJoueurs(). new Joueur(nom,  ! sc.next().equals("oui") ) ;
        partie.getJoueurs().addPlayer(j);

        System.out.println("Creation de " + j.getName() + ", un " +(j.isIA() ? "robot" : "humain" )+ "." );
           
    }

    public void ajoutertoutJoueur(){
        // ajout de 2 jours minimun
        for (int i = 0; i < 2; i++) {
            ajoutJoueur();
        }

        // Joueur en plus max 6
        while(partie.getJoueurs().nbJoueurs() <=6){
            System.out.println();
            System.out.println("Ajouter un nouveau joueur? (oui/non)");
            if (sc.next().equals("oui")){
                ajoutJoueur();
            }
            else break  ;
        }
    }

    public void TourJoueur(){

        boolean continuer = true ;
        clearTerminal();
        printPlateau();
        ScoreBoard();
        System.out.println();
        System.out.println("C'est au tour de " + partie.getJoueurs().joueurAuTrait().getName()+"." );
        System.out.println();
        System.out.println("Choisi quoi faire :");
        
        while(continuer){
            printTuile((TuileDomino)partie.aJouer);
            System.out.println();
            System.out.println("Tourner à gauche ou droite (G/D), défausser (jeter), placer (placer)");
            switch (sc.next()){
                case "G" :
                    partie.aJouer.rotate(false);
                    break ;
                
                case "D" :
                    partie.aJouer.rotate(true);
                    break;

                case "jeter" :
                    continuer = false ;
                    break ;
                default :
                    
                    continuer = ! placer() ;

                    if (continuer) System.out.println("Placement impossible ici");
                    break ;


            }
        }


    }

    public boolean placer(){
        System.out.println();
        System.out.println("Renseigner vos coordonnnées (x,y)");
        String[] coord  =  sc.next().split(",") ;
        int x = Integer.valueOf( coord[0] ) ;
        int y = Integer.valueOf( coord[1] ) ;

        return partie.jouerTerminal(x, y) ; 

    }
    
    
    public void JeuDomino(){
        ajoutertoutJoueur();
        
        // fait jouer les joueur tour à tour
        
        partie.getJoueurs().nextJoueurAuTrait();
        while( ! partie.partieFinie()){
            partie.aJouer = partie.getPioche().pickOne() ;

            if (partie.getJoueurs().joueurAuTrait() .isIA()){
                partie.gestionTourIA();
                System.out.println("L'IA a jouer ! ");
            }else{
                TourJoueur();
            }
            
            partie.getJoueurs().nextJoueurAuTrait();
        }
        
    
    }
    
    
    
        public static void main(String[] args) {
            DominoTerminal  d1 = new DominoTerminal() ;
            
            d1.JeuDomino();
        }

    public void ScoreBoard(){
        System.out.println();
        System.out.println("                Score :");
        for (Joueur j : partie.getJoueurs().getList()) {
            System.out.println("             " + j.getName() + " : " + j.getScore() + " pts.");
        }
        System.out.println();
        System.out.println();
    }

    public String[] StringTabTuille(TuileDomino act){
        String[] AffichesTuiles = new String[]{"", "", "", "", ""} ;

            // affichage haut 1
            AffichesTuiles[0] += "* " ;
            for (int nombre : act.nord.getNumeros()) {
                AffichesTuiles[0] += nombre + " " ;
            }
            AffichesTuiles[0] += "*" ;
        // affichage bas 5 :
            AffichesTuiles[4] += "* " ;
            for (int nombre : act.sud.getNumeros()) {
                AffichesTuiles[4] += nombre + " " ;
            }
            AffichesTuiles[4] += "*" ;


        // affichage ligne 2,3,4 :
            AffichesTuiles[1] += act.ouest.getNumeros()[0] + " ".repeat(7) + act.est.getNumeros()[0] ;
            AffichesTuiles[2] += act.ouest.getNumeros()[1] + " ".repeat(3) + "#" + " ".repeat(3) + act.est.getNumeros()[1] ;
            AffichesTuiles[3] += act.ouest.getNumeros()[2] + " ".repeat(7) + act.est.getNumeros()[2] ;

        return AffichesTuiles ;
    }
    
    
    public void printTuile(TuileDomino tuile){
        
        for (String ligne : StringTabTuille(tuile)) {
            System.out.println("   " + ligne);
        }

    }

    public void clearTerminal(){
        try {
            if(System.getProperty("os.name" ).startsWith("Windows" ))
            Runtime.getRuntime().exec("cls" );
            else
            Runtime.getRuntime().exec("clear" );
        }
        catch(Exception e) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }

    public void printPlateau(){
        Tuile[][] platTab = partie.getPlateau().plateau ; 
        Tuile act  ;

        System.out.println(" " + "_".repeat((9+2) *(platTab[0].length ) +2 ) + " ");
        System.out.println("|"+  " ".repeat((9+2) *(platTab[0].length ) + 2)  +"|");


        for(int i = 0 ; i < platTab.length ; i++ ){
            String[] AffichesTuiles = new String[5] ;

            for (int nbrligne = 0 ; nbrligne < AffichesTuiles.length ; nbrligne++) {
                AffichesTuiles[nbrligne]  = "|  " ;
            }
            

            for(int j = 0 ; j < platTab[0].length ; j++ ){
                act = platTab[i][j] ;
                if (act != null && act.nord != null){
                    // System.out.println(act.getClass());
                    String[] dispTuile = StringTabTuille((TuileDomino)act) ;
                    for (int j2 = 0; j2 < AffichesTuiles.length; j2++) {
                        AffichesTuiles[j2] += dispTuile[j2] ;
                    }
                    
                }else{
                    for (int nbrligne = 0 ; nbrligne < AffichesTuiles.length ; nbrligne++){
                        if (nbrligne == 2) AffichesTuiles[nbrligne] += " ".repeat(4) +"#"+ " ".repeat(4) ; 

                        else AffichesTuiles[nbrligne] += " ".repeat(9) ; 
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

}

