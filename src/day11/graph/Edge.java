package day11.graph;


public class Edge {

    private final double weight;
    private final Node n1;
    private final Node n2;
    private final Direction direction;

    public Edge(Node from, Node to) {
        n1 = from;
        n2 = to;
        direction = Direction.N1N2;
        weight = 1;

    }

    public Edge(Node from, Node to, Direction direction) {
        n1 = from;
        n2 = to;
        this.direction = direction;
        weight = 1;
    }

    public Node getN1() {
        return n1;
    }

    public Node getN2() {
        return n2;
    }

    public Direction getDirection() {
        return direction;
    }

    enum Direction {
        N1N2,
        N2N1,
        BOTH
    }
}
