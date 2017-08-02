package dataStructures.Trees;

public class MyBinarySearchTree<T extends Comparable<T>>{

    private static class TreeNode<T extends Comparable<T>>{
        T data;
        TreeNode<T> leftChild;
        TreeNode<T> rightChild;

        private TreeNode(T data, TreeNode<T> leftChild, TreeNode<T> rightChild) {
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        static <T extends Comparable<T>> TreeNode<T> create(T data){
            return new TreeNode<>(data, null, null);
        }

        static <T extends Comparable<T>> TreeNode<T> create(T data, TreeNode<T> left, TreeNode<T> right){
            return new TreeNode<>(data, left, right);
        }
    }

    private TreeNode<T> root;

    public void insert(T data) {
        if(isEmpty()) root = TreeNode.create(data);
        else{}
    }

    public void delete(T data) {

    }

    public void display() {

    }

    public T find(T data, TreeNode<T> current) {
        if(current.data.equals(data)) return data;
        if(current.data.compareTo(data) < 0) return find(data, current.leftChild);
        else return find(data, current.rightChild);
    }

    public int getHeight() {
        return 0;
    }

    public boolean isEmpty() {
        return root == null;
    }

}
