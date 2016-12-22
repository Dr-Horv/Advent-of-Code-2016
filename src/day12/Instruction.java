package day12;

import java.util.Arrays;

public class Instruction {

    private final Type type;
    private final String[] args;

    public Instruction(Type t, String[] arguments) {
        type = t;
        args = arguments;
    }

    public Type getType() {
        return type;
    }

    public String[] getArgs() {
        return args;
    }

    enum Type {
        COPY,
        INC,
        DEC,
        JUMP,
        NOP
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "type=" + type +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
