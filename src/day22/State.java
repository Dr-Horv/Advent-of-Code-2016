package day22;

public class State {

    private StorageNode emptyNode;
    private StorageNode storageNodeWithGoalData;


    public State(StorageNode emptyNode, StorageNode storageNodeWithGoalData) {
        this.emptyNode = emptyNode;
        this.storageNodeWithGoalData = storageNodeWithGoalData;
    }

    public State(State s) {
        emptyNode = new StorageNode(s.emptyNode);
        storageNodeWithGoalData = new StorageNode(s.storageNodeWithGoalData);
    }

    public StorageNode getStorageNodeWithGoalData() {
        return storageNodeWithGoalData;
    }

    public StorageNode getEmptyNode() {
        return emptyNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        if (!emptyNode.equals(state.emptyNode)) return false;
        return storageNodeWithGoalData.equals(state.storageNodeWithGoalData);
    }

    @Override
    public int hashCode() {
        int result = emptyNode.hashCode();
        result = 31 * result + storageNodeWithGoalData.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "State{" +
                "emptyNode=" + emptyNode +
                ", storageNodeWithGoalData=" + storageNodeWithGoalData +
                '}';
    }
}
