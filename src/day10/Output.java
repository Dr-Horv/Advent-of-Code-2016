package day10;

import java.util.ArrayList;

public class Output implements Receiver {

    private final String name;

    public String getName() {
        return name;
    }

    public ArrayList<Chip> getBin() {
        return bin;
    }

    private final ArrayList<Chip> bin = new ArrayList<>();

    public Output(String name) {
        this.name = name;
    }


    @Override
    public void receive(Chip c) {
        bin.add(c);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Output output = (Output) o;

        return name != null ? name.equals(output.name) : output.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
