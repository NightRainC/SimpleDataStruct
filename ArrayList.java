package com.fc;

public class ArrayList<T> {
    private int size;
    private int total;
    private Object[] data;

    public ArrayList() {
        data = new Object[2];
        total = 2;
    }

    public ArrayList(ArrayList<T> list) {
        this.size = list.size;
        this.total = list.total;
        this.data = list.data;
    }

    private void grow() {
        Object[] newArr = new Object[total + (total >> 1)];
        System.arraycopy(data, 0, newArr, 0, size);
        data = newArr;
        total = data.length;
    }

    public void add(T o) {
        insert(size, o);
    }

    public boolean insert(int index, T o) {
        if (index < 0 || index >= total) {
            System.err.println("插入失败!");
            return false;
        }
        if (size + 1 >= total) {
            grow();
        }

        if (size != 0) {
            int temp = index <= size ? size : total;
            System.arraycopy(data, index, data, index + 1, temp - index);
        }
        data[index] = o;
        size++;
        return true;
    }


    private void trim() {
        int k;
        for (int i = 0; i < size; i++) {
            k = i + 1;
            if (data[i] == null && i != size - 1) {
                while (data[k] == null && k != total - 1) {
                    k++;
                }
                data[i] = data[k];
                data[k] = null;
            }
            if (i == size - 1 && data[i] == null) {
                while (data[k] == null && k != total - 1) {
                    k++;
                }
                data[i] = data[k];
                data[k] = null;
            }
        }
    }

    public void removeAll(Object o) {
        if (o != null) {
            for (int i = 0; i < total; i++) {
                if (o.equals(data[i])) {
                    data[i] = null;
                    size--;
                }
            }
        }
        trim();
    }

    public void removeFirst(Object o) {
        if (o != null) {
            for (int i = 0; i < size; i++) {
                if (o.equals(data[i])) {
                    data[i] = null;
                    size--;
                    if (i + 1 == total) return;
                    System.arraycopy(data, i + 1, data, i, size - i);
                    return;
                }
            }
        }
    }

    public void removeLast(Object o) {
        if (o != null) {
            int flag = total - 1;
            for (int i = flag; i >= 0; --i) {
                if (o.equals(data[i])) {
                    data[i] = null;
                    size--;
                    if (i == flag)
                        return;
                    System.arraycopy(data, i + 1, data, i, size - i);
                    return;
                }
            }
        }
    }

    public boolean delete(int index) {
        if (index < 0 || index >= total) {
            System.err.println("下标异常");
            return false;
        }
        data[index] = null;
        size--;
        if (index + 1 != total) {
            System.arraycopy(data, index + 1, data, index, total - index);
        }
        return true;
    }

    public void set(int index, Object o) {
        data[index] = o;
    }

    public T get(int index) {
        if (index < 0 || index >= total) {
            throw new RuntimeException("下标越界");
        }
        return (T) data[index];
    }

    public void trimCapacity() {
        trim();
        Object[] tem = new Object[size];
        System.arraycopy(data, 0, tem, 0, size);
        data = tem;
        total = size;
    }

    public int indexOf(T o) {
        for (int i = 0; i < total; i++) {
            if (data[i] != null) {
                if (o.equals(data[i]))
                    return i;
            }
            if (data[i] == null && o == null) return i;
        }
        return -1;
    }

    public int lastIndexOf(T o) {
        for (int i = total - 1; i >= 0; i--) {
            if (data[i] != null) {
                if (o.equals(data[i]))
                    return i;
            }
            if (data[i] == null && o == null) return i;
        }
        return -1;
    }

    public int size() {
        return size;
    }

    public int getTotal() {
        return total;
    }

}
