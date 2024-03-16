import java.util.Objects;

public class Node {
    private int value;
    private Node next;
    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    public Node getNext(){
        return next;
    }

    public void setNext(Node next){
        this.next = next;
    }

    public int getValue(){
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return value == node.value && Objects.equals(next, node.next);
    }
}

