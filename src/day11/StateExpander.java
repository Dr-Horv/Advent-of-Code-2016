package day11;

import utils.graph.Node;
import utils.graph.NodeExpander;

import java.util.ArrayList;
import java.util.List;

public class StateExpander implements NodeExpander<Node<State>> {

    @Override
    public List<Node<State>> getNeighbouringNodes(Node<State> n) {
        State state = n.getElement();

        List<Node<State>> neighbours = new ArrayList<>();

        int[] levelChanges = new int[] {-1, 1};


        int currentFloor = state.getElevatorPosition();
        String floorKey = "F" + currentFloor;
        List<String> items = state.getItemsOnFloor(floorKey);

        for (int dy : levelChanges) {
            int newFloor = currentFloor + dy;
            if(!isValidFloorNumber(state, newFloor)) {
                continue;
            }


            if(dy < 0 && allFloorBelowEmpty(state, currentFloor)) {
                continue;
            }

            String newFloorKey = "F" + newFloor;
            moveOneItem(state, neighbours, floorKey, items, newFloor, newFloorKey);
            if(dy > 0) {
                moveTwoItems(state, neighbours, floorKey, items, newFloor, newFloorKey);
            }

        }

        return neighbours;

    }

    private boolean allFloorBelowEmpty(State state, int currentFloor) {
        for(int i = 1; i < currentFloor; i++) {
            if(state.getItemsOnFloor("F"+i).size() > 0) {
                return false;
            }
        }

        return true;

    }

    private void moveTwoItems(State state, List<Node<State>> neighbours, String floorKey, List<String> items, int newFloor, String newFloorKey) {
        for (int i = 0; i < items.size(); i++) {
            String item1 = items.get(i);
            for (int j = i+1; j < items.size(); j++) {
                String item2 = items.get(j);

                if(item1.equals(item2)) {
                    continue;
                }

                State c = getNewState(state, floorKey, newFloor, newFloorKey, item1);
                State newState = getNewState(c, floorKey, newFloor, newFloorKey, item2);

                if(newState.isValid()) {
                    neighbours.add(new Node<>(newState));
                }

            }
        }
    }

    private void moveOneItem(State state, List<Node<State>> neighbours, String floorKey, List<String> items, int newFloor, String newFloorKey) {
        for(String item : items) {
            State newState = getNewState(state, floorKey, newFloor, newFloorKey, item);
            if(newState.isValid()) {
                neighbours.add(new Node<>(newState));
            }
        }
    }

    private State getNewState(State state, String floorKey, int newFloor, String newFloorKey, String item) {
        State copy = new State(state);
        copy.setElevatorPosition(newFloor);
        copy.removeItemFromFloor(floorKey, item);
        copy.addItemToFloor(newFloorKey, item);
        return copy;
    }

    private boolean isValidFloorNumber(State state, int newFloor) {
        return newFloor <= state.getMaxFloor() && newFloor >= 1;
    }

    public int estimatedCost(Node<State> n) {
        State state = n.getElement();
        int cost = 0;
        for (int i = 1; i <= state.getMaxFloor(); i++) {
            String fk = "F"+i;
            List<String> items = state.getItemsOnFloor(fk);
            cost += items.size() * (state.getMaxFloor()-i);
        }

        return cost;
    }

}
