package utils.graph;

import java.util.List;

public interface NodeExpander<Node> {

    public List<Node> getNeighbouringNodes(Node n);
    public int estimatedCost(Node n);

}
