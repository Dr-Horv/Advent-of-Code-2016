package utils.assembunny;

import java.util.Arrays;

public class Instruction {

    private Type type;
    private final String[] args;
    private static int nextIndex = 0;
    private final int index;


    public Instruction(Type t, String[] arguments) {
        type = t;
        args = arguments;
        index = nextIndex;
        nextIndex++;
    }

    public Type getType() {
        return type;
    }

    public String[] getArgs() {
        return args;
    }

    public void toggle() {
        switch (type) {
            case DEC:
            case TOGGLE:
                type = Type.INC;
                break;
            case INC:
                type = Type.DEC;
                break;
            case JUMP:
                type = Type.COPY;
                break;
            case COPY:
                type = Type.JUMP;
        }
    }

    public enum Type {
        COPY,
        INC,
        DEC,
        JUMP,
        TOGGLE,
        OUT,
        NOP
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "index=" + index +
                ", type=" + type +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
