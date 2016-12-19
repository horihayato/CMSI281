class MaxHeap {

  class Node {
    
    int data;
    Node left;
    Node right;

    public Node(int data) {
      this.data = data;
      this.left = null;
      this.right = null;
    }
  }

  public static Node root;

  public MaxHeap() {
    this.root = null;
  }

  public void insert(int n) {
    Node current = root;
    Node parent = null;

    if (root == null) {
      Node newNode = new Node(n);
      root = newNode;
      return;
    }

    while (current != null) {
      parent = current;
      if (current.data > n) {
        current = current.right;
        if (current == null) {
          Node newNode = new Node(n);
          parent.right = newNode;
          return;
        }
      } else {
        current = current.left;
        if (current == null) {
          Node newNode = new Node(n);
          parent.left = newNode;
          return;
        }
      }
    }
  }

  public boolean search(int n) {
    Node current = root;

    while (current != null) {
      if (current.data == n) {
        return true;
      } else if (current.data < n) {
        current = current.left;
      } else {
        current = current.right;
      }
    }
    return false;
  }

  public boolean delete(int n) {
    Node parent = root;
    Node current = root;
    boolean isLeftChild = false;

    while (current.data != n) {
      parent = current;
      if (current.data < n) {
        isLeftChild = true;
        current = current.left;
      } else {
        isLeftChild = false;
        current = current.right;
      }
      if (current == null) {
        return false;
      }
    }

    // removing a node with no children
    if (current.left == null && current.right == null) {
      if (current == root) {
        root = null;
      }

      if (isLeftChild == true) {
        parent.left = null;
      } else {
        parent.right = null;
      }
    }

    //removing a node with one child
    if (current.right == null) {
      if (current == root) {
        root = current.left;
      } else {
        parent.right = current.right;
      }
    } else {
      if (current == root) {
        root = current.right;
      } else {
        parent.left = current.left;
      }
    }

    // removing a node with two children;
    if (current.left != null && current.right != null) {
      Node successor = findSuccessor(current);
      if (current == root) {
        root = successor;
      } else if (isLeftChild) {
        parent.left = successor;
      } else {
        parent.right = successor;
      }
      successor.left = current.left;
    }
    return true;
  }

  // compares two nodes to see which will take the place of the deleted parent node
  public Node findSuccessor(Node deletedNode) {
    Node parent = null;
    Node successor = null;
    Node current = deletedNode.right;

    while (current != null) {
      parent = successor;
      successor = current;
      current = current.left;
    }

    if (successor != deletedNode.left) {
      parent.left = successor.right;
      successor.right = deletedNode.right;
    }
    return successor;
  }

  public void display(Node root) {
    if (root != null) {
      display(root.left);
      System.out.print(" " + root.data);
      display(root.right);
    }
  }
  
  public static void main(String[] args) {
    MaxHeap heap = new MaxHeap();
    int[] temp = new int[args.length];
    int i = 0;

    for (i = 0; i < args.length; i++) {
      temp[i] = Integer.parseInt(args[i]);
      heap.insert(temp[i]);
    }

    System.out.println("Here is your heap!");
    heap.display(heap.root);
  }

}
