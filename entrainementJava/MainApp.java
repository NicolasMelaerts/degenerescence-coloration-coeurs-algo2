package entrainementJava;


public class MainApp{
    public static void main(String[] args) {
        final double PI = 3.1415;

        int ageMrBacon  = 10;
        final char jourNaissance = 'M';     // final -> const
        System.out.println("Bonjour, je suis MrBacon et j'ai " + ageMrBacon + " ans et la première lettre de mon nom est " + jourNaissance);
        System.out.println("\tLa valeur de PI est : " + PI);

        int nb1 = 1;
        int nb2 = nb1++;
        int nb3 = ++nb1;

        System.out.println("nb1 = " + nb1 + " nb2 = " + nb2 + " nb3 = " + nb3);

        conditionEtBoucle(args);
    }
    public static void typeDeDonnee(String[] args){
        boolean bool;       // O et 1
        byte b;             // -128 à 127

        char c;             // Unicode
        short shortint;     //-32768 à 32767
        int i;              // -2147483648 à -2147483647
        long l;             // -2^63 à 2^63-1
        float f;            // 1.4*10^-45 à 3.4*10^-38
        double d;           // 4.9*10^-324 à 1.7*10^308
    }

    public static void conditionEtBoucle(String[] args){
        int value1 = 0;
        int value2 = 100;

        if (value1<value2){
            System.out.println("0 est bien inférieur à 100");
            value1 = 101;
        }
        else if(value1>value2){
            System.out.println("100 est bien supérieur à 0");
        }
        else{
            System.out.println("Problème");
        }

        int mouv = 2;

        switch(mouv){
            case 2:
                System.out.println("mouvement 2");
                break;
            default:
                System.out.println("Pas de mouvement");
        }
        int i = 0;
        while(i < 10){
            System.out.println("NB = " +i);
            i++;
        }

        do{
            System.out.println("NB = " +i);
            i++;
        }while(i < 20);

        for (int j = 100; j<110; j++){
            System.out.println("NB in for = " +j);
        }
    }

}