package project;

/*

Du coup l'idée c'est de reprendre le même algorithme que l'exercice 1...
Donc on parcourt le graphe (sous forme de vecteur) et au moment de supprimer un sommet
on retient sa profondeur, qui est égale à k.
Comme au début quand k=1 on supprime les sommets qui sont en mauve sur la figure 3, ce sont les 1-coeurs.

Maintenant je sais pas si on doit faire un programme qui renvoie la profondeur de tous les sommets du graphe
ou bien si on doit faire une fonction c(v) qu'on appelle sur chaque sommet v dont on veut la profondeur individuellement.

Si c'est pour chaque sommet individuellement alors on parcourt le graphe en supprimant les sommets
et on s'arrête (sans mauvais jeu de mot) au moment de supprimer le sommet qu'on étudie et on renvoie k.
Faut juste passer le sommet dont on veut la profondeur en paramètre à la fonction je pense.

Sinon si c'est pour donner la profondeur de tous les sommets en une fois je sais pas
sous quelle forme on doit renvoyer le résultat, soit on print chaque fois le sommet qu'on supprime et k,
soit on trouve un système de dictionnaire qui associe un sommet et une valeur de k...

*/

import java.util.Vector;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/*

Le constructeur DegenerescenceGraph(Graph g) et les méthodes deg(int v) et toString() ont été reprise 
dans algs4.jar (http://algs4.cs.princeton.edu) et ont été réalisé par Robert Sedgewick and Kevin Wayne

*/



public class CoeurGraph{
    private int nb_V;
    private int nb_E;
    private Vector<Integer>[] adjacent;

    public CoeurGraph(Graph g){

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

    public int getSize(){
        return nb_V;
    }


    // renvoie le degré du sommet v
    public int deg(int v){
        return adjacent[v].size();
    }



    // renvoie la profondeur d'un sommet 
    public int c(int sommet){
        int k = 1;
        boolean retry = false;

        Vector<Integer> allDegree = new Vector(nb_V);
        for (int v = 0; v < nb_V; v++) {
            allDegree.add(deg(v));
        }

        int ctn_zero = 0;

        while (ctn_zero!=nb_V){
            for (int v = 0; v < nb_V; v++) {
                if (allDegree.elementAt(v) <= k && allDegree.elementAt(v)>0){
                    for (int w : adjacent[v]){
                        if (allDegree.elementAt(w)>0){
                            allDegree.set(w, allDegree.elementAt(w)-1);
                            allDegree.set(v, allDegree.elementAt(v)-1);

                            if (allDegree.elementAt(w) == 0){
                                ctn_zero++;
                                if (w == sommet){
                                    return k;
                                }
                            }
                            if (allDegree.elementAt(v) == 0){
                                ctn_zero++;
                                if (v == sommet){
                                    return k;
                                }
                            }
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

        CoeurGraph my_g = new CoeurGraph(g);

        long debut;
        long tmps;

        debut = System.currentTimeMillis();
        for (int v=0; v < my_g.getSize(); v++){
            StdOut.println("c(" + v + ") : " + my_g.c(v));
        }
        tmps = System.currentTimeMillis()-debut;
        
        System.out.println("Temps moyen profondeur graphe facebook = " + tmps + "millisec");
    }
}