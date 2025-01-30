import java.util.*;

public class Vertex<T> implements VertexInterface<T> {
    private T label;
    private Map<Vertex<T>, Double> neighbors;

    public Vertex(T label) {
        this.label = label;
        neighbors = new HashMap<>();
    }

    public double getEdgeWeight(T target) {
        for (Map.Entry<Vertex<T>, Double> entry : neighbors.entrySet()) {
            if (entry.getKey().getLabel().equals(target)) {
                return entry.getValue();
            }
        }
        throw new IllegalArgumentException("Edge to target not found.");
    }

    public Map<Vertex<T>, Double> getNeighbors() {
        return neighbors;
    }

    @Override
    public T getLabel() {
        return label;
    }

    @Override
    public void addEdge(Vertex<T> neighbor, double weight) {
        neighbors.put(neighbor, weight);
    }

    @Override
    public boolean hasEdge(T target) {
        for (Vertex<T> neighbor : neighbors.keySet()) {
            if (neighbor.getLabel().equals(target)) return true;
        }
        return false;
    }
}
