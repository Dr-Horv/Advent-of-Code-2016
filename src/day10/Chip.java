package day10;

public class Chip {

    public final int value;
    public Chip(int value) {
        this.value = value;
    }

    public Chip(Chip other) {
        value = other.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chip chip = (Chip) o;

        return value == chip.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return "Chip{" +
                "value=" + value +
                '}';
    }
}
