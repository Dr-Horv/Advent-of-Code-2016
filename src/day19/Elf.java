package day19;

public class Elf {

    private final int number;
    private int presents;

    public Elf(int number) {
        this.number = number;
        presents = 1;
    }

    public int getNumber() {
        return number;
    }

    public int getPresents() {
        return presents;
    }

    public void losePresents() {
        presents = 0;
    }

    public void gainPresents(int i) {
        presents += i;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Elf elf = (Elf) o;

        return number == elf.number;
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public String toString() {
        return "Elf " + number;
        /*
        return "Elf{" +
                "number=" + number +
                ", presents=" + presents +
                '}';*/
    }
}
