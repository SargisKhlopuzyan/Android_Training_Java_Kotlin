package com.example.sargiskh.android_training_java_kotlin.services.intent_service_with_result_receiver;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sargiskh.android_training_java_kotlin.R;

public class MainActivity_IntentService extends AppCompatActivity {

    private MyResultReceiver resultReceiver;

    private Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_intent_service);

        buttonStart = findViewById(R.id.start_button);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartService();
            }
        });

        setupServiceReceiver();
    }

    private void setupServiceReceiver() {
        resultReceiver = new MyResultReceiver(new Handler());
        resultReceiver.setReceiver(new MyResultReceiver.Receiver() {
            @Override
            public void onReceiveResult(int resultCode, Bundle resultData) {
                Log.e("LOG_TAG", "onReceiveResult");
                if (resultCode == RESULT_OK) {
                    String resultValue = resultData.getString("resultValue");
                    Toast.makeText(MainActivity_IntentService.this, resultValue, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void onStartService() {
        Log.e("LOG_TAG", "onStartService");
        Intent intent = new Intent(this, IntentService_With_ResultReceiver.class);
        intent.putExtra("key", "value");
        intent.putExtra("receiver", resultReceiver);
        startService(intent);
    }

}
