package com.example.async_task_loader;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    public static final int OPERATION_SEARCH_LOADER = 22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportLoaderManager().initLoader(OPERATION_SEARCH_LOADER, null, this);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new ExtendsAsyncTaskLoader(getApplicationContext());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        Log.e("LOG_TAG", "onLoadFinished -> " + s);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        Log.e("LOG_TAG", "onLoaderReset");
    }
}
