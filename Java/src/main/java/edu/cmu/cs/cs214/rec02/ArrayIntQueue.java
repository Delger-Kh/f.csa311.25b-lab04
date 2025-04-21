package edu.cmu.cs.cs214.rec02;

import java.util.Arrays;

/**
 * A resizable-array implementation of the {@link IntQueue} interface. The head of
 * the queue starts out at the head of the array, allowing the queue to grow and
 * shrink in constant time.
 */
public class ArrayIntQueue implements IntQueue {

    private int[] elementData;
    private int head;
    private int size;

    private static final int INITIAL_SIZE = 10;

    public ArrayIntQueue() {
        elementData = new int[INITIAL_SIZE];
        head = 0;
        size = 0;
    }

    /** {@inheritDoc} */
    public void clear() {
        Arrays.fill(elementData, 0);
        size = 0;
        head = 0;
    }

    /** {@inheritDoc} */
    public Integer dequeue() {
        if (isEmpty()) {
            return null;
        }
        Integer value = elementData[head];
        head = (head + 1) % elementData.length;
        size--;
        return value;
    }

    /** {@inheritDoc} */
    public boolean enqueue(Integer value) {
        ensureCapacity();
        int tail = (head + size) % elementData.length;
        elementData[tail] = value;
        size++;
        return true;
    }

    /** ✅ FIXED: should return true only when empty */
    public boolean isEmpty() {
        return size == 0;
    }

    /** ✅ FIXED: avoid peeking when empty */
    public Integer peek() {
        return isEmpty() ? null : elementData[head];
    }

    /** {@inheritDoc} */
    public int size() {
        return size;
    }

    /** ✅ FIXED: correct queue order when expanding */
    private void ensureCapacity() {
        if (size == elementData.length) {
            int oldCapacity = elementData.length;
            int newCapacity = 2 * oldCapacity + 1;
            int[] newData = new int[newCapacity];
            for (int i = 0; i < size; i++) {
                newData[i] = elementData[(head + i) % oldCapacity];
            }
            elementData = newData;
            head = 0;
        }
    }
}
