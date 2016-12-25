package day17;

import utils.Position;
import utils.Solver;
import utils.graph.AStar;
import utils.graph.GoalFunction;
import utils.graph.Node;

import java.util.List;

public class Day17 implements Solver {

    class Finder implements GoalFunction<Node<State>> {

        @Override
        public boolean isGoal(Node<State> stateNode) {
            return stateNode.getElement().getPosition().equals(new Position(3,3));
        }
    }

    @Override
    public String solve() {

        String passcode = "dmypynyp";
        String hash = StateExpander.getHash(passcode);
        String valid = "BCDEF";
        System.out.println(hash);

        boolean up = valid.contains(""+hash.charAt(0));
        boolean down = valid.contains(""+hash.charAt(1));
        boolean left = valid.contains(""+hash.charAt(2));
        boolean right = valid.contains(""+hash.charAt(3));

        State start = new State(new Position(0, 0), "", up, down, left, right);

        Node<State> sn = new Node<>(start);

        List<Node<State>> search = AStar.search(sn, new Finder(), new StateExpander(passcode, new Position(3, 3)));

        System.out.println(search.get(0).getElement());


        return search.get(0).getElement().getPath().length() + "";
    }
}
