package day23;

import utils.FileReader;
import utils.Solver;
import utils.assembunny.AssemBunny;
import utils.assembunny.Instruction;
import utils.assembunny.Parser;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Day23 implements Solver {
    @Override
    public String solve() {
        Stream<String> input = FileReader.readFile("day23/input");
        String[] inputArr = input.toArray(String[]::new);

        AssemBunny bunny = new AssemBunny();

        ArrayList<Instruction> instructions = Parser.parseInstructions(inputArr);

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
        int deltaIndex = Parser.getValue(arg, bunny);
        int value = index + deltaIndex;
        if(value >= instructions.size() || value < 0) {
            return;
        }
        Instruction instruction = instructions.get(value);
        instruction.toggle();
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
