public class CircularLinkedList extends AbstractLinkedList implements CircularCollectible {

    public CircularLinkedList() {
        super();
    }

    public CircularLinkedList(String[] elements) {
        super(elements);
    }


    public boolean isEmpty() {
        if (first == null) {
            return true;
        }    
        return false;
    }

    public int size() {
        return n; 
    }

    public void add(String s) {
        Node newFirst = new Node(first, s);
        first = newFirst;
        n++;
    }

    public String first() {
        return first.value;
    }

     public void remove(String s) {
        Node current = first;
        Node previous = null; 
        if (first.value.equals(s)) {
        	first = first.next;
        	n--;
        	return;
        }
        while (current != null) {
        	if (current.value.equals(s)) {
        		previous.next = current.next;
        		n--;
        		return;
        	}
        	previous = current;
        	current = current.next;
        }
    }

    public CircularIterator iterator() {
        return new CircularLinkedListIterator();
    }

    class CircularLinkedListIterator implements CircularIterator {
        private Node current;
        private Node previous;

        public CircularLinkedListIterator() {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public String next() {
            if (!hasNext()) throw new UnsupportedOperationException();
            current = current.next;
            return current.value;
        }

       	public void remove() {
       		previous.next = current.next;
       		current = current.next;
       		n--;
       	}

        public String removeKthElement(int k) {
        	int num = 0;
        	String temp = "";

        	while (num != k) {
        		previous = current;
        		current = current.next;
        		num++;
        	}
        	if (num == k) {
        		temp = current.value;
        		previous.next = current.next;
        	}
        	n--;
        	return temp;
        }

        public boolean oneElementLeft() {
            if (n == 1) {
            	return true;
            }
            return false;
        }
    }
}

