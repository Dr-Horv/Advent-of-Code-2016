package day24;

import utils.FileReader;
import utils.Solver;
import utils.graph.AStar;
import utils.graph.GoalFunction;
import utils.graph.Node;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day24 implements Solver {
    @Override
    public String solve() {
        Stream<String> input = FileReader.readFile("day24/input");
        List<String> inputList = input.collect(Collectors.toList());

        StateExpander expander = new StateExpander(inputList);

        State start = new State(expander.getStart(), expander.getNumbers());
        Node<State> sn = new Node<>(start);
        GoalFunction<Node<State>> gf = (n -> n.getElement().getNumbers().size() == 0);

        List<Node<State>> search = AStar.search(sn, gf, expander, true);

        if(search == null) {
            return "Found nothing";
        }

        return (search.size()-1) + "";


    }
}
