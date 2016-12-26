package day24;

import utils.Position;

import java.util.ArrayList;
import java.util.List;

public class State {

    private Position pos;
    private ArrayList<Position> numbers;


    public State(State s) {
        this.pos = s.pos;
        this.numbers = new ArrayList<>(s.numbers);
    }

    public State(Position pos, List<Position> numbers) {
        this.pos = pos;
        this.numbers = new ArrayList<>(numbers);
    }

    public Position getPos() {
        return pos;
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
        return numbers.equals(state.numbers);
    }

    @Override
    public int hashCode() {
        int result = pos.hashCode();
        result = 31 * result + numbers.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "State{" +
                "pos=" + pos +
                ", numbers=" + numbers +
                '}';
    }
}
