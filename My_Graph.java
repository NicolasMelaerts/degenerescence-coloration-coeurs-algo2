import java.util.Vector;
import java.util.*;

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
        int edgeRemoved = 0;

        int [] allDegree = new int[nb_V];

        for (int v = 0; v < nb_V; v++) {
            allDegree[v] = deg(v);
        }

        while (edgeRemoved!=nb_E){
            for (int v = 0; v < nb_V; v++) {
                if (allDegree[v] <= k && allDegree[v]>0){
                    for (int w : adjacent[v]){
                        if (allDegree[w]>0){
                            allDegree[w] = allDegree[w]-1;
                            allDegree[v] = allDegree[v]-1;
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

        int [] allColor = new int[nb_V];
        int [] allDegree = new int[nb_V];

        Vector<Integer> allColorNeightboor = new Vector();

        for (int v = 0; v < nb_V; v++) {
            allDegree[v] = deg(v);
            allColor[v] = 0;
        }

        while (edgeRemoved!=nb_E){
            for (int v = 0; v < nb_V; v++) {
                if (allDegree[v] <= k && allDegree[v]>0){
                    for (int w : adjacent[v]){
                        if (allDegree[w]>0){
                            allDegree[w] = allDegree[w]-1;
                            allDegree[v] = allDegree[v]-1;
                            edgeRemoved++;
                            retry = true;
                            lastVertice = w;
                        }
                        if (allColor[w] != 0){
                            allColorNeightboor.add(allColor[w]);
                        }
                    }
                    newc = color(nb_color, allColorNeightboor);
                    allColor[v] = newc;
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
            if (allColor[w] != 0){
                allColorNeightboor.add(allColor[w]);
            }
        }
        newc = color(nb_color, allColorNeightboor);
        allColor[lastVertice] = newc;
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
        int removedEdges = 0;

        int [] allDegree = new int[nb_V];
        for (int v = 0; v < nb_V; v++) {
            allDegree[v] = deg(v);
        }

        while (removedEdges!=nb_E && !done){
            for (int v = 0; v < nb_V; v++) {
                if (allDegree[v] <= k && allDegree[v]>0){
                    for (int w : adjacent[v]){
                        if (allDegree[w]>0){
                            allDegree[w] = allDegree[w]-1;
                            allDegree[v] = allDegree[v]-1;

                            if ((sommet == v && allDegree[v] == 0) || (sommet == w && allDegree[w] == 0)){
                                done = true;
                                break;
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

    public void profondeur(){
        for (int v = 0; v < getV(); v++){
            c(v);
        }
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

        
        debut = System.currentTimeMillis();
        profondeur();
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
