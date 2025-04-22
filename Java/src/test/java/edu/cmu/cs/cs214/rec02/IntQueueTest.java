package edu.cmu.cs.cs214.rec02;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
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
       // mQueue = new LinkedIntQueue();
     mQueue = new ArrayIntQueue();

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
    assertTrue(!mQueue.isEmpty());
}  
 
@Test
public void testPeekEmptyQueue() {
    assertEquals(null, mQueue.peek());
}


@Test
public void testPeekNoEmptyQueue() {
    mQueue.enqueue(42);
    assertEquals((Integer)42, mQueue.peek());
    assertEquals(1, mQueue.size());  // make sure peek doesn’t remove the element
}

@Test
public void testQueueShrinks() {
    // Fill the queue to grow it first
    for (int i = 0; i < 20; i++) {
        mQueue.enqueue(i);
    }

    // Remove most elements to trigger shrinking
    for (int i = 0; i < 17; i++) {
        mQueue.dequeue();
    }

    // This will force the queue to shrink in size
    mQueue.dequeue(); // should hit the shrink condition
}
@Test
public void testEnsureCapacityTriggersShrink() {
    // Enqueue a lot of elements to force the internal array to grow
    for (int i = 0; i < 40; i++) {
        mQueue.enqueue(i);
    }

    // Dequeue most elements to reduce size below 1/4 of capacity
    for (int i = 0; i < 35; i++) {
        mQueue.dequeue();
    }

    // This dequeue should make size small enough to trigger a shrink
    mQueue.dequeue();
}

@Test
public void testDequeueFromEmptyQueue() {
    assertNull(mQueue.dequeue());
}

@Test
public void testClear() {
    mQueue.enqueue(10);
    mQueue.enqueue(20);
    mQueue.enqueue(30);
    mQueue.clear();
    assertTrue(mQueue.isEmpty());
    assertEquals(0, mQueue.size());
    //assertNull(mQueue.dequeue());
}
@Test
public void testEnsureCapacityShrink() {
    // Step 1: Enqueue enough to grow the array (usually doubles to 21, 43, etc.)
    for (int i = 0; i < 50; i++) {
        mQueue.enqueue(i);
    }

    // Step 2: Dequeue until size is <= 1/4 of elementData.length
    for (int i = 0; i < 40; i++) {
        mQueue.dequeue();
    }

    // Step 3: Force ensureCapacity() to be called (e.g., via enqueue)
    mQueue.enqueue(999); // This should now trigger the shrinking branch
}

@Test
public void testNullEnqueueThrows() {
    try {
        mQueue.enqueue(null);
        fail("Expected IllegalArgumentException for null enqueue");
    } catch (IllegalArgumentException e) {
        assertEquals("Null values are not allowed in queue", e.getMessage());
    }
}

@Test
public void testClearWhenEmpty() {
    // Queue is empty initially
    mQueue.clear(); // Should hit the `if (size > 0)` as false
    assertTrue(mQueue.isEmpty());
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
public void testDequeue() {
    mQueue.enqueue(1);
    mQueue.enqueue(2);
    mQueue.enqueue(3);
    
    assertEquals((Integer)1, mQueue.dequeue());
    assertEquals((Integer)2, mQueue.dequeue());
    assertEquals((Integer)3, mQueue.dequeue());
    assertTrue(mQueue.isEmpty());
}


    @Test
    public void testContent() throws IOException {
        // This is an example unit test
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

            for (Integer result : correctResult) {
                assertEquals(mQueue.dequeue(), result);
            }
        }
    }


}
