import java.util.List;

public interface GraphAlgorithmsInterface<T> {
    List<T> getShortestPath(T start, T end);
}
