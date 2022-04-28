import java.util.Vector;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/*

Le constructeur My_Graph(Graph g) et les méthodes deg(int v) et toString() ont été reprise 
dans algs4.jar (http://algs4.cs.princeton.edu) et ont été réalisé par Robert Sedgewick and Kevin Wayne

*/

public class My_Graph {
    private int nb_V;
    private int nb_E;
    private Vector<Integer>[] adjacent;

    public My_Graph(Graph g){

        this.nb_V = g.V();
        this.nb_E = g.E();

        adjacent = (Vector<Integer>[]) new Vector[nb_V];
        for (int v = 0; v < nb_V; v++) {
            adjacent[v] = new Vector<Integer>();
        }

        for (int v = 0; v < nb_V; v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : g.adj(v)) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adjacent[v].add(w);
            }
        }
    }

    // renvoie le nombre de sommet du graph
    public int getV(){
        return nb_V;
    }

    // renvoie le degré du sommet v
    public int deg(int v){
        return adjacent[v].size();
    }

    // renvoie la k-dégénération du graph
    public int degenerate(){
        int k = 1;
        boolean retry = false;

        Vector<Integer> allDegree = new Vector(nb_V);
        for (int v = 0; v < nb_V; v++) {
            allDegree.add(deg(v));
        }

        int edgeRemoved = 0;

        while (edgeRemoved!=nb_E){
            for (int v = 0; v < nb_V; v++) {
                if (allDegree.elementAt(v) <= k && allDegree.elementAt(v)>0){
                    for (int w : adjacent[v]){
                        if (allDegree.elementAt(w)>0){
                            allDegree.set(w, allDegree.elementAt(w)-1);
                            allDegree.set(v, allDegree.elementAt(v)-1);
                            edgeRemoved++;
                            retry = true;
                        }
                    }
                }
            }
            if (!retry) {   // des sommets ont été supprimé de nouveau sommet peuvent avoir un degré <= k
                k++;
            }
            retry = false;

        }
        return k;
    }

    // renvoie la couleur qui sera attribué au sommet en fonction des couleurs de ses sommets voisins
    public int color(int nb_color, Vector<Integer> allColorNeightboor){
        for (int i = 1; i <= nb_color; i++){
            if (!allColorNeightboor.contains(i)){
                return i;
            }
        }
        return nb_color+1;
    }

    // renvoie le nombre de couleur minimal coloriant le graphe suivant le même principe que la dégénérescence
    public int coloration(){
        int edgeRemoved = 0;
        int k = 1;
        int nb_color = 1;
        int lastVertice = -1;
        int newc;

        boolean retry = false;

        Vector<Integer> allColor = new Vector(nb_V);
        Vector<Integer> allColorNeightboor = new Vector();

        Vector<Integer> allDegree = new Vector(nb_V);
        for (int v = 0; v < nb_V; v++) {
            allDegree.add(deg(v));
            allColor.add(0);
        }

        while (edgeRemoved!=nb_E){
            for (int v = 0; v < nb_V; v++) {
                if (allDegree.elementAt(v) <= k && allDegree.elementAt(v)>0){
                    for (int w : adjacent[v]){
                        if (allDegree.elementAt(w)>0){
                            allDegree.set(w, allDegree.elementAt(w)-1);
                            allDegree.set(v, allDegree.elementAt(v)-1);
                            edgeRemoved++;
                            retry = true;
                            lastVertice = w;
                        }
                        if (allColor.elementAt(w) != 0){
                            allColorNeightboor.add(allColor.elementAt(w));
                        }
                    }
                    newc = color(nb_color, allColorNeightboor);
                    allColor.set(v, newc);
                    if (newc>nb_color){
                        nb_color = newc;
                    }
                    allColorNeightboor.clear();
                }
            }
            if (!retry) {   // des sommets ont été supprimé de nouveau sommet peuvent avoir un degré <= k
                k++;
            }
            retry = false;

        }

        // placer la dernière couleur
        for (int w : adjacent[lastVertice]){
            if (allColor.elementAt(w) != 0){
                allColorNeightboor.add(allColor.elementAt(w));
            }
        }
        newc = color(nb_color, allColorNeightboor);
        allColor.set(lastVertice, newc);
        if (newc>nb_color){
            nb_color = newc;
        }

        return nb_color;
    }

    // renvoie la profondeur d'un sommet 
    public int c(int sommet){
        int k = 1;
        boolean done = false;
        boolean retry = false;

        Vector<Integer> allDegree = new Vector(nb_V);
        for (int v = 0; v < nb_V; v++) {
            allDegree.add(deg(v));
        }

        int removedEdges = 0;

        while (removedEdges!=nb_E && !done){
            for (int v = 0; v < nb_V; v++) {
                if (allDegree.elementAt(v) <= k && allDegree.elementAt(v)>0){
                    for (int w : adjacent[v]){
                        if (allDegree.elementAt(w)>0){
                            allDegree.set(w, allDegree.elementAt(w)-1);
                            allDegree.set(v, allDegree.elementAt(v)-1);

                            if (allDegree.elementAt(w) == 0){
                                if (w == sommet){
                                    done = true;
                                    break;
                                }
                            }
                            if (allDegree.elementAt(v) == 0){
                                if (v == sommet){
                                    done = true;
                                    break;
                                }
                            }
                            removedEdges++;
                            retry = true;
                        }
                    }
                    if (done){
                        break;
                    }
                }
            }
            if (!retry && !done) {   // des sommets ont été supprimé de nouveau sommet peuvent avoir un degré <= k
                k++;
            }
            retry = false;

        }        
        return k;
    }

    public void testDegen(){
        long debut;
        long tmps;
        int degen;

        debut = System.currentTimeMillis();
        degen = degenerate();
        tmps = System.currentTimeMillis()-debut;
        
        System.out.println("Dégénéréscence de " + degen);
        System.out.println("Temps moyen dégénérescence graphe = " + tmps + "millisec\n");
    }

    public void testColor(){
        long debut;
        long tmps;
        int color;

        debut = System.currentTimeMillis();
        color = coloration();
        tmps = System.currentTimeMillis()-debut;

        System.out.println("Coloration de " + color);
        System.out.println("Temps moyen coloration graphe = " + tmps + "millisec\n");
    }

    public void testCore(){
        long debut;
        long tmps;

        int not_to_long = nb_V;

        if (not_to_long > 100){
            not_to_long = 10;
        }

        debut = System.currentTimeMillis();
        for (int v=0; v < not_to_long; v++){
            StdOut.print("c(" + v + ") : " + c(v) + ", ");
        }
        tmps = System.currentTimeMillis()-debut;
        
        System.out.println("\nTemps moyen profondeur graphe = " + tmps + "millisec\n");
    }

    public static void main(String[] args) {
        String fichier = "./src/facebook_combined.txt";

        In in = new In(fichier);
        Graph g = new Graph(in);

        My_Graph my_g = new My_Graph(g);
        my_g.testDegen();
        my_g.testColor();
        my_g.testCore();
    }
    
}
