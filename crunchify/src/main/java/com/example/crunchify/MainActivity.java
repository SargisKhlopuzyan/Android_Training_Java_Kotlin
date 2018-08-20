package com.example.crunchify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.crunchify.annotation.DocumentedAnnotations;
import com.example.crunchify.override_static_method.OverrideStaticMethod;
import com.example.crunchify.retry_n_times.RetryNTimes;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.crunchify.R.layout.activity_main);

//        try {
//            OutOfMemoryClass.generateOOM();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        UniqueArrayList.sortList(UniqueArrayList.getInitialStringList());
//        UniqueArrayList.sortList(UniqueArrayList.getInitialIntegerList());

//        RetryNTimes.startDownloadingInNewThread();

//        OverrideStaticMethod.test();

        DocumentedAnnotations.test();

    }
}