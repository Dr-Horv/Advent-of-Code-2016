package day02;

import utils.FileReader;
import utils.Position;
import utils.Solver;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Day2 implements Solver {

    private static final HashMap<Position, String> posToCode = new HashMap<>();
    private Position pos;

    public Day2(boolean isPartOne) {

        if(isPartOne) {
            pos = new Position(0,0);
            initNormalKeyPad();
        } else {
            pos = new Position(-2, 0);
            initPart2Keypad();
        }
    }

    private void initPart2Keypad() {
        posToCode.put(new Position(0, 0), "7");
        posToCode.put(new Position(0, 1), "3");
        posToCode.put(new Position(0, 2), "1");
        posToCode.put(new Position(0, -1), "B");
        posToCode.put(new Position(0, -2), "D");

        posToCode.put(new Position(-1, 1), "2");
        posToCode.put(new Position(-1, 0), "6");
        posToCode.put(new Position(-1, -1), "A");

        posToCode.put(new Position(1, 1), "4");
        posToCode.put(new Position(1, 0), "8");
        posToCode.put(new Position(1, -1), "C");

        posToCode.put(new Position(-2, 0), "5");
        posToCode.put(new Position(2, 0), "9");
    }

    private void initNormalKeyPad() {
        posToCode.put(new Position(-1, 1), "1");
        posToCode.put(new Position(-1, 0), "4");
        posToCode.put(new Position(-1, -1), "7");

        posToCode.put(new Position(0, 1), "2");
        posToCode.put(new Position(0, 0), "5");
        posToCode.put(new Position(0, -1), "8");

        posToCode.put(new Position(1, 1), "3");
        posToCode.put(new Position(1, 0), "6");
        posToCode.put(new Position(1, -1), "9");
    }

    private Position getMovement(Position pos, String line) {
        for(char c : line.toCharArray()) {
            Position oldPos = pos;
            switch (c) {
                case 'L':
                    pos = pos.subtract(new Position(1, 0));
                    break;
                case 'R':
                    pos = pos.add(new Position(1, 0));
                    break;
                case 'U':
                    pos = pos.add(new Position(0, 1));
                    break;
                case 'D':
                    pos = pos.subtract(new Position(0, 1));
                    break;
            }
            if(!isWithinBounds(pos)) {
                pos = oldPos;
            }
        }
        return pos;
    }

    @Override
    public String solve() {
        Stream<String> input = FileReader.readFileAndSplitBy("day02/input", ",");

        List<String> code = new LinkedList<>();

        for(String line : input.toArray(String[]::new)) {
            pos = getMovement(pos, line);
            String digit = posToCode.get(pos);
            code.add(digit);
        }

        String output = "";
        for (String d : code) {
            output += d;
        }
        return output;
    }


    private boolean isWithinBounds(Position pos) {
        return posToCode.get(pos) != null;
    }
}
