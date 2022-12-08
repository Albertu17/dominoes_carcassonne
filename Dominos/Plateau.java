public class Plateau {
    private Tuiles[][] plateau;


    Plateau(int nombreTuiles){
        plateau  = new Tuiles[nombreTuiles+2][] ;
        for (Tuiles[] tab : plateau){
            tab = new Tuiles[nombreTuiles+2] ;
        }
    }
}
