package day11.graph;

import java.util.ArrayList;

public class Node<E> {

    private ArrayList<Edge> neigbours;
    private final E element;

    public Node(E element) {
        this.element = element;
        neigbours = new ArrayList<>();
    }

    public void addEdge(Edge e) {
        neigbours.add(e);
    }

    public void removeEdge(Edge e) {
        neigbours.remove(e);
    }

    public E getElement() {
        return element;
    }

    public ArrayList<Edge> getNeigbours() {
        return neigbours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node<?> node = (Node<?>) o;

        return element != null ? element.equals(node.element) : node.element == null;
    }

    @Override
    public int hashCode() {
        return element != null ? element.hashCode() : 0;
    }
}
