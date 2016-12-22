package day11.graph;

import java.util.ArrayList;

public class Node<E> {

    private ArrayList<Edge<E>> neigbours;
    private final E element;

    public Node(E element) {
        this.element = element;
        neigbours = new ArrayList<>();
    }

    public void addEdge(Edge<E> e) {
        neigbours.add(e);
    }

    public void removeEdge(Edge<E> e) {
        neigbours.remove(e);
    }

    public E getElement() {
        return element;
    }

    public ArrayList<Edge<E>> getNeigbours() {
        return neigbours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node<?> node = (Node<?>) o;

        return element.equals(node.element);
    }

    @Override
    public int hashCode() {
        return element != null ? element.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Node{" +
                "element=" + element +
                '}';
    }
}
