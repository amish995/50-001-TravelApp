import java.util.Arrays;

/**
 * Created by woshibiantai on 19/11/16.
 */
public class Testing {
    public static void main(String[] args) {
        int V = 4;
        int E = 5;
        Graph graph = new Graph(V, E);
        SubGraph subgraph = new SubGraph(graph);

        // add edge 0-1
        graph.edge[0].source = 0;
        graph.edge[0].destination = 1;
        graph.edge[0].weight = 10;

        // add edge 0-2
        graph.edge[1].source = 0;
        graph.edge[1].destination = 2;
        graph.edge[1].weight = 6;

        // add edge 0-3
        graph.edge[2].source = 0;
        graph.edge[2].destination = 3;
        graph.edge[2].weight = 5;

        // add edge 1-3
        graph.edge[3].source = 1;
        graph.edge[3].destination = 3;
        graph.edge[3].weight = 15;

        // add edge 2-3
        graph.edge[4].source = 2;
        graph.edge[4].destination = 3;
        graph.edge[4].weight = 4;

        for (Nodes n : subgraph.getOddDegrees()){

        }

    }
}
