package day25;

import utils.FileReader;
import utils.Solver;
import utils.assembunny.AssemBunny;
import utils.assembunny.Instruction;
import utils.assembunny.Parser;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Day25 implements Solver {


    @Override
    public String solve() {

        Stream<String> input = FileReader.readFile("day25/input");
        String[] inputArr = input.toArray(String[]::new);
        int threshold = 10_000;

        ArrayList<Instruction> instructions = Parser.parseInstructions(inputArr);

        ArrayList<Integer> output;
        int outputSize = 0;
        int current = 0;
        while(true) {
            AssemBunny bunny = new AssemBunny(current);
            output = new ArrayList<>();
            outputSize = 0;
            int index = 0;
            while(index < instructions.size()) {
                index = performInstruction(instructions, output, bunny, index);
                if(outputSize != output.size()) {
                    outputSize = output.size();
                    if (isValidOutput(output) )  {
                        if(output.size() > threshold) {
                            return current + "";
                        }
                    } else {
                        break;
                    }

                }
            }
            current++;
        }
    }

    private boolean isValidOutput(ArrayList<Integer> output) {
        if(output.size() < 2) {
            return true;
        }

        int even = output.get(0);
        int odd = output.get(1);

        if(! ((even == 0 && odd == 1) || (even == 1 || odd == 0))) {
            return false;
        }

        for (int i = 0; i < output.size(); i++) {
            Integer curr = output.get(i);
            if(i % 2 == 0) {
                if(curr != even) {
                    return false;
                }
            } else {
                if(curr != odd) {
                    return false;
                }
            }
        }

        return true;
    }

    private int performInstruction(ArrayList<Instruction> instructions, ArrayList<Integer> output, AssemBunny bunny, int index) {
        Instruction instruction = instructions.get(index);
        String[] args = instruction.getArgs();
        int dIndex = 1;
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
            case OUT:
                output.add(Parser.getValue(args[0], bunny));
            case TOGGLE:
                break;
            case NOP:
                break;
        }
        index += dIndex;
        return index;
    }


    private int doJump(String from, String dIndex, AssemBunny bunny) {
        int value = Parser.getValue(from, bunny);
        if(value != 0) {
            return Parser.getValue(dIndex, bunny);
        } else {
            return 1;
        }
    }
}
