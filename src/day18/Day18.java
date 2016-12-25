package day18;

import utils.Solver;

public class Day18 implements Solver {
    @Override
    public String solve() {

        String next = ".^^^.^.^^^.^.......^^.^^^^.^^^^..^^^^^.^.^^^..^^.^.^^..^.^..^^...^.^^.^^^...^^.^.^^^..^^^^.....^....";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 400000; i++) {
            sb.append(next).append("\n");
            next = getNextRow(next);
        }

        String map = sb.toString();

        long safe = map.chars().filter(c -> ((int) '.') == c).count();

        return "" + safe;
    }

    private String getNextRow(String row) {
        char[] elements = row.toCharArray();

        char[] next = new char[elements.length];

        for(int i = 0; i < next.length; i++) {
            if(firstCondition(i, elements) || secondCondition(i, elements) || thirdCondition(i, elements) || fourthCondition(i, elements)) {
                next[i] = '^';
            } else {
                next[i] = '.';
            }
        }

        return new String(next);


    }

    private boolean fourthCondition(int i, char[] elements) {
        int li = i - 1;
        int ri = i + 1;

        return isSafe(li, elements) && isSafe(i, elements) && !isSafe(ri, elements);
    }

    private boolean thirdCondition(int i, char[] elements) {
        int li = i - 1;
        int ri = i + 1;

        return !isSafe(li, elements) && isSafe(i, elements) && isSafe(ri, elements);
    }


    private boolean secondCondition(int i, char[] elements) {
        int li = i - 1;
        int ri = i + 1;

        return isSafe(li, elements) && !isSafe(i, elements) && !isSafe(ri, elements);
    }

    private boolean firstCondition(int i, char[] elements) {
        int li = i - 1;
        int ri = i + 1;
        return !isSafe(li, elements) && !isSafe(i, elements) && isSafe(ri, elements);
    }

    private boolean isSafe(int i, char[] elements) {
        if (i < 0 || i >= elements.length) {
            return true;
        } else {
            return elements[i] == '.';
        }
    }
}
