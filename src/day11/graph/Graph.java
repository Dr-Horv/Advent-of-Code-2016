package day11.graph;


import java.util.ArrayList;
import java.util.Collection;

public class Graph<T>  {

    private ArrayList<Node<T>> nodes;
    private ArrayList<Edge> edges;
    private final NodeExpander<Node<T>> expander;

    public Graph(Node<T> startNode, NodeExpander<Node<T>> expander) {
        this.expander = expander;
        this.nodes = new ArrayList<>();
        nodes.add(startNode);
    }


}
