package com.example.BooksApp;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class LRUCache {

    private Deque<String> doublyQueue;

    private HashMap<String, Long> hashMap;

    private final int CACHE_SIZE=5;

    LRUCache() {
        doublyQueue = new LinkedList<>();
        hashMap = new HashMap<>();

    }

    /* Refer the page within the LRU cache */
    public void refer(String id, long time) {
        if (!hashMap.containsKey(id)) {
            if (doublyQueue.size() == CACHE_SIZE) {
                String last = doublyQueue.removeLast();
                hashMap.remove(id);
            }
        } else {
            doublyQueue.remove(id);
        }
        doublyQueue.push(id);
        hashMap.put(id,time);
        clear(hashMap);

    }


    public void display() {
        System.out.println("DOUBLE QUE");
        Iterator<String> itr = doublyQueue.iterator();
        while (itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
        System.out.println("HASHMAP SIZE"+hashMap.size());
        for (String name: hashMap.keySet()) {
            String key = name.toString();
            String value = hashMap.get(name).toString();
            System.out.println(key + " " + value);
        }
    }

    public void clear(HashMap<String, Long> hashMap){
        long actualTime=new Date().getTime();
        hashMap.entrySet().removeIf(e -> (e.getValue()-actualTime>3600000));
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
