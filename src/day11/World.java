package day11;

import java.util.Arrays;

class World {
    private final Integer[][] map;

    public World(Integer[][] map) {
        this.map = map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        World world = (World) o;

        return Arrays.deepEquals(map, world.map);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(map);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Integer[] r : map) {
            for(Integer c : r) {
                sb.append(c);
                sb.append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public Integer[][] getMap() {
        return map;
    }
}
