package com.example.crunchify;

import android.util.Log;

// https://crunchify.com/how-to-generate-out-of-memory-oom-in-java-programatically/
public class OutOfMemoryClass {

    public static void generateOOM() throws InterruptedException {

        int iteratorValue = 20;
        Log.e("LOG_TAG", "=================> OOM test started..");

        for (int outerIterator = 1; outerIterator < 20; outerIterator ++) {

            Log.e("LOG_TAG", "Iteration " + outerIterator + " Free Mem: " + Runtime.getRuntime().freeMemory());
            int loop = 2;
            int[] memoryFillIntVar = new int[iteratorValue];

            // feel memoryFillIntVar array in loop..
            do {
                memoryFillIntVar[loop] = 0;
                loop--;
            } while (loop > 0);

            iteratorValue = iteratorValue * 5;

            Log.e("LOG_TAG", "Required Memory for next loop: " + iteratorValue);
            Thread.sleep(1000);
        }

    }

}
