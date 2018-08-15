package com.example.synchronized_collections;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ConverterHelper {

    public static void hashMapToArrayList() {

        HashMap<String, Integer> companyDetails = new HashMap<String, Integer>();

        // create hashmap with keys and values (CompanyName, #Employees)
        companyDetails.put("eBay", 4444);
        companyDetails.put("Paypal", 5555);
        companyDetails.put("IBM", 6666);
        companyDetails.put("Google", 7777);
        companyDetails.put("Yahoo", 8888);

        Iterator iterator = companyDetails.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pairs = (Map.Entry) iterator.next();
            Log.e("LOG_TAG", pairs.getKey() + " = " + pairs.getValue());
        }

        // Converting HashMap keys into ArrayList
        List<String> keyList = new ArrayList<String>(companyDetails.keySet());
        Log.e("LOG_TAG","Size of Key list: " + keyList.size());
        Log.e("LOG_TAG","KeyList: " + keyList);

        // Converting HashMap Values into ArrayList
        List<Integer> valueList = new ArrayList<Integer>(companyDetails.values());
        Log.e("LOG_TAG","Size of Value list: " + valueList.size());
        Log.e("LOG_TAG","ValueList: " + valueList);

        List<Map.Entry> entryList = new ArrayList<Map.Entry>(companyDetails.entrySet());
        Log.e("LOG_TAG","Size of Entry list: " + entryList.size());
        Log.e("LOG_TAG","EntryList: " + entryList);
    }

    // Start - Iterate Through Map and List in Java

    public static void IterateThroughMapAndList() {

        // =============== MAP ================
        Map<String, String> companyMap = new HashMap<>();
        companyMap.put("Google", "Mountain View");
        companyMap.put("Facebook", "Santa Clara");
        companyMap.put("Twitter", "San Francisco");

        // Method1: Standard Method to iterate through Java Map
        standardForEachMethod4Map(companyMap);
        // Method2: Java8 Method to iterate through Java Map
        java8ForEachMethod4Map(companyMap);

        // =============== List ================
        List<String> companyList = new ArrayList<>();
        companyList.add("Google");
        companyList.add("Facebook");
        companyList.add("Twitter");

        // Method3: Standard Method to iterate through Java List
        standardForEachMethod4List(companyList);

        // Method4,5: Java8 Method to iterate through Java List
        java8ForEachMethod4List(companyList);
    }

    private static void standardForEachMethod4Map(Map<String, String> companyMap) {
        log("============ Method1: Standard Method to iterate through Java Map");
        for (Map.Entry<String, String> entry : companyMap.entrySet()) {
            log("Company: " + entry.getKey() + ", address: " + entry.getValue());
        }
    }

    private static void java8ForEachMethod4Map(Map<String, String> crunchifyCompanyMap) {
        log("============ Method2: Java8 Method to iterate through Java Map");
        crunchifyCompanyMap.forEach((k, v) -> log("Company: " + k + ", address: " + v));
    }

    private static void standardForEachMethod4List(List<String> crunchifyList) {
        log("============ Method3: Standard Method to iterate through Java List");
        for (String item : crunchifyList) {
            log(item);
        }
    }

    private static void java8ForEachMethod4List(List<String> crunchifyList) {
        // lambda method
        log("============ Method4: Java8 Method to iterate through Java List - using Lambda");
        crunchifyList.forEach(item -> log(item));

        // using method reference
        log("============ Method5: Java8 Method to iterate through Java List - using Method Reference");
        crunchifyList.forEach(System.out::println);
    }

    private static void log(String string) {
        Log.e("LOG_TAG", string);
    }

    // End - Iterate Through Map and List in Java

}
