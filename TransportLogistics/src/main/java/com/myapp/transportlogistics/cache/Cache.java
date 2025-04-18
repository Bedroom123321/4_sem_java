package com.myapp.transportlogistics.cache;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Cache<K, V> {
    private final Map<K, V> cacheStorage;
    private final Queue<K> sequence;
    private static final int MAX_SIZE = 100;

    public Cache() {
        this.cacheStorage = new HashMap<>(MAX_SIZE, 0.75f);
        this.sequence = new ArrayDeque<>();
    }

    public void put(K key, V value) {

        if (getSize() >= MAX_SIZE) {
            K oldestKey = sequence.poll();
            remove(oldestKey);
        }

        cacheStorage.put(key, value);
        sequence.add(key);
    }

    public Optional<V> get(K key) {
        V value = cacheStorage.get(key);

        if (value != null) {
            sequence.remove(key);
            sequence.add(key);
        }
        return Optional.ofNullable(value);
    }

    public void remove(K key) {
        if (key == null) {
            return;
        }

        if (cacheStorage.remove(key) != null) {
            sequence.remove(key);
        }
    }

    public int getSize() {
        return cacheStorage.size();
    }
}
