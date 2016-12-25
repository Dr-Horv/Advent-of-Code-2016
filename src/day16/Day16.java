package day16;

import utils.Solver;

import java.util.Comparator;
import java.util.stream.Collectors;

public class Day16 implements Solver {
    @Override
    public String solve() {

        String input = "01111001100111011";

        String data = generateData(input, 35651584);
        String checksum = calculateChecksum(data);

        return checksum;
    }

    private String calculateChecksum(String data) {

        do {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < data.length() - 1; i += 2) {
                if (data.charAt(i) == data.charAt(i + 1)) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
            }
            data = sb.toString();
        } while (data.length() % 2 == 0);


        return data;
    }

    private String generateData(String input, int size) {

        String result = input;

        while(result.length() < size) {
            String a = result;

            StringBuilder sb = new StringBuilder();
            char[] array = a.toCharArray();
            for (int i = 0; i < array.length; i++) {
                char c = array[array.length - 1 - i];
                if(c == '1') {
                    sb.append("0");
                } else {
                    sb.append("1");
                }
            }
            String b = sb.toString();

            result = a + "0" + b;
        }

        return result.substring(0, size);

    }
}
