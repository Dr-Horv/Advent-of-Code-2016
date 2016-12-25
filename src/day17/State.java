package day17;

import utils.Position;


public class State {

    private final Position position;
    private final String path;
    private final boolean up, down, left, right;
    public State(Position position, String path, boolean up, boolean down, boolean left, boolean right) {
        this.position = position;
        this.path = path;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    public Position getPosition() {
        return position;
    }

    public String getPath() {
        return path;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        if (up != state.up) return false;
        if (down != state.down) return false;
        if (left != state.left) return false;
        if (right != state.right) return false;
        if (!position.equals(state.position)) return false;
        return path.equals(state.path);
    }

    @Override
    public int hashCode() {
        int result = position.hashCode();
        result = 31 * result + path.hashCode();
        result = 31 * result + (up ? 1 : 0);
        result = 31 * result + (down ? 1 : 0);
        result = 31 * result + (left ? 1 : 0);
        result = 31 * result + (right ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "State{" +
                "position=" + position +
                ", path='" + path + '\'' +
                ", up=" + up +
                ", down=" + down +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
