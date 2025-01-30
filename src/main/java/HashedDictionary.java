import java.util.*;

public class HashedDictionary<K, V> implements DictionaryInterface<K, V> {
    private Map<K, V> map;

    public HashedDictionary() {
        map = new HashMap<>();
    }

    @Override
    public void add(K key, V value) {
        map.put(key, value);
    }

    @Override
    public V remove(K key) {
        return map.remove(key);
    }

    @Override
    public V getValue(K key) {
        return map.get(key);
    }

    @Override
    public boolean contains(K key) {
        return map.containsKey(key);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }
}
