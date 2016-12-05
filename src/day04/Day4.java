package day04;

import utils.FileReader;
import utils.Solver;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day4 implements Solver {

    @Override
    public String solve() {
        Stream<String> input = FileReader.readFile("day04/input");

        int sum = 0;
        StringBuilder sb = new StringBuilder();
        for(String line : input.toArray(String[]::new)) {
            if (isRealRoom(line)) {
                int sectorId = getSectorId(line);
                sum += sectorId;
                sb.append(sectorId).append(" ").append(rotateString(getRoomName(line), sectorId)).append('\n');
            }
        }
        return "" + sum + "\n" + sb.toString();
    }

    private boolean isRealRoom(String line) {
        String[] parts = line.split("-");

        HashMap<Character, Integer> letterFrequency = getLetterFrequencyMap(getRoomName(parts));
        ArrayList<LetterCount> list = getLetterCountSortedList(letterFrequency);

        String idAndChecksum = parts[parts.length-1];

        String calculatedChecksum = calculateChecksum(list);
        String checksum = getChecksum(idAndChecksum);

        return calculatedChecksum.equals(checksum);
    }

    private String calculateChecksum(ArrayList<LetterCount> list) {
        LinkedHashSet<Character> checksumSet = new LinkedHashSet<>();
        int i = 0;
        while(i < list.size() && checksumSet.size() < 5) {
            checksumSet.add(list.get(i).getLetter());
            i++;
        }

        StringBuilder sccb = new StringBuilder();
        for(Character c : checksumSet){
            sccb.append(c);
        }

        return sccb.toString();
    }

    private ArrayList<LetterCount> getLetterCountSortedList(HashMap<Character, Integer> letterFrequency) {
        ArrayList<LetterCount> list = new ArrayList<>();

        for(Map.Entry<Character, Integer> e : letterFrequency.entrySet()) {
            list.add(new LetterCount(e.getValue(), e.getKey()));
        }

        Collections.sort(list, (lc1, lc2) -> {
            int diff = lc2.getCount() - lc1.getCount();
            if (diff == 0) {
                diff = Character.compare(lc1.getLetter(), lc2.getLetter());
            }
            return diff;
        });
        return list;
    }

    private HashMap<Character, Integer> getLetterFrequencyMap(char[] chars) {
        HashMap<Character, Integer> letterFrequency = new HashMap<>();
        for(char c : chars) {
            Integer count = letterFrequency.getOrDefault(c, 0);
            letterFrequency.put(c, count+1);
        }
        return letterFrequency;
    }

    private char[] getRoomName(String[] parts) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length - 1; i++) {
            sb.append(parts[i]);
        }
        return sb.toString().toCharArray();
    }

    private String rotateString(String s, int rotation) {
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == ' '){
                continue;
            }
            char shiftedChar = getRotatedChar(rotation, arr[i]);
            arr[i] = shiftedChar;
        }
        return new String(arr);
    }

    private char getRotatedChar(int rotation, char c) {
        return (char) ((((c - 'a' + rotation) % ('z' - 'a' + 1))) + 'a');
    }

    private int getSectorId(String s) {
        Pattern p = Pattern.compile("([0-9]*)\\[");
        Matcher matcher = p.matcher(s);
        if(matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        } else {
            return 0;
        }
    }

    private String getChecksum(String idAndChecksum) {
        Pattern p = Pattern.compile("\\[([a-zA-Z]*)\\]");
        Matcher matcher = p.matcher(idAndChecksum);
        if(matcher.find()) {
            return matcher.group(1);
        } else {
            return "INVALID";
        }
    }

    private String getRoomName(String s) {
        String[] parts = s.split("-");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < parts.length - 1; i++) {
            sb.append(parts[i]);
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}
