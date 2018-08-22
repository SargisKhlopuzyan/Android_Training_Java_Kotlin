package com.example.synchronized_application.geeksforgeeks;

public class ThreadedSend extends Thread {

    private String msg;
    private Thread t;
    Sender  sender;

    public ThreadedSend(String m,  Sender obj) {
        msg = m;
        sender = obj;
    }

    @Override
    public void run() {
        // Only one thread can send a message at a time.
        synchronized(sender)
        {
            // synchronizing the snd object
            sender.showMessage(msg);
        }
    }
}
