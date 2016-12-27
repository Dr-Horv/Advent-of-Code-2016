package day22;

import utils.Position;
import utils.graph.Node;
import utils.graph.NodeExpander;

import java.util.ArrayList;
import java.util.List;

public class StateExpander implements NodeExpander<Node<State>> {

    private StorageNode[][] storageNodes;

    public StateExpander(List<StorageNode> ns) {
        int maxY = 0;
        int maxX = 0;

        for (StorageNode n : ns) {
            Position pos = n.getPosition();
            if(pos.y > maxY) {
                maxY = pos.y;
            }
            if(pos.x > maxX) {
                maxX = pos.x;
            }
        }

        storageNodes = new StorageNode[maxY+1][maxX+1];

        for (StorageNode n : ns) {
            Position pos = n.getPosition();
            storageNodes[pos.y][pos.x] = new StorageNode(n);
        }
    }

    private boolean validPair(StorageNode a, StorageNode b) {
        if(a.getUsed() > 0) {
            if (!a.equals(b)) {
                return a.getUsed() <= b.getAvailable();
            }
        }
        return false;
    }

    @Override
    public List<Node<State>> getNeighbouringNodes(Node<State> n) {
        ArrayList<Node<State>> list = new ArrayList<>();

        State s = n.getElement();

        /*
        System.out.println("---STATE---");
        prettyPrint(s);
        System.out.println("--- --- ---");
*/

        StorageNode emptyNode = s.getEmptyNode();
        generateNeighbour(emptyNode.getPosition().add(new Position(0,1)), emptyNode, s, list);
        generateNeighbour(emptyNode.getPosition().add(new Position(0,-1)), emptyNode, s, list);
        generateNeighbour(emptyNode.getPosition().add(new Position(1,0)), emptyNode, s, list);
        generateNeighbour(emptyNode.getPosition().add(new Position(-1,0)), emptyNode, s, list);

        /*
        System.out.println("--- NEIGHBOURS ---");
        list.forEach(snnn -> prettyPrint(snnn.getElement()));
        System.out.println("--- ---- ---");
*/

        return list;
    }

    private void generateNeighbour(Position dpos, StorageNode emptyNode, State s, ArrayList<Node<State>> list) {
        if(!validPos(dpos.y, dpos.x, storageNodes)) {
            return;
        }

        StorageNode dNode = new StorageNode(storageNodes[dpos.y][dpos.x]);

        if(dNode.getType() != StorageNode.Type.BLOCKED) {
            StorageNode goalNode = new StorageNode(s.getStorageNodeWithGoalData());
            if(goalNode.getPosition().equals(dNode.getPosition())) {
                goalNode = new StorageNode(emptyNode);
                goalNode.setType(StorageNode.Type.MOVABLE);
                goalNode.setHasGoalData(true);
            }

            dNode.setType(StorageNode.Type.EMPTY);

            State s2 = new State(dNode, goalNode);
            list.add(new Node<>(s2));
        }

    }


    private boolean validPos(int i, int j, StorageNode[][] storageNodes) {
        boolean test = storageNodes.length > i && i >= 0 && storageNodes[i].length > j && j >= 0;
        return test;
    }

    @Override
    public int estimatedCost(Node<State> sn) {
        StorageNode n = sn.getElement().getStorageNodeWithGoalData();
        Position diff = new Position(0,0).subtract(n.getPosition());
        return Math.abs(diff.x) + Math.abs(diff.y);
    }

    public void prettyPrint(State s) {
        StorageNode goalNode = s.getStorageNodeWithGoalData();
        StorageNode emptyNode = s.getEmptyNode();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < storageNodes.length; i++) {
            sb.append("\n");
            for (int j = 0; j < storageNodes[i].length; j++) {
                StorageNode sn = storageNodes[i][j];
                Position p = new Position(j,i);
                if(goalNode.getPosition().equals(p)) {
                    sb.append("g");
                    continue;
                } else if (emptyNode.getPosition().equals(p)) {
                    sb.append("_");
                    continue;
                }

                if(sn.getType() == StorageNode.Type.BLOCKED) {
                    sb.append("#");
                } else if (sn.getType() == StorageNode.Type.MOVABLE) {
                    if(sn.hasGoalData()) {
                        sb.append("G");
                    } else {
                        sb.append(".");
                    }
                } else {
                    sb.append("E");
                }
            }
        }

        System.out.println(sb.toString());

    }


}
