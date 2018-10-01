package com.example.async_task;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    final String LOG_TAG = "LOG_TAG";
    static final int LOADER_TIME_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = new Bundle();
        bundle.putString(MyLoader.ARGS_TIME_FORMAT, MyLoader.TIME_FORMAT_SHORT);
        getSupportLoaderManager().initLoader(LOADER_TIME_ID, bundle, this);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle bundle) {
        Loader<String> loader = null;
        if(id == LOADER_TIME_ID) {
            loader = new MyLoader(this, bundle);
            Log.d(LOG_TAG, "onCreateLoader: " + loader.hashCode());
        }
        return loader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        Log.d(LOG_TAG, "onLoadFinished for loader " + loader.hashCode() + ", result = " + s);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        Log.d(LOG_TAG, "onLoaderReset for loader " + loader.hashCode());
    }

}
