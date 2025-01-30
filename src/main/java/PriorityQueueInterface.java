public interface PriorityQueueInterface<T> {
    void add(T item, double priority);

    T remove();

    T peek();

    boolean isEmpty();
}
