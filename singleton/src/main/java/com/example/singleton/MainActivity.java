package com.example.singleton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Singleton_Double_Checked_Locking_And_Volatile singletonDoubleCheckedLockingAndVolatile = Singleton_Double_Checked_Locking_And_Volatile.getInstance();
        Singleton_Synchronized_Accessor singletonSynchronizedAccessorSynchronizedAccessor = Singleton_Synchronized_Accessor.getInstance();
        Singleton_With_Object_Lock singleton_With_Object_Lock = Singleton_With_Object_Lock.getInstance();
    }
}
