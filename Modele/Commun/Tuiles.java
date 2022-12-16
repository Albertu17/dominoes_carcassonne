package Modele.Commun;
public class Tuiles {


    private Face[] allface ;
    

    Tuiles(int[][] allside) {
        allface = new Face[allside.length] ;

        for (int i = 0  ; i < allface.length ; i++){
            allface[i] = new Face(allside[i]) ;
        }
    }

    Tuiles(){}

    public Face getHaut() {
        return allface[0];
    }

    public Face getDroite() {
        return allface[1];
    }

    public Face getBas() {
        return allface[2];
    }

    public Face getGauche() {
        return allface[3];
    }

    // positif --> tourner dans le sens Horaire
    public void Rotate(int SensHoraire){

        Face hold  = allface[0] ;
        for (int i = 0  ; i < allface.length ; i++){
            allface[i] = allface[(i+SensHoraire)%allface.length] ;
        }

        allface[(SensHoraire)%allface.length]  = hold ;

    }
    
 // anciennne 
    // public void Rotate(int SensHoraire){
    //     if (SensHoraire > 0){
    //         Face hold = haut  ;
    //         haut = droite ;
    //         droite = bas ;
    //         bas = gauche ; 
    //         gauche = hold ;
    //     }else{
    //         Face hold = haut ; 
    //         haut = gauche ;
    //         gauche  = bas ;
    //         bas =  droite ;
    //         droite = hold  ;
    //     }
    //     if ( ! ( SensHoraire <=  0 ) ){
    //         Rotate(SensHoraire - 1);
    //     }
    // }
}