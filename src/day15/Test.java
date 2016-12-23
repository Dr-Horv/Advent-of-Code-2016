package day15;

public class Test {

    public static void main(String[] args) {
        Disc d1 = new Disc(4, 5);
        Disc d2 = new Disc(1, 2);

        System.out.println(d1.passAtTime(1));
        System.out.println(d2.passAtTime(2));
        System.out.println(d1.passAtTime(6));
        System.out.println(d2.passAtTime(7));
    }
}
