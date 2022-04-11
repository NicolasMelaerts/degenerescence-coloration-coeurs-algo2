package entrainementJava;

public class Bacon {
    private String name;
    private int age;
    private boolean enVie = true;


    public Bacon(String name, int age){
        this.name = name;
        this.age = age;
        
    }

    public void sePresente(){
        System.out.println("Je suis un bacon, mon nom est " + name + " et mon age est " + age);
    }

    public boolean getEnVie(){
        return enVie;
    }

    public String getName(){
        return name;
    }
}
