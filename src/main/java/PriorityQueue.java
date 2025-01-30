import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityQueue<T> {
    private List<Node<T>> heap;

    public PriorityQueue() {
        heap = new ArrayList<>();
    }

    // Insert an item with a priority
    public void add(T item, double priority) {
        Node<T> newNode = new Node<>(item, priority);
        heap.add(newNode);
        bubbleUp(heap.size() - 1);
    }

    // Remove and return the item with the highest priority (smallest value)
    public T remove() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }
        T minItem = heap.get(0).item;
        Node<T> lastNode = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, lastNode);
            bubbleDown(0);
        }

        return minItem;
    }

    // Return the item with the highest priority without removing it
    public T peek() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }
        return heap.get(0).item;
    }

    // Check if the priority queue is empty
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void bubbleUp(int index) {
        int parentIndex;
        while (index > 0) {
            parentIndex = (index - 1) / 2;
            if (heap.get(index).priority >= heap.get(parentIndex).priority) {
                break;
            }
            Collections.swap(heap, index, parentIndex);
            index = parentIndex;
        }
    }

    private void bubbleDown(int index) {
        int leftChild, rightChild, smallestChild;

        while (index < heap.size()) {
            leftChild = 2 * index + 1;
            rightChild = 2 * index + 2;
            smallestChild = index;

            if (leftChild < heap.size() && heap.get(leftChild).priority < heap.get(smallestChild).priority) {
                smallestChild = leftChild;
            }
            if (rightChild < heap.size() && heap.get(rightChild).priority < heap.get(smallestChild).priority) {
                smallestChild = rightChild;
            }
            if (smallestChild == index) {
                break;
            }
            Collections.swap(heap, index, smallestChild);
            index = smallestChild;
        }
    }

    private static class Node<T> {
        T item;
        double priority;

        Node(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }
    }
}
