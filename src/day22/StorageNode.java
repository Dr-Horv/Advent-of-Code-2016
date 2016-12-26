package day22;

import utils.Position;

public class Node {
    private final Position position;
    private int available;
    private int used;
    private boolean hasGoalData = false;

    public Node(Position position, int available, int used) {
        this.position = position;
        this.available = available;
        this.used = used;
    }

    public Node(Node node) {
        this.position = node.position;
        this.available = node.available;
        this.used = node.used;
        this.hasGoalData = node.hasGoalData;
    }

    public Position getPosition() {
        return position;
    }

    public int getAvailable() {
        return available;
    }

    public int getUsed() {
        return used;
    }

    public void gain(int data) {
        used += data;
        available -= data;
    }

    public int lose() {
        int currentUsed = used;
        available += used;
        used = 0;
        return currentUsed;
    }

    public void setHasGoalData(boolean hasGoalData) {
        this.hasGoalData = hasGoalData;
    }

    public boolean hasGoalData() {
        return hasGoalData;
    }

    @Override
    public String toString() {
        return "Node{" +
                "position=" + position +
                ", available=" + available +
                ", used=" + used +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (available != node.available) return false;
        if (used != node.used) return false;
        if (hasGoalData != node.hasGoalData) return false;
        return position.equals(node.position);
    }

    @Override
    public int hashCode() {
        int result = position.hashCode();
        result = 31 * result + available;
        result = 31 * result + used;
        result = 31 * result + (hasGoalData ? 1 : 0);
        return result;
    }
}
