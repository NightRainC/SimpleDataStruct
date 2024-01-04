package com.fc;

public class HashTable<K, V> {
    private final Node<K, V>[] DEFAULT_TABLE = (Node<K, V>[]) new Node[16];
    private Node<K, V>[] table = DEFAULT_TABLE;
    private ArrayList<Node<K, V>> entry;
    private int size;

    class Node<K, V> {
        final int hashcode;
        K key;
        V val;
        Node<K, V> next;

        public Node(K key, V val, Node<K, V> next) {
            this.key = key;
            this.val = val;
            this.next = next;
            this.hashcode = hash(key);
        }
    }

    //计算hashcode
    int hash(Object key) {
        int h;
        return key == null ? 0 : (h = key.hashCode()) ^ (h >> 16);
    }

    //计算下标位置
    int indexFor(int h, int length) {
        return h & (length - 1);
    }


    /**
     * 添加键值对
     *
     * @param key 键
     * @param val 新值
     * @return被替换的旧值
     */
    V put(K key, V val) {
        //根据键的哈希码获取在数组中的下标位置
        int index = indexFor(hash(key), table.length);
        //准备新的节点
        Node<K, V> newNode = new Node<>(key, val, null);
        //拿到节点
        Node<K, V> node = table[index];
        //无hash冲突
        if (node == null) {
            table[index] = newNode;
            size++;
            return null;
        }
        Node<K, V> prev = null;
        while (node != null) {
            //判断key是否重复
            if (node.key.equals(key)) {
                V oldVal = node.val;
                node.val = val;
                return oldVal;
            }
            prev = node;
            node = node.next;
        }
        prev.next = newNode;
        size++;
        return null;
    }

    /**
     * 根据key删除指定的值
     *
     * @param key 键
     * @return被删除的旧值
     */
    V remove(Object key) {
        int index = indexFor(hash(key), table.length);
        Node<K, V> node = table[index];
        if (node == null) {
            return null;
        }

        if (node.key.equals(key)) {
            if (node.next == null) {
                table[index] = null;
            } else {
                table[index] = node.next;
            }
            size--;
            return node.val;
        }
        //如果发生hash冲突
        Node<K, V> prev;
        while (node.next != null) {
            prev = node;
            node = node.next;
            //找到被删除的节点
            if (node.key.equals(key)) {
                prev.next = node.next;
                //获取旧数据
                V oldVal = node.val;
                size--;
                return oldVal;
            }
        }
        return null;
    }

    /**
     * 根据键获取值
     *
     * @param key 键
     * @return key对应的值
     */
    V get(Object key) {
        int index = indexFor(hash(key), table.length);
        Node<K, V> node = table[index];
        if (node == null) return null;

        //发生hash冲突
        while (node != null) {
            if (node.key.equals(key)) {
                return node.val;
            }
            node = node.next;
        }
        return null;
    }

    /**
     * @return list 存放完成的容器
     */
    public ArrayList<Node<K, V>> getEntry() {
        if (size == 0) return null;
        entry = new ArrayList<>(size);
        Node<K, V> node;
        for (int i = 0, k = 0; k < table.length && i < size; k++) {
            node = table[k];
            while (node != null) {
                entry.add( node);
                i++;
                node = node.next;
            }
        }
        return this.entry;
    }



    /**
     * @return 节点个数
     */
    public int size() {
        return size;
    }


}
