package day19;

public class DoubleLinkedList<E> {

    class Node<T extends E> {
        private T element;
        private Node<T> next;
        private Node<T> prev;

        public T getElement() {
            return element;
        }

        public Node<T> getNext() {
            return next;
        }

        public Node<T> getPrev() {
            return prev;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    ", next=" + next.element.toString() +
                    ", prev=" + prev.element.toString() +
                    '}';
        }
    }

    private Node<E> start;
    private int size = 0;

    DoubleLinkedList() {};

    public void removeNode(Node<E> n) {
        Node<E> prev = n.prev;
        Node<E> next = n.next;

        if(start.element.equals(n.getElement())) {
            start = start.next;
        }

        prev.next = next;
        next.prev = prev;
        size--;
    }

    public void addFirst(E elem) {
        Node<E> n = new Node<>();
        n.element = elem;

        if(size == 0) {
            start = n;
            start.next = n;
            start.prev = n;
        } else {
            Node<E> oldStart = start;
            Node<E> last = oldStart.prev;

            start = n;

            start.next = oldStart;
            oldStart.prev = start;

            start.prev = last;
            last.next = start;
        }
        size++;
    }

    public int size() {
        return size;
    }

    public void addLast(E elem) {
        Node<E> n = new Node<>();
        n.element = elem;

        if(size == 0) {
            n.next = n;
            n.prev = n;
            start = n;
        } else {
            Node<E> oldLast = start.prev;

            n.next = start;
            start.prev = n;
            n.prev = oldLast;
            oldLast.next = n;

        }
        size++;
    }

    public Node<E> getIndex(int i) {
        if (i >= size) {
            return null;
        }
        Node<E> n = start;
        boolean startInBack = i > size/2;
        int steps = 0;
        int index = startInBack ? size-1 : 0;
        while(index != i) {
            if(startInBack) {
                n = n.prev;
                index--;
            } else {
                n = n.next;
                index++;
            }
            steps++;
            if(steps == size) {
                return null;
            }
        }

        return n;
    }

    public Node<E> getNodeWithElement(E elem) {
        Node<E> n = start;
        int steps = 0;
        while(!n.element.equals(elem)) {
            n = n.next;
            steps++;
            if(steps == size) {
                return null;
            }
        }
        return n;
    }

}
