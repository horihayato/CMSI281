public abstract class AbstractLinkedList implements Collectible {
    Node head;
    Node tail;
    Node first;
    int n;

    public AbstractLinkedList() {
      head = null;
      tail = null;
      first = null;
      n = 0;
    }
    
    public AbstractLinkedList(String[] elements) {
        for (String s : elements) {
            if (s != null) {
                // take advantage of your own add() method
                // to make this constructor
                add(s);
            }
        }
    }

    public void add(String s) {
        Node newFirst = new Node(first, s);
        first = newFirst;
        n++;
    }


    class Node {
        Node next;
        String value;

        public Node(Node next, String value) {
            this.next = next;
            this.value = value;
        }
    }

}
