package day08;


import java.util.LinkedList;
import java.util.List;

public class Screen {

    private boolean[][] state;
    private int height;
    private int width;

    public Screen(int width, int height) {
        this.height = height;
        this.width = width;
        state = new boolean[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                state[i][j] = false;
            }
        }
    }

    public void rect(int width, int height) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                state[y][x] = true;
            }
        }
    }

    public void rotateX(int x, int steps) {
        LinkedList<Boolean> list = new LinkedList<>();
        for (int row = 0; row < height; row++) {
            list.add( state[row][x] );
        }
        for (int i = 0; i < steps; i++) {
            list.addFirst(list.removeLast());
        }
        for (int row = 0; row < height; row++) {
            state[row][x] = list.removeFirst();
        }
    }

    public void rotateY(int y, int steps) {
        LinkedList<Boolean> list = new LinkedList<>();
        for (int col = 0; col < width; col++) {
            list.add( state[y][col] );
        }
        for (int i = 0; i < steps; i++) {
            list.addFirst(list.removeLast());
        }
        for (int col = 0; col < width; col++) {
            state[y][col] = list.removeFirst();
        }

    }

    public int nbrActivePixels() {
        int count = 0;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                count += state[row][col] ? 1 : 0;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if(state[row][col]) {
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
