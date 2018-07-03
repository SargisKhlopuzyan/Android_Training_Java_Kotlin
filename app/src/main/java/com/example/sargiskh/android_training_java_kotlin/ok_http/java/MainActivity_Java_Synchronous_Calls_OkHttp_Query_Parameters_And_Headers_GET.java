package com.example.sargiskh.android_training_java_kotlin.ok_http.java;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.sargiskh.android_training_java_kotlin.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity_Java_Synchronous_Calls_OkHttp_Query_Parameters_And_Headers_GET extends AppCompatActivity {

    public final String url = "https://reqres.in/api/users/2";
//    public final String url = "https://httpbin.org/get";

    private TextView txtString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_java_synchronous_calls_ok_http_query_parameters_get);

        txtString= (TextView)findViewById(R.id.txtString);

        OkHttpHandler okHttpHandler= new OkHttpHandler();
        okHttpHandler.execute(url);
    }

    public class OkHttpHandler extends AsyncTask {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected Object doInBackground(Object[] objects) {

            // OkHttp Query Parameters Example
            HttpUrl.Builder urlBuilder = HttpUrl.parse(String.valueOf(objects[0])).newBuilder();
            /*
            urlBuilder.addQueryParameter("website", "www.journaldev.com");
            urlBuilder.addQueryParameter("tutorials", "android");
            */
            String urlWithQueryParameter = urlBuilder.build().toString();
            //

            Request.Builder builder = new Request.Builder();
            builder.url(urlWithQueryParameter);
            // OkHttp Android Headers Example
//            builder.header("Authorization", "replace this text with your token");

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
//            txtString.setText(String.valueOf(o));

            // Processing the JSON Response
            try {
                JSONObject json = new JSONObject(String.valueOf(o));
                String str = json.getJSONObject("data").getString("first_name") + " " + json.getJSONObject("data").getString("last_name");
                Log.e("LOG_TAG", "str: " + str);
                txtString.setText(str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // END - Processing the JSON Response
        }
    }

}
