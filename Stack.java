package com.fc;

public class Stack<T> {
    private int size;
    private Node top;

    private  class Node {
        private T data;
        private Node next;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(T data) {
        Node oldNode = top;
        Node newNode = new Node();
        newNode.next = oldNode;
        newNode.data = data;
        top = newNode;
        size++;
    }

    public T peek() {
        if (top == null) return null;
        return top.data;
    }

    public T pop() {
        if (top == null) return null;
        Node node = top;
        top = top.next;
        node.next = null;
        size--;
        return node.data;
    }

    public int size() {
        return size;
    }

}
