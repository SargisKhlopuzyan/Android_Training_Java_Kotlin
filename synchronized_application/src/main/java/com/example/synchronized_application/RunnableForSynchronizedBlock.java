package com.example.synchronized_application;

public class RunnableForSynchronizedBlock implements Runnable {

    private SynchronizedBlockClass synchronizedBlockClass;

    private String threadName;

    public RunnableForSynchronizedBlock(SynchronizedBlockClass synchronizedBlockClass, String threadName) {
        this.synchronizedBlockClass = synchronizedBlockClass;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            synchronizedBlockClass.runSynchronizedBlock(threadName);
        }
    }

}
