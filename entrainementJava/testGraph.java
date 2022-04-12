package entrainementJava;

import edu.princeton.cs.algs4.*;

public class testGraph {
    public static void main(String[] args) {
        Graph g = new Graph(29);

        g.addEdge(0, 1);

        g.addEdge(1, 2);

        g.addEdge(2, 3);

        g.addEdge(3, 4);
        g.addEdge(3, 5);
        g.addEdge(3, 10);
        g.addEdge(3, 18);
        g.addEdge(3, 11);

        g.addEdge(4, 5);

        g.addEdge(5, 10);
        g.addEdge(5, 19);
        g.addEdge(5, 6);

        g.addEdge(6, 20);
        g.addEdge(6, 7);

        g.addEdge(7, 21);
        g.addEdge(7, 8);

        g.addEdge(8, 9);

        g.addEdge(10, 19);
        g.addEdge(10, 19);

        g.addEdge(11, 12);
        g.addEdge(11, 13);

        g.addEdge(12, 14);

        g.addEdge(13, 14);
        g.addEdge(13, 15);

        g.addEdge(14, 18);

        g.addEdge(15, 16);
        g.addEdge(15, 17);

        g.addEdge(18, 19);

        g.addEdge(19, 20);
        g.addEdge(19, 24);

        g.addEdge(20, 21);

        g.addEdge(21, 22);

        g.addEdge(22, 23);

        g.addEdge(24, 25);
        g.addEdge(24, 26);
        g.addEdge(24, 27);

        g.addEdge(25, 26);

        g.addEdge(26, 28);


        System.out.println("Vertices = " + g.V() + "\nEdges = " + g.E());
    }
}
