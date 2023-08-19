import java.util.LinkedHashMap;
import java.util.Map;

// https://leetcode.com/problems/lru-cache/
// Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
// Implement the LRUCache class:
// LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
// int get(int key) Return the value of the key if the key exists, otherwise return -1.
// void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair 
// to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
// The functions get and put must each run in O(1) average time complexity.

// https://www.interviewcake.com/concept/java/lru-cache
// To implement LinkedHashMap, we should use HasMap, where value points to a node of double LLD. 
// We should maintain keep pointer of map, head tail of DLL.
// We should imeplement, insert, delete, get, getFirst methods.

class LRUCache {

    int capacity;
    Map<Integer, Integer> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<Integer, Integer>(capacity);
    }
    
    public int get(int key) {
        Integer value = this.cache.get(key);
        if (value == null){
            return -1;
        }
        this.cache.remove(key);
        this.cache.put(key, value);
        return value;

    }
    
    public Integer put(int key, int value) {
        if (this.cache.containsKey(key)){
            this.cache.remove(key);
        }
        else if (this.cache.size() == this.capacity){
            Integer oldest_key = this.cache.keySet().iterator().next();
            this.cache.remove(oldest_key);
        }
        this.cache.put(key, value);
        return null;
    }

    public static void main(String[] args){
        LRUCache lruCache = new LRUCache(2);

        System.out.println(lruCache.get(2));
        System.out.println(lruCache.put(2,6));
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.put(1,5));
        System.out.println(lruCache.put(1,2));
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));

    }
}

