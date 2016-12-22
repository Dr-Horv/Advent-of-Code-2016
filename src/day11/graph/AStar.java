package day11.graph;

import java.util.*;

public class AStar {

    public static <E> List<Node<E>> search(Node<E> start, Node<E> goal, NodeExpander<Node<E>> expander) {
        System.out.println("----SEARCH----");
        System.out.println(start.getElement());
        System.out.println();
        System.out.println(goal.getElement());
        System.out.println("--------------");

        Set<Node<E>> closedSet = new HashSet<>();
        Set<Node<E>> openSet = new HashSet<>();

        openSet.add(start);

        HashMap<Node<E>, Node<E>> cameFrom = new HashMap<>();

        HashMap<Node<E>, Integer> gScore = new HashMap<>();
        gScore.put(start, 0);

        HashMap<Node<E>, Integer> fScore = new HashMap<>();
        fScore.put(start, expander.estimatedCost(start));
        int loop = 0;
        while (openSet.size() > 0) {
            loop++;

            int lowest = Integer.MAX_VALUE;
            Node<E> current = null;

            for (Node<E> n : openSet) {
                Integer value = fScore.getOrDefault(n, Integer.MAX_VALUE);
                if (value <= lowest) {
                    lowest = value;
                    current = n;
                }
            }


            if (current.equals(goal)) {
                System.out.println("Reached goal");
                System.out.println("Explored: " + loop);
                System.out.println("Open: " + openSet.size());
                System.out.println("Closed " + closedSet.size());
                return reconstructPath(cameFrom, current);
            }

            openSet.remove(current);
            closedSet.add(current);

            for (Node<E> neighbour : expander.getNeighbouringNodes(current)) {
                if (closedSet.contains(neighbour)) {
                    continue;
                }

                int tentativeGScore = gScore.getOrDefault(current, Integer.MAX_VALUE) + 1;
                if (!openSet.contains(neighbour)) {
                    openSet.add(neighbour);
                } else if (tentativeGScore >= gScore.getOrDefault(neighbour, Integer.MAX_VALUE)) {
                    continue;
                }

                cameFrom.put(neighbour, current);
                gScore.put(neighbour, tentativeGScore);
                fScore.put(neighbour, gScore.getOrDefault(neighbour, Integer.MAX_VALUE) + expander.estimatedCost(neighbour));

            }
        }

        return new ArrayList<>();


    }

    private static <E> List<Node<E>> reconstructPath(HashMap<Node<E>, Node<E>> cameFrom, Node<E> current) {
        System.out.println("Reconstructing path");
        List<Node<E>> path = new ArrayList<>();
        while (cameFrom.keySet().contains(current)) {
            current = cameFrom.get(current);
            path.add(current);
        }

        return path;
    }
}
