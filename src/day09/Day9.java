package day09;

import utils.FileReader;
import utils.Solver;

import java.io.BufferedInputStream;
import java.util.Scanner;
import java.util.stream.Stream;

public class Day9 implements Solver {

    @Override
    public String solve() {
        Stream<String> input = FileReader.readFile("day09/input");

        String[] inputArr = input.toArray(String[]::new);
        StringBuilder sb = new StringBuilder();
        boolean inMarker = false;
        boolean inFirstPart = true;
        String firstPart = "";
        String lastPart = "";
        String parsed = "";

        for(String line : inputArr) {
            Scanner sc = new Scanner(line);
            sc.useDelimiter("");
            while(sc.hasNext()) {
                char c = sc.next().charAt(0);
                parsed += c;
                //System.out.println(parsed);

                if(!inMarker && c == '(') {
                    inMarker = true;
                    firstPart = "";
                    lastPart = "";
                    continue;
                }

                if(inMarker) {
                    if(c == ')') {
                        inMarker = false;
                        inFirstPart = true;

                        int firstPartInt = Integer.parseInt(firstPart);
                        int lastPartInt = Integer.parseInt(lastPart);
                        String repeatPart = "";

                        for (int i = 0; i < firstPartInt; i++) {
                            repeatPart += sc.next();
                        }

                        for (int i = 0; i < lastPartInt; i++) {
                            sb.append(repeatPart);
                        }
                    }

                    if(inFirstPart) {
                        if(c == 'x') {
                            inFirstPart = false;
                        } else {
                            firstPart += c;
                        }
                    } else {
                        lastPart += c;
                    }
                } else {
                    sb.append(c);
                }

            }
        }

        return "" + sb.toString().length();
    }
}
