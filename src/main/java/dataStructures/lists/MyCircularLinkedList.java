package dataStructures.lists;

public class MyCircularLinkedList<T> implements SimpleLinkedList<T> {

    private static class Node<T> {

        T data;
        Node<T> next;

        Node(T data, Node<T> next){

            this.data = data;
            this.next = next;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public MyCircularLinkedList(){

        tail.next = head;
    }

    public T head() { return head.data; }
    public T tail() { return tail.data; }
    public int getSize() { return size; }

    public void insertFront(T data){
        Node<T> newNode = new Node<>(data, head);
        tail.next = newNode;
        head = newNode;
    }
    public void insertTail(T data){
       insertFront(data);
    }
    public void delete(T dataToDelete) {
        if (head.data.equals(dataToDelete)){
            tail.next = head.next;
            head = head.next;
        }
        else {
            Node<T> current = head;
            while (current.next != head) {
                Node<T> temp = current.next;
                if (temp.data.equals(dataToDelete)) {
                    current.next = temp.next;
                    if(current.next.data == head.data) tail = current;
                    size--;
                    return;
                }
                current = current.next;
            }
        }
        throw new IllegalArgumentException("Data to delete is not in list");
    }
    public boolean isEmpty(){ return getSize() == 0; }

    public boolean isCircular(){
        return tail.next.equals(head);
    }
}
