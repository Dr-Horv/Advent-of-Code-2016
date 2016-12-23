package day15;

import utils.FileReader;
import utils.Solver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day15 implements Solver {
    @Override
    public String solve() {

        Stream<String> input = FileReader.readFile("day15/input2");
        String[] inputArr = input.toArray(String[]::new);

        List<Disc> disc = new ArrayList<>();

        for (String line : inputArr) {
            disc.add(parseDisc(line));
        }

        disc.forEach(System.out::println);

        int t = 0;
        while(true) {
            boolean test = true;
            for (int i = 0; i < disc.size(); i++) {
                if(!disc.get(i).passAtTime(t+1+i)) {
                    test = false;
                    break;
                }
            }
            if(test) {
                break;
            }
            t++;

        }


        return "" + t;
    }

    private Disc parseDisc(String line) {
        String[] parts = line.split(" ");
        int positions = Integer.parseInt(parts[3]);
        String part = parts[parts.length - 1];
        int start = Integer.parseInt(part.substring(0, part.length()-1));
        return new Disc(start, positions);
    }
}
