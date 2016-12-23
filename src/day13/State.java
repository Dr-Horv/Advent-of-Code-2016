package day13;

import utils.Position;

public class State {


    private final Position pos;

    public State(Position pos) {
        this.pos = pos;
    }


    public Position getPos() {
        return pos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        return pos.equals(state.pos);
    }

    @Override
    public int hashCode() {
        return pos.hashCode();
    }
}
