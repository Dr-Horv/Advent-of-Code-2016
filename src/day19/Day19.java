package day19;

import utils.Solver;

import java.util.*;
import java.util.stream.Collectors;

public class Day19 implements Solver {
    @Override
    public String solve() {

        int startingElves = 3004953;

        List<Elf> elves = new ArrayList<>(startingElves);

        for (int i = 0; i < startingElves; i++) {
            elves.add(new Elf(i+1));
        }

        List<Elf> elves1 = partOne(new ArrayList<>(elves));
        List<Elf> elves2 = partTwo(elves);

        return elves1.get(0).toString() + " " + elves2.get(0).toString();


    }

    private List<Elf> partTwo(List<Elf> elves) {

        long timestamp = System.currentTimeMillis();
        DoubleLinkedList<Elf> elfRing = new DoubleLinkedList<>();
        for(Elf e : elves) {
            elfRing.addLast(e);
        }

        DoubleLinkedList<Elf>.Node<Elf> current = elfRing.getIndex(0);
        DoubleLinkedList<Elf>.Node<Elf> opposite = elfRing.getIndex(elfRing.size() / 2);

        while(elfRing.size() > 1) {

            Elf elf = current.getElement();
            Elf second = opposite.getElement();

            elf.gainPresents(second.getPresents());

            DoubleLinkedList<Elf>.Node<Elf> nextOpposite = opposite.getNext();

            if(elfRing.size() % 2 != 0 ) {
                nextOpposite = nextOpposite.getNext();
            }

            elfRing.removeNode(opposite);

            opposite = nextOpposite;
            current = current.getNext();

            long now = System.currentTimeMillis();

            if((now - timestamp) > 10_000 ) {
                timestamp = now;
                System.out.println("Update " + elfRing.size());
            }

        }


        Elf elf = elfRing.getIndex(0).getElement();
        List<Elf> ret = new LinkedList<Elf>();
        ret.add(elf);

        return ret;



    }

    private List<Elf> partOne(List<Elf> elves) {
        while(elves.size() > 1) {
            for (int i = 0; i < elves.size(); i++) {
                Elf curr = elves.get(i);
                if(curr.getPresents() == 0) {
                    continue;
                }

                int nextIndex = (i+1) < elves.size() ? i+1 : 0;
                Elf leftElf = elves.get(nextIndex);
                curr.gainPresents(leftElf.getPresents());
                leftElf.losePresents();
            }

            elves = elves.stream().filter(e -> e.getPresents() > 0).collect(Collectors.toCollection(ArrayList::new));

        }
        return elves;
    }
}


