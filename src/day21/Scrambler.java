package day21;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Scrambler {

    private ArrayList<Character> data;

    public Scrambler(String s) {
        data = new ArrayList<>();
        for(char c : s.toCharArray()) {
            data.add(c);
        }
    }

    public String getString() {
        return data.stream().map(c -> c + "").collect(Collectors.joining());
    }

    public void swapPosition(int x, int y) {
        Character[] arr = data.toArray(new Character[data.size()]);
        char xLetter = arr[x];
        char yLetter = arr[y];

        arr[x] = yLetter;
        arr[y] = xLetter;

        data = new ArrayList<>();
        for(char c : arr) {
            data.add(c);
        }

    }

    public void swapLetter(char x, char y) {
        data = data.stream().map(c -> {
            if(c == x) {
                return y;
            } else if (c == y) {
                return x;
            }
            return c;
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    public void rotateDir(String direction, int steps) {
        LinkedList<Character> list = new LinkedList<>(data);
        for (int i = 0; i < steps; i++) {
            if(direction.equals("left")) {
                list.addLast(list.removeFirst());
            } else {
                list.addFirst(list.removeLast());
            }
        }
        data = new ArrayList<>(list);
    }

    public void rotateByPosition(char c) {
        int index = data.indexOf(c);
        int rotation = 1;
        if(index == -1) {
            index = 0;
        }
        rotation += index;

        if(index >= 4) {
            rotation++;
        }

        rotateDir("right", rotation);
    }

    public void reversePositions(int x, int y) {
        while(x < y) {
            swapPosition(x, y);
            x++;
            y--;
        }
    }

    public void move(int x, int y) {
        Character c = data.remove(x);
        data.add(y, c);
    }


}
