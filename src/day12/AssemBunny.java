package day12;

import java.util.HashMap;

public class AssemBunny {
    private HashMap<String, Integer> registers;

    public AssemBunny() {
        registers = new HashMap<String, Integer>() {{
            put("a", 0);
            put("b", 0);
            put("c", 1);
            put("d", 0);
        }};
    }

    public void incRegistry(String registry) {
        Integer value = registers.get(registry);
        value++;
        registers.put(registry, value);
    }

    public void decRegistry(String registry) {
        Integer value = registers.get(registry);
        value--;
        registers.put(registry, value);
    }

    public int getRegistry(String registry) {
        return registers.get(registry);
    }

    public void copy(String source, String to) {
        int value;
        try {
            value = Integer.parseInt(source);
        } catch (NumberFormatException e) {
            value = registers.get(source);
        }

        registers.put(to, value);
    }




}
