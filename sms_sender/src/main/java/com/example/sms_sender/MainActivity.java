package com.example.sms_sender;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String SMS_SENT_ACTION = "com.example.sms_sender.SMS_SENT";
    private static final String SMS_DELIVERED_ACTION = "com.example.sms_sender.SMS_DELIVERED";
    private static final String EXTRA_NUMBER = "number";
    private static final String EXTRA_MESSAGE = "message";

    // Initialize our sample numbers list.
    private final List<String> numberList = new ArrayList<String>() {{{
        add("111-111-1111");
        add("222-222-2222");
        add("333-333-3333");
    }}};

    // Initialize our sample message list.
    private final List<String> messageList = new ArrayList<String>() {{{
        add("Hello.");
        add("Howdy.");
        add("Hi.");
    }}};

    private SmsManager smsManager;
    private IntentFilter intentFilter;
    private BroadcastReceiver resultsReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smsManager = SmsManager.getDefault();
        resultsReceiver = new SmsResultReceiver();

        intentFilter = new IntentFilter(SMS_SENT_ACTION);
        intentFilter.addAction(SMS_DELIVERED_ACTION);

        Button btnSendSMS = findViewById(R.id.btnSendSMS);
        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNextMessage();
            }
        });
    }

    private void sendNextMessage() {
        // We're going to remove numbers and messages from
        // the lists as we send, so if the lists are empty, we're done.
        if (numberList.size() == 0) {
            return;
        }

        // The list size is a sufficiently unique request code,
        // for the PendingIntent since it decrements for each send.
        int requestCode = numberList.size();

        String number = numberList.get(0);
        String message = messageList.get(0);

        // The Intents must be implicit for this example,
        // as we're registering our Receiver dynamically.
        Intent sentIntent = new Intent(SMS_SENT_ACTION);
        Intent deliveredIntent = new Intent(SMS_DELIVERED_ACTION);

        // We attach the recipient's number and message to
        // the Intents for easy retrieval in the Receiver.
        sentIntent.putExtra(EXTRA_NUMBER, number);
        sentIntent.putExtra(EXTRA_MESSAGE, message);
        deliveredIntent.putExtra(EXTRA_NUMBER, number);
        deliveredIntent.putExtra(EXTRA_MESSAGE, message);

        // Construct the PendingIntents for the results.
        // FLAG_ONE_SHOT cancels the PendingIntent after use so we
        // can safely reuse the request codes in subsequent runs.
        PendingIntent sentPI = PendingIntent.getBroadcast(this, requestCode, sentIntent, PendingIntent.FLAG_ONE_SHOT);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, requestCode, deliveredIntent, PendingIntent.FLAG_ONE_SHOT);

        // Send our message.
        smsManager.sendTextMessage(number, null, message, sentPI, deliveredPI);

        // Remove the number and message we just sent to from the lists.
        numberList.remove(0);
        messageList.remove(0);
    }


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(resultsReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(resultsReceiver);
    }


    private class SmsResultReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // A simple result Toast text.
            String result = null;

            // Get the result action.
            String action = intent.getAction();

            // Retrieve the recipient's number and message.
            String number = intent.getStringExtra(EXTRA_NUMBER);
            String message = intent.getStringExtra(EXTRA_MESSAGE);

            // This is the result for a send.
            if (SMS_SENT_ACTION.equals(action)) {
                int resultCode = getResultCode();
                result = "Send result : " + translateSentResult(resultCode);

                // The current send is complete. Send the next one.
                sendNextMessage();
            }
            // This is the result for a delivery.
            else if (SMS_DELIVERED_ACTION.equals(action)) {
                SmsMessage sms = null;

                // A delivery result comes from the service
                // center as a simple SMS in a single PDU.
                byte[] pdu = intent.getByteArrayExtra("pdu");
                String format = intent.getStringExtra("format");

                // Construct the SmsMessage from the PDU.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && format != null) {
                    sms = SmsMessage.createFromPdu(pdu, format);
                }
                else {
                    sms = SmsMessage.createFromPdu(pdu);
                }

                // getResultCode() is not reliable for delivery results.
                // We need to get the status from the SmsMessage.
                result = "Delivery result : " + translateDeliveryStatus(sms.getStatus());
            }

            result = "*result* : " + number + ", " + message + "\n" + result;
            Log.e("LOG_TAG", result);
        }

        String translateSentResult(int resultCode) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    return "Activity.RESULT_OK";
                case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                    return "SmsManager.RESULT_ERROR_GENERIC_FAILURE";
                case SmsManager.RESULT_ERROR_RADIO_OFF:
                    return "SmsManager.RESULT_ERROR_RADIO_OFF";
                case SmsManager.RESULT_ERROR_NULL_PDU:
                    return "SmsManager.RESULT_ERROR_NULL_PDU";
                case SmsManager.RESULT_ERROR_NO_SERVICE:
                    return "SmsManager.RESULT_ERROR_NO_SERVICE";
                default:
                    return "Unknown error code";
            }
        }

        String translateDeliveryStatus(int status) {
            switch (status) {
                case Telephony.Sms.STATUS_COMPLETE:
                    return "Sms.STATUS_COMPLETE";
                case Telephony.Sms.STATUS_FAILED:
                    return "Sms.STATUS_FAILED";
                case Telephony.Sms.STATUS_PENDING:
                    return "Sms.STATUS_PENDING";
                case Telephony.Sms.STATUS_NONE:
                    return "Sms.STATUS_NONE";
                default:
                    return "Unknown status code";
            }
        }
    }

}
