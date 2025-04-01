package com.myapp.transportlogistics.cache;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Cache {
    private final Map<String, Object> cache;
    private final Queue<String> sequence;
    private final int maxSize = 100;

    private static final Logger logger = LoggerFactory.getLogger(Cache.class);

    public Cache() {
        this.cache = new HashMap<>(maxSize);
        this.sequence = new ArrayDeque<>();
    }

    public void put(String key, Object value) {

        if (getSize() >= maxSize) {
            String oldestKey = sequence.poll();
            if (oldestKey != null) {
                cache.remove(oldestKey);
            }
        }

        cache.put(key, value);
        sequence.add(key);
        logger.info("Записано в кэш: ключ={}, значение={}", key, value);
    }

    public Optional<Object> get(String key) {
        Object value = cache.get(key);

        if (value != null) {
            sequence.remove(key);
            sequence.add(key);
            logger.info("Попадание в кэш: ключ={}, значение={}", key, value);
        } else {
            logger.info("Промах кэша: ключ={}", key);
        }

        return Optional.ofNullable(value);
    }

    public void remove(String key) {
        if (key == null) {
            logger.warn("Попытка удалить несуществующий ключ");
            return;
        }

        if (cache.remove(key) != null) {
            sequence.remove(key);
            logger.info("Удалено из кэша: ключ={}", key);
        }
    }

    public int getSize() {
        int size = cache.size();
        logger.info("Текущий размер кэша: {}", size);
        return size;
    }
}
