package day11;

import java.util.*;
import java.util.stream.Collectors;

public class State {

    private final HashMap<String, TreeSet<String>> floors;
    private int elevatorPosition = 1;

    public State() {
        floors = new HashMap<>();
    }

    public State(State state) {
        this();
        for (String fk : state.floors.keySet()) {
            this.floors.put(fk, new TreeSet<>(state.floors.get(fk)));
        }
        elevatorPosition = state.elevatorPosition;
    }

    public int getElevatorPosition() {
        return elevatorPosition;
    }

    public void setElevatorPosition(int i) {
        elevatorPosition = i;
    }

    public void addFloor(String name) {
        floors.put(name, new TreeSet<>());
    }

    public void addItemToFloor(String floor, String item) {
        TreeSet<String> floorItems = floors.getOrDefault(floor, new TreeSet<>());
        floorItems.add(item);
        floors.put(floor, floorItems);
    }

    public void removeItemFromFloor(String floor, String item) {
        SortedSet<String> items = floors.get(floor);
        items.remove(item);
    }

    public List<String> getItemsOnFloor(String floor) {
        return floors.get(floor).stream().collect(Collectors.toList())  ;
    }

    public int getMaxFloor() {
        return floors.keySet().size();
    }

    public boolean isValid() {
        for (String floorKey : floors.keySet()) {
            List<String> items = getItemsOnFloor(floorKey);
            if (!isValidItemCombo(items)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidItemCombo(List<String> items) {
        items.sort(Comparator.reverseOrder());
        Queue<String> itemQueue = new LinkedList<>(items);
        Set<String> generators = items.stream().filter(s -> s.charAt(1) == 'G').collect(Collectors.toSet());

        while (!itemQueue.isEmpty()) {
            String item = itemQueue.poll();
            if (item.charAt(1) == 'M') {
                if (itemQueue.size() > 0 && itemQueue.peek().equals(item.charAt(0) + "G")) {
                    continue;
                }
                if (generators.size() > 0) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        if (elevatorPosition != state.elevatorPosition) return false;
        if(!floors.keySet().equals(state.floors.keySet())) return false;

        for(String k : floors.keySet()) {
            List<String> l1 = getItemsOnFloor(k);
            List<String> l2 = state.getItemsOnFloor(k);
            if(l1.size() != l2.size()) {
                return false;
            }

            long l1gens = l1.stream().filter(s -> s.charAt(1) == 'G').count();
            long l2gens = l2.stream().filter(s -> s.charAt(1) == 'G').count();

            if(l1gens != l2gens) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        Long value = floors.entrySet().stream().map(e -> e.getValue().stream().filter(s -> s.charAt(1) == 'G').count()).reduce(0l, (l1, l2) -> l1 + l2);
        int result = elevatorPosition;
        result = 31 * result + value.intValue();
        return result;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        floors.keySet().stream().sorted((s1, s2) -> s2.compareTo(s1)).forEach(s -> {
            sb.append(s).append(": ");
            List<String> items = getItemsOnFloor(s);
            sb.append(String.join(", ", items));
            sb.append("\n");
        });

        sb.append("Elevator: ").append(elevatorPosition);

        return sb.toString();

    }
}
