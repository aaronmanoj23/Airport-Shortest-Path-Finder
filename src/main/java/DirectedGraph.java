import java.util.*;

public class DirectedGraph<T> implements GraphInterface<T> {
    private Map<T, Vertex<T>> vertices;
    private int edgeCount;

    public DirectedGraph() {
        vertices = new HashMap<>();
        edgeCount = 0;
    }

    @Override
    public void addVertex(T vertex) {
        vertices.putIfAbsent(vertex, new Vertex<>(vertex));
    }

    @Override
    public boolean addEdge(T begin, T end, double weight) {
        Vertex<T> startVertex = vertices.get(begin);
        Vertex<T> endVertex = vertices.get(end);

        if (startVertex != null && endVertex != null) {
            startVertex.addEdge(endVertex, weight);
            edgeCount++;
            return true;
        }
        return false;
    }

    @Override
    public boolean hasEdge(T begin, T end) {
        Vertex<T> startVertex = vertices.get(begin);
        return startVertex != null && startVertex.hasEdge(end);
    }

    @Override
    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    @Override
    public int getNumberOfVertices() {
        return vertices.size();
    }

    @Override
    public int getNumberOfEdges() {
        return edgeCount;
    }

    // Retrieve the weight of an edge between two vertices
    public double getEdgeWeight(T begin, T end) {
        Vertex<T> startVertex = vertices.get(begin);
        if (startVertex != null) {
            return startVertex.getEdgeWeight(end);
        }
        throw new IllegalArgumentException("Edge does not exist between " + begin + " and " + end);
    }

    // Get the shortest path using Dijkstra's algorithm
    public List<T> getShortestPath(T start, T end) {
        Map<T, Double> distances = new HashMap<>();
        Map<T, T> predecessors = new HashMap<>();
        PriorityQueue<T> pq = new PriorityQueue<>();

        for (T vertex : vertices.keySet()) {
            distances.put(vertex, Double.MAX_VALUE);
        }
        distances.put(start, 0.0);
        pq.add(start, 0.0);

        while (!pq.isEmpty()) {
            T current = pq.remove();
            Vertex<T> currentVertex = vertices.get(current);

            for (Map.Entry<Vertex<T>, Double> neighbor : currentVertex.getNeighbors().entrySet()) {
                T neighborLabel = neighbor.getKey().getLabel();
                double newDist = distances.get(current) + neighbor.getValue();

                if (newDist < distances.get(neighborLabel)) {
                    distances.put(neighborLabel, newDist);
                    predecessors.put(neighborLabel, current);
                    pq.add(neighborLabel, newDist);
                }
            }
        }

        return constructPath(predecessors, start, end);
    }

    // Helper to construct the shortest path from predecessors
    private List<T> constructPath(Map<T, T> predecessors, T start, T end) {
        LinkedList<T> path = new LinkedList<>();
        T current = end;

        while (current != null && !current.equals(start)) {
            path.addFirst(current);
            current = predecessors.get(current);
        }

        if (current == null) {
            return Collections.emptyList(); // No path found
        }

        path.addFirst(start);
        return path;
    }
}

