package com.example.crunchify;

import android.util.Log;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class UniqueArrayList {

    public static List<String> getInitialStringList() {
        List<String> initialList = Arrays.asList("eBay", "paypal", "google", "eBay", "google", "paypal");
        return initialList;
    }

    public static List<Integer> getInitialIntegerList() {
        List<Integer> initialList = Arrays.asList(12, 2, 10, 4, 2, 7, 19, 10);
        return initialList;
    }

    public static <T> void sortList (List<T> list){

        Set<T> hashSetList = new HashSet<T>(list);
        Log.e("LOG_TAG", "Unique values using HashSet: " + hashSetList);

        Set<T> treeSetList = new TreeSet<T>(list);
        Log.e("LOG_TAG", "Unique values using TreeSet - Sorted order: " + treeSetList);

    }

}
