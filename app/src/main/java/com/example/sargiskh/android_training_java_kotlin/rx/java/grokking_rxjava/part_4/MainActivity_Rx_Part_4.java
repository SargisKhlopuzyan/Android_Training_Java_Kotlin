package com.example.sargiskh.android_training_java_kotlin.rx.java.grokking_rxjava.part_4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

//import com.cantrowitz.rxbroadcast.RxBroadcast;
import com.example.sargiskh.android_training_java_kotlin.R;
import com.example.sargiskh.android_training_java_kotlin.rx.java.grokking_rxjava.part_4.rx_with_retrofit.ApiInterface;
import com.example.sargiskh.android_training_java_kotlin.rx.java.grokking_rxjava.part_4.rx_with_retrofit.Category;
import com.example.sargiskh.android_training_java_kotlin.rx.java.grokking_rxjava.part_4.rx_with_retrofit.RetrofitClientInstance_Java;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.android.view.OnClickEvent;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
//import rx.android.view.OnClickEvent;
//import rx.android.view.ViewObservable;

public class MainActivity_Rx_Part_4 extends AppCompatActivity {

    private EditText editText;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rx);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        /*
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
//        AndroidObservable.fromBroadcast(getBaseContext(), filter)
        RxBroadcast.fromBroadcast(getBaseContext(), filter)
                .subscribe(intent -> handleConnectivityChange(intent));
        /**/


        /*
        ViewObservable.clicks(button, false)
                .subscribe(view -> handleClick(view));
        */


        /*
        Observable.zip(
                service.getUserPhoto(id),
                service.getPhotoMetadata(id),
                (photo, metadata) -> createPhotoWithData(photo, metadata))
                .subscribe(photoWithData -> showPhoto(photoWithData));
        */

        /*
        Log.e("LOG_TAG", "start");
//        newMethod_1();//.subscribe(result -> Log.e("LOG_TAG", "result: " + result));
        newMethod_2().subscribe(result -> Log.e("LOG_TAG", "result: " + result));
        Log.e("LOG_TAG", "end");
        */

        // NOT WORKING
        /**/
        ApiInterface service = RetrofitClientInstance_Java.getRetrofitInstance().create(ApiInterface.class);
        service.getAllPhoto()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .cache().subscribe(categories -> populate(categories));
        /**/


        doSomething();

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

        mCompositeSubscription.unsubscribe();
    }



    private void doSomething() {
//        mCompositeSubscription.add(AndroidObservable.bindActivity(this, Observable.just("Hello, World!")).subscribe(s -> Log.e("LOG_TAG", "s: " + s)));
    }


    private void populate(List<Category> categories) {
        Log.e("LOG_TAG", "size: " + categories.size());
    }


    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();


    /**/
    private void handleClick(OnClickEvent onClickEvent) {
        Log.e("LOG_TAG", "handleClick");
    }
    /**/

    private void handleConnectivityChange(Intent intent) {
        Log.e("LOG_TAG", "handleConnectivityChange");

    }


    //
    public Observable<Object> newMethod_1() {
        return Observable.just(oldMethod_1());
    }
    private Object oldMethod_1() {
        Log.e("LOG_TAG", "start: oldMethod_1");
        for (int i = 0; i< Integer.MAX_VALUE; i++) {

        }
        Log.e("LOG_TAG", "end: oldMethod_1");
        return "Sargis";
    }
    //


    //
    public Observable<Object> newMethod_2() {
        return Observable.defer(() -> Observable.just(slowBlockingMethod()));
    }
    private Object slowBlockingMethod() {
        Log.e("LOG_TAG", "start: slowBlockingMethod");
        for (int i = 0; i< Integer.MAX_VALUE; i++) {

        }
        Log.e("LOG_TAG", "end: slowBlockingMethod");
        return "Sargis";
    }
    //

}
