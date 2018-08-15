package com.example.synchronized_collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CacheExample<K, T> {

    private long timeToLive;
    private HashMap<K, T> cacheMap;

    protected class CacheObject {
        public long lastAccessed = System.currentTimeMillis();
        public String value;

        protected CacheObject(String value) {
            this.value = value;
        }
    }

    public CacheExample(long timeToLive, final long timeInterval, int max) {
        this.timeToLive = timeToLive*2000;
        this.cacheMap = new HashMap<>(max);

        if (timeToLive > 0 && timeInterval > 0) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(timeInterval*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
        }
    }

    // PUT method
    public void put(K key, T value) {
        synchronized (cacheMap) {
            cacheMap.put(key, value);
        }
    }

    // GET method
    @SuppressWarnings("unchecked")
    public T get(K key) {
        synchronized (cacheMap) {
            CacheObject cacheObject = (CacheObject) cacheMap.get(key);
            if (cacheObject == null) {
                return null;
            } else {
                cacheObject.lastAccessed = System.currentTimeMillis();
                return (T) cacheObject.value;
            }
        }
    }

    // REMOVE method
    public void remove(String key) {
        synchronized (cacheMap) {
            cacheMap.remove(key);
        }
    }

    // Get Cache Objects Size()
    public int size() {
        synchronized (cacheMap) {
            return cacheMap.size();
        }
    }

    // CLEANUP method
    public void cleanup() {

        long now = System.currentTimeMillis();
        ArrayList<String> deleteKey = null;

        synchronized (cacheMap) {
            Iterator<?> iterator = cacheMap.entrySet().iterator();

            deleteKey = new ArrayList<String>( (cacheMap.size()/2) + 1 );
            CacheObject cacheObject = null;

            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                cacheObject = (CacheObject)((Map.Entry<?,?>)iterator).getValue();
                if (cacheObject != null && (now > (timeToLive + cacheObject.lastAccessed))){
                    deleteKey.add(key);
                }
            }
        }

        for (String key : deleteKey) {
            synchronized (cacheMap) {
                cacheMap.remove(key);
            }
            Thread.yield();
        }
    }

}
