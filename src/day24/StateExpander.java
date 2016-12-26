package day24;

import utils.Position;
import utils.graph.Node;
import utils.graph.NodeExpander;

import java.util.ArrayList;
import java.util.List;

public class StateExpander implements NodeExpander<Node<State>> {


    private final Entity[][] map;

    private final ArrayList<Position> numbers = new ArrayList<>();
    private Position start;

    static class Entity {
        public final Type type;
        public final int number;

        Entity(Type type) {
            this.type = type;
            number = -1;
        }

        Entity(int number) {
            type = Type.NUMBER;
            this.number = number;
        }
    }

    enum Type {
        WALL,
        OPEN,
        NUMBER;
    }


    public StateExpander(List<String> map) {

        this.map = new Entity[map.size()][map.get(0).length()];

        for (int r = 0; r < map.size(); r++) {
            char[] row = map.get(r).toCharArray();
            for (int c = 0; c < row.length; c++) {
                char curr = row[c];
                if(curr == '#') {
                    this.map[r][c] = new Entity(Type.WALL);
                } else if (curr == '.') {
                    this.map[r][c] = new Entity(Type.OPEN);
                } else if (curr == '0') {
                    this.map[r][c] = new Entity(Type.OPEN);
                    start = new Position(c, r);
                } else {
                    int number = Integer.parseInt("" + curr);
                    this.map[r][c] = new Entity(number);
                    numbers.add(new Position(c, r));
                }
            }
        }
    }

    public ArrayList<Position> getNumbers() {
        return numbers;
    }

    public Position getStart() {
        return start;
    }

    @Override
    public List<Node<State>> getNeighbouringNodes(Node<State> n) {

        State s = n.getElement();
        List<Node<State>> list = new ArrayList<>();

        Position[] diff = new Position[] {
                new Position(0,1),
                new Position(0, -1),
                new Position(1, 0),
                new Position(-1, 0)
        };

        for (Position p : diff) {
            Position newPos = s.getPos().add(p);
            if(validPos(newPos)) {
                ArrayList<Position> ns = new ArrayList<>(s.getNumbers());
                if(ns.contains(newPos)) {
                    ns.remove(newPos);

                    if(ns.size() == 0 && !newPos.equals(start)) {
                        ns.add(start);
                    }
                }

                State s2 = new State(newPos, ns);
                list.add(new Node<>(s2));
            }
        }

        return list;
    }

    private boolean validPos(Position pos) {
        int height = map.length;
        int width = map[0].length;
        return pos.x > 0 && pos.x < width &&
                pos.y > 0 && pos.y < height &&
                ((map[pos.y][pos.x].type == Type.OPEN) || (map[pos.y][pos.x].type == Type.NUMBER));
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

        int extra = numbers.contains(start) ? 0 : 1;
        return min + numbers.size() + extra;
    }

    private int distance(Position p1, Position p2) {
        Position diff = p1.subtract(p2);
        return Math.abs(diff.x) + Math.abs(diff.y);
    }
}
