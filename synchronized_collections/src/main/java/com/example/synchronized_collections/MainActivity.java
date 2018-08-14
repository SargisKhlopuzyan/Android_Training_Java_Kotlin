package com.example.synchronized_collections;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.synchronized_collections.R.layout.activity_main);

        SynchronizedListFromArrayListClass synchronizedListFromArrayListClass = new SynchronizedListFromArrayListClass();
        synchronizedListFromArrayListClass.getSynchronizedListFromArrayList();
        synchronizedListFromArrayListClass.getSynchronizedMapFromMap();

    }
}
