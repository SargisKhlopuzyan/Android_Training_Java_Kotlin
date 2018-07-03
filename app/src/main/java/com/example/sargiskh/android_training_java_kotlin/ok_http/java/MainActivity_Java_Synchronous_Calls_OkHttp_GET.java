package com.example.sargiskh.android_training_java_kotlin.ok_http.java;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sargiskh.android_training_java_kotlin.R;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// The MainActivity_Java_Synchronous_Calls_OkHttp_GET.java for Synchronous Calls is given below
public class MainActivity_Java_Synchronous_Calls_OkHttp_GET extends AppCompatActivity {

    public final String url = "https://reqres.in/api/users/2";

    private TextView txtString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_java_synchronous_calls_ok_http_get);

        txtString= (TextView)findViewById(R.id.txtString);

        OkHttpHandler okHttpHandler= new OkHttpHandler();
        okHttpHandler.execute(url);
    }

    public class OkHttpHandler extends AsyncTask {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected Object doInBackground(Object[] objects) {

            Request.Builder builder = new Request.Builder();
            builder.url(String.valueOf(objects[0]));

            Request request = builder.build();

            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            txtString.setText(String.valueOf(o));
        }
    }
}
