package project;
import java.util.Vector;

import edu.princeton.cs.algs4.*;

public class CouleurGraph {
    private int nb_V;
    private int nb_E;
    private Vector<Integer>[] adjacent;

    private Graph my_g;
    private Vector<Integer> sortVertices = new Vector();    // trier les sommet par degré du plus grand au plus petit
    private Vector<Integer> sortVerticesColorPlaced = new Vector();

    private int nb_color = 1;

    public CouleurGraph(Graph g){
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

    public int maxDegree(){
        int max = 0;
        for (int v = 0; v < nb_V; v++){
            if (my_g.degree(v)>max){
                max = my_g.degree(v);
            }
        }
        return max;
    }

    public void setSortVertices(){        
        for (int i = maxDegree(); i>0; i--){
            for (int v = 0; v < nb_V; v++){
                if (my_g.degree(v) == i){
                    sortVertices.add(v);
                }
            }
        }
    }

    public void setSortVerticesColorPlaced(){
        for (int v = 0; v < nb_V; v++){
            sortVerticesColorPlaced.add(-1);
        }
    }

    public Vector<Integer> getSortVertices(){
        setSortVertices();
        return sortVertices;
    }

    public int coloration(){
        setSortVertices();
        setSortVerticesColorPlaced();

        Vector<Integer> verticeToColored = new Vector();

        StdOut.println(sortVertices);
        StdOut.println(sortVerticesColorPlaced);
        // colorie le sommet avec le plus grand degree
        sortVertices.remove(0);
        sortVerticesColorPlaced.remove(0);
        sortVerticesColorPlaced.add(0, nb_color);
        

        StdOut.println(sortVertices);
        StdOut.println(sortVerticesColorPlaced);

        while (sortVertices.size()!=0){

            for (int v = 0; v < nb_V; v++){
                
            }
            break;
        }

        return nb_color;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph g = new Graph(in);

        StdOut.println(g);

        CouleurGraph cg = new CouleurGraph(g);

        System.out.println(cg.maxDegree());

        cg.coloration();

    }
}
