public interface GraphInterface<T> extends BasicGraphInterface<T>, GraphAlgorithmsInterface<T> {
    int getNumberOfVertices();

    int getNumberOfEdges();

    boolean isEmpty();
}
