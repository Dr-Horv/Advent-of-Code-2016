package day24;

import utils.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class State {

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

    private Entity[][] map;
    private Position pos;
    private ArrayList<Position> numbers = new ArrayList<>();

    public State(List<String> map) {
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
                    pos = new Position(c,r);
                } else {
                    int number = Integer.parseInt("" + curr);
                    numbers.add(new Position(c, r));
                    this.map[r][c] = new Entity(number);
                }
            }
        }
    }

    public State(State s) {
        this.map = new Entity[s.map.length][s.map[0].length];
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length; c++) {
                this.map[r][c] = s.map[r][c];
            }
        }
        this.pos = s.pos;
        this.numbers = new ArrayList<>(s.numbers);
    }

    public Entity[][] getMap() {
        return map;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public ArrayList<Position> getNumbers() {
        return numbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        if (!pos.equals(state.pos)) return false;
        return  numbers.equals(state.numbers);
     }

    @Override
    public int hashCode() {
        int result = 31 * pos.hashCode();
        result = 31 * result + numbers.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pos: ").append(pos);

        for (int r = 0; r < map.length; r++) {
            sb.append("\n");
            for (int c = 0; c < map[r].length; c++) {
                Entity entity = map[r][c];
                if(entity.type == Type.WALL) {
                    sb.append("#");
                } else if (entity.type == Type.OPEN) {
                    sb.append(".");
                } else {
                    sb.append(entity.number);
                }
            }
        }

        return sb.toString();

    }
}
