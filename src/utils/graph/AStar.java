package utils.graph;

import java.util.*;

public class AStar {


    public static <E> List<Node<E>> search(Node<E> start, GoalFunction<Node<E>> goalFinder, NodeExpander<Node<E>> expander) {
        return search(start, goalFinder, expander, true);
    }

    public static <E> List<Node<E>> search(Node<E> start, GoalFunction<Node<E>> goalFinder, NodeExpander<Node<E>> expander, boolean verbose) {
        if(verbose) {
            System.out.println("----SEARCH----");
            System.out.println(start.getElement());
            System.out.println();
            //System.out.println(goal.getElement());
            System.out.println("--------------");
        }

        Long timestamp = System.currentTimeMillis();

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

            Long now = System.currentTimeMillis();
            if((now - timestamp) > 10_000 ) {
                timestamp = now;
                printReport(closedSet, openSet, loop);
            }

            int lowest = Integer.MAX_VALUE;
            Node<E> current = null;


            for (Node<E> n : openSet) {
                Integer value = fScore.getOrDefault(n, Integer.MAX_VALUE);
                if (value <= lowest) {
                    lowest = value;
                    current = n;
                }
            }


            if (goalFinder.isGoal(current)) {
                if(verbose) {
                    System.out.println("Reached goal");
                    printReport(closedSet, openSet, loop);
                }
                return reconstructPath(cameFrom, current, verbose);
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

        if(verbose) {
            printReport(closedSet, openSet, loop);
        }

        return null;


    }

    private static <E> void printReport(Set<Node<E>> closedSet, Set<Node<E>> openSet, int loop) {
        System.out.println("Explored: " + loop);
        System.out.println("Open: " + openSet.size());
        System.out.println("Closed " + closedSet.size());
    }

    private static <E> List<Node<E>> reconstructPath(HashMap<Node<E>, Node<E>> cameFrom, Node<E> current, boolean verbose) {
        if(verbose) {
            System.out.println("Reconstructing path");
        }
        List<Node<E>> path = new ArrayList<>();
        path.add(current);
        while (cameFrom.keySet().contains(current)) {
            current = cameFrom.get(current);
            path.add(current);
        }

        return path;
    }
}
