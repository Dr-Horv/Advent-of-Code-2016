package day13;

import utils.Position;
import utils.Solver;
import utils.graph.AStar;
import utils.graph.Node;

import java.util.ArrayList;
import java.util.List;

public class Day13 implements Solver {
    @Override
    public String solve() {
        int number = 1352;

        Position start = new Position(1,1);
        Position goal = new Position(31,39);

        Node<Position> sn = new Node<>(start);
        Node<Position> gn = new Node<>(goal);
        PositionExpander expander = new PositionExpander(number, goal);

        List<Node<Position>> result = AStar.search(sn, (n) -> n.equals(gn), expander);
        String part1 = "" + result.size();

        ArrayList<Position> valid = new ArrayList<>();


        for (int y = 0; y < 51; y++) {
            for (int x = 0; x < 51; x++) {

                Position pos = new Position(x, y);
                Node<Position> goalNode = new Node<>(pos);
                PositionExpander newExpander = new PositionExpander(number, pos);

                if(newExpander.isBlocked(pos) || newExpander.estimatedCost(goalNode) > 50) {
                    continue;
                }

                List<Node<Position>> searchResult = AStar.search(sn, n -> n.equals(goalNode), newExpander, false);
                if(searchResult != null && searchResult.size() <= 50) {
                    if(pos.equals(new Position(8,0))) {
                        System.out.println("Adding 8.0");
                    }
                    valid.add(pos);
                }
            }
        }


        String part2 = valid.size() + "";

        return "Part1 " + part1 + " Part2 " + part2;
    }
}
