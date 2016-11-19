//  This is a class to represent the data as a connected, undirected and weighted graph.
//  This graph will then be used to create a Minimum Spanning Tree (MST)

import javax.xml.soap.Node;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {

//  A class to represent the graph's edge...
    class Edge implements Comparable<Edge> {
        int source, destination;
        double weight;

//  Comparator function used for sorting edges based on their weight
        public int compareTo(Edge compareEdge) {
            return (int) (this.weight - compareEdge.weight)*100;
        }
    }

    int V, E;   // V: number of vertices. E: number of edges.
    Edge edge[];    // Collection of all edges

//  Constructor creates a graph with V vertices and E edges
    Graph(int v, int e) {
        V = v;
        E = e;
        edge = new Edge[E];

        for (int i = 0; i < e; i++) {   // creating an Edge() in every slot of edge[]
            edge[i] = new Edge();
        }
    }

    //  A class to represent a subset for union-find data structure
    class Subset {
        int parent, rank;
    }


//  Looking for connected components here... Through the Union-Find algorithm.
//  Using path compression technique to find set of an element i.

    int find(Subset subsets[], int i) {
//      Find root of i, then make it the parent of i (path compression)
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);   // recursively finding the parent of the parent
        }

        return subsets[i].parent;
    }

//  A function that does union of two sets of x and y (uses union by rank)
    void Union(Subset subsets[], int x, int y) {
        int x_root = find(subsets, x);
        int y_root = find(subsets, y);

//      Attaching a smaller rank tree under root of high rank tree (union by rank)
        if (subsets[x_root].rank < subsets[y_root].rank) {
            subsets[x_root].parent = y_root;
        } else if (subsets[x_root].rank > subsets[y_root].rank) {
            subsets[y_root].parent = x_root;
        }

//      If ranks are the same, then make one as root and increment its rank by one
        else {
            subsets[y_root].parent = x_root;
            subsets[x_root].rank++;
        }
    }

//    The main function to construct MST using Kruskal's algorithm
        List<Nodes> KruskalMST() {
        Edge output[] = new Edge[V];    // This will store the resultant MST
        int e = 0;  // an index variable, used for output[]
        int i = 0;  // an index variable, used for sorted edges

        for (i = 0; i < V; i++) {       // Creating a new Edge() for each slot in the output.
            output[i] = new Edge();
        }

        //      Step 1: sort all the edges in increasing order fo their weight.
        Arrays.sort(edge);

//      Creating V subsets and filling each slot with Subset()
        Subset subsets[] = new Subset[V];
        for (i = 0; i < V; i++) {
            subsets[i] = new Subset();
        }

//      Create V subsets with single elements
        for (int j = 0; j < V; j++) {
            subsets[j].parent = j;
            subsets[j].rank = 0;
        }

        int k = 0;  // Index used to pick next edge
//      Number of edges to be taken is equal to V-1
        int size = 1; // to get the size of the output graph!

        while (e < V - 1) {
//          Step 2: Pick the smallest edge and increment the index for the next iteration
            Edge nextEdge;
            nextEdge = edge[k++];

            int x = find(subsets , nextEdge.source);
            int y = find(subsets, nextEdge.destination);

//          If no cycles are formed, include this in output[] and move on to next edge.
            if (x != y) {
                output[e++] = nextEdge;
                Union(subsets, x, y);
                size++;
            }
        }

//        Nodes finalOut[] = new Nodes[size];
        List<Nodes> finalOut = new ArrayList<Nodes>();
        for (i = 0; i < e; i++) {
//            finalOut[0] = new Nodes(output[i].source, output[i].destination, output[i].weight);
            finalOut.add(new Nodes(output[i].source, output[i].destination, output[i].weight));
        }


        System.out.println("Edges of MST: ");
        for (i = 0; i < e; i++) {
            System.out.println(output[i].source + " -- " + output[i].destination + " == " + output[i].weight);
        }

        return finalOut;
    }
}

