package com.example.singleton;

public class Singleton_With_Object_Lock {

    private static volatile Singleton_With_Object_Lock instance;
    private static Object lock = new Object();

    private Singleton_With_Object_Lock() {}

    public static Singleton_With_Object_Lock getInstance() {
        Singleton_With_Object_Lock result = instance;
        if (result == null) {
            synchronized (lock) { // While we were waiting for the lock, another
                result = instance; // thread may have instantiated the object.
                if (result == null)
                    instance = result = new Singleton_With_Object_Lock();
            }
        }
        return result;
    }

}
