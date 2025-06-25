 class Node1 {
    String data;
    Node1 next;

    public Node1(String data) {
        this.data = data;
        this.next = null;
    }
}

public class Stack {

    private Node1 top;
    private int size;

    public Stack() {
        this.top = null;
        this.size = 0;
    }

    public void push(String data) {
        Node1 newNode = new Node1(data);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public void top() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        System.out.println("Element at the top is " + top.data);
    }

    public String pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return "-1";
        }
        String poppedData = top.data;
        top = top.next;
        size--;
        return poppedData;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int getSize() {
        return size;
    }
}
