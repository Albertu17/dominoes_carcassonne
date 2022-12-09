public class Face {
    private int[] numero ;

    Face(int[] entier){
        numero  = new int[3] ;

        for (int i = 0 ; i < numero.length ; i++){
            numero[i] = entier[i] ;
        }
    }

    public int[] getNumero() {
        return numero;
    }

    
}
