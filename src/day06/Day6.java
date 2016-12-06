package day06;

import utils.FileReader;
import utils.Solver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day6 implements Solver {
    @Override
    public String solve() {
        Stream<String> input = FileReader.readFile("day06/input");

        String[] inputArr = input.toArray(String[]::new);

        ArrayList<List<LetterCount>> letters = getLettersList(inputArr[0].length());

        for(String line : inputArr) {
            char[] chars = line.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                analyzeLine(letters.get(i), chars[i]);
            }
        }

        return retrieveOutput(letters);
    }

    private String retrieveOutput(ArrayList<List<LetterCount>> letters) {
        String output = "";
        for(List<LetterCount> positionList : letters) {
            positionList.sort((lc1, lc2) ->  lc1.getCount() - lc2.getCount());
            output += positionList.get(0).getLetter();
        }
        return output;
    }

    private void analyzeLine(List<LetterCount> letterCounts, char aChar) {
        LetterCount lc = new LetterCount(0, aChar);
        int index = letterCounts.indexOf(lc);
        if(index > -1) {
            lc = letterCounts.remove(index);
        }
        lc.incCount();
        letterCounts.add(lc);
    }

    private ArrayList<List<LetterCount>> getLettersList(int size) {
        ArrayList<List<LetterCount>> letters = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            letters.add(new ArrayList<>());
        }
        return letters;
    }
}
