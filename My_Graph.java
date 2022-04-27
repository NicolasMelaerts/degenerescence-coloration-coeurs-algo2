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

    private Graph my_g;


    public My_Graph(Graph g){

        this.nb_V = g.V();
        this.nb_E = g.E();
        this.my_g = g;

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

    public int maxDegree(){
        int max = 0;
        for (int v = 0; v < nb_V; v++){
            if (my_g.degree(v)>max){
                max = my_g.degree(v);
            }
        }
        return max;
    }

    public void setSortVertices(Vector<Integer> sortVertices){   
        for (int i = maxDegree(); i>0; i--){
            for (int v = 0; v < nb_V; v++){
                if (my_g.degree(v) == i){
                    sortVertices.add(v);
                }
            }
        }
        /*
        for (int v = 0; v < nb_V; v++){
            sortVertices.add(v);   
        }
        */
    }

    public void setSortVerticesColorPlaced(Vector<Integer> sortVerticesColorPlaced){
        for (int v = 0; v < nb_V; v++){
            sortVerticesColorPlaced.add(0);
        }
    }

    public int coloration(){
        int nb_color = 1;
        Vector<Integer> sortVertices = new Vector();    // trier les sommet par degré du plus grand au plus petit
        Vector<Integer> sortVerticesColorPlaced = new Vector();

        setSortVertices(sortVertices);
        setSortVerticesColorPlaced(sortVerticesColorPlaced);

        // colorie le sommet avec le plus grand degree
        sortVerticesColorPlaced.set(sortVertices.get(0), nb_color);
        int colorPlaced = 1;
        boolean ok = true;

        while (colorPlaced != nb_V){
            for (int v = 0; v < nb_V; v++){
                if (sortVerticesColorPlaced.get(sortVertices.get(v))==0){

                    for (int w : adjacent[sortVertices.get(v)]){
                        if (sortVerticesColorPlaced.get(w) == nb_color){
                            ok = false;
                            break;
                        }
                    }
                    if (ok){
                        sortVerticesColorPlaced.set(sortVertices.get(v), nb_color);
                        colorPlaced++;
                        
                    }
                    ok = true;
                }
            }
            nb_color++;
        }

        return nb_color-1;
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
            not_to_long = 100;
        }

        debut = System.currentTimeMillis();
        for (int v=0; v < not_to_long; v++){
            StdOut.print("c(" + v + ") : " + c(v) + ", ");
        }
        tmps = System.currentTimeMillis()-debut;
        
        System.out.println("\nTemps moyen profondeur graphe = " + tmps + "millisec\n");
    }

    public static void main(String[] args) {
        String fichier = "./src/graphEnonce.txt";

        In in = new In(fichier);
        Graph g = new Graph(in);

        My_Graph my_g = new My_Graph(g);
        my_g.testDegen();
        my_g.testColor();
        my_g.testCore();

    }
    
}
