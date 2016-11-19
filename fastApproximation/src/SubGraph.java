import java.util.ArrayList;
import java.util.List;

// Here, we take the vertices of odd degree in the initial graph to create a subgraph.

public class SubGraph {
    Graph graph;
    List<Nodes> output = new ArrayList<Nodes>();    // This output will contain the subgraph.

    SubGraph(Graph graph) {
        this.graph = graph;
    }

    public List<Nodes> getOddDegrees() {
        List<Nodes> originalGraph = graph.KruskalMST();
        int size = originalGraph.size();

        int[] counter = new int[graph.E];
        for (Nodes nodes : originalGraph) {     // counting the occurrences of each node in the MST
            counter[nodes.getSource()]++;
            counter[nodes.getDestination()]++;
        }

        List<Integer> oddVertices = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {    // get the nodes that occurs an odd number of times
            if (counter[i] % 2 != 0) {
                oddVertices.add(i);
            }
        }

        System.out.println("getOddDegrees: " + oddVertices);
        return output;
    }
}
