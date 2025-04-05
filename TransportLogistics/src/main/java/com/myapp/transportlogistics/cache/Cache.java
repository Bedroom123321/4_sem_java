package com.myapp.transportlogistics.cache;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Component
public class Cache<K, V> {
    Map<K, V> cacheStorage;
    Queue<K> sequence;
    static int maxSize = 100;

    static Logger logger = LoggerFactory.getLogger(Cache.class);

    public Cache() {
        this.cacheStorage = new HashMap<>(maxSize, 0.75f);
        this.sequence = new ArrayDeque<>();
    }

    public void put(K key, V value) {

        if (getSize() >= maxSize) {
            K oldestKey = sequence.poll();
            remove(oldestKey);
        }

        cacheStorage.put(key, value);
        sequence.add(key);
        logger.info("Записано в кэш: ключ={}, значение={}", key, value);
    }

    public Optional<V> get(K key) {
        V value = cacheStorage.get(key);

        if (value != null) {
            sequence.remove(key);
            sequence.add(key);
            logger.info("Попадание в кэш: ключ={}, значение={}", key, value);
        } else {
            logger.info("Промах кэша: ключ={}", key);
        }

        return Optional.ofNullable(value);
    }

    public void remove(K key) {
        if (key == null) {
            logger.warn("Попытка удалить несуществующий ключ");
            return;
        }

        if (cacheStorage.remove(key) != null) {
            sequence.remove(key);
            logger.info("Удалено из кэша: ключ={}", key);
        }
    }

    public int getSize() {
        int size = cacheStorage.size();
        logger.info("Текущий размер кэша: {}", size);
        return size;
    }
}
