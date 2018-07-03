package com.example.sargiskh.android_training_java_kotlin.ok_http.java;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.sargiskh.android_training_java_kotlin.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// The MainActivity_Java_Asynchronous_Calls_OkHttp_GET.java for Asynchronous Calls is given below
public class MainActivity_Java_Asynchronous_Calls_OkHttp_GET extends AppCompatActivity {

    private final String url= "https://reqres.in/api/users/2";

    private TextView txtString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_java_asynchronous_calls_ok_http_get);

        txtString = (TextView)findViewById(R.id.txtString);

        run();
    }

    private void run() {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();

                MainActivity_Java_Asynchronous_Calls_OkHttp_GET.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        txtString.setText(myResponse);

                        // Processing the JSON Response
                        try {
                            JSONObject json = new JSONObject(myResponse);
                            String str = json.getJSONObject("data").getString("first_name")+ " "+json.getJSONObject("data").getString("last_name");
                            txtString.setText(str);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // END - Processing the JSON Response
                    }
                });
            }
        });
    }

}
