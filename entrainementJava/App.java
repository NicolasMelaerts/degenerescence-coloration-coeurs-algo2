package entrainementJava;

public class App {
    public static void main(String[] args) {
        Bacon archibald = new Bacon("Archibald", 20);
        archibald.sePresente();
        if (archibald.getEnVie()){
            System.out.println(archibald.getName() + " est un bacon vivant");
        }

        Bacon bridinette = new Bacon("Bridinette", 22);
        bridinette.sePresente();

        Bacon nancianne = new Bacon("Nancianne", 22);
        bridinette.sePresente();

        Bacon elohim = new Bacon("Elohim", 22);
        bridinette.sePresente();

        // vecteur 
        Bacon[] enclos = {archibald, bridinette, nancianne, elohim};
        for (int i = 0; i<enclos.length; i++){
            System.out.println(enclos[i].getName() + " est dans l'enclos");
        }

        // alternative 
        for (Bacon i : enclos){
            System.out.println(i.getName() + " est dans l'enclos");
        }
    
        // matrice 
        int[][] M =  {{1,2}, {3,4}, {5,6}, {7,8}};
        for (int i[] : M){
            for (int j : i){
                System.out.println(j);
            }
        }


    }
    
}
