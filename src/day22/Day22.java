package day22;

import utils.FileReader;
import utils.Position;
import utils.Solver;
import utils.graph.AStar;
import utils.graph.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day22 implements Solver {
    @Override
    public String solve() {

        ArrayList<StorageNode> storageNodes = new ArrayList<>();


        Stream<String> input = FileReader.readFile("day22/input");
        String[] inputArr = input.toArray(String[]::new);

        for (String line : inputArr) {
            addNode(line, storageNodes);
        }

        StorageNode empty = storageNodes.get(0);
        StorageNode goal = storageNodes.get(0);
        for (StorageNode n : storageNodes) {
            Position p = n.getPosition();
            if(p.y < goal.getPosition().y || p.y == 0 && p.x > goal.getPosition().x ) {
                goal = n;
            }
            if(n.getAvailable() > empty.getAvailable()) {
                empty = n;
            }
        }

        empty.setType(StorageNode.Type.EMPTY);

        for(StorageNode n : storageNodes) {
            if(validPair(n, empty)) {
                n.setType(StorageNode.Type.MOVABLE);
            } else if (!n.equals(empty)) {
                n.setType(StorageNode.Type.BLOCKED);
            }
        }

        System.out.println(goal);
        goal.setHasGoalData(true);
        State start = new State(empty, goal);

        Node<State> sn = new Node<>(start);
        StateExpander expander = new StateExpander(storageNodes);

        expander.prettyPrint(start);

        List<Node<State>> search = AStar.search(sn, new GoalFinder(), expander);

        if(search == null) {
            return "Found nothing";
        }

        return part1(storageNodes) + " " + (search.size() - 1);
    }

    private String part1(ArrayList<StorageNode> storageNodes) {
        int pairs = 0;

        for (StorageNode n1 : storageNodes) {
            for(StorageNode n2 : storageNodes) {
                if(validPair(n1, n2)) {
                    pairs++;
                }
            }
        }

        return pairs + "";
    }

    private boolean validPair(StorageNode a, StorageNode b) {
        if(a.getUsed() > 0) {
            if (!a.equals(b)) {
                return a.getUsed() <= b.getAvailable();
            }
        }
        return false;
    }

    private void addNode(String line, List<StorageNode> storageNodes) {
        String regex = "\\/dev\\/grid\\/node-x(\\d+)-y(\\d+)\\s+(\\d+)T\\s+(\\d+)T\\s+(\\d+)T\\s+(\\d+)%";
        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(line);
        if(m.find()) {
            Position pos = new Position(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
            StorageNode n = new StorageNode(pos, Integer.parseInt(m.group(5)), Integer.parseInt(m.group(4)));
            storageNodes.add(n);
        }
    }

}
