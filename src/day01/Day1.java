package day01;
import utils.FileReader;
import utils.Solver;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Day1 implements Solver {

    public Direction getNewDirection(Direction current, Step.Rotation r) {
        if(r == Step.Rotation.LEFT) {
            switch (current) {
                case NORTH:
                    return Direction.WEST;
                case SOUTH:
                    return Direction.EAST;
                case WEST:
                    return Direction.SOUTH;
                case EAST:
                    return Direction.NORTH;
            }
        } else {
            switch (current) {
                case NORTH:
                    return Direction.EAST;
                case SOUTH:
                    return Direction.WEST;
                case WEST:
                    return Direction.NORTH;
                case EAST:
                    return Direction.SOUTH;
            }
        }
        System.out.println("No change");
        return current;
    }

    @Override
    public String solve() {
        Stream<String> input = FileReader.readFileAndSplitBy("day01/input", ",");

        Stream<Step> steps = input.map(s -> new Step(s.charAt(0) == 'L' ? Step.Rotation.LEFT : Step.Rotation.RIGHT, Integer.parseInt(s.substring(1))));

        Position pos = new Position(0,0);
        Direction dir = Direction.NORTH;

        List<Position> history = new LinkedList<>();
        history.add(pos);
        Position revisitedFirst = null;

        for(Step s : steps.toArray(Step[]::new)) {
            dir = getNewDirection(dir, s.rotation);
            for(int i = 0; i < s.amount; i++){
                pos = new Position(pos.x + dir.x, pos.y + dir.y);
                if(revisitedFirst == null && history.contains(pos)) {
                    revisitedFirst = pos;
                }
                history.add(pos);

            }
        }

        return "End pos: " + pos + " distance " + getTaxiDistance(pos) + " first revisit " + revisitedFirst + " distance " + getTaxiDistance(revisitedFirst);
    }

    private int getTaxiDistance(Position pos) {
        return Math.abs(pos.x) + Math.abs(pos.y);
    }
}
