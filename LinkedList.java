package com.fc;

public class LinkedList<T> {
    private int size;
    private Node firstNode;
    private Node lastNode;

    private class Node {
        private T data;
        private Node prev;
        private Node next;
    }

    public int size() {
        return size;
    }

    public void pushFirst(T val) {
        Node newNode = new Node();
        newNode.data = val;
        Node node = lastNode;
        if (node == null) {
            lastNode = newNode;
            firstNode = newNode;
            size++;
            return;
        }
        newNode.prev = node;
        node.next = newNode;
        lastNode = newNode;
        size++;
    }

    public void pushLast(T val) {
        Node newNode = new Node();
        newNode.data = val;
        Node node = lastNode;
        if (node == null) {
            lastNode = newNode;
            firstNode = newNode;
            size++;
            return;
        }
        node.next = newNode;
        newNode.prev = node;
        lastNode = newNode;
        size++;
    }

    public void add(T val) {
        pushFirst(val);
    }

    public boolean isEmpty() {
        return size == 0;
    }


    private Node getNode(int index) {
        if (index < 1 || index > size) {
            throw new RuntimeException("下标越界[1,size]");
        }
        Node node;
        if (index > size >> 1) {
            node = lastNode;
            for (int i = size; i > index; i--) {
                node = node.prev;
            }
        } else {
            node = firstNode;
            for (int i = 1; i < index; i++) {
                node = node.next;
            }
        }
        return node;
    }

    public T get(int index) {
        Node node = getNode(index);
        if (node == null || node.data == null) return null;
        return node.data;
    }


    public boolean insert(int index, T val) {
        Node node;
        try {
            node = getNode(index);
        } catch (Exception e) {
            System.err.println("插入失败！范围[1,size]");
            return false;
        }
        if (node == null) {
            pushFirst(val);
            return true;
        }
        Node newNode = new Node();
        newNode.data = val;
        if (node.prev == null) {
            node.prev = newNode;
            newNode.next = node;
            firstNode = newNode;
            size++;
            return true;
        }
        Node prev = node.prev;
        prev.next = newNode;
        newNode.next = node;
        newNode.prev = prev;
        node.prev = newNode;
        size++;
        return true;
    }

    public boolean delete(int index) {
        Node node = getNode(index);
        if (node == null) return true;
        Node prev = node.prev;
        Node next = node.next;
        if (prev == null && next == null) {
            lastNode = null;
            firstNode = null;
        } else if (prev == null) {
            next.prev = null;
            firstNode = next;
        } else if (next == null) {
            prev.next = null;
            lastNode = prev;
        } else {
            prev.next = next;
            next.prev = prev;
        }
        size--;
        return true;
    }

    public T peekLast() {
        if (lastNode == null) return null;
        return lastNode.data;
    }

    public T peekFirst() {
        if (firstNode == null) return null;
        return firstNode.data;
    }

    public T pollFirst() {
        Node node = firstNode;
        if (node == null) return null;
        delete(1);
        return node.data;
    }

    public T pollLast() {
        Node node = lastNode;
        if (node == null) return null;
        delete(size);
        return node.data;
    }


}
