package day11.graph;

import java.util.List;

public interface NodeExpander<Node> {

    public List<Node> getNeighbouringNodes(Node n);

}
