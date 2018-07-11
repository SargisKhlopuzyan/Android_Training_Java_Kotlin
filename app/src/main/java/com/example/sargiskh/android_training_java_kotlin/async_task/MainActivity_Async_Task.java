package com.example.sargiskh.android_training_java_kotlin.async_task;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sargiskh.android_training_java_kotlin.R;

public class MainActivity_Async_Task extends AppCompatActivity {

    private TextView progressTxtView, finalResultTxtView;
    private AsyncTaskSubClass asyncExample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_async_task);

        progressTxtView = (TextView) findViewById(R.id.progress_text_view);
        finalResultTxtView = (TextView) findViewById(R.id.result_text_view);
    }

    public void startComputation(View view) {
        asyncExample = new AsyncTaskSubClass();
        asyncExample.execute("1,000,000"); // ten Million
        Toast.makeText(MainActivity_Async_Task.this, "Computation Started", Toast.LENGTH_SHORT).show();
    }

    public void cancelComputation(View view) {
        if (asyncExample != null){
            //Calling this method guarantees that onPostExecute(Long result) is never invoked
            asyncExample.cancel(true);
            asyncExample = null;
            Toast.makeText(this, "Async Task Stopped!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "You have no running task!", Toast.LENGTH_SHORT).show();
        }
    }


    private class AsyncTaskSubClass extends AsyncTask<String, Integer, Long> {

        private String firstString;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // initializing our textViews before doInBackground
            progressTxtView.setText("");
            finalResultTxtView.setText("Result would be displayed Here");
        }

        @Override
        protected Long doInBackground(String... strings) {
            firstString = strings[0]; // first String
            int number = 1 + Integer.parseInt(firstString.replace(",", ""));
            long result = 0;
            // we are going to do a long computation
            for (int i = 1; i < 101; i++) { // from 1 to 100

                if (isCancelled())  {
                    return result;
                }
                result = 0;
                for (int j = 1; j < number; j++) {
                    result = result + j;
                }
                Log.e("LOG_TAG", "doInBackground: Result"+i+" = " +result);
                publishProgress(i);
            }
            return result;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressTxtView.setText(values[0] + " %");
        }

        @Override
        protected void onPostExecute(Long longResult) {
            super.onPostExecute(longResult);
            asyncExample = null;
            String string = "The summation of Numbers between 1 to " + firstString + " is \n=" + longResult;
            finalResultTxtView.setText(string);
            Toast.makeText(MainActivity_Async_Task.this, "Computation Completed!", Toast.LENGTH_SHORT).show();
        }
    }
}
