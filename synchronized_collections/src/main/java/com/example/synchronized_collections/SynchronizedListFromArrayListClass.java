package com.example.synchronized_collections;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SynchronizedListFromArrayListClass {

    // ********************** synchronizedList ************************
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

    // ********************** synchronizedMap ************************
    public Map<String,String> getSynchronizedMapFromMap() {
        Map<String,String> hashMap = new HashMap();
        hashMap.put("1", "eBay");
        hashMap.put("2", "Paypal");
        hashMap.put("3", "Google");
        hashMap.put("4", "Yahoo");
        Map<String,String> synchronizedMap = Collections.synchronizedMap(hashMap);
        Log.e("LOG_TAG", "synchronizedMap contains : " + synchronizedMap);
        return hashMap;
    }

}
