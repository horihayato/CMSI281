public class CircularArrayList extends AbstractArrayList implements CircularCollectible {

    public CircularArrayList() {
        super();
    }

    public CircularArrayList(String[] elements) {
        super(elements);
    }

    public static void main (String[] args) {
    	CircularArrayList test = new CircularArrayList();
    	test.add("Yo");
    	test.add("yo");
    	test.remove("Yo");
    	System.out.println(test.size());
    }

    public boolean isEmpty() {
        if (size == 0) {
        	return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void add(String s) {
        if (size < arraySize) {
            elements[size] = s;         
            size++;
        } else {
            arraySize = arraySize * 2;
            String[] doubleElements = new String[arraySize];

            for (int i = 0; i < size; i++) {
                doubleElements[i] = elements[i];
            }

            elements[size] = s;         
            size++;
        }
    }

    public void remove(String s) {       
        for (int i = 0; i < elements.length; i++) {
        	if (elements[i].equals(s)) {
        		break; 
        	}
        }

        for (int j = i; j < elements.length; j++) {
        	elements[j] = elements[j + 1];
    	}
    }	
    
    public String first() {
      return elements[0];
    }

    public CircularIterator iterator() {
        return new CircularArrayListIterator();
    }

    class CircularArrayListIterator implements CircularIterator {
    	int index;
    	String current = "";

        public CircularArrayListIterator() {
            index = 0;
        }
        
        public boolean hasNext() {
            return current != elements[0];
        }

        public String next() {
            if (!hasNext()) throw new UnsupportedOperationException();
            return current = elements[index + 1];
        }

        public void remove() {
            for (int i = index; i < elements.length; i++) {
            	elements[i] = elements[i + 1];
            }
            index--;
            size--;
        }

        public String removeKthElement(int k) {
            String temp = "";
            while (index < size) {
            	if (index == k) {
            		temp = elements[index];
            		for (int i = index; i < elements.length; i++){
            			elements[i] = elements[i + 1];
            		}
            		return temp;
            	}
            	index++;
            }
            return null;
        }

        public boolean oneElementLeft() {
            if (size == 1) {
            	return true;
            }
            return false;
        }
    }

}
