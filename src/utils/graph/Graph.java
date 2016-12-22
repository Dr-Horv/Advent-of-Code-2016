package utils.graph;


import java.util.*;

public class Graph<T>  {

    private Set<Node<T>> nodes;
    private Set<Edge<T>> edges;
    private final NodeExpander<Node<T>> expander;

    public Graph(Node<T> startNode, NodeExpander<Node<T>> expander) {
        this.expander = expander;
        this.nodes = new HashSet<>();
        this.edges = new HashSet<>();
        nodes.add(startNode);
    }

    public List<Node<T>> expand(Node<T> node) {
        List<Node<T>> neighbours = expander.getNeighbouringNodes(node);
        for (Node<T> n : neighbours) {
            edges.add(new Edge<T>(node, n));
            nodes.add(n);
        }
        return neighbours;
    }

    public Set<Node<T>> getNodes() {
        return nodes;
    }

    public Set<Edge<T>> getEdges() {
        return edges;
    }
}
