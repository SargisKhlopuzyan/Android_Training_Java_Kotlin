package com.example.synchronized_application;

import android.util.Log;

import java.util.ArrayList;

public class SynchronizedMethodClass {

//    private ArrayList<Integer> numbers = new ArrayList<>();
    private ArrayList<Integer> numbers;
    private Integer position;

    public SynchronizedMethodClass() {
        numbers = new ArrayList<>();
    }

    public ArrayList<Integer> getNumbers() {
        return numbers;
    }

//    public void runSynchronizedMethod(String threadName) {
    public synchronized void runSynchronizedMethod(String threadName) {

        Integer nextNumber;
        if (numbers.size() == 0) {
            nextNumber = 0;
            position = 0;
            numbers.add(nextNumber);
        } else {
            Integer lastNumber;
            lastNumber = numbers.get(numbers.size() - 1);
            nextNumber = lastNumber + 1;
            position = position + 1;
            numbers.add(nextNumber);
        }
        Log.e("LOG_TAG", "Thread_" + threadName + "  |  number: " + nextNumber + "  |  position: " + position);
    }
}
