import java.util.EmptyStackException;

public class ArrayStack<T> implements StackInterface<T> {
    private T[] stack;
    private int topIndex;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayStack(int initialCapacity) {
        stack = (T[]) new Object[initialCapacity];
        topIndex = -1;
    }

    @Override
    public void push(T newEntry) {
        if (topIndex == stack.length - 1) {
            expandArray();
        }
        stack[++topIndex] = newEntry;
    }

    @Override
    public T pop() {
        if (isEmpty()) throw new EmptyStackException();
        T result = stack[topIndex];
        stack[topIndex--] = null;
        return result;
    }

    @Override
    public T peek() {
        if (isEmpty()) throw new EmptyStackException();
        return stack[topIndex];
    }

    @Override
    public boolean isEmpty() {
        return topIndex < 0;
    }

    private void expandArray() {
        @SuppressWarnings("unchecked")
        T[] newStack = (T[]) new Object[2 * stack.length];
        System.arraycopy(stack, 0, newStack, 0, stack.length);
        stack = newStack;
    }
}
