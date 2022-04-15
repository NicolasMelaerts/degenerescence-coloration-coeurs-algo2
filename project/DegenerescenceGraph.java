package project;

import edu.princeton.cs.algs4.*;

import java.util.NoSuchElementException;
import java.util.Vector;

/*

Le constructeur DegenerescenceGraph(Graph g) et les méthodes deg(int v) et toString() ont été reprise 
dans algs4.jar (http://algs4.cs.princeton.edu) et ont été réalisé par Robert Sedgewick and Kevin Wayne

*/



public class DegenerescenceGraph{
    private int nb_V;
    private int nb_E;
    private Vector<Integer>[] adjacent;
    private Vector<Integer> Vremoved = new Vector();

    public DegenerescenceGraph(Graph g){

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

    public void removeVertice(int v){
        for (int w : adjacent[v]) {     // pour chaque sommet relié au sommet v
            for(int idx = 0; idx < adjacent[w].size(); idx++){  // pour trouver l'indice du sommet w dans adjacent[w]
                if (adjacent[w].elementAt(idx) == v){
                    // supprime l'arête
                    adjacent[w].remove(idx);    
                    nb_E--;
                }
            }
        }
        adjacent[v].clear();    // supprime toutes les arêtes de v
        Vremoved.add(v);       
    }

    // cherche si value est dans vector 
    public boolean check(Vector<Integer> vector, int value){
        boolean in = false;
        for(int i : vector){
            if(i == value){
                in = true;
                break;
            }
        }
        return in;
    }

    // renvoie le degré du sommet v
    public int deg(int v){
        return adjacent[v].size();
    }

    // renvoie la k-dégénération du graph
    public int degenerate(){
        int k = 1;
        boolean retry = false;  // on enlève les sommets de degrè k jusque quand il n'y a plus de sommet de degré k
        while (nb_E != 0){  // tant qu'il reste des arêtes
            for (int v = 0; v < nb_V; v++) {    // parcour tous les sommets du graphes 
                if (!check(Vremoved, v)) {  // sommet déjà supprimé
                    if (deg(v) <= k) {
                        removeVertice(v);   // retire le sommet car <= k
                        retry = true;
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

    public String toString() {
        String NEWLINE = System.getProperty("line.separator");
        StringBuilder s = new StringBuilder();
        s.append(nb_V + " vertices, " + nb_E + " edges " + NEWLINE);
        for (int v = 0; v < nb_V; v++) {
            s.append(v + ": ");
            for (int w : adjacent[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph g = new Graph(in);

        StdOut.println(g);

        DegenerescenceGraph my_g = new DegenerescenceGraph(g);


        long debut;
        long tmps;
        int degen;

        debut = System.currentTimeMillis();
        degen = my_g.degenerate();
        tmps = System.currentTimeMillis()-debut;
        
        System.out.println("Dégénéréscence de " + degen);
        System.out.println("Temps moyen dégénérescence graphe facebook = " + tmps + "millisec");

    }


}
