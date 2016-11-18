import java.util.NoSuchElementException;

public class CircularLinkedList extends AbstractLinkedList implements CircularCollectible {

    public CircularLinkedList() {
        super();
    }

    public CircularLinkedList(String[] elements) {
        super(elements);
    }


    public boolean isEmpty() {
        if (head == null) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return n;
    }

    public void add(String s) {
        Node newNode = new Node(null, s);
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        n++;
    }

    public void remove() {
        if (head == null) {
            return;
        }

        Node dequeuedNode = head;
        head = head.next;
        n--;

        if (head == null) {
            tail = null;
        }
    }

    public String first() {
        return head.value;
    }

    public CircularIterator iterator() {
        return new CircularLinkedListIterator();
    }

    class CircularLinkedListIterator implements CircularIterator {
        private Node current;
        
        public CircularLinkedListIterator() {
            current = head;
        }

        public boolean hasNext() {
            return current != null;
        }

        public String next() {
            if (!hasNext()) throw new NoSuchElementException();
            Node node = current;
            current = current.next;
            return node.value;
        }

        public void remove() {
            if (head == null) {
                return;
            }

            Node dequeuedNode = head;
            head = head.next;
            n--;

            if (head == null) {
                tail = null;
            }
        }

        public String removeKthElement(int k) {
            int count = 1;
            String temp = "";
            Node link = new Node(null, null);
            tail.next = head;

            while (count != k) {
                if (n == 1) {
                    temp = head.value;
                    break;
                }
                link = current;
                current = current.next;
                count++;
            }
            // Node is not being removed from either end
            if (count == k && current != head && current != tail) {
                temp = current.value;
                current = current.next;
                link.next = current;
                count = 1;
                n--;
            }
            // Node removed from head
            if (count == k && current == head) {
                temp = head.value;
                current = head.next;
                head = current;
                tail.next = head;
                link.next = current;
                count = 1;
                n--;
            }
            // Node removed from tail
            if (count == k && current == tail) {
                temp = current.value;
                current = current.next;
                link.next = current;
                count = 1;
                n--;
            }
            return temp;
        }

        public boolean oneElementLeft() {
            if (head.next == tail.next) {
                return true;
            } else {
                return false;
            }
        }
    }
}
