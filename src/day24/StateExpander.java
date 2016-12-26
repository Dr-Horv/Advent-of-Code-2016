package day24;

import utils.Position;
import utils.graph.Node;
import utils.graph.NodeExpander;

import java.util.ArrayList;
import java.util.List;

public class StateExpander implements NodeExpander<Node<State>> {

    @Override
    public List<Node<State>> getNeighbouringNodes(Node<State> n) {

        State s = n.getElement();
        List<Node<State>> list = new ArrayList<>();

        State.Entity[][] map = s.getMap();
        Position[] diff = new Position[] {
                new Position(0,1),
                new Position(0, -1),
                new Position(1, 0),
                new Position(-1, 0)
        };

        for (Position p : diff) {
            Position newPos = s.getPos().add(p);
            if(validPos(newPos, map)) {
                State s2 = new State(s);
                s2.setPos(newPos);
                if(map[newPos.y][newPos.x].type == State.Type.NUMBER) {
                    s2.getNumbers().remove(new Position(newPos.x, newPos.y));
                    s2.getMap()[newPos.y][newPos.x] = new State.Entity(State.Type.OPEN);
                }

                list.add(new Node<>(s2));
            }
        }

        return list;
    }

    private boolean validPos(Position pos, State.Entity[][] map) {
        int height = map.length;
        int width = map[0].length;
        return pos.x > 0 && pos.x < width &&
                pos.y > 0 && pos.y < height &&
                ((map[pos.y][pos.x].type == State.Type.OPEN) || (map[pos.y][pos.x].type == State.Type.NUMBER));
    }

    @Override
    public int estimatedCost(Node<State> n) {

        State state = n.getElement();
        ArrayList<Position> numbers = state.getNumbers();
        if(state.getNumbers().size() == 0) {
            return 0;
        }

        Position pos = n.getElement().getPos();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < numbers.size(); i++) {
            int dist = distance(numbers.get(i), pos);
            if(dist < min) {
                min = dist;
            }
        }


        return min + numbers.size();
    }

    private int distance(Position p1, Position p2) {
        Position diff = p1.subtract(p2);
        return Math.abs(diff.x) + Math.abs(diff.y);
    }
}
