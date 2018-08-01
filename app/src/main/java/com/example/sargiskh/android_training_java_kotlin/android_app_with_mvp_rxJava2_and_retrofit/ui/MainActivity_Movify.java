package com.example.sargiskh.android_training_java_kotlin.android_app_with_mvp_rxJava2_and_retrofit.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.sargiskh.android_training_java_kotlin.R;
import com.example.sargiskh.android_training_java_kotlin.android_app_with_mvp_rxJava2_and_retrofit.adapters.MoviesAdapter;
import com.example.sargiskh.android_training_java_kotlin.android_app_with_mvp_rxJava2_and_retrofit.models.MovieResponse;
import com.example.sargiskh.android_training_java_kotlin.android_app_with_mvp_rxJava2_and_retrofit.ui.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

// https://medium.com/@anujguptawork/simple-android-app-with-mvp-rxjava2-and-retrofit-part-1-f6041cd407d6
public class MainActivity_Movify extends AppCompatActivity implements MainViewInterface {

    private String TAG = "LOG_TAG";

//    @BindView(R.id.recyclerViewMovies)
    RecyclerView recyclerViewMovies;

    MoviesAdapter adapter;

    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movify_activity_main);

        ButterKnife.bind(this);

        setupMVP();
        setupView();
        getMovieList();
    }

    private void setupMVP() {
        mainPresenter = new MainPresenter(this);
    }

    private void setupView() {
        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));
    }

    public void getMovieList() {
        mainPresenter.getMovies();
    }


    @Override
    public void showToast(String str) {
        Log.e(TAG,"showToast");
        Toast.makeText(MainActivity_Movify.this, str, Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayMovies(MovieResponse movieResponse) {
        if (movieResponse != null) {
            Log.e(TAG,"Movies response");
            Log.e(TAG,movieResponse.getResults().get(1).getTitle());
            adapter = new MoviesAdapter(movieResponse.getResults(), MainActivity_Movify.this);
            recyclerViewMovies.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            Log.e(TAG,"Movies response null");
        }
    }

    @Override
    public void displayError(String e) {
        showToast(e);
    }


}
