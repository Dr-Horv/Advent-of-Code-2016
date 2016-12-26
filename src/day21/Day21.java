package day21;

import sun.security.krb5.internal.PAData;
import utils.FileReader;
import utils.Solver;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day21 implements Solver {
    @Override
    public String solve() {
        String data = "abcdefgh";
        String password = "fbgdceah";
        Stream<String> input = FileReader.readFile("day21/input");
        String[] inputArr = input.toArray(String[]::new);

        String regex1 = "swap position (\\d+) with position (\\d+)";
        Pattern p1 = Pattern.compile(regex1);

        String regex2 = "swap letter (\\w) with letter (\\w)";
        Pattern p2 = Pattern.compile(regex2);

        String regex3 = "rotate (\\w+) (\\d+) step";
        Pattern p3 = Pattern.compile(regex3);

        String regex4 = "move position (\\d+) to position (\\d+)";
        Pattern p4 = Pattern.compile(regex4);

        String regex5 = "rotate based on position of letter (\\w)";
        Pattern p5 = Pattern.compile(regex5);

        String regex6 = "reverse positions (\\d+) through (\\d+)";
        Pattern p6 = Pattern.compile(regex6);


        String part1 = part1(data, inputArr, p1, p2, p3, p4, p5, p6);
        String part2 = part2(password, inputArr, p1, p2, p3, p4, p5, p6);


        return part1+ " " + part2;
    }

    private static List<String> permutation(String str) {
        List<String> list = new ArrayList<>();
        permutation("", str, list);
        return list;
    }

    private static void permutation(String prefix, String str, List<String> list) {
        int n = str.length();
        if (n == 0) list.add(prefix);
        else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n), list);
        }
    }

    private String part2(String goal, String[] inputArr, Pattern p1, Pattern p2, Pattern p3, Pattern p4, Pattern p5, Pattern p6) {
        for (String permutation : permutation(goal)) {
            String scrambled = part1(permutation, inputArr, p1, p2, p3, p4, p5, p6);
            if(scrambled.equals(goal)) {
                return permutation;
            }
        }

        return null;
    }

    private String part1(String data, String[] inputArr, Pattern p1, Pattern p2, Pattern p3, Pattern p4, Pattern p5, Pattern p6) {
        Scrambler scrambler = new Scrambler(data);

        for (String line : inputArr) {
            Matcher m = p1.matcher(line);
            if(m.find()) {
                scrambler.swapPosition(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
                continue;
            }

            m = p2.matcher(line);
            if(m.find()) {
                scrambler.swapLetter(m.group(1).charAt(0), m.group(2).charAt(0));
                continue;
            }

            m = p3.matcher(line);
            if(m.find()) {
                scrambler.rotateDir(m.group(1), Integer.parseInt(m.group(2)));
                continue;
            }

            m = p4.matcher(line);
            if(m.find()) {
                scrambler.move(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
                continue;
            }

            m = p5.matcher(line);
            if(m.find()) {
                scrambler.rotateByPosition(m.group(1).charAt(0));
                continue;
            }

            m = p6.matcher(line);
            if(m.find()) {
                scrambler.reversePositions(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
            }
        }
        return scrambler.getString();
    }
}
