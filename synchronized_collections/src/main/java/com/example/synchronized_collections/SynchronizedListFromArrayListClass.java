package com.example.synchronized_collections;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SynchronizedListFromArrayListClass {

    // ********************** Collections.synchronizedList ************************
    // Returns a synchronized (thread-safe) list backed by the specified list.
    // In order to guarantee serial access, it is critical that all access to the backing list is accomplished through the returned list.
    public List<String> getSynchronizedListFromArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("eBay");
        arrayList.add("Paypal");
        arrayList.add("Google");
        arrayList.add("Yahoo");

        List<String> synchronizedList = Collections.synchronizedList(arrayList);
        Log.e("LOG_TAG", "synchronizedList contains : " + synchronizedList);
        return synchronizedList;
    }

    // ********************** Collections.synchronizedMap ************************
    public Map<String,String> getHashtable() {
        Map<String,String> hashtable = new Hashtable<>();
        hashtable.put("1", "eBay");
        hashtable.put("2", "Paypal");
        hashtable.put("3", "Google");
        hashtable.put("4", "Yahoo");

        Map<String,String> synchronizedHashtable = Collections.synchronizedMap(hashtable);
        Log.e("LOG_TAG", "synchronizedHashtable contains : " + synchronizedHashtable);
        return synchronizedHashtable;
    }

    // ********************** Collections.synchronizedMap ************************
    public Map<String,String> getSynchronizedMapFromMap() {
        Map<String,String> hashMap = new HashMap();
        hashMap.put("1", "eBay");
        hashMap.put("2", "Paypal");
        hashMap.put("3", "Google");
        hashMap.put("4", "Yahoo");

        Map<String,String> synchronizedMap = Collections.synchronizedMap(hashMap);
        Log.e("LOG_TAG", "synchronizedMap contains : " + synchronizedMap);
        return synchronizedMap;
    }

    // ********************** ConcurrentHashMap ************************
    public Map<String,String> getConcurrentHashMap() {
        Map<String,String> hashMap = new HashMap();
        hashMap.put("1", "eBay");
        hashMap.put("2", "Paypal");
        hashMap.put("3", "Google");
        hashMap.put("4", "Yahoo");

        Map<String,String> concurrentHashMap = new ConcurrentHashMap<>(hashMap);
        Log.e("LOG_TAG", "concurrentHashMap contains : " + concurrentHashMap);
        return concurrentHashMap;
    }
}
