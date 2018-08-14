package com.example.synchronized_application;

public class RunnableForSynchronizedMethod implements Runnable {

    private SynchronizedMethodClass synchronizedMethodClass;

    private String threadName;

    public RunnableForSynchronizedMethod(SynchronizedMethodClass synchronizedMethodClass, String threadName) {
        this.synchronizedMethodClass = synchronizedMethodClass;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            synchronizedMethodClass.runSynchronizedMethod(threadName);
        }
    }

}
