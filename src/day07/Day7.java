package day07;

import utils.FileReader;
import utils.Solver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Day7 implements Solver {

    public String solve() {
        Stream<String> input = FileReader.readFile("day07/input");

        //long count = input.filter(Day7::supportTLS).count();
        long count = input.filter(Day7::supportSSL).count();

        return ""+ count;
    }

    private static boolean supportTLS(String s) {
        boolean isOutsideBrackets = false;
        boolean isInsideBrackets = false;
        boolean inside = false;

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length - 3; i++) {
            char first = chars[i];
            if(first == '[' || first == ']') {
                inside = !inside;
                continue;
            }

            char second = chars[i+1];
            char third = chars[i+2];
            char fourth = chars[i+3];

            if (first == fourth && second == third && first != second) {
                if(inside) {
                    isInsideBrackets = true;
                } else {
                    isOutsideBrackets = true;
                }
            }
        }

        return isOutsideBrackets && !isInsideBrackets;
    }

    private static boolean supportSSL(String s) {
        Set<String> abas = new HashSet<>();
        Set<String> babs = new HashSet<>();
        boolean inside = false;

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length - 2; i++) {
            char first = chars[i];
            if(first == '[' || first == ']') {
                inside = !inside;
                continue;
            }

            char second = chars[i+1];
            char third = chars[i+2];

            if(first == third && second != first) {
                String part = "" + first + second + third;
                if(inside) {
                    abas.add(part);
                } else {
                    babs.add(part);
                }
            }
        }


        for(String aba : abas) {
            char b = aba.charAt(1);
            char a = aba.charAt(0);
            if(babs.contains("" + b + a + b)){
                return true;
            }
        }

        return false;
    }
}
