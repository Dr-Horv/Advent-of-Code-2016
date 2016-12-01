package day01;

public class Step {

    public enum Rotation {
        LEFT,
        RIGHT;
    }

    public final Rotation rotation;
    public final int amount;

    public Step(Rotation rotation, int amount) {
        this.rotation = rotation;
        this.amount = amount;
    }
}
