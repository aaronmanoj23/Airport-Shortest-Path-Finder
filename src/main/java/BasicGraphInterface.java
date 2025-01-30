public interface BasicGraphInterface<T> {
    void addVertex(T vertex);

    boolean addEdge(T begin, T end, double weight);

    boolean hasEdge(T begin, T end);
}
