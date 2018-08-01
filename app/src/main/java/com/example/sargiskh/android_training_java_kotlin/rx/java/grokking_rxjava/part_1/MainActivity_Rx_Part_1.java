package com.example.sargiskh.android_training_java_kotlin.rx.java.grokking_rxjava.part_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.sargiskh.android_training_java_kotlin.R;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

// https://blog.danlew.net/2014/09/15/grokking-rxjava-part-1/
public class MainActivity_Rx_Part_1 extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rx);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                demoObservableFrom();
            }
        });
    }

    private void demoObservableFrom() {

        // ***************** version 1 *****************
        /**
        Observable<String> myObservable = Observable.create( new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext("Hello, world!");
                        sub.onCompleted();
                    }
                }
        );
        myObservable.subscribe(mySubscriber);
        */


        // ***************** version 2 *****************
        /*
        Observable<String> myObservable = Observable.just("Hello, world!");
        myObservable.subscribe(onNextAction);
        myObservable.subscribe(onNextAction, onNextAction_Error);
        myObservable.subscribe(onNextAction, onNextAction_Error, onNextAction_Complete);
        */


        // ***************** version 3 *****************
        //------ using Java 8 lambdas to get rid of that ugly Action1 code ------
        /*
         Observable.just("Hello, world!").subscribe( s -> Log.e("LOG_TAG", "onNextAction -> s: " + s)  );
        */


        // ***************** version 4 ***************** //
        /*
        Observable.just("Hello, world!").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s + "-Sargis";
            }
        }).subscribe(s -> Log.e("LOG_TAG", "s: " + s));
        */


        // ***************** version 5 *****************
        /*
        Observable.just("Hello, world!").map(s -> s + "-Sargis").subscribe(s -> Log.e("LOG_TAG", "s: " + s));
        */


        // ***************** version 6 ***************** //
        /*
        Observable.just("Hello, world!").map(new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                return s.hashCode();
            }
        }).subscribe(s -> Log.e("LOG_TAG", "s: " + s));
        */


        // ***************** version 7 ***************** //
        /*
        Observable.just("Hello, world!")
                .map(s -> s.hashCode())
                .subscribe(s -> Log.e("LOG_TAG", "s: " + s));
        */

        // ***************** version 8 ***************** //
        Observable.just("Hello, world!")
                .map(s -> s.hashCode())
                .map(s -> s.toString())
                .subscribe(s -> Log.e("LOG_TAG", "s: " + s));

    }


    // <version 1 - start>
    Subscriber<String> mySubscriber = new Subscriber<String>() {

        @Override
        public void onNext(String s) {
            Log.e("LOG_TAG", "mySubscriber -> onNext -> s: " + s);
        }

        @Override
        public void onCompleted() { }

        @Override
        public void onError(Throwable e) { }
    };
    // <version 1 - end>


    // <version 2 - start>
    Action1<String> onNextAction = new Action1<String>() {
        @Override
        public void call(String s) {
            Log.e("LOG_TAG", "onNextAction -> s: " + s);
        }
    };

    Action1<Throwable> onNextAction_Error = new Action1<Throwable>() {

        @Override
        public void call(Throwable throwable) {
            Log.e("LOG_TAG", "onNextAction_Error");
        }
    };

    Action0 onNextAction_Complete = new Action0() {
        @Override
        public void call() {
            Log.e("LOG_TAG", "onNextAction_Complete");
        }
    };
    // <version 2 - end>

}
