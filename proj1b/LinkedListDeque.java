/*链表双端队列*/

public class LinkedListDeque<T> implements Deque<T>{

    private IntNode sentinel;
    private int size;

    private class IntNode {
        private T item;
        private IntNode prev;
        private IntNode next;

        public IntNode(IntNode prev, T item, IntNode next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    public LinkedListDeque() {
        sentinel = new IntNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

//    public LinkedListDeque(T item) {
//        sentinel = new IntNode(null, null, null);
//        sentinel.next = new IntNode(sentinel, item, sentinel);
//        sentinel.prev = sentinel.next;
//        size++;
//    }

    @Override
    public void addFirst(T item) {
        if (isEmpty()) {
            sentinel.next = new IntNode(sentinel, item, sentinel.next);
            sentinel.prev = sentinel.next;
        } else {
            sentinel.next = new IntNode(sentinel, item, sentinel.next);
            sentinel.next.next.prev = sentinel.next;
        }
        size++;
    }
    @Override
    public void addLast(T item) {
        sentinel.prev.next = new IntNode(sentinel.prev, item, sentinel);
        sentinel.prev = sentinel.prev.next;
        size++;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        IntNode ptr = sentinel.next;
        while (ptr != sentinel) {
            System.out.print(ptr.item + " " + "\n");
            ptr = ptr.next;
        }
    }
    @Override
    public T removeFirst() {
        if (!isEmpty()) {
            T tmp = sentinel.next.item;
            if (sentinel.next.next == sentinel) {
                sentinel.next = sentinel.next.next;
                sentinel.prev = sentinel.prev.prev;
                sentinel.next.prev = sentinel;
                size--;
                return tmp;
            } else {
                sentinel.next = sentinel.next.next;
                sentinel.next.prev = sentinel;
                size--;
                return tmp;
            }
        } else {
            return null;
        }
    }
    @Override
    public T removeLast() {
        if (!isEmpty()) {
            T tmp = sentinel.prev.item;
            IntNode intermediateNode = sentinel.prev.prev;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = intermediateNode;
            size--;
            return tmp;
        } else {
            return null;
        }
    }
    @Override
    public T get(int index) {
        IntNode ptr = sentinel;
        int tmp = 0;
        while (ptr.next != sentinel) {
            if (tmp == index) {
                return ptr.next.item;
            }
            tmp++;
            ptr = ptr.next;
        }
        return null;
    }

    public T getRecursive(int index) {
        return getRecursive(sentinel, index, 0);
    }

    private T getRecursive(IntNode x, int index, int tmp) {
        IntNode ptr = x;
        if (ptr.next == sentinel) {
            return null;
        } else if (tmp == index) {
            return ptr.next.item;
        }
        return getRecursive(ptr.next, index, tmp + 1);
    }
}
