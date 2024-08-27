package synthesizer;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Tests the ArrayRingBuffer class.
 *
 * @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        for (int i = 0; i < arb.capacity; i++) {
            arb.enqueue(i);
        }
//        assertEquals(0, (long) arb.dequeue());
//        arb.enqueue(12);
//        assertEquals(0, (long) arb.peek());

        Iterator<Integer> iterator = arb.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * Calls tests for ArrayRingBuffer.
     */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
