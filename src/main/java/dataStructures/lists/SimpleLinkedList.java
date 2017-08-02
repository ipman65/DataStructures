package dataStructures.lists;

public interface SimpleLinkedList<T> {
    T head();
    T tail();
    int getSize();
    void insertFront(T data);
    void insertTail(T data);
    void delete(T data);
    boolean isEmpty();
    boolean isCircular();
}