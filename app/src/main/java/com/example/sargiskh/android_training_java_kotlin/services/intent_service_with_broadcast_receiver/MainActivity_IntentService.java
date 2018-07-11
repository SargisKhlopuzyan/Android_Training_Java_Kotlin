package com.example.sargiskh.android_training_java_kotlin.services.intent_service_with_broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sargiskh.android_training_java_kotlin.R;

public class MainActivity_IntentService extends AppCompatActivity {

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
    }

    private void onStartService() {
        Log.e("LOG_TAG", "onStartService");
        Intent intent = new Intent(this, IntentService_With_BroadcastReceiver.class);
        intent.putExtra("key", "value");
        startService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(IntentService_With_BroadcastReceiver.ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }


    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("LOG_TAG", "onReceive");
            int resultCode = intent.getIntExtra("resultCode", RESULT_CANCELED);
            if (resultCode == RESULT_OK) {
                String resultValue = intent.getStringExtra("resultValue");
                Toast.makeText(MainActivity_IntentService.this, resultValue, Toast.LENGTH_SHORT).show();
            }
        }
    };
}
