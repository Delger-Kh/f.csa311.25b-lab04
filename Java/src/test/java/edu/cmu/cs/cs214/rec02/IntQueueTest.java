package edu.cmu.cs.cs214.rec02;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


/**
 * TODO: 
 * 1. The {@link LinkedIntQueue} has no bugs. We've provided you with some example test cases.
 * Write your own unit tests to test against IntQueue interface with specification testing method 
 * using mQueue = new LinkedIntQueue();
 * 
 * 2. 
 * Comment `mQueue = new LinkedIntQueue();` and uncomment `mQueue = new ArrayIntQueue();`
 * Use your test cases from part 1 to test ArrayIntQueue and find bugs in the {@link ArrayIntQueue} class
 * Write more unit tests to test the implementation of ArrayIntQueue, with structural testing method
 * Aim to achieve 100% line coverage for ArrayIntQueue
 *
 * @author Alex Lockwood, George Guo, Terry Li
 */




public class IntQueueTest {

    private IntQueue mQueue;
    private List<Integer> testList;

    /**
     * Called before each test.
     */
    @Before
    public void setUp() {
        // comment/uncomment these lines to test each class
        mQueue = new ArrayIntQueue();

    //    mQueue = new ArrayIntQueue();

        testList = new ArrayList<>(List.of(1, 2, 3));
    }

    @Test
    public void testIsEmpty() {
        // This is an example unit test
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testNotEmpty() {
        mQueue.enqueue(10);
        System.out.println("Queue size after enqueue: " + mQueue.size());
        System.out.println("Front element: " + mQueue.peek());
       
    }

    // public void enqueue(int value) {
        // if (size == data.length) {
        //     ensureCapacity();  // Expand array if full
        // }
        // data[rear] = value;  // Store value
        // rear = (rear + 1) % data.length;  // Move rear pointer
        // size++;  // Increase size
    // }
    
    // public boolean isEmpty() {
        // return size == 0;  
    // }
    


  
@Test
public void testPeekOnNonEmptyQueue() {
    mQueue.enqueue(5);
    assertEquals(5, (int) mQueue.peek()); // Explicit cast to int
}


@Test
public void testPeekNoEmptyQueueAfterEnqueue() {
    mQueue.enqueue(10);
    assertEquals(10, (int) mQueue.peek()); // Explicit cast to int
}


    @Test
    public void testEnqueue() {
        // This is an example unit test
        for (int i = 0; i < testList.size(); i++) {
            mQueue.enqueue(testList.get(i));
            assertEquals(testList.get(0), mQueue.peek());
            assertEquals(i + 1, mQueue.size());
        }
    }
    
@Test
public void testPeekNoEmptyQueue() {
    mQueue.enqueue(5);
    assertEquals(5, (int) mQueue.peek()); // Explicit cast to int
}

    

@Test
public void testContent() throws IOException {
    InputStream in = new FileInputStream("src/test/resources/data.txt");
    try (Scanner scanner = new Scanner(in)) {
        scanner.useDelimiter("\\s*fish\\s*");

        List<Integer> correctResult = new ArrayList<>();
        while (scanner.hasNextInt()) {
            int input = scanner.nextInt();
            correctResult.add(input);
            System.out.println("enqueue: " + input);
            mQueue.enqueue(input);
        }

        // Print the queue size before dequeuing
        System.out.println("Queue size after all enqueues: " + mQueue.size());

        for (Integer result : correctResult) {
            System.out.println("Queue size before dequeue: " + mQueue.size());
            System.out.println("Front element before dequeue: " + mQueue.peek());

            Integer dequeuedValue = mQueue.dequeue();

            System.out.println("Dequeued: " + dequeuedValue);
   
        }
    }
}

}

        
