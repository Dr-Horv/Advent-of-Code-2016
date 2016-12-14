package day08;


import java.util.LinkedList;
import java.util.List;

public class Screen {

    private LinkedList<LinkedList<Boolean>> state;

    public Screen(int width, int height) {
        state = new LinkedList<>();
        for (int i = 0; i < height; i++) {
            LinkedList<Boolean> row = new LinkedList<>();
            for (int j = 0; j < width; j++) {
                row.add(false);
            }
            state.add(row);
        }
    }

    public void rect(int width, int height) {
        for (int y = 0; y < height; y++) {
            List<Boolean> row = state.get(y);
            for (int x = 0; x < width; x++) {
                row.set(x, true);
            }
        }
    }

    public void rotateX(int x, int steps) {
        LinkedList<Boolean> xs = new LinkedList<>();
        for (int y = 0; y < state.size(); y++) {
            List<Boolean> row = state.get(y);
            xs.add(row.get(x));
        }

        for (int i = 0; i < steps; i++) {
            xs.addFirst(xs.removeLast());
        }

        for (int y = 0; y < state.size(); y++) {
            state.get(y).set(x, xs.get(y));
        }
    }

    public void rotateY(int y, int steps) {
        LinkedList<Boolean> ys = state.get(y);
        for (int i = 0; i < steps; i++) {
            ys.addFirst(ys.removeLast());
        }

    }

    public Long nbrActivePixels() {
        return state.stream().map(l -> l.stream().filter(b -> b).count()).reduce((l1, l2) -> l1 + l2).get();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (List<Boolean> row : state) {
            for(Boolean v : row) {
                if(v) {
                    sb.append("#");
                } else {
                    sb.append(".");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
