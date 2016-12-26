package day09;

import utils.FileReader;
import utils.Solver;

import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day9 implements Solver {

    @Override
    public String solve() {
        Stream<String> input = FileReader.readFile("day09/input");

        String[] inputArr = input.toArray(String[]::new);

        String line = inputArr[0];

        String regex = "\\((\\d+)x(\\d+)\\)";
        Pattern pattern = Pattern.compile(regex);

        int[] values = new int[line.length()];
        Arrays.fill(values, 1);

        while(true) {
            Matcher m = pattern.matcher(line);

            if(m.find()) {
                String first = m.group(1);
                String second = m.group(2);
                String match = "(" + first+ "x" + second + ")";
                int index = line.indexOf(match);

                int f = Integer.parseInt(first);
                int s = Integer.parseInt(second);

                int repeatStartIndex = index + match.length();

                for (int i = 0; i < f; i++) {
                    values[repeatStartIndex + i] *= s;
                }

                for (int i = index; i < repeatStartIndex; i++) {
                    values[i] = 0;
                }

                String part1 = line.substring(0, index);
                String part2 = line.substring(repeatStartIndex);
                line = part1 + part2;


            } else {
                break;
            }

            int[] newValues = new int[line.length()];

            int i = 0;
            for (int value : values) {
                if (value == 0) {
                    continue;
                }
                newValues[i] = value;
                i++;
            }

            values = newValues;

        }

        Long totalValue = 0L;
        for (int value : values) {
            totalValue += value;
        }


        return totalValue + "";
    }
}
