package day19;

public class Test {

    public static void main(String[] args) {
        DoubleLinkedList<Elf> elfRing = new DoubleLinkedList<>();
        elfRing.addLast(new Elf(1));
        elfRing.addLast(new Elf(2));
        elfRing.addLast(new Elf(3));

        System.out.println(elfRing.getIndex(0).getElement());
        System.out.println(elfRing.getIndex(1).getElement());
        DoubleLinkedList<Elf>.Node<Elf> node = elfRing.getIndex(0);
        DoubleLinkedList<Elf>.Node<Elf> nextOpposite = node.getNext();

        System.out.println(node);
        System.out.println(node.getNext());
        System.out.println(node.getPrev());
        elfRing.removeNode(node);

        node = nextOpposite;

        System.out.println("Removed");
        System.out.println(node);
        System.out.println(node.getNext());
        System.out.println(node.getPrev());

        System.out.println();

        printRing(elfRing);

    }


    private static void printRing(DoubleLinkedList<Elf> elfRing) {
        DoubleLinkedList<Elf>.Node<Elf> first = elfRing.getIndex(0);
        String s = first.getElement().getNumber() + ", ";
        String s2 = first.getPrev().getElement().getNumber() + ", ";

        if(elfRing.size() > 1) {
            DoubleLinkedList<Elf>.Node<Elf> currNode;
            currNode = first.getNext();

            while(!currNode.getElement().equals(first.getElement())) {
                System.out.println(first.getElement().getNumber());
                System.out.println(currNode.getElement().getNumber());
                s += currNode.getElement().getNumber() + ", ";
                currNode = currNode.getNext();
            }

            first = first.getPrev();
            currNode = first.getPrev();
            while(!currNode.getElement().equals(first.getElement())) {
                s2 += currNode.getElement().getNumber() + ", ";
                currNode = currNode.getPrev();
            }
        }

        System.out.println("SIZE: " + elfRing.size() + " | " + s + " | " + s2);
    }
}
