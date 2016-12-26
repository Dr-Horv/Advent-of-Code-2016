package utils.assembunny;

import java.util.ArrayList;
import java.util.Arrays;

public class Parser {


    public static ArrayList<Instruction> parseInstructions(String[] inputArr) {
        ArrayList<Instruction> instructions = new ArrayList<>();
        for(String line : inputArr) {
            instructions.add(parseInstruction(line));
        }
        return instructions;
    }

    private static Instruction parseInstruction(String line) {
        String[] parts = line.split(" ");
        String[] args = Arrays.copyOfRange(parts, 1, parts.length);
        Instruction.Type t = parseType(parts[0]);
        return new Instruction(t, args);
    }

    private static Instruction.Type parseType(String part) {
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
                return Instruction.Type.TOGGLE;
            case "out":
                return Instruction.Type.OUT;
        }
        return Instruction.Type.NOP;
    }

    public static int getValue(String from, AssemBunny bunny) {
        int value;
        try {
            value = Integer.parseInt(from);
        } catch (NumberFormatException e) {
            value = bunny.getRegistry(from);
        }
        return value;
    }
}
