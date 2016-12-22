package day11.graph;


public class Edge<E> {

    private final double weight;
    private final Node<E> n1;
    private final Node<E> n2;
    private final Direction direction;

    public Edge(Node<E> from, Node<E> to) {
        n1 = from;
        n2 = to;
        direction = Direction.BOTH;
        weight = 1;

    }

    public Edge(Node<E> from, Node<E> to, Direction direction) {
        n1 = from;
        n2 = to;
        this.direction = direction;
        weight = 1;
    }

    public Node<E> getN1() {
        return n1;
    }

    public Node<E> getN2() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge<?> edge = (Edge<?>) o;

        if (Double.compare(edge.weight, weight) != 0) return false;
        if (!n1.equals(edge.n1)) return false;
        if (!n2.equals(edge.n2)) return false;
        return direction == edge.direction;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(weight);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + n1.hashCode();
        result = 31 * result + n2.hashCode();
        result = 31 * result + direction.hashCode();
        return result;
    }
}
