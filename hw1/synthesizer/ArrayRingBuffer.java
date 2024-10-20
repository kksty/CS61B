package synthesizer;

import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * 创建一个具有给定容量的新 ArrayRingBuffer。
     */
    public ArrayRingBuffer(int capacity) {
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        if (last == capacity) {
            last = 0;
        }
        rb[last] = x;
        fillCount++;
        last++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        if (first == capacity) {
            first = 0;
        }
        T x = rb[first];
        rb[first] = null;
        fillCount--;
        first++;
        return x;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        int x = first;
        if (first == capacity) {
            x = 0;
        }
        return rb[x];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingIterator();
    }

    private class ArrayRingIterator implements Iterator<T> {
        private int wizPos;

        public ArrayRingIterator() {
            wizPos = first;
        }

        public boolean hasNext() {
            return wizPos != last;
        }

        public T next() {
            if (wizPos == capacity) {
                wizPos = 0;
            }
            T returnItem = rb[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }

}
