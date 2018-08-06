package com.example.databinding;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.databinding.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private User user;
    private MyClickHandlers handlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        user  = new User();
        user.setName("Ravi Tamada");
        user.setEmail("ravi@androidhive.info");

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setUser(user);
        setSupportActionBar(binding.toolbar);

        user.setProfileImage("http://www.freeimageslive.com/galleries/objects/general/pics/woodenbox0482.jpg");

        handlers = new MyClickHandlers(this);
        binding.setHandlers(handlers);
        binding.content.setHandlers(handlers);

    }

    public class MyClickHandlers {

        Context context;

        public MyClickHandlers(Context context) {
            this.context = context;
        }

        public void onFabClicked(View view) {
            Toast.makeText(getApplicationContext(), "FAB clicked!", Toast.LENGTH_SHORT).show();

            Random random = new Random();
            int i = random.nextInt(500);

            user.setName("Ravi: " + i);
            user.setEmail("ravi8x@gmail.com: " + i);

            // ObservableField
            user.nameObservable.set("Ravi: " + i);
            user.emailObservable.set("ravi8x@gmail.com: " + i);
        }

        public void onButtonClick(View view) {
            Toast.makeText(getApplicationContext(), "Button clicked!", Toast.LENGTH_SHORT).show();
        }

        public void onButtonClickWithParam(View view, User user) {
            Toast.makeText(getApplicationContext(), "Button clicked! Name: " + user.name, Toast.LENGTH_SHORT).show();
        }

        public boolean onButtonLongPressed(View view) {
            Toast.makeText(getApplicationContext(), "Button long pressed!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
