package day13;

import utils.Position;
import utils.graph.Node;
import utils.graph.NodeExpander;

import java.util.ArrayList;
import java.util.List;

public class PositionExpander implements NodeExpander<Node<Position>> {

    private final int number;
    private final Position goal;

    private final Position[] POSSIBLE_STEPS = new Position[] {
            new Position(1,0),
            new Position(-1,0),
            new Position(0,1),
            new Position(0,-1),
    };

    public PositionExpander(int number, Position goal) {
        this.number = number;
        this.goal = goal;
    }

    @Override
    public List<Node<Position>> getNeighbouringNodes(Node<Position> n) {
        Position p = n.getElement();

        ArrayList<Node<Position>> nodes = new ArrayList<>();


        for(Position dp : POSSIBLE_STEPS) {
            Position newPos = p.add(dp);

            if(newPos.x < 0 || newPos.y < 0 ) {
                continue;
            }

            if(!isBlocked(newPos)) {
                nodes.add(new Node<>(newPos));
            }

        }

        return nodes;
    }

    public boolean isBlocked(Position pos) {
        int sum = pos.x * pos.x + 3 * pos.x + 2 * pos.x * pos.y + pos.y + pos.y * pos.y;
        sum += number;

        int ones = 0;
        for(char c : Integer.toBinaryString(sum).toCharArray()) {
            if(c == '1') {
                ones++;
            }
        }


        return ones % 2 != 0;
    }

    @Override
    public int estimatedCost(Node<Position> n) {
        Position p = n.getElement();
        Position distance = p.subtract(goal);
        return Math.abs(distance.x + distance.y);
    }

}
