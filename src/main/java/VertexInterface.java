public interface VertexInterface<T> {
    T getLabel();

    void addEdge(Vertex<T> neighbor, double weight);

    boolean hasEdge(T target);
}
