package com.fc;

public class HashSet<K> {
    private int size;
    @SuppressWarnings("unchecked")
    private final Node<K>[] DEFAULT_SET = (Node<K>[]) new Node[16];
    private Node<K>[] set = DEFAULT_SET;

    static class Node<K> {
        private K key;
        private Node<K> next;

        public Node(K key, Node<K> next) {
            this.key = key;
            this.next = next;
        }
    }

    int hash(Object key) {
        int h;
        int a = key == null ? 0 : (h = key.hashCode()) ^ (h >> 16);
        return a & (set.length - 1);
    }

    //添加
    public boolean add(K key) {
        int hashcode = hash(key);
        Node<K> newNode = new Node<>(key, null);
        Node<K> node = set[hashcode];
        if (node == null) {
            set[hashcode] = newNode;
            size++;
            return true;
        }
        Node<K> prev = null;
        while (node != null) {
            if (node.key.equals(key)) {
                System.err.println("键重复");
                return false;
            }
            prev = node;
            node = node.next;
        }
        prev.next = newNode;
        size++;
        return true;
    }

    //删除
    public boolean remove(K key) {
        if (key == null) return false;
        int hashcode = hash(key);
        Node<K> node = set[hashcode];
        if (node == null) {
            return false;
        }
        if (node.key.equals(key)) {
            if (node.next == null) {
                set[hashcode] = null;
            } else {
                set[hashcode] = node.next;
            }
            size--;
            return true;
        }
        Node<K> prev;
        while (node.next != null) {
            prev = node;
            node = node.next;
            if (node.key.equals(key)) {
                prev.next = node.next;
                size--;
                return true;
            }
        }
        return false;
    }

    //清空
    public void clear() {
        for (int i = 0; i < set.length; i++) {
            if (set[i] != null) {
                set[i] = null;
            }
        }
        size = 0;
    }

    //判断是否为空集
    public boolean isEmpty() {
        return size == 0;
    }

    //判断是否包含某个元素
    public boolean contains(Object key) {
        int hashcode = hash(key);
        Node<K> node = set[hashcode];
        if (node == null) {
            return false;
        }
        while (node != null) {
            if (node.key.equals(key)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    //集合转数组
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        for (Node<K> kNode : set) {
            Node<K> node = kNode;
            if (node != null) {
                while (node != null) {
                    array[index++] = node.key;
                    node = node.next;
                }
            }
        }
        return array;
    }

    //取并集
    public boolean addAll(HashSet<K> set) {
        if (set == null || set.isEmpty()) {
            return false;
        }
        int oldSize = size;
        //集合转数组
        Object[] array = set.toArray();
        for (Object o : array) {
            add((K) o);
        }
        return size != oldSize;
    }

    //取交集
    public boolean retainALl(HashSet<K> set) {
        if (set == null || set.isEmpty()) {
            return false;
        }
        int oldSize = size;
        HashSet<K> newSet = new HashSet<>();
        //集合转数组
        Object[] array = set.toArray();

        for (Object o : array) {
            if (contains(o)) {
                newSet.add((K) o);
            }
        }
        this.set = newSet.set;
        this.size = newSet.size;
        return size != oldSize;
    }

    //取差集
    public boolean removeAll(HashSet<K> set) {
        if (set == null || set.isEmpty()) {
            return false;
        }
        int oldSize = size;
        Object[] array = set.toArray();
        for (Object o : array) {
            remove((K) o);
        }
        return size != oldSize;
    }

    //判断是否子集
    boolean containsAll(HashSet<K> set) {
        if (set == null || set.isEmpty() || set.size > size) {
            return false;
        }
        Object[] array = set.toArray();
        for (Object o : array) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }
}
