public interface DictionaryInterface<K, V> {
    void add(K key, V value);

    V remove(K key);

    V getValue(K key);

    boolean contains(K key);

    boolean isEmpty();
}
