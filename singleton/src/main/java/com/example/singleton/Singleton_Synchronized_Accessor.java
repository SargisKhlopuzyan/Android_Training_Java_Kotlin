package com.example.singleton;

// + Lazy initialization
// - Low performance (critical section) in the most typical access
public class Singleton_Synchronized_Accessor {

    public Singleton_Synchronized_Accessor() {}

    private static Singleton_Synchronized_Accessor instance;

    public static synchronized Singleton_Synchronized_Accessor getInstance() {
        if (instance == null) {
            instance = new Singleton_Synchronized_Accessor();
        }
        return instance;
    }

}
