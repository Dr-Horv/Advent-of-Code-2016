import day10.Day10;
import day11.Day11;
import day12.Day12;

public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        //System.out.println(new Day1().solve());
        //System.out.println(new Day2(false).solve());
        //System.out.println(new Day3().solve());
        //System.out.println(new Day4().solve());
        //System.out.println(new Day5().solve());
        //System.out.println(new Day6().solve());
        //System.out.println(new Day7().solve());
        //System.out.println(new Day8().solve());
        //System.out.println(new Day9().solve());
        //System.out.println(new Day10().solve());
        System.out.println(new Day11().solve());
        //System.out.println(new Day12().solve());

        long now = System.currentTimeMillis();

        System.out.println( ((now - start)/1000.0) );

    }

}
