package com.example.sargiskh.android_training_java_kotlin.ok_http.java;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.sargiskh.android_training_java_kotlin.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity_Java_Asynchronous_Calls_OkHttp_POST extends AppCompatActivity {

    public String postUrl= "https://reqres.in/api/users/";
    public String postBody="{\n" +
            "    \"name\": \"morpheus\",\n" +
            "    \"job\": \"leader\"\n" +
            "}";

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_java_asynchronous_calls_ok_http_post);

        postRequest(postUrl,postBody);
    }

    private void postRequest(String postUrl, String postBody) {

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, postBody);

        Request request = new Request.Builder().url(postUrl).post(body).build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("LOG_TAG", response.body().string());
            }
        });

    }
}
