package day06;

public class LetterCount {

    private int count;
    private final char letter;

    public LetterCount(int count, char letter) {
        this.count = count;
        this.letter = letter;
    }

    public int getCount() {
        return count;
    }

    public char getLetter() {
        return letter;
    }

    public void incCount() {
        count++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LetterCount that = (LetterCount) o;

        return letter == that.letter;

    }

    @Override
    public int hashCode() {
        return (int) letter;
    }
}
