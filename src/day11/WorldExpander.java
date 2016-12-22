package day11;

import utils.graph.Node;
import utils.graph.NodeExpander;

import java.util.ArrayList;
import java.util.List;


public class WorldExpander implements NodeExpander<Node<World>> {

    public static void main(String[] args) {
        Integer[][] state = Test.getZeros(3, 3);
        state[0][1] = 1;

        Node<Integer[][]> s = new Node<>(state);
        System.out.println("\n");

    }

    @Override
    public List<Node<World>> getNeighbouringNodes(Node<World> s) {
        World w = s.getElement();
        Integer[][] n = w.getMap();

        List<Node<World>> list = new ArrayList<>();

        int height = n.length;
        int width = n[0].length;

        int x = 0;
        int y = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(n[i][j] == 1) {
                    y = i;
                    x = j;
                }
            }
        }

        for (int dy = -1; dy < 2; dy++) {
            for (int dx = -1; dx < 2; dx++) {
                if(dx != 0 && dy != 0) {
                    continue;
                }

                int nx = x + dx;
                int ny = y + dy;

                if(nx == x && ny == y) {
                    continue;
                }

                if(nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    Integer[][] copy = new Integer[height][width];
                    for (int r = 0; r < height; r++) {
                        for (int c = 0; c < width; c++) {
                            copy[r][c] = n[r][c];
                        }
                    }

                    copy[ny][nx] = 1;
                    copy[y][x] = 0;

                    list.add(new Node<>(new World(copy)));

                }

            }

        }

        return list;
    }

    @Override
    public int estimatedCost(Node<World> n) {
        return 0;
    }

}
