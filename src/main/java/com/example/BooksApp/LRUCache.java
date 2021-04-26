package com.example.BooksApp;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class LRUCache {

    private Deque<String> doublyQueue;
    private HashMap<String, Long> hashMap;
    private final int CACHE_SIZE = 5;
    private final long milisecondsInHour = 3600000;

    LRUCache() {
        doublyQueue = new LinkedList<>();
        hashMap = new HashMap<>();

    }

    public void refer(String id, long time) {
        if (!hashMap.containsKey(id)) {
            if (doublyQueue.size() == CACHE_SIZE) {
                String last = doublyQueue.removeLast();
                hashMap.remove(last);
            }
        } else {
            doublyQueue.remove(id);
        }
        doublyQueue.push(id);
        hashMap.put(id, time);
        clear(hashMap);

    }

    public void clear(HashMap<String, Long> hashMap) {
        long actualTime = new Date().getTime();
        hashMap.entrySet().removeIf(e -> (e.getValue() - actualTime > milisecondsInHour));
    }

    public Deque<String> getDoublyQueue() {
        return doublyQueue;
    }

    public void setDoublyQueue(Deque<String> doublyQueue) {
        this.doublyQueue = doublyQueue;
    }

    public HashMap<String, Long> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<String, Long> hashMap) {
        this.hashMap = hashMap;
    }
}
