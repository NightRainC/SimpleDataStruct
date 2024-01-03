package com.fc;

public class Stack<T> {
    private int size;
    private Node<T> top;

    private static class Node<T> {
        T data;
        Node<T> next;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(T data) {
        Node<T> oldNode = top;
        Node<T> newNode = new Node<>();
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
        Node<T> node = top;
        top = top.next;
        node.next = null;
        size--;
        return node.data;
    }

    public int size() {
        return size;
    }

}
