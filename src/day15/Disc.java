package day15;

public class Disc {
    private final int startPos;
    private final int nbrPositions;

    public Disc(int startPos, int nbrPositions) {
        this.startPos = startPos;
        this.nbrPositions = nbrPositions;
    }

    public boolean passAtTime(int time) {
        return (startPos + time) % nbrPositions == 0;
    }

    @Override
    public String toString() {
        return "Disc{" +
                "startPos=" + startPos +
                ", nbrPositions=" + nbrPositions +
                '}';
    }
}
