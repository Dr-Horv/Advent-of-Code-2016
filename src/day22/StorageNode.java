package day22;

import utils.Position;

public class StorageNode {
    private final Position position;
    private int available;
    private int used;
    private boolean hasGoalData = false;
    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    enum Type {
        EMPTY,
        BLOCKED,
        MOVABLE
    }

    public StorageNode(Position position, int available, int used) {
        this.position = position;
        this.available = available;
        this.used = used;
    }

    public StorageNode(StorageNode storageNode) {
        this.position = storageNode.position;
        this.available = storageNode.available;
        this.used = storageNode.used;
        this.hasGoalData = storageNode.hasGoalData;
        this.type = storageNode.type;
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
        String s = "";
        if(used < 10) {
            s += " ";
        }
        s += used;
        s += "/";
        int total = used+available;

        s += total;

        if(total < 10) {
            s += " ";
        }

        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StorageNode storageNode = (StorageNode) o;

        if(!position.equals(storageNode.position)) return false;
        if (available != storageNode.available) return false;
        if (used != storageNode.used) return false;

        return hasGoalData == storageNode.hasGoalData;
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
