package day14;

public class Test {
    public static void main(String[] args) {
        System.out.println(Day14.hashContainsSameCharacterXTimes("12345", 5));
        System.out.println(Day14.hashContainsSameCharacterXTimes("abbefffffebb", 5));
        System.out.println(Day14.hashContainsSameCharacterXTimes("abbbe", 3));
        System.out.println(Day14.hashContainsSameCharacterXTimes("abc", 3));

        System.out.println(Day14.hashContainsSpecificCharacterXTimes("abbbe", 3, 'b'));
        System.out.println(Day14.hashContainsSpecificCharacterXTimes("abbbe", 3, 'a'));

    }
}
