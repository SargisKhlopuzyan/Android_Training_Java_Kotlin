package com.example.sargiskh.android_training_java_kotlin.rx.java.grokking_rxjava.part_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.sargiskh.android_training_java_kotlin.R;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

// https://blog.danlew.net/2014/09/22/grokking-rxjava-part-2/
// Grokking RxJava, Part 2: Operator, Operator
public class MainActivity_Rx_Part_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rx);

        // ***************** version 1 *****************
        /*
        query("Hello, world!")
                .subscribe(urls -> {
                    for (String url : urls) {
                        Log.e("LOG_TAG", "urls: " + urls);
                    }
                });
        */


        // ***************** version 2 *****************
        /*
        query("Hello, world")
                .subscribe(urls -> {
                    Observable.from(urls)
                            .map(url -> url + "-Sargis")
                            .subscribe(url -> Log.e("LOG_TAG", "url: " + url));
                });
        */

        // ***************** version 2 *****************
        /*
        query("Hello, world")
                .flatMap(new Func1<List<String>, Observable<?>>() {
                    @Override
                    public Observable<?> call(List<String> urls) {

                        return Observable.from(urls);
                    }
                })
                .map(url -> url + "-Sargis")
                .subscribe(url -> Log.e("LOG_TAG", "url: " + url));
        */


        // ***************** version 3 *****************
        /*
        query("Hello, world")
                .flatMap(urls -> Observable.from(urls))
                .map(url -> url + "-Sargis")
                .subscribe(url -> Log.e("LOG_TAG", "url: " + url));
        */

        // ***************** version 4 *****************
        /*
        query("Hello, world")
                .flatMap(urls -> Observable.from(urls))
                .flatMap(new Func1<String, Observable<?>>() {
                    @Override
                    public Observable<?> call(String s) {
                        return getTitle(s);
                    }
                })
                .subscribe(title -> Log.e("LOG_TAG", "Title: " + title));
        */


        // ***************** version 4 *****************
        /*
        query("Hello, world")
                .flatMap(urls -> Observable.from(urls))
                .flatMap(url -> getTitle(url))
                .subscribe(title -> Log.e("LOG_TAG", "Title: " + title));
        */


        // ***************** version 5 *****************
        // filter() emits the same item it received, but only if it passes the boolean check.
        /*
        query("Hello, world")
                .flatMap(urls -> Observable.from(urls))
                .flatMap(url -> getTitle(url))
                .filter(title -> title != null)
                .subscribe(title -> Log.e("LOG_TAG", "Title: " + title));
        */


        // ***************** version 6 *****************
        // take() emits, at most, the number of items specified. (If there are fewer than 5 titles it'll just stop early.)
        // we want to show only 5 results at most:
        /*
        query("Hello, world")
                .flatMap(urls -> Observable.from(urls))
                .filter(urls -> urls != null)
                .flatMap(url -> getTitle(url))
                .filter(title -> title != null)
                .take(5)
                .subscribe(title -> Log.e("LOG_TAG", "Title: " + title));
        */


        // ***************** version 7 *****************
        query("Hello, world")
                .flatMap(urls -> Observable.from(urls))
                .filter(urls -> urls != null)
                .flatMap(url -> getTitle(url))
                .filter(title -> title != null)
                .filter(title -> title != null)
                .take(5)
                .doOnNext(title -> saveTitle(title))
                .subscribe(title -> Log.e("LOG_TAG", "Title: " + title));
    }


    Observable<List<String>> query(String text) {

        List<String> urls = new ArrayList<String>();
        urls.add("1");
        urls.add("2");
        urls.add("3");
        urls.add(null);
        urls.add("5");
        urls.add("6");
        urls.add("7");
        urls.add("8");

        return Observable.just(urls);
    }

    Observable<String> getTitle(String URL) {
        return Observable.just(URL + "-Title" );
//        return Observable.just(getCustomTitle(URL));
    }

    private String getCustomTitle(String url) {
        url = url + "-Title";
        if (url.contains("3")) {
            url = url + "-SUPER";
        }
        return url;
    }

    private String saveTitle(String title) {
        // save title to disk
        Log.e("LOG_TAG", title + " is saved to disk");
        return title;
    }

}