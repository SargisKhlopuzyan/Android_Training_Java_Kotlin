package com.example.sargiskh.android_training_java_kotlin.rx.java.grokking_rxjava.part_3;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.sargiskh.android_training_java_kotlin.R;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
//import io.reactivex.Observable;

// https://blog.danlew.net/2014/09/30/grokking-rxjava-part-3/
// Grokking RxJava, Part 3: Reactive with Benefits
public class MainActivity_Rx_Part_3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rx);

        // ***************** version 1 *****************
        /*
//        Observable.just("Hello, world!")
        Observable.just("0")
                .map(s -> potentialException(s))
                .map(s -> anotherPotentialException(s))
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onCompleted() {
                        Log.e("LOG_TAG", "onCompleted!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("LOG_TAG", "Ouch!: " + e);
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("LOG_TAG", "onNext: " + s);
                    }
                });
        */

        /*
        // ***************** version 2 *****************
        // ??????????????????????????????????????????????????
        myObservableServices.retrieveImage("url")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bitmap -> myImageView.setImageBitmap(bitmap));
        */


        // ***************** version 3 *****************
//        Observable observable = Observable.just("Hello, world!");
        Observable observable = Observable
                .create( new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        /*
                        sub.onNext("Hello, world!");
                        sub.onCompleted();
                        */
                        /**/
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                sub.onNext("Hello, world!");
                                sub.onCompleted();
                            }
                        }, 4000);
                        /**/
                    }
                }
        );
        Subscription subscription_1 = observable.subscribe(subscriber_1);
        Subscription subscription_2 = observable.subscribe(subscriber_2);

        subscription_1.unsubscribe();
        Log.e("LOG_TAG", "1: Unsubscribed = " + subscription_1.isUnsubscribed());
        Log.e("LOG_TAG", "2: Unsubscribed = " + subscription_2.isUnsubscribed());

    }


    Subscriber subscriber_1 = new Subscriber() {

        @Override
        public void onCompleted() {
            Log.e("LOG_TAG", "1: onCompleted()");
        }

        @Override
        public void onError(Throwable e) {
            Log.e("LOG_TAG", "1: onError(): " + e);
        }

        @Override
        public void onNext(Object o) {
            Log.e("LOG_TAG", "1: onNext(): " + o);
        }
    };

    Subscriber subscriber_2 = new Subscriber() {

        @Override
        public void onCompleted() {
            Log.e("LOG_TAG", "2: onCompleted()");
        }

        @Override
        public void onError(Throwable e) {
            Log.e("LOG_TAG", "2: onError(): " + e);
        }

        @Override
        public void onNext(Object o) {
            Log.e("LOG_TAG", "2: onNext(): " + o);
        }
    };


    // ????????????????????????????????????????????
    private static class myObservableServices extends Observable {

        protected myObservableServices(OnSubscribe f) {
            super(f);
        }

        public static Observable retrieveImage(String url) {
            return Observable.just("just");
        }
    }

    private String potentialException(String s)/* throws Exception */{
//        if (s.equals("0")) {
//            throw new NullPointerException();
//        }
        return s;
    }

    private String anotherPotentialException(String s) {
//        if (s.equals("0")) {
//            throw new NullPointerException();
//        }
        return s;
    }

}
