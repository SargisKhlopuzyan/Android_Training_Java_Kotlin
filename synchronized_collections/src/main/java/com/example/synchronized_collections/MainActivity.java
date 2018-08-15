package com.example.synchronized_collections;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.synchronized_collections.R.layout.activity_main);

        SynchronizedListFromArrayListClass synchronizedListFromArrayListClass = new SynchronizedListFromArrayListClass();
//        synchronizedListFromArrayListClass.getSynchronizedListFromArrayList();
//        synchronizedListFromArrayListClass.getSynchronizedMapFromMap();
//        synchronizedListFromArrayListClass.getConcurrentHashMap();

//        ConverterHelper.hashMapToArrayList();
//        ConverterHelper.IterateThroughMapAndList();
    }
}
