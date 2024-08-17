/*此接口包含 ArrayDeque 和 LinkedListDeque 中出现的所有基本方法*/

public interface Deque<T> {
    void addFirst(T item);

    void addLast(T item);

    default boolean isEmpty() {
        return size() == 0;
    }

    int size();

    void printDeque();

    T removeFirst();

    T removeLast();

    T get(int index);

}
