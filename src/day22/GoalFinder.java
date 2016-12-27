package day22;


import utils.Position;
import utils.graph.*;
import utils.graph.Node;

public class GoalFinder implements GoalFunction<utils.graph.Node<State>> {

    @Override
    public boolean isGoal(Node<State> stateNode) {
        State state = stateNode.getElement();
        return state.getStorageNodeWithGoalData().getPosition().equals(new Position(0, 0));
    }
}
