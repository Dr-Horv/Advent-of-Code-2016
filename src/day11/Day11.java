package day11;

import utils.graph.AStar;
import utils.graph.Node;
import utils.FileReader;
import utils.Solver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day11 implements Solver {

    @Override
    public String solve() {

        Stream<String> input = FileReader.readFile("day11/input");
        String[] inputArr = input.toArray(String[]::new);

        State state = getStartState(inputArr);
        ArrayList<String> allItems = getAllItemsFromInput(inputArr);
        State goal = getGoalState(allItems);

        Node<State> sn = new Node<>(state);
        Node<State> gn = new Node<>(goal);

        System.out.println("START");
        System.out.println(sn.getElement());
        System.out.println(new StateExpander().estimatedCost(sn));
        System.out.println(new StateExpander().estimatedCost(gn));
        System.out.println("GOAL");
        System.out.println(gn.getElement());
        System.out.println("----");

        List<Node<State>> result = AStar.search(sn, n -> n.equals(gn), new StateExpander());


        return "" + result.size();

    }

    private State getGoalState(ArrayList<String> allItems) {
        State state = new State();
        for (int i = 1; i < 4; i++) {
            state.addFloor("F"+i);
        }
        state.addFloor("F4");
        for(String item : allItems) {
            state.addItemToFloor("F4", item);
        }
        state.setElevatorPosition(4);
        return state;
    }

    private ArrayList<String> getAllItemsFromInput(String[] inputArr) {
        ArrayList<String> items = new ArrayList<>();
        for (String line : inputArr) {
            String[] parts = line.split(" ");
            List<String> itemsFromFloor = getItemsFromInput(parts);
            items.addAll(itemsFromFloor);
        }
        return items;
    }

    private State getStartState(String[] inputArr) {
        State state = new State();
        for (String line : inputArr) {
            String[] parts = line.split(" ");
            String fk = wordToFloorKey(parts[1]);
            List<String> items = getItemsFromInput(parts);
            state.addFloor(fk);
            for(String item : items) {
                state.addItemToFloor(fk, item);
            }
        }
        return state;
    }

    private List<String> getItemsFromInput(String[] parts) {
        int index = 0;
        List<String> items = new ArrayList<>();
        while(index < parts.length) {
            if (parts[index].equals("a")) {
                String fp = parts[index+1];
                String sp = parts[index+2];
                items.add(Character.toUpperCase(fp.charAt(0)) + "" + Character.toUpperCase(sp.charAt(0)));
            }
            index++;
        }

        return items;
    }

    public String wordToFloorKey(String word) {
        switch (word) {
            case "first":
                return "F1";
            case "second":
                return "F2";
            case "third":
                return "F3";
            case "fourth":
                return "F4";
        }
        return "NO FLOOR";
    }
}
