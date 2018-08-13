package com.example.singleton;

// + Lazy initialization
// + High performance
// - Supported only with JDK 1.5 [5]
public class Singleton_Double_Checked_Locking_And_Volatile {

    private static volatile Singleton_Double_Checked_Locking_And_Volatile instance;

    public static Singleton_Double_Checked_Locking_And_Volatile getInstance() {

        Singleton_Double_Checked_Locking_And_Volatile localInstance = instance;

        if (localInstance == null) {
            synchronized (Singleton_Double_Checked_Locking_And_Volatile.class) {    // While we were waiting for the lock, another
                localInstance = instance;   // thread may have instantiated the object.

                if (localInstance == null) {
                    instance = localInstance = new Singleton_Double_Checked_Locking_And_Volatile();
                }
            }
        }
        return localInstance;
    }

}
