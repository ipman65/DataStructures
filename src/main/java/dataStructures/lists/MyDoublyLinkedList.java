package dataStructures.lists;
import static java.util.Objects.requireNonNull;

public class MyDoublyLinkedList<T> implements SimpleLinkedList<T> {

    private static class Node<T>{
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T data, Node<T> next, Node<T> prev) {
            this.data = requireNonNull(data);
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<T> head, tail;
    private int size = 0;

    public T head() { return head.data; }
    public T tail() { return tail.data; }
    public int getSize(){ return size; }

    public void insertFront(T data){
        if(isEmpty()) head = tail = new Node<>(data, null, null);
        else {
            // Can you do better than this
            head.prev = new Node<>(data, head, null);
            head = head.prev;
        }
        size++;
    }
    public void insertTail(T data){
        if(isEmpty()) head = tail = new Node<>(data, null, null);
        else{
            tail = tail.next = new Node<>(data, null, tail);
        }
        size++;
    }

    public void insertAtPos(T data, int pos){
        if(pos > getSize()) throw new IllegalArgumentException("position is greater than list size");
        else if(pos == getSize()) insertTail(data);
        else if(pos == 0) insertFront(data);
        else {
            Node<T> newNode = new Node<>(data, head, null);
            for(int i = 0; i < pos; i++){
                newNode = newNode.next;
            }
            newNode.next.prev = newNode = newNode.prev.next;
        }
        size++;
    }

    public void delete(T dataToDelete){
        Node<T> traverse = head;
        while(traverse != null){
            if(traverse.data.equals(dataToDelete)){
                traverse.next.prev = traverse.prev;
                traverse.prev.next = traverse.next;
                if(traverse.next.data == null) tail = traverse.next;
                size--;
                return;
            }
        }
        throw new IllegalArgumentException("Data to delete is not in list");
    }

    public boolean isEmpty(){
        return getSize() == 0;
    }

    public boolean isCircular(){
        return head.prev.equals(tail) && tail.next.equals(head);
    }
}
