public class LinkedListDeque<T> {

    private IntNode sentinel;
    private int size;

    public class IntNode {
        public T item;
        public IntNode prev;
        public IntNode next;

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

    public LinkedListDeque(T item) {
        sentinel = new IntNode(null, null, null);
        sentinel.next = new IntNode(sentinel, item, sentinel);
        sentinel.prev = sentinel.next;
        size++;
    }

    public void addFirst(T item) {
        if (isEmpty()) {
            sentinel.next = new IntNode(sentinel, item, sentinel.next);
            sentinel.prev = sentinel.next;
        } else {
            sentinel.next = new IntNode(sentinel, item, sentinel.next);
        }

        size++;
    }

    public void addLast(T item) {
        sentinel.prev.next = new IntNode(sentinel.prev, item, sentinel);
        sentinel.prev = sentinel.prev.next;
        size++;
    }

    public boolean isEmpty() {
        if (sentinel.next == sentinel) {
            return true;
        } else return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        IntNode ptr = sentinel.next;
        while (ptr != sentinel) {
            System.out.print(ptr.item + " " + "\n");
            ptr = ptr.next;
        }
    }

    public T removeFirst() {
        if (!isEmpty()) {
            T tmp = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            return tmp;
        } else return null;
    }

    public T removeLast() {
        if (!isEmpty()) {
            T tmp = sentinel.prev.item;
            sentinel.prev.prev.next = sentinel;
            return tmp;
        } else return null;
    }

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

//    public LinkedListDeque(LinkedListDeque other) {
//
//    }

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
