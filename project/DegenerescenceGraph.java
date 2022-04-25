package project;

import java.util.Vector;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/*

Le constructeur DegenerescenceGraph(Graph g) et les méthodes deg(int v) et toString() ont été reprise 
dans algs4.jar (http://algs4.cs.princeton.edu) et ont été réalisé par Robert Sedgewick and Kevin Wayne

*/



public class DegenerescenceGraph{
    private int nb_V;
    private int nb_E;
    private Vector<Integer>[] adjacent;

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
        System.out.println("Temps moyen dégénérescence graphe = " + tmps + "millisec");
        
    }


}
