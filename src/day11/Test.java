package day11;

import day11.graph.AStar;
import day11.graph.Node;

import java.util.List;

public class Test {

    public static Integer[][] getZeros(int height, int width) {
        Integer[][] matrix = new Integer[height][width];
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                matrix[r][c] = 0;
            }
        }

        return matrix;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Integer[][] start = getZeros(3,3);
        Integer[][] goal = getZeros(3, 3);
        start[0][0] = 1;
        goal[2][2] = 1;

        World startWorld = new World(start);
        World goalWorld = new World(goal);

        System.out.println(startWorld.toString());


        System.out.println("\n\n\n");

        WorldExpander expander = new WorldExpander();
        Node<World> s = new Node<>(startWorld);
        Node<World> g = new Node<>(goalWorld);
        List<Node<World>> list = AStar.search(s, g, expander);
        System.out.println("RESULT");
        for(Node<World> n : list) {
            System.out.println(n.getElement().toString());
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long now = System.currentTimeMillis();
        System.out.println(((now - startTime)/1000));


    }

}
