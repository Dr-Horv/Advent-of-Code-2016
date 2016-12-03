package day03;

import utils.FileReader;
import utils.Solver;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3 implements Solver {
    @Override
    public String solve() {
        Stream<String> input = FileReader.readFile("day03/input");
        int valid = 0;
        String[] lines = input.toArray(String[]::new);
        for(int i = 0; i < lines.length - 2; i = i + 3) {
            String[] line1 = lines[i].split("\\s+");
            String[] line2 = lines[i+1].split("\\s+");
            String[] line3 = lines[i+2].split("\\s+");

            for(int c = 0; c < line1.length; c++) {
                List<Integer> sides = convertPartsToInts(new String[]{ line1[c], line2[c], line3[c] });
                if(isValidTriangle(sides)) {
                    valid++;
                }
            }

        }

        return "" + valid;
    }

    private List<Integer> convertPartsToInts(String[] line1) {
        return Arrays.stream(line1).map(String::trim).map(Integer::parseInt).collect(Collectors.toList());
    }

    private boolean isValidTriangle(List<Integer> sides) {
        return (sides.get(0) + sides.get(1)) > sides.get(2) &&
                (sides.get(1) + sides.get(2)) > sides.get(0) &&
                (sides.get(2) + sides.get(0)) > sides.get(1);
    }
}
