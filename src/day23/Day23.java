package day23;

import utils.FileReader;
import utils.Solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class Day23 implements Solver {
    @Override
    public String solve() {
        Stream<String> input = FileReader.readFile("day23/input");
        String[] inputArr = input.toArray(String[]::new);

        AssemBunny bunny = new AssemBunny();

        ArrayList<Instruction> instructions = parseInstructions(inputArr);

        //instructions.stream().forEach(System.out::println);
        //System.out.println();

        int index = 0;
        while(index < instructions.size()) {
            Instruction instruction = instructions.get(index);
            String[] args = instruction.getArgs();
            int dIndex = 1;

            //System.out.println(instruction);

            switch (instruction.getType()) {
                case COPY:
                    bunny.copy(args[0], args[1]);
                    break;
                case INC:
                    bunny.incRegistry(args[0]);
                    break;
                case DEC:
                    bunny.decRegistry(args[0]);
                    break;
                case JUMP:
                    dIndex = doJump(args[0], args[1], bunny);
                    break;
                case TOGGLE:
                    doToggle(index, args[0], bunny, instructions);
                    break;
                case NOP:
                    break;
            }
            index += dIndex;

        }



        return "" + bunny.getRegistry("a");



    }

    private void doToggle(int index, String arg, AssemBunny bunny, ArrayList<Instruction> instructions) {
        int deltaIndex = getValue(arg, bunny);
        int value = index + deltaIndex;
        if(value >= instructions.size() || value < 0) {
            return;
        }
        Instruction instruction = instructions.get(value);
        instruction.toggle();
    }

    private int doJump(String from, String dIndex, AssemBunny bunny) {
        int value = getValue(from, bunny);
        if(value != 0) {
            return getValue(dIndex, bunny);
        } else {
            return 1;
        }
    }

    private int getValue(String from, AssemBunny bunny) {
        int value;
        try {
            value = Integer.parseInt(from);
        } catch (NumberFormatException e) {
            value = bunny.getRegistry(from);
        }
        return value;
    }

    private ArrayList<Instruction> parseInstructions(String[] inputArr) {
        ArrayList<Instruction> instructions = new ArrayList<>();
        for(String line : inputArr) {
            instructions.add(parseInstruction(line));
        }
        return instructions;
    }

    private Instruction parseInstruction(String line) {
        String[] parts = line.split(" ");
        String[] args = Arrays.copyOfRange(parts, 1, parts.length);
        Instruction.Type t = parseType(parts[0]);
        return new Instruction(t, args);
    }

    private Instruction.Type parseType(String part) {
        switch (part) {
            case "cpy":
                return Instruction.Type.COPY;
            case "inc":
                return Instruction.Type.INC;
            case "dec":
                return Instruction.Type.DEC;
            case "jnz":
                return Instruction.Type.JUMP;
            case "tgl":
                return Instruction.Type.TOGGLE
                        ;
        }
        return Instruction.Type.NOP;
    }
}
