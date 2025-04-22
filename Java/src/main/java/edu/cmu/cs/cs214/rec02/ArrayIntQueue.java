package edu.cmu.cs.cs214.rec02;

/**
 * A resizable-array implementation of the {@link IntQueue} interface. The head of
 * the queue starts out at the head of the array, allowing the queue to grow and
 * shrink in constant time.
 */
public class ArrayIntQueue implements IntQueue {

    /**
     * An array holding this queue's data
     */
    private int[] elementData;

    /**
     * Index of the next dequeue-able value
     */
    private int head;

    /**
     * Current size of queue
     */
    private int size;

    /**
     * The initial size for new instances of ArrayQueue
     */
    private static final int INITIAL_SIZE = 10;

    /**
     * Constructs an empty queue with an initial capacity of ten.
     */
    public ArrayIntQueue() {
        elementData = new int[INITIAL_SIZE];
        head = 0;
        size = 0;
    }

    /** {@inheritDoc} */
    public void clear() {
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                elementData[(head + i) % elementData.length] = 0;
            }
            size = 0;
            head = 0;
        }
    }

    /** {@inheritDoc} */
    public Integer dequeue() {
        if (isEmpty()) {
            return null;
        }
        // Bug Fix #2: Convert to Integer explicitly
        Integer value = elementData[head];
        elementData[head] = 0; // Optional: helps with garbage collection
        head = (head + 1) % elementData.length;
        size--;
        return value;
    }

    public boolean enqueue(Integer value) {
        // Bug Fix #3: Null check
        if (value == null) {
            throw new IllegalArgumentException("Null values are not allowed in queue");
        }

        ensureCapacity();
        int tail = (head + size) % elementData.length;
        elementData[tail] = value;
        size++;
        return true;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Integer peek() {
        return isEmpty() ? null : elementData[head];
    }

    /** {@inheritDoc} */
    public int size() {
        return size;
    }
   
    
    /**
     * Increases or decreases the capacity of this <tt>ArrayIntQueue</tt> instance, if
     * necessary, to ensure that it can hold at least size + 1 elements. 
     * The queue shrinks when it becomes too sparse to optimize memory usage.
     */
    private void ensureCapacity() {
        if (size == elementData.length) {
            resize(elementData.length * 2 + 1);
        } else if (size <= elementData.length / 4 && elementData.length > INITIAL_SIZE) {
            resize(elementData.length / 2);
        }
    }

    /**
     * Resizes the internal array to the specified new capacity.
     */
    private void resize(int newCapacity) {
        int[] newData = new int[newCapacity];
        // Copy the elements into the new array, adjusting for the circular nature
        for (int i = 0; i < size; i++) {
            newData[i] = elementData[(head + i) % elementData.length];
        }
        elementData = newData;
        head = 0; // Reset head to 0 since the new array is a flat copy
    }
    
}

