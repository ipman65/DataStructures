package dataStructures.lists;

import static java.util.Objects.requireNonNull;

public class MySinglyLinkedList<T> implements SimpleLinkedList<T>{

    private static class Node<T>{

        T data;
        Node<T> next;
        Object k;

        static <T> Node<T> create(T data) {
            return new Node<>(data, null);
        }

        static <T> Node<T> create(T data, Node<T> next){
            return new Node<>(data, next);
        }

        private Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

    }

    private Node<T> head, tail;
    private int size;

    public boolean isEmpty(){
        return head == null;
    }

    public int getSize(){ return size; }

    public void insertFront(T data){
        if(isEmpty()){
            head = tail = Node.create(data);
        }
        else {
            head = Node.create(data, head.next);
        }
        size++;
    }

    public T head(){
        return (isEmpty()) ? null : head.data;
    }

    public T tail(){
        return (isEmpty()) ? null : tail.data;
    }

    public int indexOf(T data){
        if(isEmpty()) return -1;
        // replace preconditions with requireNonNull since it is more readable.
        data = preconditions(data);
        Node<T> node = head;
        // becarefull when calling function in the loop, why not reference size var directly?
        // this function is weird! what is the contract to the user if item is not found? would it return -1 or throw exception?
        // is exception appropriate in this case? If you throw exception, it means that the data is expected to be in the list
        // therefore, if the item does not exist, exception is thrown. Does it make sense in this case
        for(int i = 0; i < getSize(); i++){
            if(node.data.equals(data)) return i;
            node = node.next;
        }
        throw new IllegalArgumentException("Data does not exist in list");
    }

    public void insertTail(T data){
         // again remove precondition funciton here 
        data = preconditions(data);
        // this logic is weird. If it is empty, you insertFront() why should you go on and then check isEmpty again?
        if (isEmpty()) insertFront(data);
        Node<T> node = Node.create(data);
        size++;
        if(isEmpty()){
            head = node;
            tail = head;
        }
        else{
            tail.next = node;
            tail = node;
        }
    }

    public void insertAtPos(T data, int pos){
        data = preconditions(data);
        pos = preconditions(pos);
        // try to avoid multiple if...else if... else since it is not readable.
        // do:
        //    if (...) { 
        //       insertFront(...);
        //       return;
        //     }
        //
        //     if (pos ==...) {
        //        inserTail
        //        return;
        //     }
        //
        //     /// else...
        
        if(pos == 0) insertFront(data);
        else if(pos == getSize()) insertTail(data);
        else {
            // Logic is more readable if you do:
            // while(..) to scan for the position and find the node at pos first
            // then insert.
            Node<T> node = Node.create(data);
            Node<T> temp = head;
            Node<T> n = temp.next;
            for(int i = 0; i <= pos; i++){
                temp = temp.next;
                n = n.next;
            }
            temp.next = node;
            node.next = n;
            size++;
        }
    }

    public void delete(T data){
        if(isEmpty()) throw new IllegalArgumentException("this List is empty");
        data = preconditions(data);
        Node<T> node = head;
        // this logic is insufficient. What happen if you only have one node? also what happen if deleted node is head or tail? 
        while(node != null){
            Node<T> temp = node.next;
            if(temp.data == data){
                node = temp.next;
                return;
            }
            node = node.next;
        }
        throw new IllegalArgumentException("Item to delete not present in this List.");
    }

    public void delete(int pos) {
        pos = preconditions(pos);
        Node<T> current = head.next;
        Node<T> trail = head;
        for (int i = 1; i <= pos; i++) {
            current = current.next;
            trail = trail.next;
        }
        // did you for get to decrement size?
        // also try if list only have 1 element, delete pos = 0 or pos = size-1
        trail = current.next;
    }

    public void print(){
        if (isEmpty()) System.out.println("This is an empty list");
        else{
            Node n = head;
            System.out.print("[ ");
            while(n != null){
                System.out.print(n + ", ");
                n = n.next;
            }
            System.out.print("]");
        }
    }

    public boolean isCircular(){
        return tail.next.equals(head);
    }

    private T preconditions(final T data){
        return requireNonNull(data, "data cannot be null");
    }

    private int preconditions(final int size){
        // what are you checking? what is 'size'? is it position in the list? If it is a list index, then it should be between
        // 0 and size - 1. 
        // I believe this check is wrong.
        if(size < 0 || size > getSize()) throw new IndexOutOfBoundsException("required: pos >= 0 && pos <= " + getSize());
        else return size;
    }


}
