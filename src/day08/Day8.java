package day08;

import utils.FileReader;
import utils.Solver;

import java.util.stream.Stream;

public class Day8 implements Solver {
    @Override
    public String solve() {

        Screen screen = new Screen(7, 3);
        //Screen screen = new Screen(7, 3);

        Stream<String> input = FileReader.readFile("day08/input1");

        String[] inputArr = input.toArray(String[]::new);

        for (String line : inputArr) {
            String[] parts = line.split(" ");
            if (parts[0].equals("rect")) {
                String[] args = parts[1].split("x");
                int width = Integer.parseInt(args[0]);
                int height = Integer.parseInt(args[1]);
                screen.rect(width, height);
            } else if (parts[0].equals("rotate")) {
                int arg = Integer.parseInt(parts[2].split("=")[1]);
                int steps = Integer.parseInt(parts[4]);
                if(parts[1].equals("row")) {
                    screen.rotateY(arg, steps);
                } else {
                    screen.rotateX(arg, steps);
                }
            }
            System.out.println(screen.toString());
        }


        //System.out.println(screen.toString());


        return "" + screen.nbrActivePixels();
    }
}
